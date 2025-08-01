/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
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
package org.apache.nutch.protocol;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.nutch.util.NutchConfiguration;
import org.apache.nutch.util.SuffixStringMatcher;

import crawlercommons.robots.BaseRobotRules;
import crawlercommons.robots.SimpleRobotRules;
import crawlercommons.robots.SimpleRobotRules.RobotRulesMode;
import crawlercommons.robots.SimpleRobotRulesParser;

/**
 * This class uses crawler-commons for handling the parsing of
 * {@code robots.txt} files. It emits SimpleRobotRules objects, which describe
 * the download permissions as described in SimpleRobotRulesParser.
 * 
 * Protocol-specific implementations have to implement the method
 * {@link getRobotRulesSet}.
 */
public abstract class RobotRulesParser implements Tool {

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  protected static final Hashtable<String, BaseRobotRules> CACHE = new Hashtable<>();
  
  /**
   * A {@link BaseRobotRules} object appropriate for use when the
   * {@code robots.txt} file is empty or missing; all requests are allowed.
   */
  public static final BaseRobotRules EMPTY_RULES = new SimpleRobotRules(
      RobotRulesMode.ALLOW_ALL);

  /**
   * A {@link BaseRobotRules} object appropriate for use when the
   * {@code robots.txt} file is not fetched due to a {@code 403/Forbidden}
   * response; all requests are disallowed.
   */
  public static BaseRobotRules FORBID_ALL_RULES = new SimpleRobotRules(
      RobotRulesMode.ALLOW_NONE);

  /**
   * A {@link BaseRobotRules} object appropriate for use when the
   * {@code robots.txt} file failed to fetch with a 503 &quot;Internal Server
   * Error&quot; (or other 5xx) status code. The crawler should suspend crawling
   * for a certain (but not too long) time, see property
   * <code>http.robots.503.defer.visits</code>.
   */
  public static final BaseRobotRules DEFER_VISIT_RULES = new SimpleRobotRules(
      RobotRulesMode.ALLOW_NONE);
  static {
    DEFER_VISIT_RULES.setDeferVisits(true);
  }

  private static SimpleRobotRulesParser robotParser = new SimpleRobotRulesParser();
  static {
    robotParser.setMaxCrawlDelay(Long.MAX_VALUE);
  }

  protected Configuration conf;
  protected Set<String> agentNames;
  protected int maxNumRedirects = 5;

  /** set of host names or IPs to be explicitly excluded from robots.txt checking */
  protected Set<String> allowList = new HashSet<>();
  
  /* Matcher user for efficiently matching URLs against a set of suffixes. */
  private SuffixStringMatcher matcher = null;

  public RobotRulesParser() {
  }

  public RobotRulesParser(Configuration conf) {
    setConf(conf);
  }

  /**
   * Set the {@link Configuration} object
   */
  @Override
  public void setConf(Configuration conf) {
    this.conf = conf;

    // Grab the agent names we advertise to robots files.
    String agentName = conf.get("http.agent.name");
    if (agentName == null || (agentName = agentName.trim()).isEmpty()) {
      throw new RuntimeException("Agent name not configured!");
    }
    agentNames = new LinkedHashSet<>();
    if (!agentName.equals("*")) {
      /*
       * skip wildcard "*" - crawler-commons' SimpleRobotRulesParser expects an
       * empty set of agent names to use the wildcard rules
       */
      agentNames.add(agentName.toLowerCase());
    }

    // If there are any other agents specified, append those to the list of
    // agents
    String[] otherAgents = conf.getStrings("http.robots.agents");
    if (otherAgents != null && otherAgents.length > 0) {
      for (String otherAgent : otherAgents) {
        otherAgent = otherAgent.toLowerCase();
        if (otherAgent.equals("*") || otherAgent.equalsIgnoreCase(agentName)) {
          /*
           * skip wildcard "*" or agent name itself (required for backward
           * compatibility, cf. NUTCH-1715 and NUTCH-1718)
           */
        } else {
          agentNames.add(otherAgent);
        }
      }
    }
    LOG.info("Checking robots.txt for the following agent names: {}", agentNames);

    maxNumRedirects = conf.getInt("http.robots.redirect.max", 5);
    LOG.info("Following max. {} robots.txt redirects", maxNumRedirects);

    String[] confAllowList = conf.getStrings("http.robot.rules.allowlist");
    if (confAllowList == null) {
      LOG.debug("robots.txt allowlist not configured.");
    } else {
      for (int i = 0; i < confAllowList.length; i++) {
        if (confAllowList[i].isEmpty()) {
      	  LOG.info("Empty allowlisted URL skipped!");
      	  continue;
        }
        allowList.add(confAllowList[i]);
      }
      
      if (allowList.size() > 0) {
        matcher = new SuffixStringMatcher(allowList);
        LOG.info("Allowlisted hosts: {}", allowList);
      }
    }
  }

  /**
   * Get the {@link Configuration} object
   */
  @Override
  public Configuration getConf() {
    return conf;
  }

