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
package org.apache.nutch.crawl;

import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.apache.hadoop.conf.Configurable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.nutch.net.URLNormalizers;
import org.apache.nutch.util.URLUtil;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Partition urls by host, domain name or IP depending on the value of the
 * parameter 'partition.url.mode' which can be 'byHost', 'byDomain' or 'byIP'
 */
public class URLPartitioner extends Partitioner<Text, Writable> implements Configurable {
  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  public static final String PARTITION_MODE_KEY = "partition.url.mode";

  public static final String PARTITION_MODE_HOST = "byHost";
  public static final String PARTITION_MODE_DOMAIN = "byDomain";
  public static final String PARTITION_MODE_IP = "byIP";

  private int seed;
  private URLNormalizers normalizers;
  private String mode = PARTITION_MODE_HOST;

  private Configuration conf;

  @Override
  public void setConf(Configuration conf) {
    this.conf = conf;
    seed = conf.getInt("partition.url.seed", 0);
    mode = conf.get(PARTITION_MODE_KEY, PARTITION_MODE_HOST);
    // check that the mode is known
    if (!mode.equals(PARTITION_MODE_IP) && !mode.equals(PARTITION_MODE_DOMAIN)
        && !mode.equals(PARTITION_MODE_HOST)) {
      LOG.error("Unknown partition mode : {} - forcing to byHost", mode);
      mode = PARTITION_MODE_HOST;
    }
    normalizers = new URLNormalizers(conf, URLNormalizers.SCOPE_PARTITION);
  }

  @Override
  public Configuration getConf() {
    return conf;
  }

  /** Hash by host or domain name or IP address. */
  @Override
  public int getPartition(Text key, Writable value, int numReduceTasks) {
    String urlString = key.toString();
    URL url = null;
    int hashCode = 0;
    try {
      urlString = normalizers.normalize(urlString,
          URLNormalizers.SCOPE_PARTITION);
      url = new URL(urlString);
    } catch (MalformedURLException e) {
      LOG.warn("Malformed URL: '{}'", urlString);
    }

    if (url == null) {
      // failed to parse URL, must take URL string as fall-back
      hashCode = urlString.hashCode();
    } else if (mode.equals(PARTITION_MODE_HOST)) {
      hashCode = url.getHost().hashCode();
    } else if (mode.equals(PARTITION_MODE_DOMAIN)) {
      hashCode = URLUtil.getDomainName(url).hashCode();
    } else if (mode.equals(PARTITION_MODE_IP)) {
      try {
        InetAddress address = InetAddress.getByName(url.getHost());
        hashCode = address.getHostAddress().hashCode();
      } catch (UnknownHostException e) {
        Generator.LOG.info("Couldn't find IP for host: {}", url.getHost());
      }
    }

    // make hosts wind up in different partitions on different runs
    hashCode ^= seed;

    return (hashCode & Integer.MAX_VALUE) % numReduceTasks;
  }

}
