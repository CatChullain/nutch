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
package org.apache.nutch.net.urlnormalizer.basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.net.URLNormalizers;
import org.apache.nutch.util.NutchConfiguration;
import org.junit.Assert;
import org.junit.Test;

/** Unit tests for BasicURLNormalizer. */
public class TestBasicURLNormalizer {
  private BasicURLNormalizer normalizer;

  private Configuration conf;

  public TestBasicURLNormalizer() {
    normalizer = new BasicURLNormalizer();
    conf = NutchConfiguration.create();
    normalizer.setConf(conf);
  }
  
  @Test
  public void testNUTCH1098() throws Exception {
    // check that % encoding is normalized
    normalizeTest("http://foo.com/%66oo.html", "http://foo.com/foo.html");

    // check that % encoding works correctly at end of URL
    normalizeTest("http://foo.com/%66oo.htm%6c", "http://foo.com/foo.html");
    normalizeTest("http://foo.com/%66oo.ht%6dl", "http://foo.com/foo.html");

    // check that % decoder do not overlap strings
    normalizeTest("http://foo.com/%66oo.ht%6d%6c", "http://foo.com/foo.html");
    
    // check that % decoder leaves high bit chars alone
    normalizeTest("http://foo.com/%66oo.htm%C0", "http://foo.com/foo.htm%C0");

    // check that % decoder leaves control chars alone
    normalizeTest("http://foo.com/%66oo.htm%1A", "http://foo.com/foo.htm%1A");

    // check that % decoder converts to upper case letters
    normalizeTest("http://foo.com/%66oo.htm%c0", "http://foo.com/foo.htm%C0");

    // check that % decoder leaves encoded spaces alone
    normalizeTest("http://foo.com/you%20too.html", "http://foo.com/you%20too.html");

    // check that spaces are encoded into %20
    normalizeTest("http://foo.com/you too.html", "http://foo.com/you%20too.html");

    // check that encoded # are not decoded
    normalizeTest("http://foo.com/file.html%23cz", "http://foo.com/file.html%23cz");

    // check that encoded / are not decoded
    normalizeTest("http://foo.com/fast/dir%2fcz", "http://foo.com/fast/dir%2Fcz");

    // check that control chars are encoded
    normalizeTest("http://foo.com/\u001a!", "http://foo.com/%1A!");

    // check that control chars are always encoded into 2 digits
    normalizeTest("http://foo.com/\u0001!", "http://foo.com/%01!");

    // check encoding of Spanish chars
    normalizeTest("http://mydomain.com/en Espa\u00F1ol.aspx", "http://mydomain.com/en%20Espa%C3%B1ol.aspx");
  }
  
  @Test
  public void testNUTCH2064() throws Exception {
    // Ampersand and colon and other punctuation characters are not to be unescaped
    normalizeTest("http://x.com/s?q=a%26b&m=10", "http://x.com/s?q=a%26b&m=10");
    normalizeTest("http://x.com/show?http%3A%2F%2Fx.com%2Fb",
        "http://x.com/show?http%3A%2F%2Fx.com%2Fb");
    normalizeTest("http://google.com/search?q=c%2B%2B",
        "http://google.com/search?q=c%2B%2B");
    // do also not touch the query part which is application/x-www-form-urlencoded
    normalizeTest("http://x.com/s?q=a+b", "http://x.com/s?q=a+b");
    // and keep Internationalized domain names
    // http://bücher.de/ may be http://xn--bcher-kva.de/
    // but definitely not http://b%C3%BCcher.de/
    normalizeTest("http://b\u00fccher.de/", "http://b\u00fccher.de/");
    // test whether percent-encoding works together with other normalizations
    normalizeTest("http://x.com/./a/../%66.html", "http://x.com/f.html");
    // [ and ] need escaping as well
    normalizeTest("http://x.com/?x[y]=1", "http://x.com/?x%5By%5D=1");
    // boundary test for first character outside the ASCII range (U+0080)
    normalizeTest("http://x.com/foo\u0080", "http://x.com/foo%C2%80");
    normalizeTest("http://x.com/foo%c2%80", "http://x.com/foo%C2%80");
  }