  /**
   * Check whether a URL belongs to a allowlisted host.
   * @param url a {@link java.net.URL} to check against rules
   * @return true if always allowed (robots.txt rules are ignored), false otherwise
   */
  public boolean isAllowListed(URL url) {
    boolean match = false;
    String urlString = url.getHost();
    
    if (matcher != null) {
    	match = matcher.matches(urlString);
    }
    
    return match;
  }

  /**
   * Parses the robots content using the {@link SimpleRobotRulesParser} from
   * crawler-commons
   * 
   * @param url
   *          The robots.txt URL
   * @param content
   *          Contents of the robots file in a byte array
   * @param contentType
   *          The content type of the robots file
   * @param robotName
   *          A string containing all the robots agent names used by parser for
   *          matching
   * @return BaseRobotRules object
   */
  @Deprecated
  public BaseRobotRules parseRules(String url, byte[] content,
      String contentType, String robotName) {
    return robotParser.parseContent(url, content, contentType, robotName);
  }

  /**
   * Parses the robots content using the {@link SimpleRobotRulesParser} from
   * crawler-commons
   * 
   * @param url
   *          The robots.txt URL
   * @param content
   *          Contents of the robots file in a byte array
   * @param contentType
   *          The content type of the robots file
   * @param robotNames
   *          A collection containing all the robots agent names used by parser
   *          for matching
   * @return BaseRobotRules object
   */
  public BaseRobotRules parseRules(String url, byte[] content,
      String contentType, Collection<String> robotNames) {
    return robotParser.parseContent(url, content, contentType, robotNames);
  }

  /**
   * Fetch robots.txt (or it's protocol-specific equivalent) which applies to
   * the given URL, parse it and return the set of robot rules applicable for
   * the configured agent name(s).
   *
   * @param protocol
   *          {@link Protocol}
   * @param url
   *          URL to check
   * @param robotsTxtContent
   *          container to store responses when fetching the robots.txt file for
   *          debugging or archival purposes. Instead of a robots.txt file, it
   *          may include redirects or an error page (404, etc.). Response
   *          {@link Content} is appended to the passed list. If null is passed
   *          nothing is stored.
   *
   * @return robot rules (specific for this URL or default), never null
   */
  public BaseRobotRules getRobotRulesSet(Protocol protocol, Text url,
      List<Content> robotsTxtContent) {
    URL u = null;
    try {
      u = new URL(url.toString());
    } catch (Exception e) {
      return EMPTY_RULES;
    }
    return getRobotRulesSet(protocol, u, robotsTxtContent);
  }

  /**
   * Fetch robots.txt (or it's protocol-specific equivalent) which applies to
   * the given URL, parse it and return the set of robot rules applicable for
   * the configured agent name(s).
   *
   * @param protocol
   *          {@link Protocol}
   * @param url
   *          URL to check
   * @param robotsTxtContent
   *          container to store responses when fetching the robots.txt file for
   *          debugging or archival purposes. Instead of a robots.txt file, it
   *          may include redirects or an error page (404, etc.). Response
   *          {@link Content} is appended to the passed list. If null is passed
   *          nothing is stored.
   *
   * @return robot rules (specific for this URL or default), never null
   */
  public abstract BaseRobotRules getRobotRulesSet(Protocol protocol, URL url,
      List<Content> robotsTxtContent);


