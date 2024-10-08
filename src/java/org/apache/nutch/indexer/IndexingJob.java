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
package org.apache.nutch.indexer;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.nutch.metadata.Nutch;
import org.apache.nutch.segment.SegmentChecker;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.nutch.util.HadoopFSUtil;
import org.apache.nutch.util.NutchConfiguration;
import org.apache.nutch.util.NutchJob;
import org.apache.nutch.util.NutchTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic indexer which relies on the plugins implementing IndexWriter
 **/

public class IndexingJob extends NutchTool implements Tool {

  private static final Random RANDOM = new Random();

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  public IndexingJob() {
    super(null);
  }

  public IndexingJob(Configuration conf) {
    super(conf);
  }

  public void index(Path crawlDb, Path linkDb, List<Path> segments,
      boolean noCommit) throws IOException, InterruptedException, ClassNotFoundException {
    index(crawlDb, linkDb, segments, noCommit, false, null);
  }

  public void index(Path crawlDb, Path linkDb, List<Path> segments,
      boolean noCommit, boolean deleteGone)
      throws IOException, InterruptedException, ClassNotFoundException {
    index(crawlDb, linkDb, segments, noCommit, deleteGone, null);
  }

  public void index(Path crawlDb, Path linkDb, List<Path> segments,
      boolean noCommit, boolean deleteGone, String params)
      throws IOException, InterruptedException, ClassNotFoundException {
    index(crawlDb, linkDb, segments, noCommit, deleteGone, params, false, false);
  }

  public void index(Path crawlDb, Path linkDb, List<Path> segments,
      boolean noCommit, boolean deleteGone, String params, boolean filter,
      boolean normalize) throws IOException, InterruptedException, ClassNotFoundException {
    index(crawlDb, linkDb, segments, noCommit, deleteGone, params, false,
        false, false);
  }

  public void index(Path crawlDb, Path linkDb, List<Path> segments,
      boolean noCommit, boolean deleteGone, String params,
      boolean filter, boolean normalize, boolean addBinaryContent)
      throws IOException, InterruptedException, ClassNotFoundException {
    index(crawlDb, linkDb, segments, noCommit, deleteGone, params, false,
        false, false, false);
  }

  public void index(Path crawlDb, Path linkDb, List<Path> segments,
      boolean noCommit, boolean deleteGone, String params,
      boolean filter, boolean normalize, boolean addBinaryContent,
      boolean base64) throws IOException, InterruptedException, ClassNotFoundException {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    LOG.info("Indexer: starting");

    final Job job = Job.getInstance(getConf(),
        "Nutch IndexingJob: crawldb: " + crawlDb + " segment(s): " + segments);
    job.setJobName("Indexer");
    Configuration conf = job.getConfiguration();

    LOG.info("Indexer: deleting gone documents: {}", deleteGone);
    LOG.info("Indexer: URL filtering: {}", filter);
    LOG.info("Indexer: URL normalizing: {}", normalize);
    if (addBinaryContent) {
      if (base64) {
        LOG.info("Indexer: adding binary content as Base64");
      } else {
        LOG.info("Indexer: adding binary content");
      }
    }

    IndexerMapReduce.initMRJob(crawlDb, linkDb, segments, job, addBinaryContent);

    conf.setBoolean(IndexerMapReduce.INDEXER_DELETE, deleteGone);
    conf.setBoolean(IndexerMapReduce.URL_FILTERING, filter);
    conf.setBoolean(IndexerMapReduce.URL_NORMALIZING, normalize);
    conf.setBoolean(IndexerMapReduce.INDEXER_BINARY_AS_BASE64, base64);
    conf.setBoolean(IndexerMapReduce.INDEXER_NO_COMMIT, noCommit);

    if (params != null) {
      conf.set(IndexerMapReduce.INDEXER_PARAMS, params);
    }

    job.setReduceSpeculativeExecution(false);

    final Path tmp = new Path("tmp_" + System.currentTimeMillis() + "-"
        + RANDOM.nextInt());

    FileOutputFormat.setOutputPath(job, tmp);
    try {
      try{
        boolean success = job.waitForCompletion(true);
        if (!success) {
          String message = NutchJob.getJobFailureLogMessage("Indexing", job);
          LOG.error(message);
          throw new RuntimeException(message);
        }
      } catch (IOException | InterruptedException | ClassNotFoundException e) {
        LOG.error(StringUtils.stringifyException(e));
        throw e;
      }
      LOG.info("Indexer: number of documents indexed, deleted, or skipped:");
      for (Counter counter : job.getCounters().getGroup("IndexerStatus")) {
        LOG.info("Indexer: {}  {}",
            String.format(Locale.ROOT, "%6d", counter.getValue()),
            counter.getName());
      }
      stopWatch.stop();
      LOG.info("Indexer: finished, elapsed: {} ms", stopWatch.getTime(
          TimeUnit.MILLISECONDS));
    } finally {
      tmp.getFileSystem(conf).delete(tmp, true);
    }
  }

