/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.nutch.util;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.map.MultithreadedMapper;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MapFileOutputFormat;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.hostdb.HostDatum;
import org.apache.nutch.net.URLFilters;
import org.apache.nutch.net.URLNormalizers;
import org.apache.nutch.protocol.Content;
import org.apache.nutch.protocol.Protocol;
import org.apache.nutch.protocol.ProtocolFactory;
import org.apache.nutch.protocol.ProtocolOutput;
import org.apache.nutch.protocol.ProtocolStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crawlercommons.robots.BaseRobotRules;
import crawlercommons.sitemaps.AbstractSiteMap;
import crawlercommons.sitemaps.SiteMap;
import crawlercommons.sitemaps.SiteMapIndex;
import crawlercommons.sitemaps.SiteMapParser;
import crawlercommons.sitemaps.SiteMapURL;

/**
 * <p>
 * Performs <a href="https://sitemaps.org/">sitemap</a> processing by fetching
 * sitemap links, parsing the content and merging the URLs from sitemaps (with
 * the metadata) into the CrawlDb.
 * </p>
 *
 * <p>
 * There are two use cases supported in Nutch's sitemap processing:
 * </p>
 * <ol>
 * <li>Sitemaps are considered as "remote seed lists". Crawl administrators can
 * prepare a list of sitemap links and inject and fetch only the pages listed in
 * the sitemaps. This suits well for targeted crawl of specific hosts.</li>
 * <li>For an open web crawl, it is not possible to track each host and get the
 * sitemap links manually. Nutch automatically detects the sitemaps for all
 * hosts seen in the crawls and present in the HostDb and injects the URLs from
 * the sitemaps into the CrawlDb.</li>
 * </ol>
 *
 * @see
 * <a href="https://cwiki.apache.org/confluence/display/NUTCH/SitemapFeature">SitemapFeature</a>
 */
public class SitemapProcessor extends Configured implements Tool {
  private static final Logger LOG = LoggerFactory.getLogger(SitemapProcessor.class);
  public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static final String CURRENT_NAME = "current";
  public static final String LOCK_NAME = ".locked";
  public static final String SITEMAP_STRICT_PARSING = "sitemap.strict.parsing";
  public static final String SITEMAP_URL_FILTERING = "sitemap.url.filter";
  public static final String SITEMAP_URL_NORMALIZING = "sitemap.url.normalize";
  public static final String SITEMAP_ALWAYS_TRY_SITEMAPXML_ON_ROOT = "sitemap.url.default.sitemap.xml";
  public static final String SITEMAP_OVERWRITE_EXISTING = "sitemap.url.overwrite.existing";
  public static final String SITEMAP_REDIR_MAX = "sitemap.redir.max";
  public static final String SITEMAP_SIZE_MAX = "sitemap.size.max";

  private static class SitemapMapper extends Mapper<Text, Writable, Text, CrawlDatum> {
    private ProtocolFactory protocolFactory = null;
    private boolean strict = true;
    private boolean filter = true;
    private boolean normalize = true;
    private boolean tryDefaultSitemapXml = true;
    private int maxRedir = 3;
    private float minFetchInterval = 60f;
    private float maxFetchInterval = 31536000f; // one year
    private URLFilters filters = null;
    private URLNormalizers normalizers = null;
    private CrawlDatum datum = new CrawlDatum();
    private SiteMapParser parser = null;

    @Override
    public void setup(Context context) {
      Configuration conf = context.getConfiguration();
      int maxSize = conf.getInt(SITEMAP_SIZE_MAX, SiteMapParser.MAX_BYTES_ALLOWED);
      conf.setInt("http.content.limit", maxSize);
      conf.setInt("file.content.limit", maxSize);
      this.protocolFactory = new ProtocolFactory(conf);
      this.filter = conf.getBoolean(SITEMAP_URL_FILTERING, true);
      this.normalize = conf.getBoolean(SITEMAP_URL_NORMALIZING, true);
      this.strict = conf.getBoolean(SITEMAP_STRICT_PARSING, true);
      this.tryDefaultSitemapXml = conf.getBoolean(SITEMAP_ALWAYS_TRY_SITEMAPXML_ON_ROOT, true);
      this.maxRedir = conf.getInt(SITEMAP_REDIR_MAX, 3);
      this.parser = new SiteMapParser(strict);
      this.minFetchInterval = conf
          .getFloat("db.fetch.schedule.adaptive.min_interval", (float) 60.0);
      this.maxFetchInterval = conf.getFloat(
          "db.fetch.schedule.adaptive.max_interval",
          (float) 31536000.0 /* one year */);

      if (filter) {
        filters = new URLFilters(conf);
      }
      if (normalize) {
        normalizers = new URLNormalizers(conf, URLNormalizers.SCOPE_DEFAULT);
      }
    }