  @Override
  public int run(String[] args) {

    if (args.length < 2) {
      String[] help = {
          "Usage: RobotRulesParser [ -Dproperty=... ] <robots-file-or-url> <url-file> [<agent-names>]",
          "",
          "<robots-file-or-url>\tlocal file or URL parsed as robots.txt file",
          "\tIf <robots-file-or-url> starts with a protocol specification",
          "\t(`http', `https', `ftp' or `file'), the URL is parsed, URL path",
          "\tand query are removed and the path \"/robots.txt\" is appended.",
          "\tThe resulting URL (the canonical robots.txt location) is then",
          "\tfetched using the specified protocol.",
          "\tIf the URL does not include a protocol, a local file is assumed.",
          "",
          "<url-file>\tlocal file with URLs (one per line), for every URL",
          "\tthe path part (including the query) is checked whether",
          "\tit is allowed by the robots.txt rules.  Other parts of the URLs",
          "\t(mainly the host) are ignored.",
          "",
          "<agent-names>\tuser-agent name (aka. \"product token\")",
          "\tused to select rules from the robots.txt file.",
          "\tMultiple agent names can be passed as comma-separated string.",
          "\tIf no agent name is given the properties http.agent.name",
          "\tand http.robots.agents are used.",
          "\tIf also http.agent.name and http.robots.agents are empty,",
          "\trobots.txt is checked for rules assigned to the user",
          "\tagent `*' (meaning any other).",
          "",
          "Important properties:",
          " -D fetcher.store.robotstxt=true",
          "\toutput content and HTTP meta data of fetched robots.txt (if not a local file)",
          " -D http.agent.name=...\t(primary) agent name",
          " -D http.robots.agents=...\tadditional agent names",
          " -D http.robot.rules.allowlist=..."};
      for (String s : help) {
        System.err.println(s);
      }
      return -1;
    }

    if (args.length > 2) {
      // set agent name from command-line in configuration
      // Note: when fetching via protocol this must be done
      // before the protocol is configured
      String agents = args[2];
      conf.set("http.robots.agents", agents);
      conf.set("http.agent.name", agents.split(",")[0]);
      setConf(conf);
    }

    Protocol protocol = null;
    URL robotsTxtUrl = null;
    if (args[0].matches("^(?:https?|ftp|file)://?.*")) {
      try {
        robotsTxtUrl = new URL(args[0]);
      } catch (MalformedURLException e) {
        LOG.warn("Not a valid URL, assuming local file: {}", args[0]);
      }
      ProtocolFactory factory = new ProtocolFactory(conf);
      try {
        protocol = factory.getProtocol(robotsTxtUrl);
        LOG.debug("Using protocol {} to fetch robots.txt", protocol.getClass());
      } catch (ProtocolNotFound e) {
        LOG.error("No protocol found for {}: {}", args[0],
            StringUtils.stringifyException(e));
        return -1;
      }
    }

    if (robotsTxtUrl == null) {
      // try as local file
      File robotsFile = new File(args[0]);
      if (!robotsFile.exists()) {
        LOG.error("File does not exist: {}", args[0]);
        return -1;
      } else {
        try {
          robotsTxtUrl = robotsFile.toURI().toURL();
        } catch (MalformedURLException e) {
        }
      }
    }

    File urlFile = new File(args[1]);

    List<Content> robotsTxtContent = null;
    if (getConf().getBoolean("fetcher.store.robotstxt", false)) {
      robotsTxtContent = new LinkedList<>();
    }

    try {

      BaseRobotRules rules = getRobotRulesSet(protocol, robotsTxtUrl, robotsTxtContent);
      LOG.debug("Robots.txt rules:\n{}", rules);

      if (robotsTxtContent != null) {
        for (Content robotsTxt : robotsTxtContent) {
          LOG.info("fetched robots.txt {}:",
              robotsTxt.getUrl());
          LOG.info(robotsTxt.toString());
        }
      }

      System.out.println("Testing robots.txt for agent names: "
          + (agentNames.isEmpty() ? "* (any other agent)" : agentNames));

      LineNumberReader testsIn = new LineNumberReader(new FileReader(urlFile));
      String testPath;
      testPath = testsIn.readLine();
      while (testPath != null) {
        testPath = testPath.trim();
        try {
          // testPath can be just a path or a complete URL
          URL url = new URL(testPath);
          String status;
          if (isAllowListed(url)) {
            status = "allowlisted";
          } else if (rules.isAllowed(testPath)) {
            status = "allowed";
          } else {
            status = "not allowed";
          }
          System.out.println(status + ":\t" + testPath);
        } catch (MalformedURLException e) {
          LOG.warn("Not a valid URL: {}", testPath);
        }
        testPath = testsIn.readLine();
      }
      testsIn.close();
    } catch (IOException e) {
      LOG.error("Failed to run:", e);
      return -1;
    }

    return 0;
  }

  /**
   * {@link RobotRulesParser} implementation which expects the location of the
   * robots.txt passed by URL (usually pointing to a local file) in
   * {@link getRobotRulesSet}.
   */
  private static class TestRobotRulesParser extends RobotRulesParser {

    @Override
    public void setConf(Configuration conf) {
      /*
       * Make sure that agent name is not empty so that
       * RobotRulesParser.setConf() does not complain.
       * 
       * If provided the agent names passed as command-line argument are
       * checked, see RobotRulesParser.run(...). Also http.agent.name is then
       * filled taking the first agent name from command-line.
       */
      if (conf.get("http.agent.name", "").isEmpty()) {
        String firstRobotsAgent = conf.get("http.robots.agents", "").split(",")[0].trim();
        if (firstRobotsAgent.isEmpty()) {
          conf.set("http.agent.name", "*");
        } else {
          conf.set("http.agent.name", firstRobotsAgent);
        }
      }
      super.setConf(conf);
    }

    /**
     * @param protocol
     *          (if not null) protocol used to get robot rules,
     *          (if null) the URL is read via {@link URLConnection}
     * @param url
     *          location of the robots.txt file
     */
    @Override
    public BaseRobotRules getRobotRulesSet(Protocol protocol, URL url,
        List<Content> robotsTxtContent) {
      BaseRobotRules rules;
      if (protocol != null) {
        rules = protocol.getRobotRules(new Text(url.toString()), null,
            robotsTxtContent);
      } else {
        try {
          int contentLength = url.openConnection().getContentLength();
          byte[] robotsBytes = new byte[contentLength];
          InputStream openStream = url.openStream();
          openStream.read(robotsBytes);
          openStream.close();
          rules = robotParser.parseContent(url.toString(), robotsBytes,
              "text/plain", agentNames);
        } catch (IOException e) {
          LOG.error("Failed to open robots.txt file {}:", url, e);
          rules = EMPTY_RULES;
        }
      }
      return rules;
    }

  }

  public static void main(String[] args) throws Exception {
    Configuration conf = NutchConfiguration.create();
    int res = ToolRunner.run(conf, new TestRobotRulesParser(), args);
    System.exit(res);
  }

}
