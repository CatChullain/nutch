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
package org.apache.nutch.urlfilter.domain;

import java.lang.invoke.MethodHandles;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.net.URLFilter;
import org.apache.nutch.plugin.Extension;
import org.apache.nutch.plugin.PluginRepository;
import org.apache.nutch.util.URLUtil;

/**
 * <p>
 * Filters URLs based on a file containing domain suffixes, domain names, and
 * hostnames. Only a URL that matches one of the suffixes, domains, or hosts
 * present in the file is allowed.
 * </p>
 * 
 * <p>
 * URLs are checked in order of domain suffix, domain name, and hostname against
 * entries in the domain file. The domain file would be setup as follows with
 * one entry per line:
 * 
 * <pre>
 * com
 * apache.org
 * www.apache.org
 * </pre>
 * 
 * <p>
 * The first line is an example of a filter that would allow all .com domains.
 * The second line allows all URLs from apache.org and all of its subdomains
 * such as lucene.apache.org and hadoop.apache.org. The third line would allow
 * only URLs from www.apache.org. There is no specific ordering to entries. The
 * entries are from more general to more specific with the more general
 * overriding the more specific.
 * </p>
 * 
 * The domain file defaults to domain-urlfilter.txt in the classpath but can be
 * overridden using the:
 * 
 * <ul>
 * <li>
 * property "urlfilter.domain.file" in ./conf/nutch-*.xml, and
 * </li>
 * <li>
 * attribute "file" in plugin.xml of this plugin
 * </li>
 * </ul>
 * 
 */
public class DomainURLFilter implements URLFilter {

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  // read in attribute "file" of this plugin.
  private static String attributeFile = null;
  private Configuration conf;
  private Set<String> domainSet = new LinkedHashSet<String>();

  private void readConfiguration(Reader configReader) throws IOException {

    // read the configuration file, line by line
    BufferedReader reader = new BufferedReader(configReader);
    String line = null;
    while ((line = reader.readLine()) != null) {
      if (StringUtils.isNotBlank(line) && !line.startsWith("#")) {
        // add non-blank lines and non-commented lines
        domainSet.add(StringUtils.lowerCase(line.trim()));
      }
    }
  }

  /**
   * Sets the configuration.
   */
  @Override
  public void setConf(Configuration conf) {
    this.conf = conf;

    // get the extensions for domain urlfilter
    String pluginName = "urlfilter-domain";
    Extension[] extensions = PluginRepository.get(conf)
        .getExtensionPoint(URLFilter.class.getName()).getExtensions();
    for (int i = 0; i < extensions.length; i++) {
      Extension extension = extensions[i];
      if (extension.getDescriptor().getPluginId().equals(pluginName)) {
        attributeFile = extension.getAttribute("file");
        break;
      }
    }

    if (attributeFile != null && attributeFile.trim().isEmpty()) {
      attributeFile = null;
    }

    if (attributeFile != null) {
      LOG.info("Attribute \"file\" is defined for plugin {} as {}", pluginName, attributeFile);
    }

    // precedence hierarchy for definition of filter rules
    // (first non-empty definition takes precedence):
    // 1. string rules defined by `urlfilter.domain.rules`
    // 2. rule file name defined by `urlfilter.domain.file`
    // 3. rule file name defined in plugin.xml (`attributeFile`)
    String stringRules = conf.get("urlfilter.domain.rules");
    String file = conf.get("urlfilter.domain.file", attributeFile);
    Reader reader = null;
    if (stringRules != null) { // takes precedence over files
      reader = new StringReader(stringRules);
    } else {
      LOG.info("Reading {} rules file {}", pluginName, file);
      reader = conf.getConfResourceAsReader(file);
    }
    try {
      if (reader == null) {
        // read local file
        reader = new FileReader(file);
      }
      readConfiguration(reader);
    } catch (IOException e) {
      LOG.error("Error reading {} rule file {}", pluginName, file, e);
    }
  }

  @Override
  public Configuration getConf() {
    return this.conf;
  }

  @Override
  public String filter(String url) {
    // https://issues.apache.org/jira/browse/NUTCH-2189
    if (domainSet.size() == 0) return url;
    
    try {
      // match for suffix, domain, and host in that order. more general will
      // override more specific
      String domain = URLUtil.getDomainName(url);
      String host = URLUtil.getHost(url);
      String suffix = URLUtil.getDomainSuffix(url);

      if (domainSet.contains(suffix) || domainSet.contains(domain)
          || domainSet.contains(host)) {
        return url;
      }

      // doesn't match, don't allow
      return null;
    } catch (Exception e) {

      // if an error happens, allow the url to pass
      LOG.error("Could not apply filter on url: {}", url, e);
      return null;
    }
  }
}