    @Override
    public void map(Text key, Writable value, Context context) throws IOException, InterruptedException {
      String url;

      try {
        if (value instanceof CrawlDatum) {
          // If its an entry from CrawlDb, emit it. It will be merged in the reducer
          context.write(key, (CrawlDatum) value);
        }
        else if (value instanceof HostDatum) {
          generateSitemapsFromHostname(key.toString(), context);
        }
        else if (value instanceof Text) {
          // Input can be sitemap URL or hostname
          url = key.toString();
          if (url.startsWith("http://") ||
                url.startsWith("https://") ||
                url.startsWith("ftp://") ||
                url.startsWith("file:/")) {
            // For entry from sitemap urls file, fetch the sitemap, extract urls and emit those
            if((url = filterNormalize(url)) == null) {
              context.getCounter("Sitemap", "filtered_records").increment(1);
              return;
            }

            context.getCounter("Sitemap", "sitemap_seeds").increment(1);
            generateSitemapUrlDatum(protocolFactory.getProtocol(url), url, context); 
          } else {
            LOG.info("generateSitemapsFromHostname: {}", key.toString());
            generateSitemapsFromHostname(key.toString(), context);
          }
        }
      } catch (Exception e) {
        LOG.warn("Exception for record {} : {}", key.toString(),
            StringUtils.stringifyException(e));
      }
    }

    /* Filters and or normalizes the input URL */
    private String filterNormalize(String url) {
      try {
        if (normalizers != null)
          url = normalizers.normalize(url, URLNormalizers.SCOPE_DEFAULT);

        if (filters != null)
          url = filters.filter(url);
      } catch (Exception e) {
        return null;
      }
      return url;
    }
    
    private void generateSitemapsFromHostname(String host, Context context) {
      try {
        // For entry from hostdb, get sitemap url(s) from robots.txt, fetch the sitemap,
        // extract URLs and emit those

        // try different combinations of schemes one by one till we get rejection in all cases
        String url;
        if((url = filterNormalize("http://" + host + "/")) == null &&
            (url = filterNormalize("https://" + host + "/")) == null &&
            (url = filterNormalize("ftp://" + host + "/")) == null &&
            (url = filterNormalize("file:/" + host + "/")) == null) {
          context.getCounter("Sitemap", "filtered_records").increment(1);
          return;
        }
        // We may wish to use the robots.txt content as the third parameter for .getRobotRules
        BaseRobotRules rules = protocolFactory.getProtocol(url).getRobotRules(new Text(url), datum, null);
        List<String> sitemaps = rules.getSitemaps();

        if (tryDefaultSitemapXml && sitemaps.size() == 0) {
          sitemaps.add(url + "sitemap.xml");
        }
        for (String sitemap : sitemaps) {
          context.getCounter("Sitemap", "sitemaps_from_hostname").increment(1);
          sitemap = filterNormalize(sitemap);
          if (sitemap == null) {
            context.getCounter("Sitemap", "filtered_sitemaps_from_hostname")
                .increment(1);
          } else {
            generateSitemapUrlDatum(protocolFactory.getProtocol(sitemap),
                sitemap, context);
          }
        }
      } catch (Exception e) {
        LOG.warn("Exception for record {} : {}", host, StringUtils.stringifyException(e));
      }
    }

