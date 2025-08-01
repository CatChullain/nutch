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
package org.apache.nutch.fetcher;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.fetcher.FetchItemQueues.QueuingStatus;
import org.apache.nutch.fetcher.Fetcher.FetcherRun;
import org.apache.nutch.net.URLFilterException;
import org.apache.nutch.net.URLFilters;
import org.apache.nutch.net.URLNormalizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class feeds the queues with input items, and re-fills them as items
 * are consumed by FetcherThread-s.
 */
public class QueueFeeder extends Thread {

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  private FetcherRun.Context context;
  private FetchItemQueues queues;
  private int size;
  private URLFilters urlFilters = null;
  private URLNormalizers urlNormalizers = null;
  private String urlNormalizerScope = URLNormalizers.SCOPE_DEFAULT;

  public QueueFeeder(FetcherRun.Context context,
      FetchItemQueues queues, int size) {
    this.context = context;
    this.queues = queues;
    this.size = size;
    this.setDaemon(true);
    this.setName("QueueFeeder");
    Configuration conf = context.getConfiguration();
    if (conf.getBoolean("fetcher.filter.urls", false)) {
      urlFilters = new URLFilters(conf);
    }
    if (conf.getBoolean("fetcher.normalize.urls", false)) {
      urlNormalizers = new URLNormalizers(conf, urlNormalizerScope);
    }
  }

  /** Filter and normalize the url */
  private String filterNormalize(String url) {
    if (url != null) {
      try {
        if (urlNormalizers != null)
          url = urlNormalizers.normalize(url, urlNormalizerScope); // normalize the url
        if (urlFilters != null)
          url = urlFilters.filter(url);
      } catch (MalformedURLException | URLFilterException e) {
        LOG.warn("Skipping {}: {}", url, e);
        url = null;
      }
    }
    return url;
  }

  @Override
  public void run() {
    boolean hasMore = true;
    int cnt = 0;
    int[] queuingStatus = new int[QueuingStatus.values().length];
    while (hasMore) {
      if (queues.timelimitExceeded() || queues.timoutReached) {
        // enough ... lets' simply read all the entries from the input without
        // processing them
        if (queues.timoutReached) {
          int qstatus = QueuingStatus.HIT_BY_TIMEOUT.ordinal();
          if (queuingStatus[qstatus] == 0) {
            LOG.info("QueueFeeder stopping, timeout reached.");
          }
          queuingStatus[qstatus]++;
          context.getCounter("FetcherStatus", "hitByTimeout").increment(1);
        } else {
          int qstatus = QueuingStatus.HIT_BY_TIMELIMIT.ordinal();
          if (queuingStatus[qstatus] == 0) {
            LOG.info("QueueFeeder stopping, timelimit exceeded.");
          }
          queuingStatus[qstatus]++;
          context.getCounter("FetcherStatus", "hitByTimeLimit").increment(1);
        }
        try {
          hasMore = context.nextKeyValue();
        } catch (IOException e) {
          LOG.error("QueueFeeder error reading input, record {}", cnt, e);
          return;
        } catch (InterruptedException e) {
          LOG.info("QueueFeeder interrupted, exception:", e);
          return;
        }
        continue;
      }
      int feed = size - queues.getTotalSize();
      if (feed <= 0) {
        // queues are full - spin-wait until they have some free space
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        continue;
      }
      LOG.debug("-feeding {} input urls ...", feed);
      while (feed > 0 && hasMore) {
        try {
          hasMore = context.nextKeyValue();
          if (hasMore) {
            Text url = context.getCurrentKey();
            if (urlFilters != null || urlNormalizers != null) {
              String u = filterNormalize(url.toString());
              if (u == null) {
                // filtered or failed to normalize
                context.getCounter("FetcherStatus", "filtered").increment(1);
                continue;
              }
              url = new Text(u);
            }
            /*
             * Need to copy key and value objects because MapReduce will reuse
             * the original objects while the objects are stored in the queue.
             */
            else {
              url = new Text(url);
            }
            CrawlDatum datum = new CrawlDatum();
            datum.set(context.getCurrentValue());
            QueuingStatus status = queues.addFetchItem(url, datum);
            queuingStatus[status.ordinal()]++;
            if (status == QueuingStatus.ABOVE_EXCEPTION_THRESHOLD) {
              context
                  .getCounter("FetcherStatus", "AboveExceptionThresholdInQueue")
                  .increment(1);
            }
            cnt++;
            feed--;
          }
        } catch (IOException e) {
          LOG.error("QueueFeeder error reading input, record {}", cnt, e);
          return;
        } catch (InterruptedException e) {
          LOG.info("QueueFeeder interrupted, exception:", e);
        }
      }
    }
    // signal queues that no more new fetch items are added
    queues.feederAlive = false;
    LOG.info("QueueFeeder finished: total {} records", cnt);
    LOG.info("QueueFeeder queuing status:");
    for (QueuingStatus status : QueuingStatus.values()) {
      LOG.info("\t{}\t{}", queuingStatus[status.ordinal()], status);
    }
  }
}
