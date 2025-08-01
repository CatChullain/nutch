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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.MapFileOutputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.crawl.NutchWritable;
import org.apache.nutch.metadata.Metadata;
import org.apache.nutch.parse.ParseData;
import org.apache.nutch.parse.ParseText;
import org.apache.nutch.protocol.Content;
import org.apache.nutch.util.HadoopFSUtil;
import org.apache.nutch.util.NutchConfiguration;
import org.apache.nutch.util.NutchJob;
import org.apache.nutch.util.SegmentReaderUtil;

/** Dump the content of a segment. */
public class SegmentReader extends Configured implements Tool {

  private static final Random RANDOM = new Random();

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  private boolean co = true;
  private boolean fe = true;
  private boolean ge = true;
  private boolean pa = true;
  private boolean pd = true;
  private boolean pt = true;
  private boolean recodeContent = false;

  public static class InputCompatMapper extends
      Mapper<WritableComparable<?>, Writable, Text, NutchWritable> {

    private Text newKey = new Text();

    @Override
    public void map(WritableComparable<?> key, Writable value,
        Context context) throws IOException, InterruptedException {
      // convert on the fly from old formats with UTF8 keys.
      // UTF8 deprecated and replaced by Text.
      if (key instanceof Text) {
        newKey.set(key.toString());
        key = newKey;
      }
      context.write((Text) key, new NutchWritable(value));
    }

  }

  /** Implements a text output format */
  public static class TextOutputFormat extends
      FileOutputFormat<WritableComparable<?>, Writable> {

    @Override
    public RecordWriter<WritableComparable<?>, Writable> getRecordWriter(
        TaskAttemptContext context) throws IOException, InterruptedException {
      String name = getUniqueFile(context, "part", "");
      Path dir = FileOutputFormat.getOutputPath(context);
      FileSystem fs = dir.getFileSystem(context.getConfiguration());

      final Path segmentDumpFile = new Path(
          FileOutputFormat.getOutputPath(context), name);

      // Get the old copy out of the way
      if (fs.exists(segmentDumpFile))
        fs.delete(segmentDumpFile, true);

      final PrintStream printStream = new PrintStream(
          fs.create(segmentDumpFile), false, StandardCharsets.UTF_8.name());
      return new RecordWriter<WritableComparable<?>, Writable>() {

        @Override
        public synchronized void write(WritableComparable<?> key, Writable value)
            throws IOException {
          printStream.println(value);
        }

        @Override
        public synchronized void close(TaskAttemptContext context) throws IOException {
          printStream.close();
        }
      };
    }
  }

  public static class InputCompatReducer extends
      Reducer<Text, NutchWritable, Text, Text> {

    private long recNo = 0L;
    private boolean recodeContent = false;

    @Override
    public void setup(Context context) {
      recodeContent = context.getConfiguration()
          .getBoolean("segment.reader.content.recode", false);
    }

    @Override
    public void reduce(Text key, Iterable<NutchWritable> values,
        Context context) throws IOException, InterruptedException {
      StringBuffer dump = new StringBuffer();

      dump.append("\nRecno:: ").append(recNo++).append("\n");
      dump.append("URL:: " + key.toString() + "\n");
      Content content = null;
      // fall-back encoding for content of unparsed documents
      Charset charset = StandardCharsets.UTF_8;
      for (NutchWritable val : values) {
        Writable value = val.get(); // unwrap
        if (value instanceof CrawlDatum) {
          dump.append("\nCrawlDatum::\n").append(((CrawlDatum) value).toString());
        } else if (value instanceof Content) {
          if (recodeContent) {
            // output recoded content later when charset is extracted from HTML
            // metadata hold in ParseData
            content = (Content) value;
          } else {
            dump.append("\nContent::\n").append(((Content) value).toString());
          }
        } else if (value instanceof ParseData) {
          dump.append("\nParseData::\n").append(((ParseData) value).toString());
          if (recodeContent) {
            charset = getCharset(((ParseData) value).getParseMeta());
          }
        } else if (value instanceof ParseText) {
          dump.append("\nParseText::\n").append(((ParseText) value).toString());
        } else {
          LOG.warn("Unrecognized type: {}", value.getClass());
        }
      }
      if (recodeContent && content != null) {
        dump.append("\nContent::\n").append(content.toString(charset));
      }
      context.write(key, new Text(dump.toString()));
    }
  }

