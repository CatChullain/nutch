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
package org.apache.nutch.urlfilter.api;

import java.lang.invoke.MethodHandles;
import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.net.URLFilter;
import org.apache.nutch.util.URLUtil;

/**
 * Generic {@link org.apache.nutch.net.URLFilter} based on regular
 * expressions.
 * 
 * <p>
 * The regular expressions rules are expressed in a file. The file of rules is
 * determined for each implementation using the
 * {@link #getRulesReader(Configuration conf)} method.
 * </p>
 * 
 * <p>
 * The format of this file is made of many rules (one per line):<br>
 * <code>
 * [+-]&lt;regex&gt;
 * </code><br>
 * where plus (<code>+</code>)means go ahead and index it and minus (
 * <code>-</code>)means no.
 * </p>
 */
public abstract class RegexURLFilterBase implements URLFilter {

  /** My logger */
  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  /** An array of applicable rules */
  private List<RegexRule> rules;

  /** The current configuration */
  private Configuration conf;

  /**
   * Whether there are host- or domain-specific rules. If there are no specific
   * rules host and domain name are not extracted from the URL to speed up the
   * matching. {@link #readRules(Reader)} automatically sets this to true if
   * host- or domain-specific rules are used in the rule file.
   */
  protected boolean hasHostDomainRules = false;

  /**
   * Constructs a new empty RegexURLFilterBase
   */
  public RegexURLFilterBase() {
  }

  /**
   * Constructs a new RegexURLFilter and init it with a file of rules.
   * 
   * @param filename
   *          is the name of rules file.
   * @throws IOException if there is a fatal I/O error interpreting the input
   * {@link File}
   * @throws IllegalArgumentException if there is a fatal error processing the regex 
   * rules wiuthin the {@link org.apache.nutch.net.URLFilter}
   */
  public RegexURLFilterBase(File filename) throws IOException,
      IllegalArgumentException {
    this(new FileReader(filename));
  }

  /**
   * Constructs a new RegexURLFilter and inits it with a list of rules.
   * 
   * @param rules
   *          string with a list of rules, one rule per line
   * @throws IOException if there is a fatal I/O error interpreting the input
   * rules
   * @throws IllegalArgumentException if there is a fatal error processing the regex 
   * rules wiuthin the {@link org.apache.nutch.net.URLFilter}
   */
  public RegexURLFilterBase(String rules) throws IOException,
      IllegalArgumentException {
    this(new StringReader(rules));
  }

  /**
   * Constructs a new RegexURLFilter and init it with a Reader of rules.
   * 
   * @param reader
   *          is a reader of rules.
   * @throws IOException if there is a fatal I/O error interpreting the input
   * {@link Reader}
   * @throws IllegalArgumentException if there is a fatal error processing the regex 
   * rules wiuthin the {@link org.apache.nutch.net.URLFilter}
   */
  protected RegexURLFilterBase(Reader reader) throws IOException,
      IllegalArgumentException {
    rules = readRules(reader);
  }

  /**
   * Creates a new {@link RegexRule}.
   * 
   * @param sign
   *          of the regular expression. A <code>true</code> value means that
   *          any URL matching this rule must be included, whereas a
   *          <code>false</code> value means that any URL matching this rule
   *          must be excluded.
   * @param regex
   *          is the regular expression associated to this rule.
   * @return {@link RegexRule}
   */
  protected abstract RegexRule createRule(boolean sign, String regex);
  
  /**
   * Creates a new {@link RegexRule}.
   * @param 
   *        sign of the regular expression.
   *        A <code>true</code> value means that any URL matching this rule
   *        must be included, whereas a <code>false</code>
   *        value means that any URL matching this rule must be excluded.
   * @param regex
   *        is the regular expression associated to this rule.
   * @param hostOrDomain
   *        the host or domain to which this regex belongs
   * @return {@link RegexRule}
   */
  protected abstract RegexRule createRule(boolean sign, String regex, String hostOrDomain);

