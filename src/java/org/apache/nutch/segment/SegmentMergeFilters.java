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
package org.apache.nutch.segment;

import java.lang.invoke.MethodHandles;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.net.URLFilter;
import org.apache.nutch.parse.ParseData;
import org.apache.nutch.parse.ParseText;
import org.apache.nutch.plugin.Extension;
import org.apache.nutch.plugin.ExtensionPoint;
import org.apache.nutch.plugin.PluginRepository;
import org.apache.nutch.plugin.PluginRuntimeException;
import org.apache.nutch.protocol.Content;

/**
 * This class wraps all {@link SegmentMergeFilter} extensions in a single object
 * so it is easier to operate on them. If any of extensions returns
 * <code>false</code> this one will return <code>false</code> as well.
 * 
 */
public class SegmentMergeFilters {
  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());
  private SegmentMergeFilter[] filters;

  public SegmentMergeFilters(Configuration conf) {
    try {
      ExtensionPoint point = PluginRepository.get(conf).getExtensionPoint(
          SegmentMergeFilter.X_POINT_ID);
      if (point == null)
        throw new RuntimeException(URLFilter.X_POINT_ID + " not found.");
      Extension[] extensions = point.getExtensions();
      filters = new SegmentMergeFilter[extensions.length];
      for (int i = 0; i < extensions.length; i++) {
        filters[i] = (SegmentMergeFilter) extensions[i].getExtensionInstance();
      }
    } catch (PluginRuntimeException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Iterates over all {@link SegmentMergeFilter} extensions and if any of them
   * returns false, it will return false as well.
   * @param key the segment record key
   * @param generateData directory and data produced by the generation phase
   * @param fetchData directory and data produced by the fetch phase
   * @param sigData directory and data produced by the parse phase
   * @param content directory and data produced by the parse phase
   * @param parseData directory and data produced by the parse phase
   * @param parseText directory and data produced by the parse phase
   * @param linked all LINKED values from the latest segment
   * @return <code>true</code> values for this <code>key</code> (URL) should be merged
   *         into the new segment.
   */
  public boolean filter(Text key, CrawlDatum generateData,
      CrawlDatum fetchData, CrawlDatum sigData, Content content,
      ParseData parseData, ParseText parseText, Collection<CrawlDatum> linked) {
    for (SegmentMergeFilter filter : filters) {
      if (!filter.filter(key, generateData, fetchData, sigData, content,
          parseData, parseText, linked)) {
        LOG.trace("Key {} dropped by {}", key, filter.getClass().getName());
        return false;
      }
    }
    LOG.trace("Key {} accepted for merge.", key);
    return true;
  }
}