    private void generateSitemapUrlDatum(Protocol protocol, String url, Context context) throws Exception {
      ProtocolOutput output = protocol.getProtocolOutput(new Text(url), datum);
      ProtocolStatus status = output.getStatus();
      Content content = output.getContent();

      // Following redirects http > https and what else
      int maxRedir = this.maxRedir;
      while (!output.getStatus().isSuccess() && output.getStatus().isRedirect() && maxRedir > 0) {
        String[] stuff = output.getStatus().getArgs();
        url = filterNormalize(stuff[0]);
        
        // get out!
        if (url == null) {
          break;
        }
        output = protocol.getProtocolOutput(new Text(url), datum);
        status = output.getStatus();
        content = output.getContent();
        
        maxRedir--;
      }

      if(status.getCode() != ProtocolStatus.SUCCESS) {
        // If there were any problems fetching the sitemap, log the error and let it go. Not sure how often
        // sitemaps are redirected. In future we might have to handle redirects.
        context.getCounter("Sitemap", "failed_fetches").increment(1);
        LOG.error("Error while fetching the sitemap. Status code: {} for {}", status.getCode(), url);
        return;
      }

      AbstractSiteMap asm = parser.parseSiteMap(content.getContentType(),
          content.getContent(), new URL(url));

      if(asm instanceof SiteMap) {
        LOG.info("Parsing sitemap file: {}", asm.getUrl().toString());
        SiteMap sm = (SiteMap) asm;
        Collection<SiteMapURL> sitemapUrls = sm.getSiteMapUrls();
        for(SiteMapURL sitemapUrl: sitemapUrls) {
          // If 'strict' is ON, only allow valid urls. Else allow all urls
          if(!strict || sitemapUrl.isValid()) {
            String key = filterNormalize(sitemapUrl.getUrl().toString());

            if (key != null) {
              CrawlDatum sitemapUrlDatum = new CrawlDatum();
              sitemapUrlDatum.setStatus(CrawlDatum.STATUS_INJECTED);
              float priority = (float) sitemapUrl.getPriority();
              if (priority > .0f) {
                sitemapUrlDatum.setScore(priority);
              } else {
                // score == 0 would mean not fetch, use default priority (0.5) instead
                sitemapUrlDatum.setScore((float) SiteMapURL.DEFAULT_PRIORITY);
              }

              if(sitemapUrl.getChangeFrequency() != null) {
                int fetchInterval = -1;
                switch(sitemapUrl.getChangeFrequency()) {
                  case ALWAYS:  fetchInterval = 1;        break;
                  case HOURLY:  fetchInterval = 3600;     break; // 60*60
                  case DAILY:   fetchInterval = 86400;    break; // 60*60*24
                  case WEEKLY:  fetchInterval = 604800;   break; // 60*60*24*7
                  case MONTHLY: fetchInterval = 2592000;  break; // 60*60*24*30
                  case YEARLY:  fetchInterval = 31536000; break; // 60*60*24*365
                  case NEVER:   fetchInterval = Integer.MAX_VALUE; break; // Loose "NEVER" contract
                }
                /*
                 * ensure that the fetch interval is within the min and max
                 * interval
                 */
                if (fetchInterval > maxFetchInterval) {
                  fetchInterval = (int) maxFetchInterval;
                } else if (fetchInterval < minFetchInterval) {
                  fetchInterval = (int) minFetchInterval;
                }
                sitemapUrlDatum.setFetchInterval(fetchInterval);
              }

              if (sitemapUrl.getLastModified() != null
                  && sitemapUrl.getLastModified().getTime() <= System.currentTimeMillis()) {
                // set modified time if not in the future
                sitemapUrlDatum.setModifiedTime(sitemapUrl.getLastModified().getTime());
              }

              context.write(new Text(key), sitemapUrlDatum);
            }
          }
        }
      }
      else if (asm instanceof SiteMapIndex) {
        SiteMapIndex index = (SiteMapIndex) asm;
        Collection<AbstractSiteMap> sitemapUrls = index.getSitemaps(true);

        if (sitemapUrls.isEmpty()) {
          return;
        }

        LOG.info("Parsing sitemap index file: {}", index.getUrl().toString());
        for (AbstractSiteMap sitemap : sitemapUrls) {
          String sitemapUrl = filterNormalize(sitemap.getUrl().toString());
          if (sitemapUrl != null) {
            generateSitemapUrlDatum(protocol, sitemapUrl, context);
          }
        }
      }
    }
  }

  private static class SitemapReducer extends Reducer<Text, CrawlDatum, Text, CrawlDatum> {
    CrawlDatum sitemapDatum  = null;
    CrawlDatum originalDatum = null;

    private boolean overwriteExisting = false; // DO NOT ENABLE!!

    @Override
    public void setup(Context context) {
      Configuration conf = context.getConfiguration();
      this.overwriteExisting = conf.getBoolean(SITEMAP_OVERWRITE_EXISTING, false);
    }

