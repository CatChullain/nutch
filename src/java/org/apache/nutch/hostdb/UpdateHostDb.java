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
package org.apache.nutch.hostdb;

import java.lang.invoke.MethodHandles;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.nutch.crawl.CrawlDb;
import org.apache.nutch.crawl.NutchWritable;
import org.apache.nutch.util.FSUtils;
import org.apache.nutch.util.LockUtil;
import org.apache.nutch.util.NutchConfiguration;
import org.apache.nutch.util.NutchJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tool to create a HostDB from the CrawlDB. It aggregates fetch status values
 * by host and checks DNS entries for hosts.
 */
public class UpdateHostDb extends Configured implements Tool {

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());
  public static final String LOCK_NAME = ".locked";

  public static final String HOSTDB_PURGE_FAILED_HOSTS_THRESHOLD = "hostdb.purge.failed.hosts.threshold";
  public static final String HOSTDB_NUM_RESOLVER_THREADS = "hostdb.num.resolvers.threads";
  public static final String HOSTDB_RECHECK_INTERVAL = "hostdb.recheck.interval";
  public static final String HOSTDB_CHECK_FAILED = "hostdb.check.failed";
  public static final String HOSTDB_CHECK_NEW = "hostdb.check.new";
  public static final String HOSTDB_CHECK_KNOWN = "hostdb.check.known";
  public static final String HOSTDB_FORCE_CHECK = "hostdb.force.check";
  public static final String HOSTDB_URL_FILTERING = "hostdb.url.filter";
  public static final String HOSTDB_URL_NORMALIZING = "hostdb.url.normalize";
  public static final String HOSTDB_NUMERIC_FIELDS = "hostdb.numeric.fields";
  public static final String HOSTDB_STRING_FIELDS = "hostdb.string.fields";
  public static final String HOSTDB_PERCENTILES = "hostdb.percentiles";
  public static final String HOSTDB_CRAWLDATUM_PROCESSORS = "hostdb.crawldatum.processors";
  public static final String HOSTDB_URL_LIMIT = "hostdb.url.limit";
  
  private void updateHostDb(Path hostDb, Path crawlDb, Path topHosts,
    boolean checkFailed, boolean checkNew, boolean checkKnown,
    boolean force, boolean filter, boolean normalize, long urlLimit) throws Exception {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    LOG.info("UpdateHostDb: starting");

    Job job = Job.getInstance(getConf(), "Nutch UpdateHostDb");
    Configuration conf = job.getConfiguration();
    boolean preserveBackup = conf.getBoolean("db.preserve.backup", true);
    job.setJarByClass(UpdateHostDb.class);

    FileSystem fs = hostDb.getFileSystem(conf);
    Path old = new Path(hostDb, "old");
    Path current = new Path(hostDb, "current");
    Path tempHostDb = new Path(hostDb, "hostdb-"
      + Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));

    // lock an existing hostdb to prevent multiple simultaneous updates
    Path lock = new Path(hostDb, LOCK_NAME);
    if (!fs.exists(current)) {
      fs.mkdirs(current);
    }
    LockUtil.createLockFile(fs, lock, false);

    MultipleInputs.addInputPath(job, current, SequenceFileInputFormat.class);

    if (topHosts != null) {
      MultipleInputs.addInputPath(job, topHosts, KeyValueTextInputFormat.class);
    }
    if (crawlDb != null) {
      // Tell the job we read from CrawlDB
      conf.setBoolean("hostdb.reading.crawldb", true);
      MultipleInputs.addInputPath(job, new Path(crawlDb,
        CrawlDb.CURRENT_NAME), SequenceFileInputFormat.class);
    }

    FileOutputFormat.setOutputPath(job, tempHostDb);

    job.setOutputFormatClass(SequenceFileOutputFormat.class);

    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(NutchWritable.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(HostDatum.class);
    job.setMapperClass(UpdateHostDbMapper.class);
    job.setReducerClass(UpdateHostDbReducer.class);
    job.setSpeculativeExecution(false);

    conf.setBoolean("mapreduce.fileoutputcommitter.marksuccessfuljobs", false);
    conf.setBoolean(HOSTDB_CHECK_FAILED, checkFailed);
    conf.setBoolean(HOSTDB_CHECK_NEW, checkNew);
    conf.setBoolean(HOSTDB_CHECK_KNOWN, checkKnown);
    conf.setBoolean(HOSTDB_FORCE_CHECK, force);
    conf.setBoolean(HOSTDB_URL_FILTERING, filter);
    conf.setBoolean(HOSTDB_URL_NORMALIZING, normalize);
    conf.setLong(HOSTDB_URL_LIMIT, urlLimit);
    conf.setClassLoader(Thread.currentThread().getContextClassLoader());
    
    try {
      boolean success = job.waitForCompletion(true);
      if (!success) {
        String message = NutchJob.getJobFailureLogMessage("UpdateHostDb", job);
        LOG.error(message);
        NutchJob.cleanupAfterFailure(tempHostDb, lock, fs);
        throw new RuntimeException(message);
      }

      FSUtils.replace(fs, old, current, true);
      FSUtils.replace(fs, current, tempHostDb, true);

      if (!preserveBackup && fs.exists(old)) fs.delete(old, true);
    } catch (Exception e) {
      LOG.error("UpdateHostDb job failed: {}", e.getMessage());
      NutchJob.cleanupAfterFailure(tempHostDb, lock, fs);
      throw e;
    }

    LockUtil.removeLockFile(fs, lock);
    stopWatch.stop();
    LOG.info("UpdateHostDb: finished, elapsed: {} ms", stopWatch.getTime(
        TimeUnit.MILLISECONDS));
  }

  public static void main(String args[]) throws Exception {
    int res = ToolRunner.run(NutchConfiguration.create(), new UpdateHostDb(), args);
    System.exit(res);
  }

  @Override
  public int run(String[] args) throws Exception {
    if (args.length < 2) {
      System.err.println("Usage: UpdateHostDb -hostdb <hostdb> " +
        "[-tophosts <tophosts>] [-crawldb <crawldb>] [-checkAll] [-checkFailed]" +
        " [-checkNew] [-checkKnown] [-force] [-filter] [-normalize] [-urlLimit <N>]");
      return -1;
    }

    Path hostDb = null;
    Path crawlDb = null;
    Path topHosts = null;

    boolean checkFailed = false;
    boolean checkNew = false;
    boolean checkKnown = false;
    boolean force = false;
    boolean filter = false;
    boolean normalize = false;
    long urlLimit = -1l;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-hostdb")) {
        hostDb = new Path(args[i + 1]);
        LOG.info("UpdateHostDb: hostdb: {}", hostDb);
        i++;
      }
      if (args[i].equals("-crawldb")) {
        crawlDb = new Path(args[i + 1]);
        LOG.info("UpdateHostDb: crawldb: {}", crawlDb);
        i++;
      }
      if (args[i].equals("-tophosts")) {
        topHosts = new Path(args[i + 1]);
        LOG.info("UpdateHostDb: tophosts: {}", topHosts);
        i++;
      }

      if (args[i].equals("-checkFailed")) {
        LOG.info("UpdateHostDb: checking failed hosts");
        checkFailed = true;
      }
      if (args[i].equals("-checkNew")) {
        LOG.info("UpdateHostDb: checking new hosts");
        checkNew = true;
      }
      if (args[i].equals("-checkKnown")) {
        LOG.info("UpdateHostDb: checking known hosts");
        checkKnown = true;
      }
      if (args[i].equals("-checkAll")) {
        LOG.info("UpdateHostDb: checking all hosts");
        checkFailed = true;
        checkNew = true;
        checkKnown = true;
      }
      if (args[i].equals("-force")) {
        LOG.info("UpdateHostDb: forced check");
        force = true;
      }
      if (args[i].equals("-filter")) {
        LOG.info("UpdateHostDb: filtering enabled");
        filter = true;
      }
      if (args[i].equals("-normalize")) {
        LOG.info("UpdateHostDb: normalizing enabled");
        normalize = true;
      }
      if (args[i].equals("-urlLimit")) {
        urlLimit = Long.valueOf(args[i + 1]);
        LOG.info("UpdateHostDb: URL limit set to {}", urlLimit);
        i++;
      }
    }

    if (hostDb == null) {
      System.err.println("hostDb is mandatory");
      return -1;
    }

    try {
      updateHostDb(hostDb, crawlDb, topHosts, checkFailed, checkNew,
        checkKnown, force, filter, normalize, urlLimit);

      return 0;
    } catch (Exception e) {
      LOG.error("UpdateHostDb:", e);
      return -1;
    }
  }
}