  @Test
  public void testNormalizer() throws Exception {
    // check that leading and trailing spaces are removed
    normalizeTest(" http://foo.com/ ", "http://foo.com/");

    // check that protocol is lower cased
    normalizeTest("HTTP://foo.com/", "http://foo.com/");

    // check that host is lower cased
    normalizeTest("http://Foo.Com/index.html", "http://foo.com/index.html");
    normalizeTest("http://Foo.Com/index.html", "http://foo.com/index.html");

    // NUTCH-2824 unescape percent characters in host names
    normalizeTest("https://example%2Ecom/", "https://example.com/");
    normalizeTest("https://www.0251-sachverst%c3%a4ndiger.de/",
        "https://www.0251-sachverst\u00e4ndiger.de/");

    // check that port number is normalized
    normalizeTest("http://foo.com:80/index.html", "http://foo.com/index.html");
    normalizeTest("https://foo.com:443/index.html",
        "https://foo.com/index.html");
    normalizeTest("http://foo.com:81/", "http://foo.com:81/");
    // check that empty port is removed
    normalizeTest("http://example.com:/", "http://example.com/");
    normalizeTest("https://example.com:/foobar.html",
        "https://example.com/foobar.html");

    // check that null path is normalized
    normalizeTest("http://foo.com", "http://foo.com/");

    // check that references are removed
    normalizeTest("http://foo.com/foo.html#ref", "http://foo.com/foo.html");

    // check that encoding is normalized
    normalizeTest("http://foo.com/%66oo.html", "http://foo.com/foo.html");

    // check that unnecessary "../" are removed
    normalizeTest("http://foo.com/..", "http://foo.com/");
    normalizeTest("http://foo.com/aa/./foo.html", "http://foo.com/aa/foo.html");
    normalizeTest("http://foo.com/aa/../", "http://foo.com/");
    normalizeTest("http://foo.com/aa/bb/../", "http://foo.com/aa/");
    normalizeTest("http://foo.com/aa/..", "http://foo.com/");
    normalizeTest("http://foo.com/aa/bb/cc/../../foo.html",
        "http://foo.com/aa/foo.html");
    normalizeTest("http://foo.com/aa/bb/../cc/dd/../ee/foo.html",
        "http://foo.com/aa/cc/ee/foo.html");
    normalizeTest("http://foo.com/../foo.html", "http://foo.com/foo.html");
    normalizeTest("http://foo.com/../../foo.html", "http://foo.com/foo.html");
    normalizeTest("http://foo.com/../aa/../foo.html", "http://foo.com/foo.html");
    normalizeTest("http://foo.com/aa/../../foo.html", "http://foo.com/foo.html");
    normalizeTest("http://foo.com/aa/../bb/../foo.html/../../",
        "http://foo.com/");
    normalizeTest("http://foo.com/../aa/foo.html", "http://foo.com/aa/foo.html");
    normalizeTest("http://foo.com/../aa/../foo.html", "http://foo.com/foo.html");
    normalizeTest("http://foo.com/a..a/foo.html",
        "http://foo.com/a..a/foo.html");
    normalizeTest("http://foo.com/a..a/../foo.html", "http://foo.com/foo.html");
    normalizeTest("http://foo.com/foo.foo/../foo.html",
        "http://foo.com/foo.html");
    normalizeTest("http://foo.com//aa/bb/foo.html",
        "http://foo.com/aa/bb/foo.html");
    normalizeTest("http://foo.com/aa//bb/foo.html",
        "http://foo.com/aa/bb/foo.html");
    normalizeTest("http://foo.com/aa/bb//foo.html",
        "http://foo.com/aa/bb/foo.html");
    normalizeTest("http://foo.com//aa//bb//foo.html",
        "http://foo.com/aa/bb/foo.html");
    normalizeTest("http://foo.com////aa////bb////foo.html",
        "http://foo.com/aa/bb/foo.html");
    normalizeTest("http://foo.com/aa?referer=http://bar.com",
        "http://foo.com/aa?referer=http://bar.com");
    // check for NPEs when normalizing URLs without host (authority)
    normalizeTest("file:///foo/bar.txt", "file:///foo/bar.txt");
    normalizeTest("ftp:/", "ftp:/");
    normalizeTest("http:", "http:/");
    normalizeTest("http:////", "http:/");
    normalizeTest("http:///////", "http:/");
    // NUTCH-2555 path must start with '/'
    normalizeTest("http://example.com?a=1", "http://example.com/?a=1");
    // NUTCH-2547 urlnormalizer-basic fails on special characters in path/query
    normalizeTest("http://www.example.com/a/c/../b/search?q=foobar|",
        "http://www.example.com/a/b/search?q=foobar%7C");
    normalizeTest("http://www.example.com/a/c/../b/search?q=foobar%",
        "http://www.example.com/a/b/search?q=foobar%25");
    normalizeTest("http://www.example.com/a/c/../b/search?q=foobar\"",
        "http://www.example.com/a/b/search?q=foobar%22");
    normalizeTest("http://www.example.com/a/c/../b/search?q=foobar^",
        "http://www.example.com/a/b/search?q=foobar%5E");
    normalizeTest("http://www.example.com/a/c/../b/search?q=foobar<",
        "http://www.example.com/a/b/search?q=foobar%3C");
    normalizeTest("http://www.example.com/a/c/../b/search?q=foobar>",
        "http://www.example.com/a/b/search?q=foobar%3E");
    normalizeTest("http://www.example.com/a/c/../b/search?q=foobar`",
        "http://www.example.com/a/b/search?q=foobar%60");
    // escape percent in case it's an invalid escape sequence
    normalizeTest("http://www.example.com/p%zz%77%v",
        "http://www.example.com/p%25zzw%25v");
    // boundary test: percent sign close to the end of string
    normalizeTest("http://www.example.com/search?q=foobar%",
        "http://www.example.com/search?q=foobar%25");
    normalizeTest("http://www.example.com/search?q=foobar%2",
        "http://www.example.com/search?q=foobar%252");
    normalizeTest("http://www.example.com/search?q=foobar%25",
        "http://www.example.com/search?q=foobar%25");
    normalizeTest("http://www.example.com/search?q=foobar%252",
        "http://www.example.com/search?q=foobar%252");
    // NUTCH-2609 normalize path of file: URLs
    normalizeTest("file:/var/www/html/foo/../bar/index.html",
        "file:/var/www/html/bar/index.html");
    normalizeTest("file:/var/www/html/////./bar/index.html",
        "file:/var/www/html/bar/index.html");
  }