    @Override
    public void reduce(Text key, Iterable<CrawlDatum> values, Context context)
        throws IOException, InterruptedException {
      sitemapDatum  = null;
      originalDatum = null;

      for (CrawlDatum curr: values) {
        if(curr.getStatus() == CrawlDatum.STATUS_INJECTED) {
          sitemapDatum = new CrawlDatum();
          sitemapDatum.set(curr);
        }
        else {
          originalDatum = new CrawlDatum();
          originalDatum.set(curr);
        }
      }

      if(originalDatum != null) {
        // The url was already present in crawldb. If we got the same url from sitemap too, save
        // the information from sitemap to the original datum. Emit the original crawl datum
        if(sitemapDatum != null && overwriteExisting) {
          originalDatum.setScore(sitemapDatum.getScore());
          originalDatum.setFetchInterval(sitemapDatum.getFetchInterval());
          originalDatum.setModifiedTime(sitemapDatum.getModifiedTime());
        }

        context.getCounter("Sitemap", "existing_sitemap_entries").increment(1);
        context.write(key, originalDatum);
      }
      else if(sitemapDatum != null) {
        // For the newly discovered links via sitemap, set the status as unfetched and emit
        context.getCounter("Sitemap", "new_sitemap_entries").increment(1);
        sitemapDatum.setStatus(CrawlDatum.STATUS_DB_UNFETCHED);
        context.write(key, sitemapDatum);
      }
    }
  }

  public void sitemap(Path crawldb, Path hostdb, Path sitemapUrlDir, boolean strict, boolean filter,
                      boolean normalize, int threads) throws Exception {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    LOG.info("SitemapProcessor: starting");

    FileSystem fs = crawldb.getFileSystem(getConf());
    Path old = new Path(crawldb, "old");
    Path current = new Path(crawldb, "current");
    Path tempCrawlDb = new Path(crawldb, "crawldb-" + Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));

    // lock an existing crawldb to prevent multiple simultaneous updates
    Path lock = new Path(crawldb, LOCK_NAME);
    if (!fs.exists(current))
      fs.mkdirs(current);

    LockUtil.createLockFile(fs, lock, false);

    Configuration conf = getConf();
    conf.setBoolean(SITEMAP_STRICT_PARSING, strict);
    conf.setBoolean(SITEMAP_URL_FILTERING, filter);
    conf.setBoolean(SITEMAP_URL_NORMALIZING, normalize);
    conf.setBoolean("mapreduce.fileoutputcommitter.marksuccessfuljobs", false);

    Job job = Job.getInstance(conf, "Nutch SitemapProcessor: " + crawldb.toString());
    job.setJarByClass(SitemapProcessor.class);

    // add crawlDb, sitemap url directory and hostDb to input paths
    MultipleInputs.addInputPath(job, current, SequenceFileInputFormat.class);

    if (sitemapUrlDir != null)
      MultipleInputs.addInputPath(job, sitemapUrlDir, KeyValueTextInputFormat.class);

    if (hostdb != null) {
      MultipleInputs.addInputPath(job, new Path(hostdb, CURRENT_NAME), SequenceFileInputFormat.class);
      if (conf.getStrings("http.robot.rules.allowlist") != null) {
        LOG.warn("Non-empty property \"http.robot.rules.allowlist\":"
            + " sitemap discovery via robots.txt is not possible for the listed hosts!");
      }
    }

    FileOutputFormat.setOutputPath(job, tempCrawlDb);