  private static boolean segmSubdirExists(Configuration conf, Path segment,
      String subDir) throws IOException {
    Path segmSubPath = new Path(segment, subDir);
    boolean exists = segmSubPath.getFileSystem(conf).exists(segmSubPath);
    if (!exists) {
      LOG.warn("Segment subdirectory {} does not exist in {}!", subDir,
          segment);
    }
    return exists;
  }

  private static void addSegmSubDirIfExists(List<Path> inputDirs, Configuration conf,
      Path segment, String subDir) throws IOException {
    Path segmSubPath = new Path(segment, subDir);
    if (segmSubPath.getFileSystem(conf).exists(segmSubPath)) {
      inputDirs.add(segmSubPath);
    } else {
      LOG.warn("Segment subdirectory {} does not exist in {} - skipping!", subDir,
          segment);
    }
  }

  public void dump(Path segment, Path output) throws IOException,
      InterruptedException, ClassNotFoundException {

    LOG.info("SegmentReader: dump segment: {}", segment);

    Job job = Job.getInstance(getConf(), "Nutch SegmentReader: " + segment);
    Configuration conf = job.getConfiguration();

    List<Path> inputDirs = new ArrayList<>();
    if (ge) {
      addSegmSubDirIfExists(inputDirs, conf, segment,
          CrawlDatum.GENERATE_DIR_NAME);
    }
    if (fe) {
      addSegmSubDirIfExists(inputDirs, conf, segment,
          CrawlDatum.FETCH_DIR_NAME);
    }
    if (pa) {
      addSegmSubDirIfExists(inputDirs, conf, segment,
          CrawlDatum.PARSE_DIR_NAME);
    }
    if (co) {
      addSegmSubDirIfExists(inputDirs, conf, segment, Content.DIR_NAME);
    }
    if (pd) {
      addSegmSubDirIfExists(inputDirs, conf, segment, ParseData.DIR_NAME);
    }
    if (pt) {
      addSegmSubDirIfExists(inputDirs, conf, segment, ParseText.DIR_NAME);
    }
    if (inputDirs.isEmpty()) {
      String msg = "No segment subdirectories defined as input";
      LOG.error(msg);
      throw new RuntimeException(msg);
    }
    for (Path p : inputDirs) {
      FileInputFormat.addInputPath(job, p);
    }

    job.setInputFormatClass(SequenceFileInputFormat.class);
    job.setMapperClass(InputCompatMapper.class);
    job.setReducerClass(InputCompatReducer.class);
    job.setJarByClass(SegmentReader.class);

    Path tempDir = new Path(conf.get("hadoop.tmp.dir", "/tmp") + "/segread-"
        + RANDOM.nextInt());
    FileSystem fs = tempDir.getFileSystem(conf);
    fs.delete(tempDir, true);

    FileOutputFormat.setOutputPath(job, tempDir);
    job.setOutputFormatClass(TextOutputFormat.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(NutchWritable.class);

    try {
      boolean success = job.waitForCompletion(true);
      if (!success) {
        String message = NutchJob.getJobFailureLogMessage("SegmentReader", job);
        LOG.error(message);
        throw new RuntimeException(message);
      }
    } catch (IOException | InterruptedException | ClassNotFoundException e ){
      LOG.error(StringUtils.stringifyException(e));
      throw e;
    }

    // concatenate the output
    Path dumpFile = new Path(output, conf.get("segment.dump.dir", "dump"));
    FileSystem outFs = dumpFile.getFileSystem(conf);

    // remove the old file
    outFs.delete(dumpFile, true);
    FileStatus[] fstats = fs.listStatus(tempDir,
        HadoopFSUtil.getPassAllFilter());
    Path[] files = HadoopFSUtil.getPaths(fstats);

    int currentRecordNumber = 0;
    if (files.length > 0) {
      try (PrintWriter writer = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(outFs.create(dumpFile),
              StandardCharsets.UTF_8)))) {

        for (int i = 0; i < files.length; i++) {
          Path partFile = files[i];
          try {
            currentRecordNumber = append(fs, conf, partFile, writer,
                currentRecordNumber);
          } catch (IOException exception) {
            LOG.warn("Couldn't copy the content of {} into {}",
                partFile.toString(), dumpFile.toString());
            LOG.warn(exception.getMessage());
          }
        }
      }
    }
    fs.delete(tempDir, true);
    LOG.info("SegmentReader: done");
  }

  /** Appends two files and updates the Recno counter */
  private int append(FileSystem fs, Configuration conf, Path src,
      PrintWriter writer, int currentRecordNumber) throws IOException {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(fs.open(src), StandardCharsets.UTF_8))) {
      String line = reader.readLine();
      while (line != null) {
        if (line.startsWith("Recno:: ")) {
          line = "Recno:: " + currentRecordNumber++;
        }
        writer.println(line);
        line = reader.readLine();
      }
      return currentRecordNumber;
    }
  }

  private static final String[][] keys = new String[][] {
      { "co", "Content::\n" }, { "ge", "Crawl Generate::\n" },
      { "fe", "Crawl Fetch::\n" }, { "pa", "Crawl Parse::\n" },
      { "pd", "ParseData::\n" }, { "pt", "ParseText::\n" } };

  public void get(final Path segment, final Text key, Writer writer,
      final Map<String, List<Writable>> results) throws Exception {
    LOG.info("SegmentReader: get '{}'", key);
    ArrayList<Thread> threads = new ArrayList<>();
    if (co && segmSubdirExists(getConf(), segment, Content.DIR_NAME))
      threads.add(new Thread() {
        @Override
        public void run() {
          try {
            List<Writable> res = getMapRecords(new Path(segment,
                Content.DIR_NAME), key);
            results.put("co", res);
          } catch (Exception e) {
            LOG.error("Exception:", e);
          }
        }
      });
    if (fe && segmSubdirExists(getConf(), segment, CrawlDatum.FETCH_DIR_NAME))
      threads.add(new Thread() {
        @Override
        public void run() {
          try {
            List<Writable> res = getMapRecords(new Path(segment,
                CrawlDatum.FETCH_DIR_NAME), key);
            results.put("fe", res);
          } catch (Exception e) {
            LOG.error("Exception:", e);
          }
        }
      });
    if (ge
        && segmSubdirExists(getConf(), segment, CrawlDatum.GENERATE_DIR_NAME))
      threads.add(new Thread() {
        @Override
        public void run() {
          try {
            List<Writable> res = getSeqRecords(new Path(segment,
                CrawlDatum.GENERATE_DIR_NAME), key);
            results.put("ge", res);
          } catch (Exception e) {
            LOG.error("Exception:", e);
          }
        }
      });
    if (pa && segmSubdirExists(getConf(), segment, CrawlDatum.PARSE_DIR_NAME))
      threads.add(new Thread() {
        @Override
        public void run() {
          try {
            List<Writable> res = getSeqRecords(new Path(segment,
                CrawlDatum.PARSE_DIR_NAME), key);
            results.put("pa", res);
          } catch (Exception e) {
            LOG.error("Exception:", e);
          }
        }
      });
    if (pd && segmSubdirExists(getConf(), segment, ParseData.DIR_NAME))
      threads.add(new Thread() {
        @Override
        public void run() {
          try {
            List<Writable> res = getMapRecords(new Path(segment,
                ParseData.DIR_NAME), key);
            results.put("pd", res);
          } catch (Exception e) {
            LOG.error("Exception:", e);
          }
        }
      });
    if (pt && segmSubdirExists(getConf(), segment, ParseText.DIR_NAME))
      threads.add(new Thread() {
        @Override
        public void run() {
          try {
            List<Writable> res = getMapRecords(new Path(segment,
                ParseText.DIR_NAME), key);
            results.put("pt", res);
          } catch (Exception e) {
            LOG.error("Exception:", e);
          }
        }
      });
    Iterator<Thread> it = threads.iterator();
    if (!it.hasNext()) {
      LOG.error("No segment subdirectories specified as input!");
      return;
    }
    while (it.hasNext())
      it.next().start();
    int cnt;
    do {
      cnt = 0;
      try {
        Thread.sleep(5000);
      } catch (Exception e) {
      }
      ;
      it = threads.iterator();
      while (it.hasNext()) {
        if (it.next().isAlive())
          cnt++;
      }
      if (cnt > 0) {
        LOG.debug("({} to retrieve)", cnt);
      }
    } while (cnt > 0);
    for (int i = 0; i < keys.length; i++) {
      List<Writable> res = results.get(keys[i][0]);
      if (res != null && res.size() > 0) {
        for (int k = 0; k < res.size(); k++) {
          writer.write(keys[i][1]);
          if (recodeContent && keys[i][0].equals("co")) {
            Charset charset = getCharset(((ParseData) results.get("pd").get(k)).getParseMeta());
            writer.write(((Content) res.get(k)).toString(charset));
          } else {
            writer.write(res.get(k).toString());
          }
          writer.write('\n');
        }
      }
      writer.flush();
    }
  }

  private List<Writable> getMapRecords(Path dir, Text key) throws Exception {
    MapFile.Reader[] readers = MapFileOutputFormat.getReaders(dir,
        getConf());
    ArrayList<Writable> res = new ArrayList<>();
    Class<?> keyClass = readers[0].getKeyClass();
    Class<?> valueClass = readers[0].getValueClass();
    if (!keyClass.getName().equals("org.apache.hadoop.io.Text"))
      throw new IOException("Incompatible key (" + keyClass.getName() + ")");
    Writable value = (Writable) valueClass.getConstructor().newInstance();
    // we don't know the partitioning schema
    for (int i = 0; i < readers.length; i++) {
      if (readers[i].get(key, value) != null) {
        res.add(value);
        value = (Writable) valueClass.getConstructor().newInstance();
        Text aKey = (Text) keyClass.getConstructor().newInstance();
        while (readers[i].next(aKey, value) && aKey.equals(key)) {
          res.add(value);
          value = (Writable) valueClass.getConstructor().newInstance();
        }
      }
      readers[i].close();
    }
    return res;
  }

  private List<Writable> getSeqRecords(Path dir, Text key) throws Exception {
    SequenceFile.Reader[] readers = org.apache.hadoop.mapred.SequenceFileOutputFormat
        .getReaders(getConf(), dir);
    ArrayList<Writable> res = new ArrayList<>();
    Class<?> keyClass = readers[0].getKeyClass();
    Class<?> valueClass = readers[0].getValueClass();
    if (!keyClass.getName().equals("org.apache.hadoop.io.Text"))
      throw new IOException("Incompatible key (" + keyClass.getName() + ")");
    WritableComparable<?> aKey = (WritableComparable<?>) keyClass.getConstructor().newInstance();
    Writable value = (Writable) valueClass.getConstructor().newInstance();
    for (int i = 0; i < readers.length; i++) {
      while (readers[i].next(aKey, value)) {
        if (aKey.equals(key)) {
          res.add(value);
          value = (Writable) valueClass.getConstructor().newInstance();
        }
      }
      readers[i].close();
    }
    return res;
  }

  /**
   * Try to get HTML encoding from parse metadata. Try
   * {@link Metadata#CHAR_ENCODING_FOR_CONVERSION}, then
   * {@link Metadata#CONTENT_ENCODING} then fallback
   * {@link java.nio.charset.StandardCharsets#UTF_8}
   * @param parseMeta a populated {@link Metadata}
   * @return {@link Charset}
   */
  public static Charset getCharset(Metadata parseMeta) {
    Charset cs = StandardCharsets.UTF_8;
    String charset = parseMeta.get(Metadata.CHAR_ENCODING_FOR_CONVERSION);
    if (charset == null) {
      // fall-back: "Content-Encoding" (set by parse-tika)
      charset = parseMeta.get(Metadata.CONTENT_ENCODING);
    }
    try {
      cs = Charset.forName(charset);
    } catch (Exception e) {
      // fall-back to utf-8
    }
    return cs;
  }

  public static class SegmentReaderStats {
    public long start = -1L;
    public long end = -1L;
    public long generated = -1L;
    public long fetched = -1L;
    public long fetchErrors = -1L;
    public long parsed = -1L;
    public long parseErrors = -1L;
  }

  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

  public void list(List<Path> dirs, Writer writer) throws Exception {
    writer
        .write("NAME\t\tGENERATED\tFETCHER START\t\tFETCHER END\t\tFETCHED\tPARSED\n");
    for (int i = 0; i < dirs.size(); i++) {
      Path dir = dirs.get(i);
      SegmentReaderStats stats = new SegmentReaderStats();
      getStats(dir, stats);
      writer.write(dir.getName() + "\t");
      if (stats.generated == -1)
        writer.write("?");
      else
        writer.write(stats.generated + "");
      writer.write("\t\t");
      if (stats.start == -1)
        writer.write("?\t");
      else
        writer.write(sdf.format(new Date(stats.start)));
      writer.write("\t");
      if (stats.end == -1)
        writer.write("?");
      else
        writer.write(sdf.format(new Date(stats.end)));
      writer.write("\t");
      if (stats.fetched == -1)
        writer.write("?");
      else
        writer.write(stats.fetched + "");
      writer.write("\t");
      if (stats.parsed == -1)
        writer.write("?");
      else
        writer.write(stats.parsed + "");
      writer.write("\n");
      writer.flush();
    }
  }

  public void getStats(Path segment, final SegmentReaderStats stats)
      throws Exception {
    long cnt = 0L;
    Text key = new Text();
    CrawlDatum val = new CrawlDatum();
    FileSystem fs = segment.getFileSystem(getConf());

    if (ge) {
      SequenceFile.Reader[] readers = SegmentReaderUtil.getReaders(
          new Path(segment, CrawlDatum.GENERATE_DIR_NAME), getConf());
      for (int i = 0; i < readers.length; i++) {
        while (readers[i].next(key, val))
          cnt++;
        readers[i].close();
      }
      stats.generated = cnt;
    }

    if (fe) {
      Path fetchDir = new Path(segment, CrawlDatum.FETCH_DIR_NAME);
      if (fs.exists(fetchDir) && fs.getFileStatus(fetchDir).isDirectory()) {
        cnt = 0L;
        long start = Long.MAX_VALUE;
        long end = Long.MIN_VALUE;
        CrawlDatum value = new CrawlDatum();
        MapFile.Reader[] mreaders = MapFileOutputFormat.getReaders(fetchDir,
            getConf());
        for (int i = 0; i < mreaders.length; i++) {
          while (mreaders[i].next(key, value)) {
            cnt++;
            if (value.getFetchTime() < start)
              start = value.getFetchTime();
            if (value.getFetchTime() > end)
              end = value.getFetchTime();
          }
          mreaders[i].close();
        }
        stats.start = start;
        stats.end = end;
        stats.fetched = cnt;
      }
    }

    if (pd) {
      Path parseDir = new Path(segment, ParseData.DIR_NAME);
      if (fs.exists(parseDir) && fs.getFileStatus(parseDir).isDirectory()) {
        cnt = 0L;
        long errors = 0L;
        ParseData value = new ParseData();
        MapFile.Reader[] mreaders = MapFileOutputFormat.getReaders(parseDir,
            getConf());
        for (int i = 0; i < mreaders.length; i++) {
          while (mreaders[i].next(key, value)) {
            cnt++;
            if (!value.getStatus().isSuccess())
              errors++;
          }
          mreaders[i].close();
        }
        stats.parsed = cnt;
        stats.parseErrors = errors;
      }
    }
  }

  private static final int MODE_DUMP = 0;

  private static final int MODE_LIST = 1;

  private static final int MODE_GET = 2;

  @Override
  public int run(String[] args) throws Exception {
    if (args.length < 2) {
      usage();
      return -1;
    }
    int mode = -1;
    if (args[0].equals("-dump"))
      mode = MODE_DUMP;
    else if (args[0].equals("-list"))
      mode = MODE_LIST;
    else if (args[0].equals("-get"))
      mode = MODE_GET;

    // collect general options
    for (int i = 1; i < args.length; i++) {
      if (args[i].equals("-nocontent")) {
        co = false;
        args[i] = null;
      } else if (args[i].equals("-nofetch")) {
        fe = false;
        args[i] = null;
      } else if (args[i].equals("-nogenerate")) {
        ge = false;
        args[i] = null;
      } else if (args[i].equals("-noparse")) {
        pa = false;
        args[i] = null;
      } else if (args[i].equals("-noparsedata")) {
        pd = false;
        args[i] = null;
      } else if (args[i].equals("-noparsetext")) {
        pt = false;
        args[i] = null;
      } else if (args[i].equals("-recode")) {
        recodeContent = true;
        args[i] = null;
      }
    }

    if (recodeContent) {
      LOG.info("Recoding charset of HTML content");
      getConf().setBoolean("segment.reader.content.recode", true);
    }

    // collect required args
    switch (mode) {
    case MODE_DUMP:

      String input = args[1];
      if (input == null) {
        System.err.println("Missing required argument: <segment_dir>");
        usage();
        return -1;
      }
      String output = args.length > 2 ? args[2] : null;
      if (output == null) {
        System.err.println("Missing required argument: <output>");
        usage();
        return -1;
      }
      dump(new Path(input), new Path(output));
      return 0;
    case MODE_LIST:
      ArrayList<Path> dirs = new ArrayList<>();
      for (int i = 1; i < args.length; i++) {
        if (args[i] == null)
          continue;
        if (args[i].equals("-dir")) {
          Path dir = new Path(args[++i]);
          FileSystem fs = dir.getFileSystem(getConf());
          FileStatus[] fstats = fs.listStatus(dir,
              HadoopFSUtil.getPassDirectoriesFilter(fs));
          Path[] files = HadoopFSUtil.getPaths(fstats);
          if (files != null && files.length > 0) {
            dirs.addAll(Arrays.asList(files));
          }
        } else
          dirs.add(new Path(args[i]));
      }
      list(dirs, new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
      return 0;
    case MODE_GET:
      input = args[1];
      if (input == null) {
        System.err.println("Missing required argument: <segment_dir>");
        usage();
        return -1;
      }
      String key = args.length > 2 ? args[2] : null;
      if (key == null) {
        System.err.println("Missing required argument: <keyValue>");
        usage();
        return -1;
      }
      get(new Path(input), new Text(key),
          new OutputStreamWriter(System.out, StandardCharsets.UTF_8),
          new HashMap<>());
      return 0;
    default:
      System.err.println("Invalid operation: " + args[0]);
      usage();
      return -1;
    }
  }

  private static void usage() {
    System.err
        .println("Usage: SegmentReader (-dump ... | -list ... | -get ...) [general options]\n");
    System.err.println("* General options:");
    System.err.println("\t-nocontent\tignore content directory");
    System.err.println("\t-nofetch\tignore crawl_fetch directory");
    System.err.println("\t-nogenerate\tignore crawl_generate directory");
    System.err.println("\t-noparse\tignore crawl_parse directory");
    System.err.println("\t-noparsedata\tignore parse_data directory");
    System.err.println("\t-noparsetext\tignore parse_text directory");
    System.err.println("\t-recode \ttry to recode HTML content from the page's\n"
        + "\t        \toriginal charset to UTF-8\n");
    System.err.println();
    System.err
        .println("* SegmentReader -dump <segment_dir> <output> [general options]");
    System.err
        .println("  Dumps content of a <segment_dir> as a text file to <output>.\n");
    System.err.println("\t<segment_dir>\tname of the segment directory.");
    System.err
        .println("\t<output>\tname of the (non-existent) output directory.");
    System.err.println();
    System.err
        .println("* SegmentReader -list (<segment_dir1> ... | -dir <segments>) [general options]");
    System.err
        .println("  List a synopsis of segments in specified directories, or all segments in");
    System.err
        .println("  a directory <segments>, and print it on System.out\n");
    System.err
        .println("\t<segment_dir1> ...\tlist of segment directories to process");
    System.err
        .println("\t-dir <segments>\t\tdirectory that contains multiple segments");
    System.err.println();
    System.err
        .println("* SegmentReader -get <segment_dir> <keyValue> [general options]");
    System.err
        .println("  Get a specified record from a segment, and print it on System.out.\n");
    System.err.println("\t<segment_dir>\tname of the segment directory.");
    System.err.println("\t<keyValue>\tvalue of the key (url).");
    System.err
        .println("\t\tNote: put double-quotes around strings with spaces.");
  }

  public static void main(String[] args) throws Exception {
    int result = ToolRunner.run(NutchConfiguration.create(),
        new SegmentReader(), args);
    System.exit(result);
  }
}