  @Test
  public void testNUTCH3087() throws Exception {
    // NUTCH-3087 userinfo to be kept in URLs with protocols usually requiring
    // authentication
    normalizeTest("ftp://user@ftp.example.org/path/file.txt",
        "ftp://user@ftp.example.org/path/file.txt");
    normalizeTest("ftp://john.doe@ftp.example.org/",
        "ftp://john.doe@ftp.example.org/");
    normalizeTest("ftp://user:password@ftp.example.org/path/file.txt",
        "ftp://user:password@ftp.example.org/path/file.txt");
    // But for HTTP(S) the userinfo should be removed.
    // (example from https://en.wikipedia.org/wiki/Uniform_Resource_Identifier)
    normalizeTest(
        "https://john.doe@www.example.com:1234/forum/questions/?tag=networking&order=newest#top",
        "https://www.example.com:1234/forum/questions/?tag=networking&order=newest");
    // URLs with IPv6 address
    normalizeTest("ftp://user@[2600:1f18:200d:fb00:2b74:867c:ab0c:150a]/../path/file.txt",
        "ftp://user@[2600:1f18:200d:fb00:2b74:867c:ab0c:150a]/path/file.txt");
    normalizeTest("https://user@[2600:1f18:200d:fb00:2b74:867c:ab0c:150a]/",
        "https://[2600:1f18:200d:fb00:2b74:867c:ab0c:150a]/");
    normalizeTest("https://user@[2600:1f18:200d:fb00:2b74:867c:ab0c:150a]:443/",
        "https://[2600:1f18:200d:fb00:2b74:867c:ab0c:150a]/");
    normalizeTest("https://user@[2600:1f18:200d:fb00:2b74:867c:ab0c:150a]/path/../to/index.html",
        "https://[2600:1f18:200d:fb00:2b74:867c:ab0c:150a]/to/index.html");
  }
  