  private static void usage() {
    System.err.println(
        "Usage: Indexer (<crawldb> | -nocrawldb) (<segment> ... | -dir <segments>) [general options]");
    System.err.println("");
    System.err.println("Index given segments using configured indexer plugins");
    System.err.println("");
    System.err.println(
        "The CrawlDb is optional but it is required to send deletion requests for duplicates");
    System.err.println(
        "and to read the proper document score/boost/weight passed to the indexers.");
    System.err.println("");
    System.err.println("Required arguments:");
    System.err.println("");
    System.err.println("\t<crawldb>\tpath to CrawlDb, or");
    System.err.println(
        "\t-nocrawldb\tflag to indicate that no CrawlDb shall be used");
    System.err.println("");
    System.err.println("\t<segment> ...\tpath(s) to segment, or");
    System.err.println("\t-dir <segments>\tpath to segments/ directory,");
    System.err.println(
        "\t               \t(all subdirectories are read as segments)");
    System.err.println("");
    System.err.println("General options:");
    System.err.println("\t");
    System.err.println(
        "\t-linkdb <linkdb>\tuse LinkDb to index anchor texts of incoming links");
    System.err.println(
        "\t-params k1=v1&k2=v2...\tparameters passed to indexer plugins");
    System.err.println(
        "\t                      \t(via property indexer.additional.params)");
    System.err.println("");
    System.err.println(
        "\t-noCommit\tdo not call the commit method of indexer plugins");
    System.err.println(
        "\t-deleteGone\tsend deletion requests for 404s, redirects, duplicates");
    System.err
        .println("\t-filter   \tskip documents with URL rejected by configured URL filters");
    System.err.println("\t-normalize\tnormalize URLs before indexing");
    System.err.println(
        "\t-addBinaryContent\tindex raw/binary content in field `binaryContent`");
    System.err.println("\t-base64   \tuse Base64 encoding for binary content");
    System.err.println("");
  }

  @Override
  public int run(String[] args) throws Exception {
    if (args.length == 0) {
      usage();
      return -1;
    }

    Path crawlDb = null;
    boolean noCrawlDb = false;

    Path linkDb = null;

    final List<Path> segments = new ArrayList<>();
    String params = null;

    boolean noCommit = false;
    boolean deleteGone = false;
    boolean filter = false;
    boolean normalize = false;
    boolean addBinaryContent = false;
    boolean base64 = false;

    for (int i = 0; i < args.length; i++) {
      FileSystem fs = null;
      Path dir = null;
      if (args[i].equals("-nocrawldb")) {
        noCrawlDb = true;
      } else if (args[i].equals("-linkdb")) {
        linkDb = new Path(args[++i]);
      } else if (args[i].equals("-dir")) {
        dir = new Path(args[++i]);
        fs = dir.getFileSystem(getConf());
        FileStatus[] fstats = fs.listStatus(dir,
            HadoopFSUtil.getPassDirectoriesFilter(fs));
        Path[] files = HadoopFSUtil.getPaths(fstats);
        for (Path p : files) {
          if (SegmentChecker.isIndexable(p,fs)) {
            segments.add(p);
          }
        }
      } else if (args[i].equals("-noCommit")) {
        noCommit = true;
      } else if (args[i].equals("-deleteGone")) {
        deleteGone = true;
      } else if (args[i].equals("-filter")) {
        filter = true;
      } else if (args[i].equals("-normalize")) {
        normalize = true;
      } else if (args[i].equals("-addBinaryContent")) {
        addBinaryContent = true;
      } else if (args[i].equals("-base64")) {
        base64 = true;
      } else if (args[i].equals("-params")) {
        params = args[++i];
      } else if (crawlDb == null && !noCrawlDb) {
        /*
         * expect CrawlDb as first non-option argument unless -nocrawldb is
         * given
         */
        crawlDb = new Path(args[i]);
      } else {
        // remaining arguments are segments
        dir = new Path(args[i]);
        fs = dir.getFileSystem(getConf());
        if (SegmentChecker.isIndexable(dir,fs)) {
          segments.add(dir);
        }
      }
    }

    if (segments.size() == 0) {
      usage();
      System.err.println("No indexable segments passed as arguments. At least one segment is required!");
      return -1;
    }

    try {
      index(crawlDb, linkDb, segments, noCommit, deleteGone, params, filter, normalize, addBinaryContent, base64);
      return 0;
    } catch (final Exception e) {
      LOG.error("Indexer: {}", StringUtils.stringifyException(e));
      return -1;
    }
  }