    job.setOutputFormatClass(MapFileOutputFormat.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(CrawlDatum.class);

    job.setMapperClass(MultithreadedMapper.class);
    MultithreadedMapper.setMapperClass(job, SitemapMapper.class);
    MultithreadedMapper.setNumberOfThreads(job, threads);
    job.setReducerClass(SitemapReducer.class);

    try {
      boolean success = job.waitForCompletion(true);
      if (!success) {
        String message = NutchJob.getJobFailureLogMessage("SitemapProcessor",
            job);
        LOG.error(message);
        NutchJob.cleanupAfterFailure(tempCrawlDb, lock, fs);
        // throw exception so that calling routine can exit with error
        throw new RuntimeException(message);
      }

      boolean preserveBackup = conf.getBoolean("db.preserve.backup", true);
      if (!preserveBackup && fs.exists(old))
        fs.delete(old, true);
      else
        FSUtils.replace(fs, old, current, true);

      FSUtils.replace(fs, current, tempCrawlDb, true);
      LockUtil.removeLockFile(fs, lock);

      long filteredRecords = job.getCounters().findCounter("Sitemap", "filtered_records").getValue();
      long fromHostname = job.getCounters().findCounter("Sitemap", "sitemaps_from_hostname").getValue();
      long fromSeeds = job.getCounters().findCounter("Sitemap", "sitemap_seeds").getValue();
      long failedFetches = job.getCounters().findCounter("Sitemap", "failed_fetches").getValue();
      long newSitemapEntries = job.getCounters().findCounter("Sitemap", "new_sitemap_entries").getValue();

      LOG.info("SitemapProcessor: Total records rejected by filters: {}", filteredRecords);
      LOG.info("SitemapProcessor: Total sitemaps from host name: {}", fromHostname);
      LOG.info("SitemapProcessor: Total sitemaps from seed urls: {}", fromSeeds);
      LOG.info("SitemapProcessor: Total failed sitemap fetches: {}", failedFetches);
      LOG.info("SitemapProcessor: Total new sitemap entries added: {}", newSitemapEntries);

      stopWatch.stop();
      LOG.info("SitemapProcessor: finished, elapsed: {} ms", stopWatch.getTime(
          TimeUnit.MILLISECONDS));
    } catch (IOException | InterruptedException | ClassNotFoundException e) {
      LOG.error("SitemapProcessor_{}", crawldb.toString(), e);
      NutchJob.cleanupAfterFailure(tempCrawlDb, lock, fs);
      throw e;
    }
  }

  public static void main(String[] args) throws Exception {
    int res = ToolRunner.run(NutchConfiguration.create(), new SitemapProcessor(), args);
    System.exit(res);
  }

  public static void usage() {
    System.err.println("Usage:\n SitemapProcessor <crawldb> [-hostdb <hostdb>] [-sitemapUrls <url_dir>] " +
        "[-threads <threads>] [-force] [-noStrict] [-noFilter] [-noNormalize]\n");

    System.err.println("\t<crawldb>\t\tpath to crawldb where the sitemap urls would be injected");
    System.err.println("\t-hostdb <hostdb>\tpath of a hostdb. Sitemap(s) from these hosts would be downloaded");
    System.err.println("\t-sitemapUrls <url_dir>\tpath to directory with sitemap urls or hostnames");
    System.err.println("\t-threads <threads>\tNumber of threads created per mapper to fetch sitemap urls (default: 8)");
    System.err.println("\t-force\t\t\tforce update even if CrawlDb appears to be locked (CAUTION advised)");
    System.err.println("\t-noStrict\t\tBy default Sitemap parser rejects invalid urls. '-noStrict' disables that.");
    System.err.println("\t-noFilter\t\tturn off URLFilters on urls (optional)");
    System.err.println("\t-noNormalize\t\tturn off URLNormalizer on urls (optional)");
  }

  @Override
  public int run(String[] args) throws Exception {
    if (args.length < 3) {
      usage();
      return -1;
    }

    Path crawlDb = new Path(args[0]);
    Path hostDb = null;
    Path urlDir = null;
    boolean strict = true;
    boolean filter = true;
    boolean normalize = true;
    int threads = 8;

    for (int i = 1; i < args.length; i++) {
      if (args[i].equals("-hostdb")) {
        hostDb = new Path(args[++i]);
        LOG.info("SitemapProcessor: hostdb: {}", hostDb);
      }
      else if (args[i].equals("-sitemapUrls")) {
        urlDir = new Path(args[++i]);
        LOG.info("SitemapProcessor: sitemap urls dir: {}", urlDir);
      }
      else if (args[i].equals("-threads")) {
        threads = Integer.parseInt(args[++i]);
        LOG.info("SitemapProcessor: threads: {}", threads);
      }
      else if (args[i].equals("-noStrict")) {
        LOG.info("SitemapProcessor: 'strict' parsing disabled");
        strict = false;
      }
      else if (args[i].equals("-noFilter")) {
        LOG.info("SitemapProcessor: filtering disabled");
        filter = false;
      }
      else if (args[i].equals("-noNormalize")) {
        LOG.info("SitemapProcessor: normalizing disabled");
        normalize = false;
      }
      else {
        LOG.info("SitemapProcessor: Found invalid argument \"{}\"\n", args[i]);
        usage();
        return -1;
      }
    }

    try {
      sitemap(crawlDb, hostDb, urlDir, strict, filter, normalize, threads);
      return 0;
    } catch (Exception e) {
      LOG.error("SitemapProcessor: {}", StringUtils.stringifyException(e));
      return -1;
    }
  }
}