  @Test
  public void testCurlyBraces() throws Exception {
    // check whether curly braces are properly escaped
    normalizeTest("http://foo.com/{{stuff}} ", "http://foo.com/%7B%7Bstuff%7D%7D");
  }

  @Test
  public void testHostName() throws Exception {
    // (nothing to normalize in host name)
    normalizeTest("https://www.example.org/", "https://www.example.org/");
    // test Internationalized Domain Names
    BasicURLNormalizer norm = new BasicURLNormalizer();
    conf = NutchConfiguration.create();
    conf.set(BasicURLNormalizer.NORM_HOST_IDN, "toAscii");
    norm.setConf(conf);
    normalizeTest(norm, "https://нэб.рф/", "https://xn--90ax2c.xn--p1ai/");
    // verify escaping of percent-encoded characters in IDNs (NUTCH-2824)
    normalizeTest(norm, "https://www.0251-sachverst%c3%a4ndiger.de/",
        "https://www.xn--0251-sachverstndiger-ozb.de/");
    conf.set(BasicURLNormalizer.NORM_HOST_IDN, "toUnicode");
    norm.setConf(conf);
    normalizeTest(norm, "https://xn--90ax2c.xn--p1ai/", "https://нэб.рф/");
    // test removal of trailing dot
    conf.setBoolean(BasicURLNormalizer.NORM_HOST_TRIM_TRAILING_DOT, true);
    norm.setConf(conf);
    normalizeTest(norm, "https://www.example.org./",
        "https://www.example.org/");
  }

  /**
   * Test that normalizer throws MalformedURLException for invalid URLs
   */
  @Test
  public void testInvalidURLs() throws Exception {
    // invalid percent-encoded sequence in host name
    normalizeTestAssertThrowsMalformedURLException("https://example%2Xcom/");
    // not a valid UTF-8 sequence in host name
    // (only validated if parsed as Internationalized Domain Name)
    BasicURLNormalizer norm = new BasicURLNormalizer();
    conf = NutchConfiguration.create();
    conf.set(BasicURLNormalizer.NORM_HOST_IDN, "toAscii");
    norm.setConf(conf);
    normalizeTestAssertThrowsMalformedURLException(norm,
        "https://abc%FEdef.org/");
  }

  private void normalizeTest(String weird, String normal) throws Exception {
    normalizeTest(this.normalizer, weird, normal);
  }

  private void normalizeTest(BasicURLNormalizer normalizer, String weird,
      String normal) throws Exception {
    Assert.assertEquals("normalizing: " + weird, normal,
        normalizer.normalize(weird, URLNormalizers.SCOPE_DEFAULT));
    try {
      (new URL(normal)).toURI();
    } catch (MalformedURLException | URISyntaxException e) {
      Assert.fail("Output of normalization fails to validate as URL or URI: "
          + e.getMessage());
    }
  }

  private void normalizeTestAssertThrowsMalformedURLException(String weird) throws Exception {
    normalizeTestAssertThrowsMalformedURLException(this.normalizer, weird);
  }

  private void normalizeTestAssertThrowsMalformedURLException(
      BasicURLNormalizer normalizer, String weird) throws Exception {
    String normalized = null;
    try {
      normalized = normalizer.normalize(weird, URLNormalizers.SCOPE_DEFAULT);
    } catch (MalformedURLException e) {
      // ok, expected
      return;
    }
    Assert.fail("Expected MalformedURLException was not thrown on " + weird
        + " (normalized: " + normalized + ")");
  }

  public static void main(String[] args) throws Exception {
    new TestBasicURLNormalizer().testNormalizer();
  }

}