  public static void main(String[] args) throws Exception {
    final int res = ToolRunner.run(NutchConfiguration.create(),
        new IndexingJob(), args);
    System.exit(res);
  }


  //Used for REST API
  @Override
  public Map<String, Object> run(Map<String, Object> args, String crawlId) throws Exception {
    boolean noCommit = false;
    boolean deleteGone = false; 
    boolean filter = false;
    boolean normalize = false;
    boolean isSegment = false;
    boolean addBinaryContent = false;
    boolean base64 = false;
    String params= null;
    Configuration conf = getConf();

    Path crawlDb;
    if(args.containsKey(Nutch.ARG_CRAWLDB)) {
      Object crawldbPath = args.get(Nutch.ARG_CRAWLDB);
      if(crawldbPath instanceof Path) {
        crawlDb = (Path) crawldbPath;
      }
      else {
        crawlDb = new Path(crawldbPath.toString());
      }
    }
    else {
      crawlDb = new Path(crawlId+"/crawldb");
    }

    Path linkdb = null;
    List<Path> segments = new ArrayList<>();

    if(args.containsKey(Nutch.ARG_LINKDB)){
        Object path = args.get(Nutch.ARG_LINKDB);
        if(path instanceof Path) {
          linkdb = (Path) path;
        }
        else {
          linkdb = new Path(path.toString());
        }
    } else {
        linkdb = new Path(crawlId+"/linkdb");
      }

    if(args.containsKey(Nutch.ARG_SEGMENTDIR)){
      isSegment = true;
      Path segmentsDir;
      Object segDir = args.get(Nutch.ARG_SEGMENTDIR);
      if(segDir instanceof Path) {
        segmentsDir = (Path) segDir;
      }
      else {
        segmentsDir = new Path(segDir.toString());
      }
      FileSystem fs = segmentsDir.getFileSystem(getConf());
      FileStatus[] fstats = fs.listStatus(segmentsDir,
          HadoopFSUtil.getPassDirectoriesFilter(fs));
      Path[] files = HadoopFSUtil.getPaths(fstats);
      for (Path p : files) {
        if (SegmentChecker.isIndexable(p,fs)) {
          segments.add(p);
        }
      }     
    }

    if(args.containsKey(Nutch.ARG_SEGMENTS)) {
      Object segmentsFromArg = args.get(Nutch.ARG_SEGMENTS);
      ArrayList<String> segmentList = new ArrayList<String>();
      if(segmentsFromArg instanceof ArrayList) {
    	  segmentList = (ArrayList<String>)segmentsFromArg; }
      else if(segmentsFromArg instanceof Path){
        segmentList.add(segmentsFromArg.toString());
      }

      for(String segment: segmentList) {
    	  segments.add(new Path(segment));
      }
    }

    if(!isSegment){
      String segment_dir = crawlId+"/segments";
      File segmentsDir = new File(segment_dir);
      File[] segmentsList = segmentsDir.listFiles();  
      Arrays.sort(segmentsList, (f1, f2) -> {
        if(f1.lastModified()>f2.lastModified())
          return -1;
        else
          return 0;
      });
      Path segment = new Path(segmentsList[0].getPath());
      segments.add(segment);
    }

    if(args.containsKey("noCommit")){
      noCommit = true;
    }
    if(args.containsKey("deleteGone")){
      deleteGone = true;
    }
    if(args.containsKey("normalize")){
      normalize = true;
    }
    if(args.containsKey("filter")){
      filter = true;
    }
    if (args.containsKey("addBinaryContent")) {
      addBinaryContent = true;
      if (args.containsKey("base64")) {
          base64 = true;
      }
    }
    if(args.containsKey("params")){
      params = (String)args.get("params");
    }
    setConf(conf);
    index(crawlDb, linkdb, segments, noCommit, deleteGone, params, filter,
        normalize, addBinaryContent, base64);
    Map<String, Object> results = new HashMap<>();
    results.put(Nutch.VAL_RESULT, 0);
    return results;
  }
}
