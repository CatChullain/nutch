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
package org.apache.nutch.parse;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.ToolRunner;
import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.crawl.SignatureFactory;
import org.apache.nutch.metadata.Metadata;
import org.apache.nutch.net.URLNormalizers;
import org.apache.nutch.plugin.PluginRepository;
import org.apache.nutch.protocol.Content;
import org.apache.nutch.protocol.ProtocolOutput;
import org.apache.nutch.scoring.ScoringFilters;
import org.apache.nutch.util.AbstractChecker;
import org.apache.nutch.util.NutchConfiguration;
import org.apache.nutch.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parser checker, useful for testing parser. It also accurately reports
 * possible fetching and parsing failures and presents protocol status signals
 * to aid debugging. The tool enables us to retrieve the following data from any
 * url:
 * <ol>
 * <li><code>contentType</code>: The URL {@link org.apache.nutch.protocol.Content}
 * type.</li>
 * <li><code>signature</code>: Digest is used to identify pages (like unique ID) and
 * is used to remove duplicates during the dedup procedure. It is calculated
 * using {@link org.apache.nutch.crawl.MD5Signature} or
 * {@link org.apache.nutch.crawl.TextProfileSignature}.</li>
 * <li><code>Version</code>: From {@link org.apache.nutch.parse.ParseData}.</li>
 * <li><code>Status</code>: From {@link org.apache.nutch.parse.ParseData}.</li>
 * <li><code>Title</code>: of the URL</li>
 * <li><code>Outlinks</code>: associated with the URL</li>
 * <li><code>Content Metadata</code>: such as <i>X-AspNet-Version</i>, <i>Date</i>,
 * <i>Content-length</i>, <i>servedBy</i>, <i>Content-Type</i>,
 * <i>Cache-Control</i>, etc.</li>
 * <li><code>Parse Metadata</code>: such as <i>CharEncodingForConversion</i>,
 * <i>OriginalCharEncoding</i>, <i>language</i>, etc.</li>
 * <li><code>ParseText</code>: The page parse text which varies in length depdnecing
 * on <code>content.length</code> configuration.</li>
 * </ol>
 * 
 */

public class ParserChecker extends AbstractChecker {

