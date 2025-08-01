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
package org.apache.nutch.indexwriter.solr;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.indexer.IndexWriter;
import org.apache.nutch.indexer.IndexWriterParams;
import org.apache.nutch.indexer.IndexerMapReduce;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.nutch.indexer.NutchField;
import org.apache.nutch.util.StringUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrIndexWriter implements IndexWriter {

  private static final Logger LOG = LoggerFactory
      .getLogger(MethodHandles.lookup().lookupClass());

  private List<SolrClient> solrClients;
  private ModifiableSolrParams params;

  private Configuration config;

  private final List<SolrInputDocument> inputDocs = new ArrayList<>();

  private final List<String> deleteIds = new ArrayList<>();

  private String type;
  private String[] urls;
  private String collection;

  private int batchSize;
  private int numDeletes = 0;
  private int totalAdds = 0;
  private int totalDeletes = 0;
  private boolean delete = false;
  private String weightField;

  private boolean auth;
  private String username;
  private String password;
  private String authHeaderName;
  private String authHeaderValue;

  @Override
  public void open(Configuration conf, String name) {
    // Implementation not required
  }

  /**
   * Initializes the internal variables from a given index writer configuration.
   *
   * @param parameters
   *          Params from the index writer configuration.
   */
  @Override
  public void open(IndexWriterParams parameters) {
    this.type = parameters.get(SolrConstants.SERVER_TYPE, "http");
    this.urls = parameters.getStrings(SolrConstants.SERVER_URLS);
    this.collection = parameters.get(SolrConstants.COLLECTION);

    if (urls == null) {
      String message = "Missing SOLR URL.\n" + describe();
      LOG.error(message);
      throw new RuntimeException(message);
    }

    this.auth = parameters.getBoolean(SolrConstants.USE_AUTH, false);
    this.username = parameters.get(SolrConstants.USERNAME);
    this.password = parameters.get(SolrConstants.PASSWORD);
    this.authHeaderName = parameters.get(SolrConstants.AUTH_HEADER_NAME, "");
    this.authHeaderValue = parameters.get(SolrConstants.AUTH_HEADER_VALUE, "");

    this.solrClients = new ArrayList<>();

    switch (type) {
    case "http":
      for (String url : urls) {
        if (this.auth && !StringUtil.isEmpty(this.authHeaderName)
            && !StringUtil.isEmpty(this.authHeaderValue)) {
          solrClients.add(SolrUtils.getHttpSolrClientHeaderAuthorization(url,
              this.authHeaderName, this.authHeaderValue));
        } else if (this.auth && !StringUtil.isEmpty(this.username)
            && !StringUtil.isEmpty(this.password)) {
          solrClients.add(
              SolrUtils.getHttpSolrClient(url, this.username, this.password));
        } else {
          solrClients.add(SolrUtils.getHttpSolrClient(url));
        }
      }
      break;
    case "cloud":
      CloudSolrClient sc;
      if (this.auth && !StringUtil.isEmpty(this.authHeaderName)
          && !StringUtil.isEmpty(this.authHeaderValue)) {
        sc = SolrUtils.getCloudSolrClientHeaderAuthorization(
            Arrays.asList(urls), this.authHeaderName, this.authHeaderValue);
      } else if (this.auth && !StringUtil.isEmpty(this.username)
          && !StringUtil.isEmpty(this.password)) {
        sc = SolrUtils.getCloudSolrClient(Arrays.asList(urls), this.username,
            this.password);
      } else {
        sc = SolrUtils.getCloudSolrClient(Arrays.asList(urls));
      }
      sc.setDefaultCollection(this.collection);
      solrClients.add(sc);
      break;
    case "concurrent":
      // TODO: 1/08/17 Implement this
      throw new UnsupportedOperationException(
          "The type \"concurrent\" is not yet supported.");
    case "lb":
      // TODO: 1/08/17 Implement this
      throw new UnsupportedOperationException(
          "The type \"lb\" is not yet supported.");
    default:
      throw new IllegalArgumentException(
          "The type \"" + type + "\" is not supported.");
    }

    init(parameters);
  }

  private void init(IndexWriterParams properties) {
    batchSize = properties.getInt(SolrConstants.COMMIT_SIZE, 1000);
    delete = config.getBoolean(IndexerMapReduce.INDEXER_DELETE, false);
    weightField = properties.get(SolrConstants.WEIGHT_FIELD, "");

    // parse optional params
    params = new ModifiableSolrParams();
    String paramString = config.get(IndexerMapReduce.INDEXER_PARAMS);
    if (paramString != null) {
      String[] values = paramString.split("&");
      for (String v : values) {
        String[] kv = v.split("=");
        if (kv.length < 2) {
          continue;
        }
        params.add(kv[0], kv[1]);
      }
    }
  }

  @Override
  public void delete(String key) throws IOException {
    // escape solr hash separator
    key = key.replaceAll("!", "\\!");

    if (delete) {
      deleteIds.add(key);
      totalDeletes++;
    }

    if (deleteIds.size() >= batchSize) {
      push();
    }

  }

  @Override
  public void update(NutchDocument doc) throws IOException {
    write(doc);
  }

  @Override
  public void write(NutchDocument doc) throws IOException {
    final SolrInputDocument inputDoc = new SolrInputDocument();

    for (final Entry<String, NutchField> e : doc) {
      for (final Object val : e.getValue().getValues()) {
        // normalise the string representation for a Date
        Object val2 = val;

        if (val instanceof Date) {
          val2 = DateTimeFormatter.ISO_INSTANT.format(((Date) val).toInstant());
        }

        if (e.getKey().equals("content") || e.getKey().equals("title")) {
          val2 = SolrUtils.stripNonCharCodepoints((String) val);
        }

        inputDoc.addField(e.getKey(), val2);
      }
    }

    if (!weightField.isEmpty()) {
      inputDoc.addField(weightField, doc.getWeight());
    }
    inputDocs.add(inputDoc);
    totalAdds++;

    if (inputDocs.size() + numDeletes >= batchSize) {
      push();
    }
  }

  @Override
  public void close() throws IOException {
    commit();

    for (SolrClient solrClient : solrClients) {
      solrClient.close();
    }
  }

  @Override
  public void commit() throws IOException {
    push();
    try {
      for (SolrClient solrClient : solrClients) {
        if (this.auth && !StringUtil.isEmpty(this.username)
            && !StringUtil.isEmpty(this.password)) {
          UpdateRequest req = new UpdateRequest();
          req.setAction(UpdateRequest.ACTION.COMMIT, true, true);
          req.setBasicAuthCredentials(this.username, this.password);
          solrClient.request(req);
        } else {
          solrClient.commit();
        }
      }
    } catch (final SolrServerException e) {
      LOG.error("Failed to commit solr connection: {}", e.getMessage());
    }
  }

  private void push() throws IOException {
    if (inputDocs.size() > 0) {
      try {
        LOG.info("Indexing {}/{} documents", inputDocs.size(), totalAdds);
        LOG.info("Deleting {} documents", numDeletes);
        numDeletes = 0;
        UpdateRequest req = new UpdateRequest();
        req.add(inputDocs);
        req.setAction(UpdateRequest.ACTION.OPTIMIZE, false, false);
        req.setParams(params);
        if (this.auth && !StringUtil.isEmpty(this.username)
            && !StringUtil.isEmpty(this.password)) {
          req.setBasicAuthCredentials(this.username, this.password);
        }
        for (SolrClient solrClient : solrClients) {
          solrClient.request(req);
        }
      } catch (final SolrServerException e) {
        throw makeIOException(e);
      }
      inputDocs.clear();
    }

    if (deleteIds.size() > 0) {
      try {
        LOG.info("SolrIndexer: deleting {}/{} documents", deleteIds.size(),
            totalDeletes);
        
        UpdateRequest req = new UpdateRequest();
        req.deleteById(deleteIds);
        req.setAction(UpdateRequest.ACTION.OPTIMIZE, false, false);
        req.setParams(params);
        if (this.auth && !StringUtil.isEmpty(this.username)
            && !StringUtil.isEmpty(this.password)) {
          req.setBasicAuthCredentials(this.username, this.password);
        }

        for (SolrClient solrClient : solrClients) {
          solrClient.request(req);
        }

      } catch (final SolrServerException e) {
        LOG.error("Error deleting: {}", deleteIds);
        throw makeIOException(e);
      }
      deleteIds.clear();
    }
  }

  private static IOException makeIOException(SolrServerException e) {
    return new IOException(e);
  }

  @Override
  public Configuration getConf() {
    return config;
  }

  @Override
  public void setConf(Configuration conf) {
    config = conf;
  }

  /**
   * Returns {@link Map} with the specific parameters the IndexWriter instance
   * can take.
   *
   * @return The values of each row. It must have the form <code>&#60;KEY,&#60;DESCRIPTION,VALUE&#62;&#62;</code>.
   */
  @Override
  public Map<String, Entry<String, Object>> describe() {
    Map<String, Entry<String, Object>> properties = new LinkedHashMap<>();

    properties.put(SolrConstants.SERVER_TYPE, new AbstractMap.SimpleEntry<>(
        "Specifies the SolrClient implementation to use. This is a string value of one of the following \"cloud\" or \"http\"."
            + " The values represent CloudSolrServer or HttpSolrServer respectively.",
        this.type));
    properties.put(SolrConstants.SERVER_URLS, new AbstractMap.SimpleEntry<>(
        "Defines the fully qualified URL of Solr into which data should be indexed. Multiple URL can be provided using comma as a delimiter."
            + " When the value of type property is cloud, the URL should not include any collections or cores; just the root Solr path.",
        this.urls == null ? "" : String.join(",", urls)));
    properties.put(SolrConstants.COLLECTION, new AbstractMap.SimpleEntry<>(
        "The collection used in requests. Only used when the value of type property is cloud.",
        this.collection));
    properties.put(SolrConstants.COMMIT_SIZE, new AbstractMap.SimpleEntry<>(
        "Defines the number of documents to send to Solr in a single update batch. "
            + "Decrease when handling very large documents to prevent Nutch from running out of memory.\n"
            + "Note: It does not explicitly trigger a server side commit.",
        this.batchSize));
    properties.put(SolrConstants.WEIGHT_FIELD, new AbstractMap.SimpleEntry<>(
        "Field's name where the weight of the documents will be written. If it is empty no field will be used.",
        this.weightField));
    properties.put(SolrConstants.USE_AUTH, new AbstractMap.SimpleEntry<>(
        "Whether to enable HTTP basic authentication for communicating with Solr. Use the username and password properties to configure your credentials.",
        this.auth));
    properties.put(SolrConstants.AUTH_HEADER_NAME, new AbstractMap.SimpleEntry<>(
        "The authentication header content name.", this.authHeaderName));
    properties.put(SolrConstants.AUTH_HEADER_VALUE, new AbstractMap.SimpleEntry<>(
        "The authentication header content value.", StringUtil.mask(this.authHeaderValue)));
    properties.put(SolrConstants.USERNAME, new AbstractMap.SimpleEntry<>(
        "The username of Solr server.", this.username));
    properties.put(SolrConstants.PASSWORD, new AbstractMap.SimpleEntry<>(
        "The password of Solr server.", StringUtil.mask(this.password)));

    return properties;
  }
}