  /**
   * Returns the name of the file of rules to use for a particular
   * implementation.
   * 
   * @param conf
   *          is the current configuration.
   * @return the name of the resource containing the rules to use.
   * @throws IOException if there is a fatal error obtaining the 
   * {@link Reader}
   */
  protected abstract Reader getRulesReader(Configuration conf)
      throws IOException;

  @Override
  public String filter(String url) {
    String host = null;
    String domain = null;

    if (hasHostDomainRules) {
      host = URLUtil.getHost(url);
      try {
        domain = URLUtil.getDomainName(url);
      } catch (MalformedURLException e) {
        // shouldnt happen here right?
      }

      LOG.debug("URL belongs to host {} and domain {}", host, domain);
    }
    
    for (RegexRule rule : rules) {
      // Skip the skip for rules that don't share the same host and domain
      if (rule.hostOrDomain() != null &&
            !rule.hostOrDomain().equals(host) &&
            !rule.hostOrDomain().equals(domain)) {
        LOG.debug("Skipping rule [{}] for host: {}", rule.regex(),
            rule.hostOrDomain());

        continue;
      }
    
      LOG.debug("Applying rule [{}] for host {} and domain {}", rule.regex(),
          host, domain);

      if (rule.match(url)) {
        return rule.accept() ? url : null;
      }
    }
    ;
    return null;
  }

  @Override
  public void setConf(Configuration conf) {
    this.conf = conf;
    Reader reader = null;
    try {
      reader = getRulesReader(conf);
    } catch (Exception e) {
      LOG.error(e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    }
    try {
      rules = readRules(reader);
    } catch (IOException e) {
      LOG.error(e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @Override
  public Configuration getConf() {
    return this.conf;
  }

  /**
   * Read the specified file of rules.
   * 
   * @param reader
   *          is a reader of regular expressions rules.
   * @return the corresponding {@RegexRule rules}.
   */
  private List<RegexRule> readRules(Reader reader) throws IOException,
      IllegalArgumentException {

    BufferedReader in = new BufferedReader(reader);
    List<RegexRule> rules = new ArrayList<RegexRule>();
    String line;
    String hostOrDomain = null;
    
    while ((line = in.readLine()) != null) {
      if (line.length() == 0) {
        continue;
      }
      char first = line.charAt(0);
      boolean sign = false;
      switch (first) {
      case '+':
        sign = true;
        break;
      case '-':
        sign = false;
        break;
      case ' ':
      case '\n':
      case '#': // skip blank & comment lines
        continue;
      case '>':
        hostOrDomain = line.substring(1).trim();
        hasHostDomainRules = true;
        continue;
      case '<':
        hostOrDomain = null;
        continue;
      default:
        throw new IOException("Invalid first character: " + line);
      }

      String regex = line.substring(1);
      LOG.trace("Adding rule [" + regex + "] for " + hostOrDomain);
      RegexRule rule = createRule(sign, regex, hostOrDomain);
      rules.add(rule);
    }
    LOG.info("Read {} regex rules ({})", rules.size(),
        this.getClass().getName());
    return rules;
  }

  /**
   * Filter the standard input using a RegexURLFilterBase.
   * 
   * @param filter
   *          is the RegexURLFilterBase to use for filtering the standard input.
   * @param args
   *          some optional parameters (not used).
   * @throws IOException if there is a fatal I/O error interpreting the input
   * arguments
   * @throws IllegalArgumentException if there is a fatal error processing the 
   * input arguments
   */
  public static void main(RegexURLFilterBase filter, String args[])
      throws IOException, IllegalArgumentException {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String line;
    while ((line = in.readLine()) != null) {
      String out = filter.filter(line);
      if (out != null) {
        System.out.print("+");
        System.out.println(out);
      } else {
        System.out.print("-");
        System.out.println(line);
      }
    }
  }

}