  protected URLNormalizers normalizers = null;
  protected boolean dumpText = false;
  protected boolean followRedirects = false;
  protected boolean checkRobotsTxt = false;
  // used to simulate the metadata propagated from injection
  protected HashMap<String, String> metadata = new HashMap<>();
  protected String forceAsContentType = null;

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());
  private ScoringFilters scfilters;

  @Override
  public int run(String[] args) throws Exception {
    String url = null;

    String usage = "Usage:\n" //
        + "  ParserChecker [OPTIONS] <url>\n" //
        + "    Fetch single URL and parse it\n" //
        + "  ParserChecker [OPTIONS] -stdin\n" //
        + "    Read URLs to be parsed from stdin\n" //
        + "  ParserChecker [OPTIONS] -listen <port> [-keepClientCnxOpen]\n" //
        + "    Listen on <port> for URLs to be parsed\n" //
        + "Options:\n" //
        + "  -D<property>=<value>\tset/overwrite Nutch/Hadoop properties\n" //
        + "                  \t(a generic Hadoop option to be passed\n" //
        + "                  \t before other command-specific options)\n"
        + "  -normalize      \tnormalize URLs\n" //
        + "  -followRedirects\tfollow redirects when fetching URL\n" //
        + "  -checkRobotsTxt\tfail if the robots.txt disallows fetching\n" //
        + "  -dumpText       \talso show the plain-text extracted by parsers\n" //
        + "  -forceAs <mimeType>\tforce parsing as <mimeType>\n" //
        + "  -md <key>=<value>\tmetadata added to CrawlDatum before parsing\n";

    // Print help when no args given
    if (args.length < 1) {
      System.err.println(usage);
      return -1;
    }

    // initialize plugins early to register URL stream handlers to support
    // custom protocol implementations
    PluginRepository.get(getConf());

    int numConsumed;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-normalize")) {
        this.normalizers = new URLNormalizers(getConf(), URLNormalizers.SCOPE_DEFAULT);
      } else if (args[i].equals("-followRedirects")) {
        this.followRedirects = true;
      } else if (args[i].equals("-checkRobotsTxt")) {
        this.checkRobotsTxt = true;
      } else if (args[i].equals("-forceAs")) {
        this.forceAsContentType = args[++i];
      } else if (args[i].equals("-dumpText")) {
        this.dumpText = true;
      } else if (args[i].equals("-md")) {
        String k = null, v = null;
        String nextOne = args[++i];
        int firstEquals = nextOne.indexOf("=");
        if (firstEquals != -1) {
          k = nextOne.substring(0, firstEquals);
          v = nextOne.substring(firstEquals + 1);
        } else
          k = nextOne;
        this.metadata.put(k, v);
      } else if ((numConsumed = super.parseArgs(args, i)) > 0) {
        i += numConsumed - 1;
      } else if (i != args.length - 1) {
        System.err.println("ERR: Not a recognized argument: " + args[i]);
        System.err.println(usage);
        return -1;
      } else {
        url = args[i];
      }
    }

    this.scfilters = new ScoringFilters(getConf());
    
    if (url != null) {
      return super.processSingle(url);
    } else {
      // Start listening
      return super.run();
    }
  }

  @Override
  protected int process(String url, StringBuilder output) throws Exception {
    if (this.normalizers != null) {
      url = this.normalizers.normalize(url, URLNormalizers.SCOPE_DEFAULT);
    }

    LOG.info("fetching: {}", url);

    CrawlDatum datum = new CrawlDatum();

    Iterator<String> iter = this.metadata.keySet().iterator();
    while (iter.hasNext()) {
      String key = iter.next();
      String value = this.metadata.get(key);
      if (value == null)
        value = "";
      datum.getMetaData().put(new Text(key), new Text(value));
    }

    int maxRedirects = getConf().getInt("http.redirect.max", 3);
    if (this.followRedirects) {
      if (maxRedirects == 0) {
        LOG.info("Following max. 3 redirects (ignored http.redirect.max == 0)");
        maxRedirects = 3;
      } else {
        LOG.info("Following max. {} redirects", maxRedirects);
      }
    }

    ProtocolOutput protocolOutput = getProtocolOutput(url, datum,
        this.checkRobotsTxt);
    Text turl = new Text(url);

    // Following redirects and not reached maxRedirects?
    int numRedirects = 0;
    while (protocolOutput != null && !protocolOutput.getStatus().isSuccess()
        && this.followRedirects && protocolOutput.getStatus().isRedirect()
        && maxRedirects >= numRedirects) {
      String[] stuff = protocolOutput.getStatus().getArgs();
      url = stuff[0];
      LOG.info("Follow redirect to {}", url);

      if (this.normalizers != null) {
        url = this.normalizers.normalize(url, URLNormalizers.SCOPE_DEFAULT);
      }

      turl.set(url);

      // try again
      protocolOutput = getProtocolOutput(url, datum, this.checkRobotsTxt);
      numRedirects++;
    }

    if (this.checkRobotsTxt && protocolOutput == null) {
      System.err.println("Fetch disallowed by robots.txt");
      return -1;
    }

    if (!protocolOutput.getStatus().isSuccess()) {
      System.err.println("Fetch failed with protocol status: "
          + protocolOutput.getStatus());

      if (protocolOutput.getStatus().isRedirect()) {
          System.err.println("Redirect(s) not handled due to configuration.");
          System.err.println("Max Redirects to handle per config: " + maxRedirects);
          System.err.println("Number of Redirects handled: " + numRedirects);
      }
      return -1;
    }

    Content content = protocolOutput.getContent();

    if (content == null) {
      output.append("No content for " + url + "\n");
      return 0;
    }

    String contentType;
    if (this.forceAsContentType != null) {
      content.setContentType(this.forceAsContentType);
      contentType = this.forceAsContentType;
    } else {
      contentType = content.getContentType();
    }

    if (contentType == null) {
      LOG.error("Failed to determine content type!");
      return -1;
    }

    // store the guessed content type in the crawldatum
    datum.getMetaData().put(new Text(Metadata.CONTENT_TYPE),
        new Text(contentType));

    if (ParseSegment.isTruncated(content)) {
      LOG.warn("Content is truncated, parse may fail!");
    }

    // call the scoring filters
    try {
      this.scfilters.passScoreBeforeParsing(turl, datum, content);
    } catch (Exception e) {
      LOG.warn("Couldn't pass score before parsing, url {} ({})", turl, e);
      LOG.warn(StringUtils.stringifyException(e));
    }

    ParseResult parseResult = new ParseUtil(getConf()).parse(content);

    if (parseResult == null) {
      LOG.error("Parsing content failed!");
      return (-1);
    }

    // calculate the signature
    byte[] signature = SignatureFactory.getSignature(getConf()).calculate(
        content, parseResult.get(new Text(url)));

    LOG.info("parsing: {}", url);
    LOG.info("contentType: {}", contentType);
    LOG.info("signature: {}", StringUtil.toHexString(signature));

    for (Map.Entry<Text, Parse> entry : parseResult) {
      turl = entry.getKey();
      Parse parse = entry.getValue();
      // call the scoring filters
      try {
        this.scfilters.passScoreAfterParsing(turl, content, parse);
      } catch (Exception e) {
        LOG.warn("Couldn't pass score after parsing, url {} ({})", turl, e);
        LOG.warn(StringUtils.stringifyException(e));
      }

      output.append(turl).append("\n");
      output.append(parse.getData()).append("\n");
      if (this.dumpText) {
        output.append(parse.getText());
      }
    }

    return 0;
  }

  public static void main(String[] args) throws Exception {
    int res = ToolRunner.run(NutchConfiguration.create(), new ParserChecker(),
        args);
    System.exit(res);
  }

}
