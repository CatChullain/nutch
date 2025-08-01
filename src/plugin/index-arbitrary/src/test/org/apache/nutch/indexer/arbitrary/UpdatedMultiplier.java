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
package org.apache.nutch.indexer.arbitrary;

import org.apache.nutch.parse.Parse;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.crawl.Inlinks;
import org.apache.hadoop.io.Text;

import java.io.PrintStream;

public class UpdatedMultiplier {
  private float product = 1;
  private static PrintStream err = System.err;
  private static PrintStream out = System.out;


  public UpdatedMultiplier(String args[]) {
    super();
  }

  public UpdatedMultiplier(String args[],
                          NutchDocument doc,
                          Parse parse,
                          Text url,
                          CrawlDatum datum,
                          Inlinks inlinks) {
    super();
  }

  public String getProduct(String args[]) {
    int i = args.length - 1;
    try {
      while (i >= 0) {
        product = product * Float.parseFloat(args[i]);
        i--;
      }
    } catch (NumberFormatException nfe) {
      err.println("NumberFormatException while trying to parse " + String.valueOf(args[i]));
    }
    return String.valueOf(product);
  }
}
