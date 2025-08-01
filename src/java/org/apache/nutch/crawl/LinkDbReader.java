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

import java.io.Closeable;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MapFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configuration;

import org.apache.nutch.util.AbstractChecker;
import org.apache.nutch.util.NutchConfiguration;
import org.apache.nutch.util.NutchJob;



/**
 * Read utility for the LinkDb.
 */
public class LinkDbReader extends AbstractChecker implements Closeable {
  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  private static final Partitioner<Text, Inlinks> PARTITIONER = new HashPartitioner<>();

  private Path directory;
  private MapFile.Reader[] readers;

  private long lastModified = 0;

  public LinkDbReader() {
    //default constructor
  }

  public LinkDbReader(Configuration conf, Path directory) throws Exception {
    setConf(conf);
    init(directory);
  }

  public void init(Path directory) throws Exception {
    this.directory = directory;
  }

  public void openReaders() throws IOException {
    Path linkDbPath = new Path(directory, LinkDb.CURRENT_NAME);

    FileStatus stat = linkDbPath.getFileSystem(getConf()).getFileStatus(directory);
    long lastModified = stat.getModificationTime();

    synchronized (this) {
      if (readers != null) {
        if (this.lastModified == lastModified) {
          // CrawlDB not modified, re-use readers
          return;
        } else {
          // CrawlDB modified, close and re-open readers
          close();
        }
      }

      this.lastModified = lastModified;
      readers = MapFileOutputFormat.getReaders(linkDbPath, getConf());
    }
  }

  public String[] getAnchors(Text url) throws IOException {
    Inlinks inlinks = getInlinks(url);
    if (inlinks == null)
      return null;
    return inlinks.getAnchors();
  }

  public Inlinks getInlinks(Text url) throws IOException {
    openReaders();

    return (Inlinks) MapFileOutputFormat.getEntry(readers, PARTITIONER, url,
        new Inlinks());
  }

  @Override
  public void close() throws IOException {
    if (readers != null) {
      for (int i = 0; i < readers.length; i++) {
        readers[i].close();
      }
    }
  }
  
  public static class LinkDBDumpMapper extends Mapper<Text, Inlinks, Text, Inlinks> {
    Pattern pattern = null;
    Matcher matcher = null;
    
    @Override
    public void setup(Mapper<Text, Inlinks, Text, Inlinks>.Context context) {
      Configuration conf = context.getConfiguration();
      if (conf.get("linkdb.regex", null) != null) {
        pattern = Pattern.compile(conf.get("linkdb.regex"));
      }
    }

    @Override
    public void map(Text key, Inlinks value, Context context)
            throws IOException, InterruptedException {

      if (pattern != null) {
        matcher = pattern.matcher(key.toString());
        if (!matcher.matches()) {
          return;
        }
      }

      context.write(key, value);
    }
  }

  public void processDumpJob(String linkdb, String output, String regex) 
    throws IOException, InterruptedException, ClassNotFoundException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    LOG.info("LinkDb dump: starting");
    LOG.info("LinkDb dump: db: {}", linkdb);

    Path outFolder = new Path(output);

    Job job = Job.getInstance(getConf(), "Nutch LinkDbReader: " + linkdb);
    job.setJarByClass(LinkDbReader.class);
    
    Configuration conf = job.getConfiguration();
 
    if (regex != null) {
      conf.set("linkdb.regex", regex);
      job.setMapperClass(LinkDBDumpMapper.class);
    }

    FileInputFormat.addInputPath(job, new Path(linkdb, LinkDb.CURRENT_NAME));
    job.setInputFormatClass(SequenceFileInputFormat.class);

    FileOutputFormat.setOutputPath(job, outFolder);
    job.setOutputFormatClass(TextOutputFormat.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Inlinks.class);

    try{
      boolean success = job.waitForCompletion(true);
      if (!success) {
        String message = NutchJob.getJobFailureLogMessage("LinkDbRead", job);
        LOG.error(message);
        throw new RuntimeException(message);
      }
    } catch (IOException | InterruptedException | ClassNotFoundException e){
      LOG.error(StringUtils.stringifyException(e));
      throw e;
    }

    stopWatch.stop();
    LOG.info("LinkDb dump: finished, elapsed: {} ms", stopWatch.getTime(
        TimeUnit.MILLISECONDS));
  }

  @Override
  protected int process(String line, StringBuilder output) throws Exception {

    Inlinks links = getInlinks(new Text(line));
    if (links == null) {
      output.append(" - no link information.");
    } else {
      Iterator<Inlink> it = links.iterator();
      while (it.hasNext()) {
        output.append(it.next().toString());
        output.append("\n");
      }
    }
    output.append("\n");
    return 0;
  }

  public static void main(String[] args) throws Exception {
    int res = ToolRunner.run(NutchConfiguration.create(), new LinkDbReader(),
        args);
    System.exit(res);
  }

  @Override
  public int run(String[] args) throws Exception {
    if (args.length < 2) {
      System.err
          .println("Usage: LinkDbReader <linkdb> (-dump <out_dir> [-regex <regex>] | -url <url> | -listen <port>)");
      System.err
          .println("\t-dump <out_dir>\tdump whole link db to a text file in <out_dir>");
      System.err
          .println("\t\t-regex <regex>\trestrict to url's matching expression");
      System.err
          .println("\t-url <url>\tprint information about <url> to System.out");
      System.err
          .println("\t-listen <port> [-keepClientCnxOpen]\tlisten on <port> for URLs and");
      System.err
          .println("\t\t\tsend information about <url> back");
      return -1;
    }

    int numConsumed = 0;

    try {
      for (int i = 1; i < args.length; i++) {
        if (args[i].equals("-dump")) {
          String regex = null;
          for (int j = i+1; j < args.length; j++) {
            if (args[i].equals("-regex")) {
              regex = args[++j];
            }
          }
          processDumpJob(args[0], args[i+1], regex);
          return 0;
        } else if (args[i].equals("-url")) {
          init(new Path(args[0]));
          return processSingle(args[++i]);
        } else if ((numConsumed = super.parseArgs(args, i)) > 0) {
          init(new Path(args[0]));
          i += numConsumed - 1;
        } else {
          System.err.println("Error: wrong argument " + args[1]);
          return -1;
        }
      }
    } catch (Exception e) {
      LOG.error("LinkDbReader:", e);
      return -1;
    }

    if (numConsumed > 0) {
      // Start listening
      return super.run();
    }
    return 0;
  }
}
