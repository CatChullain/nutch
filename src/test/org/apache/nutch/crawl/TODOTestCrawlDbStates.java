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

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static org.apache.nutch.crawl.CrawlDatum.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.util.TimingUtil;

import org.apache.hadoop.mapreduce.Reducer.Context;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TODOTestCrawlDbStates extends TestCrawlDbStates {

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  /**
   * NUTCH-578: a fetch_retry should result in a db_gone if db.fetch.retry.max
   * is reached. Retry counter has to be reset appropriately.
   */
  @Test
  public void testCrawlDbReducerPageRetrySchedule() {
    LOG.info("NUTCH-578: test long running continuous crawl with fetch_retry");
    ContinuousCrawlTestUtil crawlUtil = new ContinuousCrawlTestFetchRetry();
    // keep going for long, to "provoke" a retry counter overflow
    try {
      if (!crawlUtil.run(150)) {
        fail("fetch_retry did not result in a db_gone if retry counter > maxRetries (NUTCH-578)");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private class ContinuousCrawlTestFetchRetry extends ContinuousCrawlTestUtil {

    private int retryMax = 3;
    private int totalRetries = 0;

    ContinuousCrawlTestFetchRetry() {
      super();
      fetchStatus = STATUS_FETCH_RETRY;
      retryMax = context.getConfiguration().getInt("db.fetch.retry.max", retryMax);
    }

    @Override
    protected CrawlDatum fetch(CrawlDatum datum, long currentTime) {
      datum.setStatus(fetchStatus);
      datum.setFetchTime(currentTime);
      totalRetries++;
      return datum;
    }

    @Override
    protected boolean check(CrawlDatum result) {
      if (result.getRetriesSinceFetch() > retryMax) {
        LOG.warn("Retry counter > db.fetch.retry.max: {}", result);
      } else if (result.getRetriesSinceFetch() == Byte.MAX_VALUE) {
        LOG.warn("Retry counter max. value reached (overflow imminent): {}", result);
      } else if (result.getRetriesSinceFetch() < 0) {
        LOG.error("Retry counter overflow: {}", result);
        return false;
      }
      // use retry counter bound to this class (totalRetries)
      // instead of result.getRetriesSinceFetch() because the retry counter
      // in CrawlDatum could be reset (eg. NUTCH-578_v5.patch)
      if (totalRetries < retryMax) {
        if (result.getStatus() == STATUS_DB_UNFETCHED) {
          LOG.info("ok: {}", result);
          result.getRetriesSinceFetch();
          return true;
        }
      } else {
        if (result.getStatus() == STATUS_DB_GONE) {
          LOG.info("ok: {}", result);
          return true;
        }
      }
      LOG.warn("wrong: {}", result);
      return false;
    }

  }

  /**
   * NUTCH-1564 AdaptiveFetchSchedule: sync_delta forces immediate re-fetch for
   * documents not modified
   * <p>
   * Problem: documents not modified for a longer time are fetched in every
   * cycle because of an error in the SYNC_DELTA calculation of
   * {@link AdaptiveFetchSchedule}. <br>
   * The next fetch time should always be in the future, never in the past.
   * </p>
   */
  @Test
  public void testAdaptiveFetchScheduleSyncDelta() {
    LOG.info("NUTCH-1564 test SYNC_DELTA calculation of AdaptiveFetchSchedule");
    Context context = CrawlDBTestUtil.createContext();
    Configuration conf = context.getConfiguration();
    conf.setLong("db.fetch.interval.default", 172800); // 2 days
    conf.setLong("db.fetch.schedule.adaptive.min_interval", 86400); // 1 day
    conf.setLong("db.fetch.schedule.adaptive.max_interval", 604800); // 7 days
    conf.setLong("db.fetch.interval.max", 604800); // 7 days
    conf.set("db.fetch.schedule.class",
        "org.apache.nutch.crawl.AdaptiveFetchSchedule");
    ContinuousCrawlTestUtil crawlUtil = new CrawlTestFetchScheduleNotModifiedFetchTime(
        context);
    crawlUtil.setInterval(FetchSchedule.SECONDS_PER_DAY / 3);
    try {
      if (!crawlUtil.run(100)) {
        fail("failed: sync_delta calculation with AdaptiveFetchSchedule");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private class CrawlTestFetchScheduleNotModifiedFetchTime extends
      CrawlTestFetchNotModified {

    // time of current fetch
    private long fetchTime;

    private long minInterval;
    private long maxInterval;

    CrawlTestFetchScheduleNotModifiedFetchTime(Context context) {
      super(context);
      Configuration conf = context.getConfiguration();
      minInterval = conf.getLong("db.fetch.schedule.adaptive.min_interval",
          86400); // 1 day
      maxInterval = conf.getLong("db.fetch.schedule.adaptive.max_interval",
          604800); // 7 days
      if (conf.getLong("db.fetch.interval.max", 604800) < maxInterval) {
        maxInterval = conf.getLong("db.fetch.interval.max", 604800);
      }
    }

    @Override
    protected CrawlDatum fetch(CrawlDatum datum, long currentTime) {
      // remember time of fetching
      fetchTime = currentTime;
      return super.fetch(datum, currentTime);
    }

    @Override
    protected boolean check(CrawlDatum result) {
      if (result.getStatus() == STATUS_DB_NOTMODIFIED) {
        // check only status notmodified here
        long secondsUntilNextFetch = (result.getFetchTime() - fetchTime) / 1000L;
        if (secondsUntilNextFetch < -1) {
          // next fetch time is in the past (more than one second)
          LOG.error("Next fetch time is in the past: {}", result);
          return false;
        }
        if (secondsUntilNextFetch < 60) {
          // next fetch time is in less than one minute
          // (critical: Nutch can hardly be so fast)
          LOG.error("Less then one minute until next fetch: {}", result);
        }
        // Next fetch time should be within min. and max. (tolerance: 60 sec.)
        if (secondsUntilNextFetch + 60 < minInterval
            || secondsUntilNextFetch - 60 > maxInterval) {
          LOG.error(
              "Interval until next fetch time ({}) is not within min. and max. interval: {}",
              TimingUtil.elapsedTime(fetchTime, result.getFetchTime()), result);
          // TODO: is this a failure?
        }
      }
      return true;
    }

  }

}
