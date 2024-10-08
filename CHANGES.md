# Nutch Change Log

## Nutch 1.20 Release 09/04/2024 (dd/mm/yyyy)
Release Report: https://s.apache.org/ovjf3

### Sub-task

- [NUTCH-2596](https://issues.apache.org/jira/browse/NUTCH-2596) - Upgrade from org.mortbay.jetty to org.eclipse.jetty
- [NUTCH-2852](https://issues.apache.org/jira/browse/NUTCH-2852) - Method invokes System.exit(...) 9 bugs
- [NUTCH-2972](https://issues.apache.org/jira/browse/NUTCH-2972) - Javadoc build fails using JDK 17
- [NUTCH-3007](https://issues.apache.org/jira/browse/NUTCH-3007) - Fix impossible casts

### Bug

- [NUTCH-2634](https://issues.apache.org/jira/browse/NUTCH-2634) - Some links marked as "nofollow" are followed anyway.
- [NUTCH-2820](https://issues.apache.org/jira/browse/NUTCH-2820) - Review sample files used in any23 unit tests
- [NUTCH-2924](https://issues.apache.org/jira/browse/NUTCH-2924) - Generate maxCount expr evaluated only once
- [NUTCH-2937](https://issues.apache.org/jira/browse/NUTCH-2937) - parse-tika: review dependency exclusions and avoid dependency conflicts in distributed mode
- [NUTCH-2973](https://issues.apache.org/jira/browse/NUTCH-2973) - Single domain names (eg https://localnet) can't be crawled - filtering fails
- [NUTCH-2974](https://issues.apache.org/jira/browse/NUTCH-2974) - Ant build fails with "Unparseable date" on certain platforms
- [NUTCH-2979](https://issues.apache.org/jira/browse/NUTCH-2979) - Upgrade Commons Text to 1.10.0
- [NUTCH-2982](https://issues.apache.org/jira/browse/NUTCH-2982) - Generator: parameter for URL normalization not passed forward
- [NUTCH-2985](https://issues.apache.org/jira/browse/NUTCH-2985) - Disable plugin urlfilter-validator by default
- [NUTCH-2992](https://issues.apache.org/jira/browse/NUTCH-2992) - Fetcher: always block fetch queues when exceptions threshold is reached
- [NUTCH-3000](https://issues.apache.org/jira/browse/NUTCH-3000) - protocol-selenium returns only the body, strips off the &lt;head/&gt; element
- [NUTCH-3001](https://issues.apache.org/jira/browse/NUTCH-3001) - protocol-selenium requires Content-Type header 
- [NUTCH-3002](https://issues.apache.org/jira/browse/NUTCH-3002) - Protocol-okhttp HttpResponse: HTTP header metadata lookup should be case-insensitive
- [NUTCH-3008](https://issues.apache.org/jira/browse/NUTCH-3008) - indexer-elastic: downgrade to ES 7.10.2 to address licensing issues
- [NUTCH-3012](https://issues.apache.org/jira/browse/NUTCH-3012) - SegmentReader when dumping with option -recode: NPE on unparsed documents
- [NUTCH-3027](https://issues.apache.org/jira/browse/NUTCH-3027) - Trivial resource leak patch in DomainSuffixes.java
- [NUTCH-3035](https://issues.apache.org/jira/browse/NUTCH-3035) - Update license and notice file for release of 1.20 

### New Feature

- [NUTCH-2832](https://issues.apache.org/jira/browse/NUTCH-2832) - Create tutorial on sending Nutch logs to Elasticsearch
- [NUTCH-2888](https://issues.apache.org/jira/browse/NUTCH-2888) - Selenium Protocol: Support for Selenium 4
- [NUTCH-2920](https://issues.apache.org/jira/browse/NUTCH-2920) - Implement a indexer-opensearch plugin
- [NUTCH-2991](https://issues.apache.org/jira/browse/NUTCH-2991) - Support HTTP/S Header Authorization for Solr connections
- [NUTCH-3029](https://issues.apache.org/jira/browse/NUTCH-3029) - Host specific max. and min. intervals in adaptive scheduler
    
### Improvement

- [NUTCH-2853](https://issues.apache.org/jira/browse/NUTCH-2853) - bin/nutch: remove deprecated commands solrindex, solrdedup, solrclean
- [NUTCH-2883](https://issues.apache.org/jira/browse/NUTCH-2883) - Provide means to run server as a persistent service in Docker container
- [NUTCH-2897](https://issues.apache.org/jira/browse/NUTCH-2897) - Do not supress deprecated API warnings
- [NUTCH-2961](https://issues.apache.org/jira/browse/NUTCH-2961) - Upgrade dependencies of parsefilter-naivebayes
- [NUTCH-2980](https://issues.apache.org/jira/browse/NUTCH-2980) - Upgrade Selenium Java to 4.7.2
- [NUTCH-2983](https://issues.apache.org/jira/browse/NUTCH-2983) - nutch-default.xml improvements
- [NUTCH-2990](https://issues.apache.org/jira/browse/NUTCH-2990) - HttpRobotRulesParser to follow 5 redirects as specified by RFC 9309
- [NUTCH-2993](https://issues.apache.org/jira/browse/NUTCH-2993) - ScoringDepth plugin to skip depth check based on URL Pattern
- [NUTCH-2995](https://issues.apache.org/jira/browse/NUTCH-2995) - Upgrade to crawler-commons 1.4
- [NUTCH-2996](https://issues.apache.org/jira/browse/NUTCH-2996) - Use new SimpleRobotRulesParser API entry point (crawler-commons 1.4)
- [NUTCH-2997](https://issues.apache.org/jira/browse/NUTCH-2997) - Add Override annotations where applicable
- [NUTCH-3004](https://issues.apache.org/jira/browse/NUTCH-3004) - Avoid NPE in HttpResponse
- [NUTCH-3005](https://issues.apache.org/jira/browse/NUTCH-3005) - Upgrade selenium as needed
- [NUTCH-3009](https://issues.apache.org/jira/browse/NUTCH-3009) - Upgrade to Hadoop 3.3.6
- [NUTCH-3010](https://issues.apache.org/jira/browse/NUTCH-3010) - Injector: count unique number of injected URLs
- [NUTCH-3011](https://issues.apache.org/jira/browse/NUTCH-3011) - HttpRobotRulesParser: handle HTTP 429 Too Many Requests same as server errors (HTTP 5xx)
- [NUTCH-3013](https://issues.apache.org/jira/browse/NUTCH-3013) - Employ commons-lang3's StopWatch to simplify timing logic
- [NUTCH-3014](https://issues.apache.org/jira/browse/NUTCH-3014) - Standardize Job names
- [NUTCH-3015](https://issues.apache.org/jira/browse/NUTCH-3015) - Add more CI steps to GitHub master-build.yml
- [NUTCH-3017](https://issues.apache.org/jira/browse/NUTCH-3017) - Allow fast-urlfilter to load from HDFS/S3 and support gzipped input
- [NUTCH-3025](https://issues.apache.org/jira/browse/NUTCH-3025) - urlfilter-fast to filter based on the length of the URL
- [NUTCH-3031](https://issues.apache.org/jira/browse/NUTCH-3031) - ProtocolFactory host mapper to support domains
- [NUTCH-3032](https://issues.apache.org/jira/browse/NUTCH-3032) - Indexing plugin as an adapter for end user's own POJO instances
- [NUTCH-3036](https://issues.apache.org/jira/browse/NUTCH-3036) - Upgrade org.seleniumhq.selenium:selenium-java dependency in lib-selenium

### Task

- [NUTCH-2959](https://issues.apache.org/jira/browse/NUTCH-2959) - Upgrade to Apache Tika 2.9.0
- [NUTCH-2977](https://issues.apache.org/jira/browse/NUTCH-2977) - Support for showing dependency tree
- [NUTCH-2978](https://issues.apache.org/jira/browse/NUTCH-2978) - Move to slf4j2 and remove log4j1 and reload4j
- [NUTCH-2984](https://issues.apache.org/jira/browse/NUTCH-2984) - Drop test proxy server and benchmark tool
- [NUTCH-2989](https://issues.apache.org/jira/browse/NUTCH-2989) - Can't have username/pw AND https on elastic-indexer?!
- [NUTCH-2998](https://issues.apache.org/jira/browse/NUTCH-2998) - Remove the Any23 plugin
- [NUTCH-2999](https://issues.apache.org/jira/browse/NUTCH-2999) - Update Lucene version to latest 8.x
- [NUTCH-3016](https://issues.apache.org/jira/browse/NUTCH-3016) - Upgrade Apache Ivy to 2.5.2
- [NUTCH-3019](https://issues.apache.org/jira/browse/NUTCH-3019) - Upgrade to Apache Tika 2.9.1
- [NUTCH-3020](https://issues.apache.org/jira/browse/NUTCH-3020) - ParseSegment should check for protocol's flags for truncation
- [NUTCH-3024](https://issues.apache.org/jira/browse/NUTCH-3024) - Remove flaky 'dependency check' target
- [NUTCH-3033](https://issues.apache.org/jira/browse/NUTCH-3033) - Upgrade Ivy to v2.5.2
- [NUTCH-3037](https://issues.apache.org/jira/browse/NUTCH-3037) - Upgrade org.apache.kafka:kafka_2.12: to v3.7.0
- [NUTCH-3038](https://issues.apache.org/jira/browse/NUTCH-3038) - Address issues discovered during 1.20 release management dryrun
                                                                                                                                                        

## Nutch 1.19 Release 22/08/2022 (dd/mm/yyyy)
Release Report: https://s.apache.org/lf6li

### Breaking Changes

- Nutch is built on JDK 11 (NUTCH-2857)
- the Nutch WebApp was moved to a separate repository (NUTCH-2886)
  - see https://github.com/apache/nutch-webapp
  - or  https://gitbox.apache.org/repos/asf?p=nutch-webapp.git
- the plugin parse-swf for parsing Shockwave/Adobe Flash content was removed (NUTCH-2861)

### Sub-task

- [NUTCH-2819](https://issues.apache.org/jira/browse/NUTCH-2819) - Move spotbugs "installation" directory to avoid that spotbugs is shipped in Nutch runtime
- [NUTCH-2846](https://issues.apache.org/jira/browse/NUTCH-2846) - Fix various bugs spotted by NUTCH-2815
- [NUTCH-2850](https://issues.apache.org/jira/browse/NUTCH-2850) - Method ignores exceptional return value
- [NUTCH-2851](https://issues.apache.org/jira/browse/NUTCH-2851) - Random object created and used only once
- [NUTCH-2855](https://issues.apache.org/jira/browse/NUTCH-2855) - Update org.elasticsearch.client

### Bug

- [NUTCH-2290](https://issues.apache.org/jira/browse/NUTCH-2290) - Update licenses of bundled libraries
- [NUTCH-2512](https://issues.apache.org/jira/browse/NUTCH-2512) - Nutch does not build under JDK9
- [NUTCH-2821](https://issues.apache.org/jira/browse/NUTCH-2821) - Deduplicate licenses in LICENSE.txt file
- [NUTCH-2822](https://issues.apache.org/jira/browse/NUTCH-2822) - Split the LICENSE.txt file into two files for source resp. binary releases
- [NUTCH-2831](https://issues.apache.org/jira/browse/NUTCH-2831) - Elastic indexer does not support SSL
- [NUTCH-2843](https://issues.apache.org/jira/browse/NUTCH-2843) - Duplicate declaration of dependencies in ivy.xml
- [NUTCH-2858](https://issues.apache.org/jira/browse/NUTCH-2858) - urlnormalizer-protocol: URL port is lost during normalization
- [NUTCH-2862](https://issues.apache.org/jira/browse/NUTCH-2862) - Do not include Ivy jar in source release package
- [NUTCH-2863](https://issues.apache.org/jira/browse/NUTCH-2863) - Injector to parse command-line flags case-insensitive
- [NUTCH-2866](https://issues.apache.org/jira/browse/NUTCH-2866) - MetaData.toString() should return "key=value ..."
- [NUTCH-2868](https://issues.apache.org/jira/browse/NUTCH-2868) - urlnormalizer-protocol fails with StringIndexOutOfBoundsException when reading invalid line in configuration file
- [NUTCH-2881](https://issues.apache.org/jira/browse/NUTCH-2881) - bug in 'nutch' symlink in docker container
- [NUTCH-2889](https://issues.apache.org/jira/browse/NUTCH-2889) - nutch indexer-elasticsearch plugin, doesn't work with https protocol
- [NUTCH-2890](https://issues.apache.org/jira/browse/NUTCH-2890) - Protocol-okhttp: upgrade okhttp to 4.9.1 to address infinite connection retries
- [NUTCH-2894](https://issues.apache.org/jira/browse/NUTCH-2894) - Java plugin compilation classpath: priorize plugin dependencies
- [NUTCH-2899](https://issues.apache.org/jira/browse/NUTCH-2899) - Remove needless warning about missing o/a/rat/anttasks/antlib.xml
- [NUTCH-2902](https://issues.apache.org/jira/browse/NUTCH-2902) - Jexl parsing error on statements
- [NUTCH-2905](https://issues.apache.org/jira/browse/NUTCH-2905) - Mask sensitive strings in log output of index writers
- [NUTCH-2910](https://issues.apache.org/jira/browse/NUTCH-2910) - FetchItemQueues overloaded constructor also interprets fetcher timeout as -1 e.g. no-timeout.
- [NUTCH-2915](https://issues.apache.org/jira/browse/NUTCH-2915) - Upgrade to log4j 2.15.0
- [NUTCH-2916](https://issues.apache.org/jira/browse/NUTCH-2916) - Fix log file rotation / rename default log file
- [NUTCH-2917](https://issues.apache.org/jira/browse/NUTCH-2917) - Remove transitive dependency to log4j 1.x
- [NUTCH-2922](https://issues.apache.org/jira/browse/NUTCH-2922) - Upgrade to log4j 2.17.0
- [NUTCH-2935](https://issues.apache.org/jira/browse/NUTCH-2935) - DeduplicationJob: failure on URLs with invalid percent encoding
- [NUTCH-2936](https://issues.apache.org/jira/browse/NUTCH-2936) - Early registration of URL stream handlers provided by plugins may fail Hadoop jobs running in distributed mode if protocol-okhttp is used
- [NUTCH-2945](https://issues.apache.org/jira/browse/NUTCH-2945) - Solr Index Writer pluging schema.xml missing a copyToField
- [NUTCH-2947](https://issues.apache.org/jira/browse/NUTCH-2947) - Fetcher: keep state of empty fetch queues unless queue feeder is finished
- [NUTCH-2949](https://issues.apache.org/jira/browse/NUTCH-2949) - Tasks of a multi-threaded map runner may fail because of slow creation of URL stream handlers
- [NUTCH-2951](https://issues.apache.org/jira/browse/NUTCH-2951) - Crawl datum with metadata WRITABLE_GENERATE_TIME_KEY awaits fetching forever
- [NUTCH-2955](https://issues.apache.org/jira/browse/NUTCH-2955) - indexer-solr: replace deprecated/removed field type solr.LatLonType
- [NUTCH-2969](https://issues.apache.org/jira/browse/NUTCH-2969) - Javadoc: Javascript search is not working when built on JDK 11

### New Feature

- [NUTCH-2901](https://issues.apache.org/jira/browse/NUTCH-2901) - migrate to maven or gradle

### Improvement

- [NUTCH-1403](https://issues.apache.org/jira/browse/NUTCH-1403) - Add default ScoringFilter for manipulating metadata
- [NUTCH-2429](https://issues.apache.org/jira/browse/NUTCH-2429) - Fix Plugin System to allow protocol plugins to bundle their URLStreamHandlers
- [NUTCH-2449](https://issues.apache.org/jira/browse/NUTCH-2449) - Usage of Tika LanguageIdentifier in language-identifier plugin
- [NUTCH-2573](https://issues.apache.org/jira/browse/NUTCH-2573) - Suspend crawling if robots.txt fails to fetch with 5xx status
- [NUTCH-2795](https://issues.apache.org/jira/browse/NUTCH-2795) - CrawlDbReader: compress CrawlDb dumps if configured
- [NUTCH-2807](https://issues.apache.org/jira/browse/NUTCH-2807) - SitemapProcessor to warn that ignoring robots.txt affects detection of sitemaps
- [NUTCH-2808](https://issues.apache.org/jira/browse/NUTCH-2808) - Document side effects of ignoring robots.txt
- [NUTCH-2840](https://issues.apache.org/jira/browse/NUTCH-2840) - Fix 'report-vulnerabilities' ant target in build.xml
- [NUTCH-2842](https://issues.apache.org/jira/browse/NUTCH-2842) - Fix Javadoc warnings, errors and add Javadoc check to Github Action and Jenkins
- [NUTCH-2845](https://issues.apache.org/jira/browse/NUTCH-2845) - Update urlfilter-suffix rules
- [NUTCH-2847](https://issues.apache.org/jira/browse/NUTCH-2847) - HttpDateFormat: Simplify based on new Java 8 DateTime API
- [NUTCH-2849](https://issues.apache.org/jira/browse/NUTCH-2849) - Replace remaining package.html files with package-info.java
- [NUTCH-2857](https://issues.apache.org/jira/browse/NUTCH-2857) - Upgrade from JDK1.8 --> JDK11
- [NUTCH-2859](https://issues.apache.org/jira/browse/NUTCH-2859) - urlnormalizer-protocol: allow to normalize domains
- [NUTCH-2861](https://issues.apache.org/jira/browse/NUTCH-2861) - Remove parse-swf
- [NUTCH-2864](https://issues.apache.org/jira/browse/NUTCH-2864) - Upgrade Dockerfile to use JDK 11
- [NUTCH-2865](https://issues.apache.org/jira/browse/NUTCH-2865) - WARC exporter support for metadata and dropping empty responses
- [NUTCH-2867](https://issues.apache.org/jira/browse/NUTCH-2867) - Support for custom HostDb aggregators
- [NUTCH-2869](https://issues.apache.org/jira/browse/NUTCH-2869) - Add @Override annotations to Nutch plugins
- [NUTCH-2879](https://issues.apache.org/jira/browse/NUTCH-2879) - fireant upgrade dependency hadoop-hdfs in ivy/ivy.xml from 3.1.3 to 3.3.1
- [NUTCH-2882](https://issues.apache.org/jira/browse/NUTCH-2882) - Configure NutchUiServer for DEPLOYMENT and improve logging
- [NUTCH-2885](https://issues.apache.org/jira/browse/NUTCH-2885) - Upgrade to Log4j2
- [NUTCH-2886](https://issues.apache.org/jira/browse/NUTCH-2886) - Move Nutch WebApp to separate repository
- [NUTCH-2891](https://issues.apache.org/jira/browse/NUTCH-2891) - Upgrade to Tika 2.1
- [NUTCH-2892](https://issues.apache.org/jira/browse/NUTCH-2892) - Upgrade to Any23 2.5
- [NUTCH-2893](https://issues.apache.org/jira/browse/NUTCH-2893) - fireant upgrade dependency elasticsearch-rest-high-level-client in src/plugin/indexer-elastic/ivy.xml from 7.11.1 to 7.13.2
- [NUTCH-2896](https://issues.apache.org/jira/browse/NUTCH-2896) - Protocol-okhttp: make connection pool configurable
- [NUTCH-2898](https://issues.apache.org/jira/browse/NUTCH-2898) - IDE Setup for nutch with Intellij IDEA is not well documented
- [NUTCH-2903](https://issues.apache.org/jira/browse/NUTCH-2903) - Unable to Connect to Elasticsearch over HTTPS
- [NUTCH-2904](https://issues.apache.org/jira/browse/NUTCH-2904) - Upgrade to crawler-commons 1.2
- [NUTCH-2908](https://issues.apache.org/jira/browse/NUTCH-2908) - Log mapreduce job messages and counters in local mode
- [NUTCH-2911](https://issues.apache.org/jira/browse/NUTCH-2911) - Add cleanup call in Fetcher.java
- [NUTCH-2914](https://issues.apache.org/jira/browse/NUTCH-2914) - nutch-default.xml: remove obsolete and unused properties
- [NUTCH-2918](https://issues.apache.org/jira/browse/NUTCH-2918) - Upgrade to log4j 2.16.0
- [NUTCH-2919](https://issues.apache.org/jira/browse/NUTCH-2919) - Upgrade to Tika 2.2.1 and Any23 2.6
- [NUTCH-2923](https://issues.apache.org/jira/browse/NUTCH-2923) - Add Job Id in Job Failure messages
- [NUTCH-2929](https://issues.apache.org/jira/browse/NUTCH-2929) - Fetcher: start threads slowly to avoid that resources are temporarily exhausted
- [NUTCH-2930](https://issues.apache.org/jira/browse/NUTCH-2930) - Protocol-okhttp: implement IP filter
- [NUTCH-2946](https://issues.apache.org/jira/browse/NUTCH-2946) - Fetcher: optionally slow down fetching from hosts with repeated exceptions
- [NUTCH-2948](https://issues.apache.org/jira/browse/NUTCH-2948) - Upgrade dependencies to Any23 2.7 and Tika 2.3.0
- [NUTCH-2950](https://issues.apache.org/jira/browse/NUTCH-2950) - UpdateHostDb: performance improvements
- [NUTCH-2952](https://issues.apache.org/jira/browse/NUTCH-2952) - Upgrade core dependencies (Hadoop 3.3.3, log4j 2.17.2)
- [NUTCH-2953](https://issues.apache.org/jira/browse/NUTCH-2953) - Indexer Elastic to ignore SSL issues
- [NUTCH-2956](https://issues.apache.org/jira/browse/NUTCH-2956) - index-geoip: dependency upgrades and improvements
- [NUTCH-2957](https://issues.apache.org/jira/browse/NUTCH-2957) - indexer-solr / Solr schema: add fall-back field definitions for unknown index fields
- [NUTCH-2958](https://issues.apache.org/jira/browse/NUTCH-2958) - Upgrade to crawler-commons 1.3
- [NUTCH-2962](https://issues.apache.org/jira/browse/NUTCH-2962) - Update and complete package info of protocol plugins
- [NUTCH-2963](https://issues.apache.org/jira/browse/NUTCH-2963) - Upgrade dependencies before release of 1.19

### Task

- [NUTCH-2826](https://issues.apache.org/jira/browse/NUTCH-2826) - Migrate Nutch Site from Apache CMS to Hugo
- [NUTCH-2870](https://issues.apache.org/jira/browse/NUTCH-2870) - fireant upgrade dependency junit in ivy/ivy.xml from 4.13.1 to 4.13.2


## Nutch 1.18 Release 14/01/2021 (dd/mm/yyyy)
Release Report: https://s.apache.org/lqara

### Breaking Changes

- As part of NUTCH-2805, the plugin urlfilter-domainblacklist has been renamed to urlfilter-domaindenylist. And the fields required for the plugin urlfilter.domainblacklist.rules and urlfilter.domainblacklist.file has been replaced with urlfilter.domaindenylist.rules and urlfilter.domaindenylist.file respectively. See [NUTCH-2802](https://issues.apache.org/jira/browse/NUTCH-2802) for more details.

### Sub-task

- [NUTCH-2671](https://issues.apache.org/jira/browse/NUTCH-2671) - Upgrade ant ivy library
- [NUTCH-2672](https://issues.apache.org/jira/browse/NUTCH-2672) - Ant build erronously installs *-test.jar instead *.jar for target "nightly"
- [NUTCH-2805](https://issues.apache.org/jira/browse/NUTCH-2805) - Rename plugin urlfilter-domainblacklist
- [NUTCH-2809](https://issues.apache.org/jira/browse/NUTCH-2809) - Upgrade any23 plugin dependency to 2.4
- [NUTCH-2816](https://issues.apache.org/jira/browse/NUTCH-2816) - Add Spotbugs target to ant build
- [NUTCH-2817](https://issues.apache.org/jira/browse/NUTCH-2817) - Avoid check for equality of URL path and file part using ==/!=
- [NUTCH-2829](https://issues.apache.org/jira/browse/NUTCH-2829) - Fix ant target "clean-cache"

### Bug

- [NUTCH-2669](https://issues.apache.org/jira/browse/NUTCH-2669) - Reliable solution for javax.ws packaging.type
- [NUTCH-2697](https://issues.apache.org/jira/browse/NUTCH-2697) - Upgrade Ivy to fix the issue of an unset packaging.type property
- [NUTCH-2801](https://issues.apache.org/jira/browse/NUTCH-2801) - RobotsRulesParser command-line checker to use http.robots.agents as fall-back
- [NUTCH-2810](https://issues.apache.org/jira/browse/NUTCH-2810) - FreeGenerator to actually apply configured number of fetch lists
- [NUTCH-2813](https://issues.apache.org/jira/browse/NUTCH-2813) - MoreIndexingFilter - can't parse erroneous date - 2019-07-03T10:28:14
- [NUTCH-2814](https://issues.apache.org/jira/browse/NUTCH-2814) - HttpDateFormat's internal time zone may change after parsing a date
- [NUTCH-2818](https://issues.apache.org/jira/browse/NUTCH-2818) - Ant build: upgrade Apache Rat report task
- [NUTCH-2823](https://issues.apache.org/jira/browse/NUTCH-2823) - IllegalStateException in IndexWriters.describe() when validating url param for SolrIndexer
- [NUTCH-2824](https://issues.apache.org/jira/browse/NUTCH-2824) - urlnormalizer-basic to unescape percent-encoded host names

### Improvement

- [NUTCH-1190](https://issues.apache.org/jira/browse/NUTCH-1190) - MoreIndexingFilter refactor: move data formats used to parse "lastModified" to a config file.
- [NUTCH-2582](https://issues.apache.org/jira/browse/NUTCH-2582) - Set pool size of XML SAX parsers used for MIME detection in Tika 1.19
- [NUTCH-2730](https://issues.apache.org/jira/browse/NUTCH-2730) - SitemapProcessor to treat sitemap URLs as Set instead of List
- [NUTCH-2782](https://issues.apache.org/jira/browse/NUTCH-2782) - protocol-http / lib-http: support TLSv1.3
- [NUTCH-2796](https://issues.apache.org/jira/browse/NUTCH-2796) - Upgrade to crawler-commons 1.1
- [NUTCH-2799](https://issues.apache.org/jira/browse/NUTCH-2799) - Add .asf.yaml file
- [NUTCH-2833](https://issues.apache.org/jira/browse/NUTCH-2833) - Upgrade to Tika 1.25
- [NUTCH-2835](https://issues.apache.org/jira/browse/NUTCH-2835) - Upgrade commons-jexl from 2 --> 3
- [NUTCH-2836](https://issues.apache.org/jira/browse/NUTCH-2836) - Upgrade various commons dependencies
- [NUTCH-2837](https://issues.apache.org/jira/browse/NUTCH-2837) - Update multiple dependencies
- [NUTCH-2841](https://issues.apache.org/jira/browse/NUTCH-2841) - Upgrade xercesImpl dependency

### Wish

- [NUTCH-2834](https://issues.apache.org/jira/browse/NUTCH-2834) - Deduplication mode via command line in crawl script

### Task

- [NUTCH-2830](https://issues.apache.org/jira/browse/NUTCH-2830) - Upgrade any23 to v2.4


## Nutch 1.17 Release 18/06/2020 (dd/mm/yyyy)
Release Report: https://s.apache.org/ovhry

### Bug

- [NUTCH-1559](https://issues.apache.org/jira/browse/NUTCH-1559) - parse-metatags duplicates extracted metatags
- [NUTCH-2379](https://issues.apache.org/jira/browse/NUTCH-2379) - crawl script dedup's crawldb update is slow
- [NUTCH-2419](https://issues.apache.org/jira/browse/NUTCH-2419) - Some URL filters and normalizers do not respect command-line override for rule file
- [NUTCH-2507](https://issues.apache.org/jira/browse/NUTCH-2507) - NutchTutorial wiki pages as a lot of outdated command line calls when it starts with the solr interaction
- [NUTCH-2511](https://issues.apache.org/jira/browse/NUTCH-2511) - SitemapProcessor limited by http.content.limit
- [NUTCH-2525](https://issues.apache.org/jira/browse/NUTCH-2525) - Metadata indexer cannot handle uppercase parse metadata
- [NUTCH-2567](https://issues.apache.org/jira/browse/NUTCH-2567) - parse-metatags writes all meta tags twice
- [NUTCH-2720](https://issues.apache.org/jira/browse/NUTCH-2720) - ROBOTS metatag ignored when capitalized
- [NUTCH-2745](https://issues.apache.org/jira/browse/NUTCH-2745) - Solr schema.xml not shipped in binary release
- [NUTCH-2748](https://issues.apache.org/jira/browse/NUTCH-2748) - Fetch status gone (redirect exceeded) not to overwrite existing items in CrawlDb
- [NUTCH-2751](https://issues.apache.org/jira/browse/NUTCH-2751) - nutch clean does not work with secured solr cloud
- [NUTCH-2753](https://issues.apache.org/jira/browse/NUTCH-2753) - Add -listen option to command-line help of CrawlDbReader and LinkDbReader
- [NUTCH-2754](https://issues.apache.org/jira/browse/NUTCH-2754) - fetcher.max.crawl.delay ignored if exceeding 5 min. / 300 sec.
- [NUTCH-2760](https://issues.apache.org/jira/browse/NUTCH-2760) - protocol-okhttp: properly record HTTP version in request message header
- [NUTCH-2761](https://issues.apache.org/jira/browse/NUTCH-2761) - ivy jar fails to download
- [NUTCH-2763](https://issues.apache.org/jira/browse/NUTCH-2763) - protocol-okhttp (store.http.headers): add whitespace in status line after status code also when message is empty
- [NUTCH-2770](https://issues.apache.org/jira/browse/NUTCH-2770) - Subcollection logic allows empty string as a whitelist value, thus matching every incoming document.
- [NUTCH-2778](https://issues.apache.org/jira/browse/NUTCH-2778) - indexer-elastic to properly log errors
- [NUTCH-2787](https://issues.apache.org/jira/browse/NUTCH-2787) - CrawlDb JSON dump does not export metadata primitive data types correctly
- [NUTCH-2789](https://issues.apache.org/jira/browse/NUTCH-2789) - Documentation: update links to point to cwiki
- [NUTCH-2790](https://issues.apache.org/jira/browse/NUTCH-2790) - CSVIndexWriter does not escape leading quotes properly
- [NUTCH-2791](https://issues.apache.org/jira/browse/NUTCH-2791) - domainstats, protocolstats and crawlcomplete do not handle GCS URLs

### New Feature

- [NUTCH-1863](https://issues.apache.org/jira/browse/NUTCH-1863) - Add JSON format dump output to readdb command

### Improvement

- [NUTCH-1194](https://issues.apache.org/jira/browse/NUTCH-1194) - Generator: CrawlDB lock should be released earlier
- [NUTCH-2002](https://issues.apache.org/jira/browse/NUTCH-2002) - ParserChecker and IndexingFiltersChecker to check robots.txt
- [NUTCH-2184](https://issues.apache.org/jira/browse/NUTCH-2184) - Enable IndexingJob to function with no crawldb
- [NUTCH-2495](https://issues.apache.org/jira/browse/NUTCH-2495) - Use -deleteGone instead of clean job in crawler script while indexing
- [NUTCH-2496](https://issues.apache.org/jira/browse/NUTCH-2496) - Speed up link inversion step in crawling script
- [NUTCH-2501](https://issues.apache.org/jira/browse/NUTCH-2501) - allow to set Java heap size when using crawl script in distributed mode
- [NUTCH-2649](https://issues.apache.org/jira/browse/NUTCH-2649) - Optionally skip TLS/SSL certificate validation for protocol-selenium and protocol-htmlunit
- [NUTCH-2733](https://issues.apache.org/jira/browse/NUTCH-2733) - protocol-okhttp: add support for Brotli compression (Content-Encoding)
- [NUTCH-2739](https://issues.apache.org/jira/browse/NUTCH-2739) - indexer-elastic: Upgrade ES and migrate to REST client
- [NUTCH-2743](https://issues.apache.org/jira/browse/NUTCH-2743) - Add list of Nutch properties (nutch-default.xml) to documentation
- [NUTCH-2746](https://issues.apache.org/jira/browse/NUTCH-2746) - Basic URL normalizer to normalize Unicode domain names
- [NUTCH-2747](https://issues.apache.org/jira/browse/NUTCH-2747) - Replace remaining o.a.commons.logging by org.slf4j
- [NUTCH-2750](https://issues.apache.org/jira/browse/NUTCH-2750) - Improve CrawlDbReader & LinkDbReader reader handling
- [NUTCH-2752](https://issues.apache.org/jira/browse/NUTCH-2752) - indexer-solr: Upgrade to latest Solr version
- [NUTCH-2755](https://issues.apache.org/jira/browse/NUTCH-2755) - Remove obsolete plugin indexer-elastic-rest
- [NUTCH-2757](https://issues.apache.org/jira/browse/NUTCH-2757) - indexer-elastic: add authentication options
- [NUTCH-2758](https://issues.apache.org/jira/browse/NUTCH-2758) - Add plugin READMEs to binary release packages
- [NUTCH-2759](https://issues.apache.org/jira/browse/NUTCH-2759) - bin/crawl: Rename option --num-slaves
- [NUTCH-2762](https://issues.apache.org/jira/browse/NUTCH-2762) - Replace http:// URLs by https:// (build files and documentation)
- [NUTCH-2767](https://issues.apache.org/jira/browse/NUTCH-2767) - Fetcher to stop filling queues skipped due to repeated exceptions
- [NUTCH-2768](https://issues.apache.org/jira/browse/NUTCH-2768) - FetcherThread: unnecessary usage of class casts
- [NUTCH-2772](https://issues.apache.org/jira/browse/NUTCH-2772) - Debugging parse filter to show serialized DOM tree
- [NUTCH-2773](https://issues.apache.org/jira/browse/NUTCH-2773) - SegmentReader (-dump or -get): show HTML content as UTF-8
- [NUTCH-2774](https://issues.apache.org/jira/browse/NUTCH-2774) - Annotate methods implementing the Hadoop API by @Override
- [NUTCH-2775](https://issues.apache.org/jira/browse/NUTCH-2775) - Fetcher to guarantee minimum delay even if robots.txt defines shorter Crawl-delay
- [NUTCH-2776](https://issues.apache.org/jira/browse/NUTCH-2776) - Fetcher to temporarily deduplicate followed redirects
- [NUTCH-2777](https://issues.apache.org/jira/browse/NUTCH-2777) - Upgrade to Hadoop 3.1
- [NUTCH-2779](https://issues.apache.org/jira/browse/NUTCH-2779) - Upgrade to Tika 1.24.1
- [NUTCH-2780](https://issues.apache.org/jira/browse/NUTCH-2780) - Upgrade index-solr to use Solr 8.5.1
- [NUTCH-2781](https://issues.apache.org/jira/browse/NUTCH-2781) - Increase default Java heap size
- [NUTCH-2783](https://issues.apache.org/jira/browse/NUTCH-2783) - Use (more) parametrized logging
- [NUTCH-2784](https://issues.apache.org/jira/browse/NUTCH-2784) - Add tool to list Nutch and Hadoop properties
- [NUTCH-2785](https://issues.apache.org/jira/browse/NUTCH-2785) - FreeGenerator: command-line option to define number of generated fetch lists
- [NUTCH-2788](https://issues.apache.org/jira/browse/NUTCH-2788) - ParseData: improve presentation of Metadata in method toString()
- [NUTCH-2794](https://issues.apache.org/jira/browse/NUTCH-2794) - Add additional ciphers to HTTP base's default cipher suite

### Test

- [NUTCH-1945](https://issues.apache.org/jira/browse/NUTCH-1945) - Test for XLSX parser

### Task

- [NUTCH-2434](https://issues.apache.org/jira/browse/NUTCH-2434) - Add methods to reset parameters HTMLMetaTags

### Sub-task

- [NUTCH-2735](https://issues.apache.org/jira/browse/NUTCH-2735) - Update the indexer-solr documentation about the schema.xml usage


## Nutch 1.16 Release 02/10/2019 (dd/mm/yyyy)
Release Report: https://s.apache.org/l2j94

### Comments

- schema.xml has been moved to indexer-solr plugin directory. This file is provided as a
  reference/guide for Solr users (NUTCH-2654)

### Breaking Changes

- The value of crawl.gen.delay is now read in milliseconds as stated in the description in nutch-default.xml.
  Previously, the value has been read in days, see [NUTCH-1842](https://issues.apache.org/jira/browse/NUTCH-1842)
  for further information.

- HostDB entries have been moved from Integer to Long in order to accomodate very large
  hosts. Remove your existing HostDB and recreate it with bin/nutch updatehostdb, see
  [NUTCH-2694](https://issues.apache.org/jira/browse/NUTCH-2694) for additional information.

- The signature class TextProfileSignature has been improved to be stable over
  consecutive runs by sorting tokens by frequency first and secondarily in lexicographic
  order.  If an existing CrawlDb contains signatures generated by TextProfileSignature
  these are likely to change when upgrading to Nutch 1.16.  The previous behavior relying
  on a semi-stable pseudo-random hash sorting could be restored setting the property
  `db.signature.text_profile.sec_sort_lex` to `false`. See also NUTCH-2381.

### Bug

- [NUTCH-1063](https://issues.apache.org/jira/browse/NUTCH-1063) - OutlinkExtractor test generates an exception but does not fail
- [NUTCH-1842](https://issues.apache.org/jira/browse/NUTCH-1842) - crawl.gen.delay has a wrong default value in nutch-default.xml or is being parsed incorrectly
- [NUTCH-2279](https://issues.apache.org/jira/browse/NUTCH-2279) - LinkRank fails when using Hadoop MR output compression
- [NUTCH-2381](https://issues.apache.org/jira/browse/NUTCH-2381) - In some situations the class TextProfileSignature gives different signatures for the same text "profile" page.
- [NUTCH-2387](https://issues.apache.org/jira/browse/NUTCH-2387) - Nutch should not index document with "noindex" meta
- [NUTCH-2457](https://issues.apache.org/jira/browse/NUTCH-2457) - Embedded documents likely not correctly parsed by Tika
- [NUTCH-2475](https://issues.apache.org/jira/browse/NUTCH-2475) - If and else-if branches has the same condition
- [NUTCH-2482](https://issues.apache.org/jira/browse/NUTCH-2482) - index-geoip not to add null values to document fields
- [NUTCH-2585](https://issues.apache.org/jira/browse/NUTCH-2585) - NPE in TrieStringMatcher
- [NUTCH-2598](https://issues.apache.org/jira/browse/NUTCH-2598) - URLNormalizerChecker fails on invalid URLs in input
- [NUTCH-2606](https://issues.apache.org/jira/browse/NUTCH-2606) - MIME detection is wrong for plain-text documents send as Content-Type "application/msword"
- [NUTCH-2635](https://issues.apache.org/jira/browse/NUTCH-2635) - Generator writes unneeded temporary output
- [NUTCH-2639](https://issues.apache.org/jira/browse/NUTCH-2639) - bin/nutch fails to set native library path on Cygwin causing jobs to fail with UnsatisfiedLinkError
- [NUTCH-2641](https://issues.apache.org/jira/browse/NUTCH-2641) - ClassCastException in webui
- [NUTCH-2642](https://issues.apache.org/jira/browse/NUTCH-2642) - MoreIndexingFilter parses ISO 8601 UTC dates in local time zone
- [NUTCH-2643](https://issues.apache.org/jira/browse/NUTCH-2643) - ant target "resolve-default" to depend on "init"
- [NUTCH-2644](https://issues.apache.org/jira/browse/NUTCH-2644) - CrawlDbReader -dump ignores filter options
- [NUTCH-2645](https://issues.apache.org/jira/browse/NUTCH-2645) - Webgraph tools ignore command-line options
- [NUTCH-2650](https://issues.apache.org/jira/browse/NUTCH-2650) - -addBinaryContent -base64 flags are causing "String length must be a multiple of four" error in IndexingJob
- [NUTCH-2652](https://issues.apache.org/jira/browse/NUTCH-2652) - Fetcher launches more fetch tasks than fetch lists
- [NUTCH-2655](https://issues.apache.org/jira/browse/NUTCH-2655) - Update Solr schema.xml for Solr 7.x
- [NUTCH-2656](https://issues.apache.org/jira/browse/NUTCH-2656) - Update description to configure Solr 7.x in tutorial
- [NUTCH-2673](https://issues.apache.org/jira/browse/NUTCH-2673) - EOFException protocol-http
- [NUTCH-2674](https://issues.apache.org/jira/browse/NUTCH-2674) - HostDb: dump shows wrong column headers
- [NUTCH-2680](https://issues.apache.org/jira/browse/NUTCH-2680) - Documentation: https supported by multiple protocol plugins not only httpclient
- [NUTCH-2687](https://issues.apache.org/jira/browse/NUTCH-2687) - Regex for reading title from Content-Disposition is wrong
- [NUTCH-2694](https://issues.apache.org/jira/browse/NUTCH-2694) - HostDB to aggregate by long instead of integer
- [NUTCH-2696](https://issues.apache.org/jira/browse/NUTCH-2696) - Nutch SegmentReader does not dump non-ASCII characters with Hadoop 3.x
- [NUTCH-2699](https://issues.apache.org/jira/browse/NUTCH-2699) - Protocol-okhttp: needless loops to increment requested bytes counter when more content is already buffered
- [NUTCH-2703](https://issues.apache.org/jira/browse/NUTCH-2703) - parse-tika: Boilerpipe should not run for non-(X)HTML pages
- [NUTCH-2706](https://issues.apache.org/jira/browse/NUTCH-2706) - -addBinaryContent flag can cause "String length must be a multiple of four" error in IndexingJob
- [NUTCH-2715](https://issues.apache.org/jira/browse/NUTCH-2715) - WARCExporter fails on large records
- [NUTCH-2716](https://issues.apache.org/jira/browse/NUTCH-2716) - protocol-http: Response headers are not stored for a compressed response
- [NUTCH-2717](https://issues.apache.org/jira/browse/NUTCH-2717) - Generator cannot open hostDB
- [NUTCH-2722](https://issues.apache.org/jira/browse/NUTCH-2722) - Fetch dependencies via https
- [NUTCH-2723](https://issues.apache.org/jira/browse/NUTCH-2723) - Indexer Solr not to decode URLs before deletion
- [NUTCH-2724](https://issues.apache.org/jira/browse/NUTCH-2724) - Metadata indexer not to emit empty values
- [NUTCH-2729](https://issues.apache.org/jira/browse/NUTCH-2729) - protocol-okhttp: fix marking of truncated content
- [NUTCH-2731](https://issues.apache.org/jira/browse/NUTCH-2731) - Solr Cleanup Step Fails when Authentication is Required
- [NUTCH-2738](https://issues.apache.org/jira/browse/NUTCH-2738) - Generator: document property generate.restrict.status
- [NUTCH-2740](https://issues.apache.org/jira/browse/NUTCH-2740) - Generator: generate.max.count overflow not logged

### New Feature

- [NUTCH-2676](https://issues.apache.org/jira/browse/NUTCH-2676) - Update to the latest selenium and add code to use chrome and firefox headless mode with the remote web driver

### Improvement

- [NUTCH-1014](https://issues.apache.org/jira/browse/NUTCH-1014) - Migrate from Apache ORO to java.util.regex
- [NUTCH-1021](https://issues.apache.org/jira/browse/NUTCH-1021) - Migrate OutlinkExtractor from Apache ORO to java.util.regex
- [NUTCH-1982](https://issues.apache.org/jira/browse/NUTCH-1982) - Make Git ignore IDE project files and add note about IDE setup
- [NUTCH-2460](https://issues.apache.org/jira/browse/NUTCH-2460) - use the headless option of firefox and chrome in protocol-selenium
- [NUTCH-2602](https://issues.apache.org/jira/browse/NUTCH-2602) - Configuration values in the description of index writers
- [NUTCH-2612](https://issues.apache.org/jira/browse/NUTCH-2612) - Support for sitemap processing by hostname
- [NUTCH-2623](https://issues.apache.org/jira/browse/NUTCH-2623) - Fetcher to guarantee delay for same host/domain/ip independent of http/https protocol
- [NUTCH-2625](https://issues.apache.org/jira/browse/NUTCH-2625) - ProtocolFactory.getProtocol(url) may create multiple plugin instances
- [NUTCH-2626](https://issues.apache.org/jira/browse/NUTCH-2626) - bin/crawl: remove option -noParsing from fetch command
- [NUTCH-2627](https://issues.apache.org/jira/browse/NUTCH-2627) - Fetcher to optionally filter URLs
- [NUTCH-2628](https://issues.apache.org/jira/browse/NUTCH-2628) - Fetcher: optionally generate signature of unparsed content
- [NUTCH-2629](https://issues.apache.org/jira/browse/NUTCH-2629) - Documentation for CSV Index Writer
- [NUTCH-2630](https://issues.apache.org/jira/browse/NUTCH-2630) - Fetcher to log skipped records by robots.txt
- [NUTCH-2631](https://issues.apache.org/jira/browse/NUTCH-2631) - KafkaIndexWriter
- [NUTCH-2632](https://issues.apache.org/jira/browse/NUTCH-2632) - protocol-okhttp doesn't accept proxy authentication
- [NUTCH-2633](https://issues.apache.org/jira/browse/NUTCH-2633) - Fix deprecation warnings when building Nutch master branch under JDK 10.0.2+13
- [NUTCH-2647](https://issues.apache.org/jira/browse/NUTCH-2647) - Skip TLS certificate checks in protocol-http plugin
- [NUTCH-2648](https://issues.apache.org/jira/browse/NUTCH-2648) - Make configurable whether TLS/SSL certificates are checked by protocol plugins
- [NUTCH-2651](https://issues.apache.org/jira/browse/NUTCH-2651) - Upgrade to Tika 1.19.1 (from 1.18)
- [NUTCH-2653](https://issues.apache.org/jira/browse/NUTCH-2653) - ProtocolFactory.getProtocol(url) creates separate plugin instances for http/https
- [NUTCH-2654](https://issues.apache.org/jira/browse/NUTCH-2654) - Remove obsolete index-writer configuration in conf/
- [NUTCH-2657](https://issues.apache.org/jira/browse/NUTCH-2657) - Protocol-http to store HTTP response header with "\r\n"
- [NUTCH-2658](https://issues.apache.org/jira/browse/NUTCH-2658) - Add README file to all plugins in src/plugin
- [NUTCH-2659](https://issues.apache.org/jira/browse/NUTCH-2659) - Add missing Apache license headers
- [NUTCH-2660](https://issues.apache.org/jira/browse/NUTCH-2660) - Unit tests of plugins parse-js, headings, index-jexl-filter to be executed during build
- [NUTCH-2661](https://issues.apache.org/jira/browse/NUTCH-2661) - Move TestOutlinks to the proper path
- [NUTCH-2663](https://issues.apache.org/jira/browse/NUTCH-2663) - Improve index-jexl-filter syntax for scripts
- [NUTCH-2666](https://issues.apache.org/jira/browse/NUTCH-2666) - Increase default value for http.content.limit / ftp.content.limit / file.content.limit
- [NUTCH-2668](https://issues.apache.org/jira/browse/NUTCH-2668) - Integrate OWASP dependency checks as ant target
- [NUTCH-2678](https://issues.apache.org/jira/browse/NUTCH-2678) - Allow for per-host configurable protocol plugin
- [NUTCH-2682](https://issues.apache.org/jira/browse/NUTCH-2682) - Upgrade to Tika 1.20
- [NUTCH-2683](https://issues.apache.org/jira/browse/NUTCH-2683) - DeduplicationJob: add option to prefer https:// over http://
- [NUTCH-2686](https://issues.apache.org/jira/browse/NUTCH-2686) - Separate field for mime types mapped by index-more plugin
- [NUTCH-2688](https://issues.apache.org/jira/browse/NUTCH-2688) - Unify the licence headers
- [NUTCH-2689](https://issues.apache.org/jira/browse/NUTCH-2689) - Speed up urlfilter-regex and urlfilter-automaton
- [NUTCH-2690](https://issues.apache.org/jira/browse/NUTCH-2690) - Configurable and fast URL filter
- [NUTCH-2691](https://issues.apache.org/jira/browse/NUTCH-2691) - Improve logging from scoring-depth plugin
- [NUTCH-2692](https://issues.apache.org/jira/browse/NUTCH-2692) - Subcollection to support case-insensitive white and black lists
- [NUTCH-2693](https://issues.apache.org/jira/browse/NUTCH-2693) - Misspelled configuration property names in documentation
- [NUTCH-2695](https://issues.apache.org/jira/browse/NUTCH-2695) - Fix some alerts raised by LGTM
- [NUTCH-2700](https://issues.apache.org/jira/browse/NUTCH-2700) - Indexchecker: improve command-line help
- [NUTCH-2701](https://issues.apache.org/jira/browse/NUTCH-2701) - Fetcher: log dates and times also in human-readable form
- [NUTCH-2702](https://issues.apache.org/jira/browse/NUTCH-2702) - Fetcher: suppress stack for frequent exceptions
- [NUTCH-2704](https://issues.apache.org/jira/browse/NUTCH-2704) - Upgrade crawler-commons dependency to 1.0
- [NUTCH-2708](https://issues.apache.org/jira/browse/NUTCH-2708) - urlfilter-automaton: update library dependency (dk.brics.automaton)
- [NUTCH-2709](https://issues.apache.org/jira/browse/NUTCH-2709) - Remove unused properties and code related to HTTP protocol
- [NUTCH-2718](https://issues.apache.org/jira/browse/NUTCH-2718) - Names of index writers and exchanges configuration files to be configurable
- [NUTCH-2719](https://issues.apache.org/jira/browse/NUTCH-2719) - NPE if exchanges.xml uses index writer not available
- [NUTCH-2725](https://issues.apache.org/jira/browse/NUTCH-2725) - Plugin lib-http to support per-host configurable cookies
- [NUTCH-2726](https://issues.apache.org/jira/browse/NUTCH-2726) - Upgrade to Tika 1.22
- [NUTCH-2727](https://issues.apache.org/jira/browse/NUTCH-2727) - Upgrade Hadoop dependencies to 2.9.2
- [NUTCH-2728](https://issues.apache.org/jira/browse/NUTCH-2728) - protocol-okhttp: upgrade okhttp dependency to 3.14.2
- [NUTCH-2732](https://issues.apache.org/jira/browse/NUTCH-2732) - Ignored and tracked configuration files by git
- [NUTCH-2736](https://issues.apache.org/jira/browse/NUTCH-2736) - Upgrade Dockerfile to be based on recent Ubuntu LTS version
- [NUTCH-2737](https://issues.apache.org/jira/browse/NUTCH-2737) - Generator: count and log reason of rejections during selection

### Task

- [NUTCH-2192](https://issues.apache.org/jira/browse/NUTCH-2192) - Get rid of oro
- [NUTCH-2613](https://issues.apache.org/jira/browse/NUTCH-2613) - Documentation for exchange component
- [NUTCH-2698](https://issues.apache.org/jira/browse/NUTCH-2698) - Remove sonar build task from build.xml

### Sub-task

- [NUTCH-1121](https://issues.apache.org/jira/browse/NUTCH-1121) - JUnit test for parse-js
- [NUTCH-2621](https://issues.apache.org/jira/browse/NUTCH-2621) - Generate report of third-party licenses
- [NUTCH-2684](https://issues.apache.org/jira/browse/NUTCH-2684) - Add README.md file to all indexer writers plugins
- [NUTCH-2685](https://issues.apache.org/jira/browse/NUTCH-2685) - Add README.md file to all exchange plugins


## Nutch 1.15 Release (25/07/2018)
Release Report: https://s.apache.org/nczS

### Breaking Changes

- indexer plugins are now configured in a single XML file (conf/index-writers.xml),
  see https://cwiki.apache.org/confluence/display/NUTCH/IndexWriters - setting or overwriting configuration
  parameters via Nutch properties is not possible anymore.

### Bug

- [NUTCH-1993](https://issues.apache.org/jira/browse/NUTCH-1993) - Nutch does not use backup parsers
- [NUTCH-2071](https://issues.apache.org/jira/browse/NUTCH-2071) - A parser failure on a single document may fail crawling job if parser.timeout=-1
- [NUTCH-2145](https://issues.apache.org/jira/browse/NUTCH-2145) - parse/index checker fail to fetch valid percent-encoded URLs
- [NUTCH-2161](https://issues.apache.org/jira/browse/NUTCH-2161) - Interrupted failed and/or killed tasks fail to clean up temp directories in HDFS
- [NUTCH-2273](https://issues.apache.org/jira/browse/NUTCH-2273) - Selenium and InteractiveSelenium Do Not Support HTTPS
- [NUTCH-2310](https://issues.apache.org/jira/browse/NUTCH-2310) - Protocol-Selenium does not support HTTPS protocol
- [NUTCH-2321](https://issues.apache.org/jira/browse/NUTCH-2321) - Indexing filter checker leaks threads
- [NUTCH-2324](https://issues.apache.org/jira/browse/NUTCH-2324) - Issue in setting default linkdb path
- [NUTCH-2447](https://issues.apache.org/jira/browse/NUTCH-2447) - Work-around SSLProtocolException: handshake alert: unrecognized_name
- [NUTCH-2454](https://issues.apache.org/jira/browse/NUTCH-2454) - REST API fix for usage of hostdb in generator
- [NUTCH-2461](https://issues.apache.org/jira/browse/NUTCH-2461) - Generate passes the data to when maxCount == 0
- [NUTCH-2466](https://issues.apache.org/jira/browse/NUTCH-2466) - Sitemap processor to follow redirects
- [NUTCH-2467](https://issues.apache.org/jira/browse/NUTCH-2467) - Sitemap type field can be null
- [NUTCH-2485](https://issues.apache.org/jira/browse/NUTCH-2485) - ParserFactory swallows exception
- [NUTCH-2486](https://issues.apache.org/jira/browse/NUTCH-2486) - Compiler Warning: Unchecked / unsafe operations in MimeTypeIndexingFilter
- [NUTCH-2489](https://issues.apache.org/jira/browse/NUTCH-2489) - Dependency collision with lucene-analyzers-common in scoring-similarity plugin
- [NUTCH-2490](https://issues.apache.org/jira/browse/NUTCH-2490) - Sitemap processing: Sitemap index files not working
- [NUTCH-2494](https://issues.apache.org/jira/browse/NUTCH-2494) - Fetcher: java.lang.IllegalArgumentException: Wrong FS: s3
- [NUTCH-2499](https://issues.apache.org/jira/browse/NUTCH-2499) - Elastic REST Indexer: Duplicate values
- [NUTCH-2505](https://issues.apache.org/jira/browse/NUTCH-2505) - nutch does not delete the .locked file, when the generator partition got an exception
- [NUTCH-2508](https://issues.apache.org/jira/browse/NUTCH-2508) - Misleading documentation about http.proxy.exception.list
- [NUTCH-2509](https://issues.apache.org/jira/browse/NUTCH-2509) - Inconsistent behavior in SitemapProcessor
- [NUTCH-2513](https://issues.apache.org/jira/browse/NUTCH-2513) - ant eclipse target fails with "protocol switch unsafe"
- [NUTCH-2517](https://issues.apache.org/jira/browse/NUTCH-2517) - mergesegs corrupts segment data
- [NUTCH-2518](https://issues.apache.org/jira/browse/NUTCH-2518) - Must check return value of job.waitForCompletion()
- [NUTCH-2520](https://issues.apache.org/jira/browse/NUTCH-2520) - Wrong Accept-Charset sent when http.accept.charset is not defined
- [NUTCH-2521](https://issues.apache.org/jira/browse/NUTCH-2521) - SitemapProcessor to use property sitemap.redir.max
- [NUTCH-2523](https://issues.apache.org/jira/browse/NUTCH-2523) - UpdateHostDB blocks usage of plugins unintentionally
- [NUTCH-2524](https://issues.apache.org/jira/browse/NUTCH-2524) - bin/crawl: fix check for HostDb in distributed mode
- [NUTCH-2533](https://issues.apache.org/jira/browse/NUTCH-2533) - Injector: NullPointerException if seed URL dir contains non-file entries
- [NUTCH-2535](https://issues.apache.org/jira/browse/NUTCH-2535) - CrawlDbReader -stats: ClassCastException
- [NUTCH-2544](https://issues.apache.org/jira/browse/NUTCH-2544) - Nutch 1.15 no longer compatible with AWS EMR and S3
- [NUTCH-2547](https://issues.apache.org/jira/browse/NUTCH-2547) - urlnormalizer-basic fails on special characters in path/query
- [NUTCH-2549](https://issues.apache.org/jira/browse/NUTCH-2549) - protocol-http does not behave the same as browsers
- [NUTCH-2550](https://issues.apache.org/jira/browse/NUTCH-2550) - Fetcher fails to follow redirects
- [NUTCH-2551](https://issues.apache.org/jira/browse/NUTCH-2551) - NullPointerException in generator
- [NUTCH-2552](https://issues.apache.org/jira/browse/NUTCH-2552) - CrawlDbReader -topN fails
- [NUTCH-2553](https://issues.apache.org/jira/browse/NUTCH-2553) - Fetcher not to modify URLs to be fetched
- [NUTCH-2554](https://issues.apache.org/jira/browse/NUTCH-2554) - parserchecker can't fetch some URLs
- [NUTCH-2565](https://issues.apache.org/jira/browse/NUTCH-2565) - MergeDB incorrectly handles unfetched CrawlDatums
- [NUTCH-2568](https://issues.apache.org/jira/browse/NUTCH-2568) - Caught exception is immediately rethrown
- [NUTCH-2569](https://issues.apache.org/jira/browse/NUTCH-2569) - ClassNotFoundException when running in (pseudo-)distributed mode
- [NUTCH-2570](https://issues.apache.org/jira/browse/NUTCH-2570) - Deduplication job fails to install deduplicated CrawlDb
- [NUTCH-2571](https://issues.apache.org/jira/browse/NUTCH-2571) - SegmentReader -list fails to read segment
- [NUTCH-2572](https://issues.apache.org/jira/browse/NUTCH-2572) - HostDb: updatehostdb does not set values
- [NUTCH-2574](https://issues.apache.org/jira/browse/NUTCH-2574) - Generator: hostCount >= maxCount comparison wrong
- [NUTCH-2581](https://issues.apache.org/jira/browse/NUTCH-2581) - Caching of redirected robots.txt may overwrite correct robots.txt rules
- [NUTCH-2589](https://issues.apache.org/jira/browse/NUTCH-2589) - HTML redirections are not followed when using parse-tika
- [NUTCH-2590](https://issues.apache.org/jira/browse/NUTCH-2590) - SegmentReader -get fails
- [NUTCH-2592](https://issues.apache.org/jira/browse/NUTCH-2592) - Fetcher to log reason of failed fetches
- [NUTCH-2593](https://issues.apache.org/jira/browse/NUTCH-2593) - Single mode doesn't work in RabbitMQ indexer
- [NUTCH-2597](https://issues.apache.org/jira/browse/NUTCH-2597) - NPE in updatehostdb
- [NUTCH-2601](https://issues.apache.org/jira/browse/NUTCH-2601) - Elasticsearch Rest and Amazon CloudSearch have the same implementation class in indexer-writers.xml
- [NUTCH-2607](https://issues.apache.org/jira/browse/NUTCH-2607) - ParserChecker should call ScoringFilters.passScoreAfterParsing() on all parses
- [NUTCH-2609](https://issues.apache.org/jira/browse/NUTCH-2609) - urlnormalizer-basic to normalize path of file: URLs
- [NUTCH-2614](https://issues.apache.org/jira/browse/NUTCH-2614) - NPE in CrawlDbReader -stats on empty CrawlDb
- [NUTCH-2616](https://issues.apache.org/jira/browse/NUTCH-2616) - Review routing of deletions by Exchange component
- [NUTCH-2618](https://issues.apache.org/jira/browse/NUTCH-2618) - protocol-okhttp not to use http.timeout for max duration to fetch document
- [NUTCH-2620](https://issues.apache.org/jira/browse/NUTCH-2620) - urlfilter-validator incorrectly assumes that top-level domains are not longer than 4 characters
- [NUTCH-2624](https://issues.apache.org/jira/browse/NUTCH-2624) - protocol-okhttp resource leak

### New Feature

- [NUTCH-1129](https://issues.apache.org/jira/browse/NUTCH-1129) - Any23 Nutch plugin
- [NUTCH-1541](https://issues.apache.org/jira/browse/NUTCH-1541) - Indexer plugin to write CSV
- [NUTCH-2412](https://issues.apache.org/jira/browse/NUTCH-2412) - Exchange component for indexing job
- [NUTCH-2492](https://issues.apache.org/jira/browse/NUTCH-2492) - Add more configuration parameters to crawl script

### Improvement

- [NUTCH-1106](https://issues.apache.org/jira/browse/NUTCH-1106) - Options to skip url's based on length
- [NUTCH-1480](https://issues.apache.org/jira/browse/NUTCH-1480) - SolrIndexer to write to multiple servers.
- [NUTCH-2012](https://issues.apache.org/jira/browse/NUTCH-2012) - Merge parsechecker and indexchecker
- [NUTCH-2375](https://issues.apache.org/jira/browse/NUTCH-2375) - Upgrade the code base from org.apache.hadoop.mapred to org.apache.hadoop.mapreduce
- [NUTCH-2390](https://issues.apache.org/jira/browse/NUTCH-2390) - No documentation on pluggable indexing
- [NUTCH-2411](https://issues.apache.org/jira/browse/NUTCH-2411) - Index-metadata to support indexing multiple values for a field
- [NUTCH-2416](https://issues.apache.org/jira/browse/NUTCH-2416) - Fetcher to log thread ID
- [NUTCH-2432](https://issues.apache.org/jira/browse/NUTCH-2432) - Protocol httpclient to disable cookies if http.enable.cookie.header is false
- [NUTCH-2441](https://issues.apache.org/jira/browse/NUTCH-2441) - ARG_SEGMENT usage
- [NUTCH-2491](https://issues.apache.org/jira/browse/NUTCH-2491) - Integrate sitemap processing and HostDB into crawl script
- [NUTCH-2493](https://issues.apache.org/jira/browse/NUTCH-2493) - Add configuration parameter for sitemap processing to crawler script
- [NUTCH-2497](https://issues.apache.org/jira/browse/NUTCH-2497) - Elastic REST Indexer: Allow multiple hosts
- [NUTCH-2502](https://issues.apache.org/jira/browse/NUTCH-2502) - Any23 Plugin: Add Content-Type filtering
- [NUTCH-2503](https://issues.apache.org/jira/browse/NUTCH-2503) - Add option to run tests for a single plugin
- [NUTCH-2510](https://issues.apache.org/jira/browse/NUTCH-2510) - Crawl script modification. HostDb : generate, optional usage and description
- [NUTCH-2516](https://issues.apache.org/jira/browse/NUTCH-2516) - Hadoop imports use wildcards
- [NUTCH-2519](https://issues.apache.org/jira/browse/NUTCH-2519) - Log mapreduce job counters in local mode
- [NUTCH-2526](https://issues.apache.org/jira/browse/NUTCH-2526) - NPE in scoring-opic when indexing document without CrawlDb datum
- [NUTCH-2527](https://issues.apache.org/jira/browse/NUTCH-2527) - URL filter: provide rules to exclude localhost and private address spaces
- [NUTCH-2530](https://issues.apache.org/jira/browse/NUTCH-2530) - Rename property db.max.anchor.length > linkdb.max.anchor.length
- [NUTCH-2534](https://issues.apache.org/jira/browse/NUTCH-2534) - CrawlDbReader -stats: make score quantiles configurable
- [NUTCH-2539](https://issues.apache.org/jira/browse/NUTCH-2539) - Not correct naming of db.url.filters and db.url.normalizers in nutch-default.xml
- [NUTCH-2543](https://issues.apache.org/jira/browse/NUTCH-2543) - readdb & readlinkdb to implement AbstractChecker
- [NUTCH-2545](https://issues.apache.org/jira/browse/NUTCH-2545) - Upgrade to Any23 2.2
- [NUTCH-2566](https://issues.apache.org/jira/browse/NUTCH-2566) - Fix exception log messages
- [NUTCH-2576](https://issues.apache.org/jira/browse/NUTCH-2576) - HTTP protocol plugin based on okhttp
- [NUTCH-2577](https://issues.apache.org/jira/browse/NUTCH-2577) - protocol-selenium can't handle https
- [NUTCH-2578](https://issues.apache.org/jira/browse/NUTCH-2578) - Avoid lock by MimeUtil in constructor of protocol.Content
- [NUTCH-2579](https://issues.apache.org/jira/browse/NUTCH-2579) - Fetcher to use parsed URL to call ProtocolFactory.getProtocol(url)
- [NUTCH-2580](https://issues.apache.org/jira/browse/NUTCH-2580) - Improvements for Rabbitmq support
- [NUTCH-2583](https://issues.apache.org/jira/browse/NUTCH-2583) - Upgrading Nutch's dependencies
- [NUTCH-2584](https://issues.apache.org/jira/browse/NUTCH-2584) - Upgrade parse-tika to use Tika 1.18
- [NUTCH-2594](https://issues.apache.org/jira/browse/NUTCH-2594) - Documentation for indexer plugins
- [NUTCH-2595](https://issues.apache.org/jira/browse/NUTCH-2595) - Upgrade crawler-commons dependency to 0.10
- [NUTCH-2600](https://issues.apache.org/jira/browse/NUTCH-2600) - Refactoring indexer-solr
- [NUTCH-2611](https://issues.apache.org/jira/browse/NUTCH-2611) - Add line-breaks when parsing HTML block-level elements
- [NUTCH-2617](https://issues.apache.org/jira/browse/NUTCH-2617) - Disable Exchange component by default
- [NUTCH-2619](https://issues.apache.org/jira/browse/NUTCH-2619) - protocol-okhttp: allow to keep partially fetched docs as truncated

### Task

- [NUTCH-1219](https://issues.apache.org/jira/browse/NUTCH-1219) - Upgrade all jobs to new MapReduce API
- [NUTCH-1228](https://issues.apache.org/jira/browse/NUTCH-1228) - Change mapred.task.timeout to mapreduce.task.timeout in fetcher

### Sub-task

- [NUTCH-1223](https://issues.apache.org/jira/browse/NUTCH-1223) - Migrate WebGraph to MapReduce API
- [NUTCH-1224](https://issues.apache.org/jira/browse/NUTCH-1224) - Migrate FreeGenerator to MapReduce API
- [NUTCH-1226](https://issues.apache.org/jira/browse/NUTCH-1226) - Migrate CrawlDbReader to MapReduce API
- [NUTCH-2152](https://issues.apache.org/jira/browse/NUTCH-2152) - CommonCrawl dump via Service endpoint
- [NUTCH-2555](https://issues.apache.org/jira/browse/NUTCH-2555) - URL normalization problem: path not starting with a '/'
- [NUTCH-2556](https://issues.apache.org/jira/browse/NUTCH-2556) - protocol-http makes invalid HTTP/1.0 requests
- [NUTCH-2557](https://issues.apache.org/jira/browse/NUTCH-2557) - protocol-http fails to follow redirections when an HTTP response body is invalid
- [NUTCH-2558](https://issues.apache.org/jira/browse/NUTCH-2558) - protocol-http cannot handle a missing HTTP status line
- [NUTCH-2559](https://issues.apache.org/jira/browse/NUTCH-2559) - protocol-http cannot handle colons after the HTTP status code
- [NUTCH-2560](https://issues.apache.org/jira/browse/NUTCH-2560) - protocol-http throws an error when an http header spans over multiple lines
- [NUTCH-2561](https://issues.apache.org/jira/browse/NUTCH-2561) - protocol-http can be made to read arbitrarily large HTTP responses
- [NUTCH-2562](https://issues.apache.org/jira/browse/NUTCH-2562) - protocol-http fails to read large chunked HTTP responses
- [NUTCH-2563](https://issues.apache.org/jira/browse/NUTCH-2563) - HTTP header spellchecking issues
- [NUTCH-2575](https://issues.apache.org/jira/browse/NUTCH-2575) - protocol-http does not respect the maximum content-size for chunked responses
- [NUTCH-2622](https://issues.apache.org/jira/browse/NUTCH-2622) - Unbundle LGPL-licensed jars from binary release


## Nutch 1.14 Release 18/12/2017 (dd/mm/yyyy)

### Breaking Changes

- the bin/crawl script now expects the path to the seed to be preceded by -s
  ([NUTCH-2046](https://issues.apache.org/jira/browse/NUTCH-2046))

### Bug

- [NUTCH-2071](https://issues.apache.org/jira/browse/NUTCH-2071) - A parser failure on a single document may fail crawling job
- [NUTCH-2235](https://issues.apache.org/jira/browse/NUTCH-2235) - Classpath discrepancy with protocol-selenium in deploy mode
- [NUTCH-2269](https://issues.apache.org/jira/browse/NUTCH-2269) - Clean not working after crawl
- [NUTCH-2295](https://issues.apache.org/jira/browse/NUTCH-2295) - Nutch master docker container broken
- [NUTCH-2297](https://issues.apache.org/jira/browse/NUTCH-2297) - CrawlDbReader -stats wrong values for earliest fetch time and shortest interval
- [NUTCH-2316](https://issues.apache.org/jira/browse/NUTCH-2316) - Library conflict with Parser-Tika Plugin and Lib Folder
- [NUTCH-2317](https://issues.apache.org/jira/browse/NUTCH-2317) - Plugin jars don't get added to classpath while running in local
- [NUTCH-2322](https://issues.apache.org/jira/browse/NUTCH-2322) - URL not available for Jexl operations
- [NUTCH-2354](https://issues.apache.org/jira/browse/NUTCH-2354) - Upgrade Hadoop dependencies to 2.7.4
- [NUTCH-2365](https://issues.apache.org/jira/browse/NUTCH-2365) - HTTP Redirects to SubDomains don't get crawled if db.ignore.external.links.mode == byDomain
- [NUTCH-2371](https://issues.apache.org/jira/browse/NUTCH-2371) - Injector to support noFilter and noNormalize
- [NUTCH-2372](https://issues.apache.org/jira/browse/NUTCH-2372) - Javadocs build failing.
- [NUTCH-2386](https://issues.apache.org/jira/browse/NUTCH-2386) - BasicURLNormalizer does not encode curly braces
- [NUTCH-2391](https://issues.apache.org/jira/browse/NUTCH-2391) - Spurious Duplications for MD5
- [NUTCH-2394](https://issues.apache.org/jira/browse/NUTCH-2394) - Possible bugs in the source code
- [NUTCH-2398](https://issues.apache.org/jira/browse/NUTCH-2398) - Fetcher saving redirected robots.txt under redirect target URL
- [NUTCH-2399](https://issues.apache.org/jira/browse/NUTCH-2399) - indexer-elastic does not index multi-value fields (only the first value is indexed)
- [NUTCH-2401](https://issues.apache.org/jira/browse/NUTCH-2401) - headings plugin does not trim values
- [NUTCH-2403](https://issues.apache.org/jira/browse/NUTCH-2403) - Nutch Selenium: Wrong documentation about PhantomJS
- [NUTCH-2413](https://issues.apache.org/jira/browse/NUTCH-2413) - Parsing fetcher to respect property "parse.filter.urls"
- [NUTCH-2420](https://issues.apache.org/jira/browse/NUTCH-2420) - Bug in variable generate.max.count and fetcher.server.delay
- [NUTCH-2436](https://issues.apache.org/jira/browse/NUTCH-2436) - Remove empty comment, and redundant semicolon from CommandRunner
- [NUTCH-2442](https://issues.apache.org/jira/browse/NUTCH-2442) - Injector to stop if job fails to avoid loss of CrawlDb
- [NUTCH-2444](https://issues.apache.org/jira/browse/NUTCH-2444) - HostDB CSV dumper to emit field header by default
- [NUTCH-2446](https://issues.apache.org/jira/browse/NUTCH-2446) - URLFiltersCheck fix
- [NUTCH-2448](https://issues.apache.org/jira/browse/NUTCH-2448) - Allow Sending an empty http.agent.version
- [NUTCH-2451](https://issues.apache.org/jira/browse/NUTCH-2451) - protocol-ftp to resolve relative URL when following redirects
- [NUTCH-2452](https://issues.apache.org/jira/browse/NUTCH-2452) - Problem retrieving encoded URLs via FTP?
- [NUTCH-2456](https://issues.apache.org/jira/browse/NUTCH-2456) - Allow to index pages/URLs not contained in CrawlDb
- [NUTCH-2458](https://issues.apache.org/jira/browse/NUTCH-2458) - TikaParser doesn't work with tika-config.xml set
- [NUTCH-2464](https://issues.apache.org/jira/browse/NUTCH-2464) - Plugin headings: Headers That Contain HTML Elements Are Not Parsed
- [NUTCH-2465](https://issues.apache.org/jira/browse/NUTCH-2465) - Broken Eclipse project. Classpaths and interactiveselenium should be fixed.
- [NUTCH-2472](https://issues.apache.org/jira/browse/NUTCH-2472) - Sitemap processor does not honour db.ignore.external.links
- [NUTCH-2473](https://issues.apache.org/jira/browse/NUTCH-2473) - Elasticsearch REST Indexer broken due to wrong depenency
- [NUTCH-2474](https://issues.apache.org/jira/browse/NUTCH-2474) - CrawlDbReader -stats fails with ClassCastException
- [NUTCH-2478](https://issues.apache.org/jira/browse/NUTCH-2478) - // is not a valid base URL
- [NUTCH-2483](https://issues.apache.org/jira/browse/NUTCH-2483) - Remove/replace indirect dependencies to org.json

### Improvement

- [NUTCH-1763](https://issues.apache.org/jira/browse/NUTCH-1763) - Improving comments on the Injector Class
- [NUTCH-2034](https://issues.apache.org/jira/browse/NUTCH-2034) - CrawlDB filtered documents counter.
- [NUTCH-2035](https://issues.apache.org/jira/browse/NUTCH-2035) - Regex filter using case sensitive rules.
- [NUTCH-2046](https://issues.apache.org/jira/browse/NUTCH-2046) - The crawl script should be able to skip an initial injection.
- [NUTCH-2135](https://issues.apache.org/jira/browse/NUTCH-2135) - Ant Eclipse build does not include protocol-interactiveselenium
- [NUTCH-2193](https://issues.apache.org/jira/browse/NUTCH-2193) - Upgrade feed parser plugin to use rome 1.5
- [NUTCH-2216](https://issues.apache.org/jira/browse/NUTCH-2216) - db.ignore.*.links to optionally follow internal redirects
- [NUTCH-2281](https://issues.apache.org/jira/browse/NUTCH-2281) - Support non-default FileSystem
- [NUTCH-2296](https://issues.apache.org/jira/browse/NUTCH-2296) - Elasticsearch Indexing Over Rest
- [NUTCH-2320](https://issues.apache.org/jira/browse/NUTCH-2320) - URLFilterChecker to run as TCP Telnet service
- [NUTCH-2335](https://issues.apache.org/jira/browse/NUTCH-2335) - Injector not to filter and normalize existing URLs in CrawlDb
- [NUTCH-2362](https://issues.apache.org/jira/browse/NUTCH-2362) - Upgrade MaxMind GeoIP version in index-geoip
- [NUTCH-2368](https://issues.apache.org/jira/browse/NUTCH-2368) - Variable generate.max.count and fetcher.server.delay
- [NUTCH-2370](https://issues.apache.org/jira/browse/NUTCH-2370) - FileDumper: save JSON mapping file -> URL
- [NUTCH-2376](https://issues.apache.org/jira/browse/NUTCH-2376) - Improve configurability of HTTP Accept* header fields
- [NUTCH-2378](https://issues.apache.org/jira/browse/NUTCH-2378) - ChildFirst plugin classloader
- [NUTCH-2380](https://issues.apache.org/jira/browse/NUTCH-2380) - indexer-elastic version upgrade to 5.3.0
- [NUTCH-2397](https://issues.apache.org/jira/browse/NUTCH-2397) - Parser to add paragraph line breaks
- [NUTCH-2400](https://issues.apache.org/jira/browse/NUTCH-2400) - Solr 6.6.0 compatibility
- [NUTCH-2406](https://issues.apache.org/jira/browse/NUTCH-2406) - Sum up constants, make minor changes
- [NUTCH-2408](https://issues.apache.org/jira/browse/NUTCH-2408) - CrawlDb: allow update from unparsed segments
- [NUTCH-2409](https://issues.apache.org/jira/browse/NUTCH-2409) - Injector: complete command-line help and counters
- [NUTCH-2414](https://issues.apache.org/jira/browse/NUTCH-2414) - Allow LanguageIndexingFilter to actually filter documents by language.
- [NUTCH-2430](https://issues.apache.org/jira/browse/NUTCH-2430) - Complete plugin build configuration
- [NUTCH-2431](https://issues.apache.org/jira/browse/NUTCH-2431) - URLFilterchecker to implement Tool-interface
- [NUTCH-2439](https://issues.apache.org/jira/browse/NUTCH-2439) - Upgrade to Apache Tika 1.17
- [NUTCH-2443](https://issues.apache.org/jira/browse/NUTCH-2443) - Extract links from the video tag with the parse-html plugin
- [NUTCH-2445](https://issues.apache.org/jira/browse/NUTCH-2445) - Fetcher following outlinks to keep track of already fetched items
- [NUTCH-2463](https://issues.apache.org/jira/browse/NUTCH-2463) - Enable sampling CrawlDB
- [NUTCH-2468](https://issues.apache.org/jira/browse/NUTCH-2468) - should filter out invalid URLs by default
- [NUTCH-2470](https://issues.apache.org/jira/browse/NUTCH-2470) - CrawlDbReader -stats to show quantiles of score
- [NUTCH-2477](https://issues.apache.org/jira/browse/NUTCH-2477) - Refactor *Checker classes to use base class for common code
- [NUTCH-2480](https://issues.apache.org/jira/browse/NUTCH-2480) - Upgrade crawler-commons dependency to 0.9

### New Feature

- [NUTCH-1465](https://issues.apache.org/jira/browse/NUTCH-1465) - Support sitemaps in Nutch
- [NUTCH-1932](https://issues.apache.org/jira/browse/NUTCH-1932) - Automatically remove orphaned pages
- [NUTCH-2333](https://issues.apache.org/jira/browse/NUTCH-2333) - Indexer for RabbitMQ
- [NUTCH-2338](https://issues.apache.org/jira/browse/NUTCH-2338) - URLNormalizerChecker to run as TCP Telnet service
- [NUTCH-2415](https://issues.apache.org/jira/browse/NUTCH-2415) - Create a JEXL based IndexingFilter
- [NUTCH-2433](https://issues.apache.org/jira/browse/NUTCH-2433) - Html Parser: keep htmltag where the outlinks are found
- [NUTCH-2435](https://issues.apache.org/jira/browse/NUTCH-2435) - New configuration allowing to choose whether to store 'parse_text' directory or not.
- [NUTCH-2484](https://issues.apache.org/jira/browse/NUTCH-2484) - Extend indexer-elastic-rest to support languages

### Task

- [NUTCH-2181](https://issues.apache.org/jira/browse/NUTCH-2181) - Add Webpage for 3rd Party Connectors/Libraries to Apache Nutch


## Nutch 1.13 Release 28/03/2017 (dd/mm/yyyy)
Release Report: https://s.apache.org/wq3x

### Sub-task

- [NUTCH-2246](https://issues.apache.org/jira/browse/NUTCH-2246) - Refactor /seed endpoint for backward compatibility

### Bug

- [NUTCH-1553](https://issues.apache.org/jira/browse/NUTCH-1553) - Property 'indexer.delete.robots.noindex' not working when using parser-html.
- [NUTCH-2242](https://issues.apache.org/jira/browse/NUTCH-2242) - lastModified not always set
- [NUTCH-2291](https://issues.apache.org/jira/browse/NUTCH-2291) - Fix mrunit dependencies
- [NUTCH-2337](https://issues.apache.org/jira/browse/NUTCH-2337) - urlnormalizer-basic to strip empty port
- [NUTCH-2345](https://issues.apache.org/jira/browse/NUTCH-2345) - FetchItemQueue logs are logged with wrong class name
- [NUTCH-2349](https://issues.apache.org/jira/browse/NUTCH-2349) - urlnormalizer-basic NPE for ill-formed URL "http:/"
- [NUTCH-2357](https://issues.apache.org/jira/browse/NUTCH-2357) - Index metadata throw Exception because writable object cannot be cast to Text
- [NUTCH-2359](https://issues.apache.org/jira/browse/NUTCH-2359) - Parsefilter-regex raises IndexOutOfBoundsException when rules are ill-formed
- [NUTCH-2364](https://issues.apache.org/jira/browse/NUTCH-2364) - http.agent.rotate: IllegalArgumentException / last element of agent names ignored
- [NUTCH-2366](https://issues.apache.org/jira/browse/NUTCH-2366) - Deprecated Job constructor in hostdb/ReadHostDb.java

### Improvement

- [NUTCH-1308](https://issues.apache.org/jira/browse/NUTCH-1308) - Add main() to ZipParser
- [NUTCH-2164](https://issues.apache.org/jira/browse/NUTCH-2164) - Inconsistent 'Modified Time' in crawl db
- [NUTCH-2234](https://issues.apache.org/jira/browse/NUTCH-2234) - Upgrade to elasticsearch 2.3.3
- [NUTCH-2236](https://issues.apache.org/jira/browse/NUTCH-2236) - Upgrade to Hadoop 2.7.2
- [NUTCH-2262](https://issues.apache.org/jira/browse/NUTCH-2262) - Utilize parameterized logging notation across Fetcher
- [NUTCH-2272](https://issues.apache.org/jira/browse/NUTCH-2272) - Index checker server to optionally keep client connection open
- [NUTCH-2286](https://issues.apache.org/jira/browse/NUTCH-2286) - CrawlDbReader -stats to show fetch time and interval
- [NUTCH-2287](https://issues.apache.org/jira/browse/NUTCH-2287) - Indexer-elastic plugin should use Elasticsearch BulkProcessor and BackoffPolicy
- [NUTCH-2299](https://issues.apache.org/jira/browse/NUTCH-2299) - Remove obsolete properties protocol.plugin.check.*
- [NUTCH-2300](https://issues.apache.org/jira/browse/NUTCH-2300) - Fetcher to optionally save robots.txt
- [NUTCH-2327](https://issues.apache.org/jira/browse/NUTCH-2327) - Seeds injected in REST workflow must be ingested into HDFS
- [NUTCH-2329](https://issues.apache.org/jira/browse/NUTCH-2329) - Update Slf4j logging for Java 8 and upgrade miredot plugin version
- [NUTCH-2336](https://issues.apache.org/jira/browse/NUTCH-2336) - SegmentReader to implement Tool
- [NUTCH-2352](https://issues.apache.org/jira/browse/NUTCH-2352) - Log with Generic Class Name at Nutch 1.x
- [NUTCH-2355](https://issues.apache.org/jira/browse/NUTCH-2355) - Protocol plugins to set cookie if Cookie metadata field is present
- [NUTCH-2367](https://issues.apache.org/jira/browse/NUTCH-2367) - Get single record from HostDB

### New Feature

- [NUTCH-2132](https://issues.apache.org/jira/browse/NUTCH-2132) - Publisher/Subscriber model for Nutch to emit events

### Task

- [NUTCH-2171](https://issues.apache.org/jira/browse/NUTCH-2171) - Upgrade Nutch Trunk to Java 1.8

 
## Nutch 1.12 Release 28/05/2016 (dd/mm/yyyy)
Release Report: https://s.apache.org/nutch1.12

### Breaking Changes

Fellow committers, Nutch 1.12 contains a breaking change [NUTCH-2220](https://issues.apache.org/jira/browse/NUTCH-2220).
Please use the note below and in the release announcement and keep it on top in this CHANGES.txt for the Nutch 1.12 release.

* replace your old conf/nutch-default.xml with the conf/nutch-default.xml from Nutch 1.12 release
* if you use LinkDB (e.g. invertlinks) and modified parameters db.max.inlinks and/or db.max.anchor.length
  and/or db.ignore.internal.links, rename those parameters to linkdb.max.inlinks and
  linkdb.max.anchor.length and linkdb.ignore.internal.links
* db.ignore.internal.links and db.ignore.external.links now operate on the CrawlDB only
* linkdb.ignore.internal.links and linkdb.ignore.external.links now operate on the LinkDB only

### Sub-task

- [NUTCH-2250](https://issues.apache.org/jira/browse/NUTCH-2250) - CommonCrawlDumper : Invalid format + skipped parts

### Bug

- [NUTCH-2042](https://issues.apache.org/jira/browse/NUTCH-2042) - parse-html increase chunk size used to detect charset
- [NUTCH-2180](https://issues.apache.org/jira/browse/NUTCH-2180) - FileDumper dumps data, but breaks midway on corrupt segments
- [NUTCH-2189](https://issues.apache.org/jira/browse/NUTCH-2189) - Domain filter must deactivate if no rules are present
- [NUTCH-2203](https://issues.apache.org/jira/browse/NUTCH-2203) - Suffix URL filter can't handle trailing/leading whitespaces
- [NUTCH-2206](https://issues.apache.org/jira/browse/NUTCH-2206) - Provide example scoring.similarity.stopword.file
- [NUTCH-2213](https://issues.apache.org/jira/browse/NUTCH-2213) - CommonCrawlDataDumper saves gzipped body in extracted form
- [NUTCH-2223](https://issues.apache.org/jira/browse/NUTCH-2223) - Upgrade xercesImpl to 2.11.0 to fix hang on issue in tika mimetype detection
- [NUTCH-2224](https://issues.apache.org/jira/browse/NUTCH-2224) - Average bytes/second calculated incorrectly in fetcher
- [NUTCH-2225](https://issues.apache.org/jira/browse/NUTCH-2225) - Parsed time calculated incorrectly
- [NUTCH-2228](https://issues.apache.org/jira/browse/NUTCH-2228) - Plugin index-replace unit test broken on Java 8
- [NUTCH-2232](https://issues.apache.org/jira/browse/NUTCH-2232) - DeduplicationJob should decode URL's before length is compared
- [NUTCH-2241](https://issues.apache.org/jira/browse/NUTCH-2241) - Unstable Selenium plugin in Nutch. Fixed bugs and enhanced configuration
- [NUTCH-2256](https://issues.apache.org/jira/browse/NUTCH-2256) - Inconsistent log level practice

### Improvement

- [NUTCH-1233](https://issues.apache.org/jira/browse/NUTCH-1233) - Rely on Tika for outlink extraction
- [NUTCH-1712](https://issues.apache.org/jira/browse/NUTCH-1712) - Use MultipleInputs in Injector to make it a single mapreduce job
- [NUTCH-2172](https://issues.apache.org/jira/browse/NUTCH-2172) - index-more: document format of contenttype-mapping.txt
- [NUTCH-2178](https://issues.apache.org/jira/browse/NUTCH-2178) - DeduplicationJob to optionally group on host or domain
- [NUTCH-2182](https://issues.apache.org/jira/browse/NUTCH-2182) - Make reverseUrlDirs file dumper option hash the URL for consistency
- [NUTCH-2183](https://issues.apache.org/jira/browse/NUTCH-2183) - Improvement to SegmentChecker for skipping non-segments present in segments directory
- [NUTCH-2187](https://issues.apache.org/jira/browse/NUTCH-2187) - Change FileDumper SHAs to all uppercase
- [NUTCH-2195](https://issues.apache.org/jira/browse/NUTCH-2195) - IndexingFilterChecker to optionally follow N redirects
- [NUTCH-2196](https://issues.apache.org/jira/browse/NUTCH-2196) - IndexingFilterChecker to optionally normalize
- [NUTCH-2197](https://issues.apache.org/jira/browse/NUTCH-2197) - Add solr5 solrcloud indexer support
- [NUTCH-2204](https://issues.apache.org/jira/browse/NUTCH-2204) - Remove junit lib from runtime
- [NUTCH-2218](https://issues.apache.org/jira/browse/NUTCH-2218) - Switch CrawlCompletion arg parsing to Commons CLI
- [NUTCH-2221](https://issues.apache.org/jira/browse/NUTCH-2221) - Introduce db.ignore.internal.links to FetcherThread
- [NUTCH-2229](https://issues.apache.org/jira/browse/NUTCH-2229) - Allow Jexl expressions on CrawlDatum's fixed attributes
- [NUTCH-2231](https://issues.apache.org/jira/browse/NUTCH-2231) - Jexl support in generator job
- [NUTCH-2252](https://issues.apache.org/jira/browse/NUTCH-2252) - Allow phantomjs as a browser for selenium options
- [NUTCH-2263](https://issues.apache.org/jira/browse/NUTCH-2263) - Support for mingram and maxgram at Unigram Cosine Similarity Model

### New Feature

- [NUTCH-961](https://issues.apache.org/jira/browse/NUTCH-961) - Expose Tika's boilerpipe support
- [NUTCH-1325](https://issues.apache.org/jira/browse/NUTCH-1325) - HostDB for Nutch
- [NUTCH-2144](https://issues.apache.org/jira/browse/NUTCH-2144) - Plugin to override db.ignore.external to exempt interesting external domain URLs
- [NUTCH-2190](https://issues.apache.org/jira/browse/NUTCH-2190) - Protocol normalizer
- [NUTCH-2191](https://issues.apache.org/jira/browse/NUTCH-2191) - Add protocol-htmlunit
- [NUTCH-2194](https://issues.apache.org/jira/browse/NUTCH-2194) - Run IndexingFilterChecker as simple Telnet server
- [NUTCH-2219](https://issues.apache.org/jira/browse/NUTCH-2219) - Criteria order to be configurable in DeduplicationJob
- [NUTCH-2227](https://issues.apache.org/jira/browse/NUTCH-2227) - RegexParseFilter
- [NUTCH-2245](https://issues.apache.org/jira/browse/NUTCH-2245) - Developed the NGram Model on the existing Unigram Cosine Similarity Model

### Task

- [NUTCH-2201](https://issues.apache.org/jira/browse/NUTCH-2201) - Remove loops program from webgraph package
- [NUTCH-2211](https://issues.apache.org/jira/browse/NUTCH-2211) - Filter and normalizer checkers missing in bin/nutch
- [NUTCH-2220](https://issues.apache.org/jira/browse/NUTCH-2220) - Rename db.* options used only by the linkdb to linkdb.*


## Nutch 1.11 Release 03/12/2015 (dd/mm/yyyy)
Release Report: http://s.apache.org/nutch11

- [NUTCH-2176](https://issues.apache.org/jira/browse/NUTCH-2176) - Clean up of log4j.properties (markus)

- [NUTCH-2107](https://issues.apache.org/jira/browse/NUTCH-2107) - plugin.xml to validate against plugin.dtd (snagel)

- [NUTCH-2177](https://issues.apache.org/jira/browse/NUTCH-2177) - Generator produces only one partition even in distributed mode (jnioche, snagel)

- [NUTCH-2158](https://issues.apache.org/jira/browse/NUTCH-2158) - Upgrade to Tika 1.11 (jnioche, snagel)

- [NUTCH-2175](https://issues.apache.org/jira/browse/NUTCH-2175) - Typos in property descriptions in nutch-default.xml (Roannel Fernández Hernández via snagel)

- [NUTCH-2069](https://issues.apache.org/jira/browse/NUTCH-2069) - Ignore external links based on domain (jnioche)

- [NUTCH-2173](https://issues.apache.org/jira/browse/NUTCH-2173) - String.join in FileDumper breaks the build (joyce)

- [NUTCH-2166](https://issues.apache.org/jira/browse/NUTCH-2166) - Add reverse URL format to dump tool (joyce)

- [NUTCH-2157](https://issues.apache.org/jira/browse/NUTCH-2157) - Addressing Miredot REST API Warnings (Sujen Shah)

- [NUTCH-2165](https://issues.apache.org/jira/browse/NUTCH-2165) - FileDumper Util hard codes part-# folder name (joyce)

- [NUTCH-2167](https://issues.apache.org/jira/browse/NUTCH-2167) - Backport TableUtil from 2.x for URL reversing (joyce)

- [NUTCH-2160](https://issues.apache.org/jira/browse/NUTCH-2160) - Upgrade Selenium Java to 2.48.2 (lewismc, kwhitehall)

- [NUTCH-2120](https://issues.apache.org/jira/browse/NUTCH-2120) - Remove MapWritable from trunk codebase (lewismc)

- [NUTCH-1911](https://issues.apache.org/jira/browse/NUTCH-1911) - Improve DomainStatistics tool command line parsing (joyce)

- [NUTCH-2064](https://issues.apache.org/jira/browse/NUTCH-2064) - URLNormalizer basic to encode reserved chars and decode non-reserved chars (markus, snagel)

- [NUTCH-2159](https://issues.apache.org/jira/browse/NUTCH-2159) - Ensure that all WebApp files are copied into generated artifacts for 1.X Webapp (lewismc)

- [NUTCH-2154](https://issues.apache.org/jira/browse/NUTCH-2154) - Nutch REST API (DB) suffering NullPointerException (Aron Ahmadia, Sujen Shah via mattmann)

- [NUTCH-2150](https://issues.apache.org/jira/browse/NUTCH-2150) - Add protocolstats utility (Michael Joyce via mattmann)

- [NUTCH-2146](https://issues.apache.org/jira/browse/NUTCH-2146) - hashCode on the Outlink class (jorgelbg via mattmann)

- [NUTCH-2155](https://issues.apache.org/jira/browse/NUTCH-2155) - Create a "crawl completeness" utility (Michael Joyce via mattmann)

- [NUTCH-1988](https://issues.apache.org/jira/browse/NUTCH-1988) - Make nested output directory dump optional... again (Michael Joyce via lewismc)

- [NUTCH-1800](https://issues.apache.org/jira/browse/NUTCH-1800) - Documentation for Nutch 1.X and 2.X REST APIs (lewismc)

- [NUTCH-2149](https://issues.apache.org/jira/browse/NUTCH-2149) - REST endpoint to read Nutch sequence files (Sujen Shah)

- [NUTCH-2139](https://issues.apache.org/jira/browse/NUTCH-2139) - Basic plugin to index inlinks and outlinks (jorgelbg)

- [NUTCH-2128](https://issues.apache.org/jira/browse/NUTCH-2128) - Review and update mapred --> mapreduce config params in crawl script (lewismc)

- [NUTCH-2141](https://issues.apache.org/jira/browse/NUTCH-2141) - Change the InteractiveSelenium plugin handler Interface to return page content (Balaji Gurumurthy via mattmann)

- [NUTCH-2129](https://issues.apache.org/jira/browse/NUTCH-2129) - Add protocol status tracking to crawl datum (Michael Joyce via mattmann)

- [NUTCH-2142](https://issues.apache.org/jira/browse/NUTCH-2142) - Nutch File Dump - FileNotFoundException (Invalid Argument) Error (Karanjeet Singh via mattmann)

- [NUTCH-2136](https://issues.apache.org/jira/browse/NUTCH-2136) - Implement a different version of Naive Bayes Parse Filter (Asitang Mishra)

- [NUTCH-2109](https://issues.apache.org/jira/browse/NUTCH-2109) - Create a brute force click-all-ajax-links utility fucntion for selenium interactive plugin (Asitang Mishra)

- [NUTCH-2108](https://issues.apache.org/jira/browse/NUTCH-2108) - Add a function to the selenium interactive plugin interface to do multiple manipulation of driver and then return the data (Asitang Mishra)

- [NUTCH-2124](https://issues.apache.org/jira/browse/NUTCH-2124) - Fetcher following same redirect again and again (Yogendra Kumar Soni via snagel)

- [NUTCH-2123](https://issues.apache.org/jira/browse/NUTCH-2123) - Seed List REST API returns Text but headers indicate/require JSON (Aron Ahmadia, Sujen Shah via mattmann)

- [NUTCH-2086](https://issues.apache.org/jira/browse/NUTCH-2086) - Nutch 1.X Webui (Sujen Shah, mattmann via lewismc)

- [NUTCH-2121](https://issues.apache.org/jira/browse/NUTCH-2121) - Update javadoc link for Hadoop 2.4.0 in default.properties (Sujen Shah)

- [NUTCH-2119](https://issues.apache.org/jira/browse/NUTCH-2119) - Eclipse shows build path errors on building Nutch (Sujen Shah)

- [NUTCH-2117](https://issues.apache.org/jira/browse/NUTCH-2117) - NutchServer CLI Option for CMD_PORT is incorrect and should be CMD_HOST (zhangmianhongni via lewismc)

- [NUTCH-2115](https://issues.apache.org/jira/browse/NUTCH-2115) - Add total counts to mimetype stats (Jimmy Joyce via lewismc)

- [NUTCH-2111](https://issues.apache.org/jira/browse/NUTCH-2111) - Delete temporary files location for selenium tmp files after driver quits (Kim Whitehall via lewismc)

- [NUTCH-2095](https://issues.apache.org/jira/browse/NUTCH-2095) - WARC exporter for the CommonCrawlDataDumper (jorgelbg)

- [NUTCH-2102](https://issues.apache.org/jira/browse/NUTCH-2102) - WARC Exporter (jnioche)

- [NUTCH-2106](https://issues.apache.org/jira/browse/NUTCH-2106) - Runtime to contain Selenium and dependencies only once (snagel)

- [NUTCH-2104](https://issues.apache.org/jira/browse/NUTCH-2104) - Add documentation to the protocol-selenium plugin Readme file re: selenium grid implementation (Kim Whitehall via mattmann)

- [NUTCH-2099](https://issues.apache.org/jira/browse/NUTCH-2099) - Refactoring the REST endpoints for integration with webui (Sujen Shah via mattmann)

- [NUTCH-2098](https://issues.apache.org/jira/browse/NUTCH-2098) - Add null SeedUrl constructor (Aron Ahmadia via mattmann)

- [NUTCH-2093](https://issues.apache.org/jira/browse/NUTCH-2093) - Indexing filters to use current signatures (markus)

- [NUTCH-2092](https://issues.apache.org/jira/browse/NUTCH-2092) -: Unit Test for NutchServer (Sujen Shah via mattmann)

- [NUTCH-2096](https://issues.apache.org/jira/browse/NUTCH-2096) - Explicitly indicate broswer binary to use when selecting selenium remote option in config (Kim Whitehall via mattmann)

- [NUTCH-2090](https://issues.apache.org/jira/browse/NUTCH-2090) - Refactor Seed Resource in REST API (Sujen Shah via mattmann)

- [NUTCH-2088](https://issues.apache.org/jira/browse/NUTCH-2088) - Add URL Processing Check to Interactive Selenium Handlers (Michael Joyce via mattmann)

- [NUTCH-2077](https://issues.apache.org/jira/browse/NUTCH-2077) - Upgrade to Tika 1.10 (Michael Joyce via lewismc)

- [NUTCH-1517](https://issues.apache.org/jira/browse/NUTCH-1517) - CloudSearch indexer (jnioche)

- [NUTCH-2085](https://issues.apache.org/jira/browse/NUTCH-2085) - Upgrade Guava (markus)

- [NUTCH-2084](https://issues.apache.org/jira/browse/NUTCH-2084) - SegmentMerger to report missing input dirs (markus)

- [NUTCH-2083](https://issues.apache.org/jira/browse/NUTCH-2083) - Implement functionality to shadow nutch-selenium-grid-plugin from Mo Omer (lewismc)

- [NUTCH-2049](https://issues.apache.org/jira/browse/NUTCH-2049) - Upgrade to Hadoop 2.4 (lewismc)

- [NUTCH-1486](https://issues.apache.org/jira/browse/NUTCH-1486) - Upgrade to Solr 4.10.2 (lewismc, markus)

- [NUTCH-2048](https://issues.apache.org/jira/browse/NUTCH-2048) - parse-tika: fix dependencies in plugin.xml (Michael Joyce via snagel)

- [NUTCH-2066](https://issues.apache.org/jira/browse/NUTCH-2066) - Parameterize Generate REST endpoint (Sujen Shah via mattmann)

- [NUTCH-2072](https://issues.apache.org/jira/browse/NUTCH-2072) - Deflate encoding support is broken when http.content.limit is set to -1 (Tanguy Moal via mattmann)

- [NUTCH-2062](https://issues.apache.org/jira/browse/NUTCH-2062) - Add Plugin for interacting with Selenium WebDriver (Michael Joyce, mattmann)

- [NUTCH-1785](https://issues.apache.org/jira/browse/NUTCH-1785) - Ability to index raw content (markus, lewismc)

- [NUTCH-2063](https://issues.apache.org/jira/browse/NUTCH-2063) - Add -mimeStats flag to FileDumper tool (Mike Joyce via lewismc)

- [NUTCH-2021](https://issues.apache.org/jira/browse/NUTCH-2021) - Use protocol-selenium to Capture Screenshots of the Page as it is Fetched (lewismc)

- [NUTCH-2058](https://issues.apache.org/jira/browse/NUTCH-2058) - Indexer plugin that allows RegEx replacements on the NutchDocument field values (Peter Ciuffetti via mattmann)

- [NUTCH-2059](https://issues.apache.org/jira/browse/NUTCH-2059) - protocol-httpclient, protocol-http unit test errors on Jenkins (Peter Ciuffetti via mattmann)

- [NUTCH-1980](https://issues.apache.org/jira/browse/NUTCH-1980) - Jexl expressions for CrawlDbReader (markus)

- [NUTCH-1692](https://issues.apache.org/jira/browse/NUTCH-1692) - SegmentReader was broken in distributed mode (markus, tejasp)

- [NUTCH-1684](https://issues.apache.org/jira/browse/NUTCH-1684) - ParseMeta to be added before fetch schedulers are run (markus)

- [NUTCH-2038](https://issues.apache.org/jira/browse/NUTCH-2038) - fix for NUTCH-2038: Naive Bayes classifier based html Parse filter (for filtering outlinks) (Asitang Mishra, snagel via mattmann)

- [NUTCH-2041](https://issues.apache.org/jira/browse/NUTCH-2041) - indexer fails if linkdb is missing (snagel)

- [NUTCH-2016](https://issues.apache.org/jira/browse/NUTCH-2016) - Remove unused class OldFetcher (snagel)

- [NUTCH-2000](https://issues.apache.org/jira/browse/NUTCH-2000) - Link inversion fails with .locked already exists (jnioche, snagel)

- [NUTCH-2036](https://issues.apache.org/jira/browse/NUTCH-2036) - Adding some continuous crawl goodies to the crawl script (jorge, snagel)

- [NUTCH-2039](https://issues.apache.org/jira/browse/NUTCH-2039) - Relevance based scoring filter (Sujen Shah, lewismc via mattmann)

- [NUTCH-2037](https://issues.apache.org/jira/browse/NUTCH-2037) - Job endpoint to support Indexing from the REST API (Sujen Shah via mattmann)

- [NUTCH-2017](https://issues.apache.org/jira/browse/NUTCH-2017) - Remove debug log from MimeUtil (snagel)

- [NUTCH-2027](https://issues.apache.org/jira/browse/NUTCH-2027) - seed list REST endpoint for Nutch 1.10 (Asitang Mishra via mattmann)

- [NUTCH-2031](https://issues.apache.org/jira/browse/NUTCH-2031) - Create Admin End point for Nutch 1.x REST service (Sujen Shah via mattmann)

- [NUTCH-2015](https://issues.apache.org/jira/browse/NUTCH-2015) - Make FetchNodeDb optional (off by default) if NutchServer is not used (Sujen Shah via mattmann)

- [NUTCH-208](https://issues.apache.org/jira/browse/NUTCH-208) - http: proxy exception list: (Matthias Günter, siren, markus, lewismc)

- [NUTCH-2007](https://issues.apache.org/jira/browse/NUTCH-2007) - add test libs to classpath of bin/nutch junit (snagel)

- [NUTCH-1995](https://issues.apache.org/jira/browse/NUTCH-1995) - Add support for wildcard to http.robot.rules.whitelist (totaro)

- [NUTCH-2013](https://issues.apache.org/jira/browse/NUTCH-2013) - Fetcher: missing logs "fetching ..." on stdout (snagel)

- [NUTCH-2014](https://issues.apache.org/jira/browse/NUTCH-2014) - Fetcher hang-up on completion (snagel)

- [NUTCH-2011](https://issues.apache.org/jira/browse/NUTCH-2011) - Endpoint to support realtime JSON output from the fetcher (Sujen Shah via mattmann)

- [NUTCH-2006](https://issues.apache.org/jira/browse/NUTCH-2006) - IndexingFiltersChecker to take custom metadata as input (jnioche)

- [NUTCH-2008](https://issues.apache.org/jira/browse/NUTCH-2008) - IndexerMapReduce to use single instance of NutchIndexAction for deletions (snagel)

- [NUTCH-1998](https://issues.apache.org/jira/browse/NUTCH-1998) - Add support for user-defined file extension to CommonCrawlDataDumper (totaro via mattmann)

- [NUTCH-1873](https://issues.apache.org/jira/browse/NUTCH-1873) - Solr IndexWriter/Job to report number of docs indexed. (snagel via lewismc)
 
- [NUTCH-1934](https://issues.apache.org/jira/browse/NUTCH-1934) - Refactor Fetcher in trunk (lewismc)

- [NUTCH-2004](https://issues.apache.org/jira/browse/NUTCH-2004) - ParseChecker does not handle redirects (mjoyce via lewismc)


## Nutch 1.10 Release - 29/04/2015 (dd/mm/yyyy)
Release Report: http://s.apache.org/nutch10

- [NUTCH-1969](https://issues.apache.org/jira/browse/NUTCH-1969) - URL Normalizer properly handling slashes (markus via mattmann)

- [NUTCH-2001](https://issues.apache.org/jira/browse/NUTCH-2001) - Sub Collection Field Name incorrect in nutch-default.xml (Jeff Cocking via mattmann)

- [NUTCH-1997](https://issues.apache.org/jira/browse/NUTCH-1997) - Add CBOR "magic header" to CommonCrawlDataDumper output (Giuseppe Totaro, Luke Sh via mattmann)

- [NUTCH-1991](https://issues.apache.org/jira/browse/NUTCH-1991) - Tika mime detection not using Nutch supplied tika-mimetypes.xml for content based detection (Iain Lopata, snagel via mattmann)

- [NUTCH-1994](https://issues.apache.org/jira/browse/NUTCH-1994) - Upgrade to Apache Tika 1.8 (lewismc)

- [NUTCH-1996](https://issues.apache.org/jira/browse/NUTCH-1996) - Make protocol-selenium README part of plugin (lewismc)

- [NUTCH-1990](https://issues.apache.org/jira/browse/NUTCH-1990) - Use URI.normalise() in BasicURLNormalizer (snagel, jnioche)

- [NUTCH-1973](https://issues.apache.org/jira/browse/NUTCH-1973) - Job Administration end point for the REST service (Sujen Shah via mattmann)

- [NUTCH-1697](https://issues.apache.org/jira/browse/NUTCH-1697) - SegmentMerger to implement Tool (markus, snagel)

- [NUTCH-1987](https://issues.apache.org/jira/browse/NUTCH-1987) - Make bin/crawl indexer agnostic (Michael Joyce, snagel via mattmann)
 
- [NUTCH-1989](https://issues.apache.org/jira/browse/NUTCH-1989) - Handling invalid URLs in CommonCrawlDataDumper (Giuseppe Totaro via mattmann)

- [NUTCH-1988](https://issues.apache.org/jira/browse/NUTCH-1988) - Make nested output directory dump optional (Michael Joyce via mattmann)

- [NUTCH-1927](https://issues.apache.org/jira/browse/NUTCH-1927) - Create a whitelist of IPs/hostnames to allow skipping of RobotRules parsing (mattmann, snagel)

- [NUTCH-1986](https://issues.apache.org/jira/browse/NUTCH-1986) - Clarify Elastic Search Indexer Plugin Settings (Michael Joyce via mattmann)

- [NUTCH-1906](https://issues.apache.org/jira/browse/NUTCH-1906) - Typo in CrawlDbReader command line help (Michael Joyce via mattmann)

- [NUTCH-1911](https://issues.apache.org/jira/browse/NUTCH-1911) - Improve DomainStatistics tool command line parsing (Michael Joyce via mattmann)

- [NUTCH-1854](https://issues.apache.org/jira/browse/NUTCH-1854) - bin/crawl fails with a parsing fetcher (Asitang Mishra via snagel)

- [NUTCH-1981](https://issues.apache.org/jira/browse/NUTCH-1981) - Upgrade to icu4j 55.1 (Marko Asplund via snagel)

- [NUTCH-1960](https://issues.apache.org/jira/browse/NUTCH-1960) - JUnit test for dump method of CommonCrawlDataDumper (Giuseppe Totaro via mattmann)

- [NUTCH-1983](https://issues.apache.org/jira/browse/NUTCH-1983) - CommonCrawlDumper and FileDumper don't dump correct JSON (mattmann)

- [NUTCH-1972](https://issues.apache.org/jira/browse/NUTCH-1972) - Dockerfile for Nutch 1.x (Michael Joyce via mattmann)

- [NUTCH-1771](https://issues.apache.org/jira/browse/NUTCH-1771) - Indexer fails if a segment is corrupted or incomplete (Diaa, Chong Li via snagel) 

- [NUTCH-1975](https://issues.apache.org/jira/browse/NUTCH-1975) - New configuration for CommonCrawlDataDumper tool (Giuseppe Totaro via mattmann)

- [NUTCH-1979](https://issues.apache.org/jira/browse/NUTCH-1979) - CrawlDbReader to implement Tool (markus)

- [NUTCH-1970](https://issues.apache.org/jira/browse/NUTCH-1970) - Pretty print JSON output in config resource (Tyler Pasulich, mattmann)

- [NUTCH-1976](https://issues.apache.org/jira/browse/NUTCH-1976) - Allow Users to Set Hostname for Server (Tyler Palsulich via mattmann)

- [NUTCH-1941](https://issues.apache.org/jira/browse/NUTCH-1941) - Optional rolling http.agent.name's (Asitang Mishra, lewismc via snagel)

- [NUTCH-1959](https://issues.apache.org/jira/browse/NUTCH-1959) - Improving CommonCrawlFormat implementations (Giuseppe Totaro via mattmann)

- [NUTCH-1974](https://issues.apache.org/jira/browse/NUTCH-1974) - keyPrefix option for CommonCrawlDataDumper tool (Giuseppe Totaro via mattmann)

- [NUTCH-1968](https://issues.apache.org/jira/browse/NUTCH-1968) - File Name too long issue of DumpFileUtil.java file (Xin Zhang, Renxia Wang via mattmann)

- [NUTCH-1966](https://issues.apache.org/jira/browse/NUTCH-1966) - Configuration endpoint for 1x REST API (Sujen Shah via mattmann)

- [NUTCH-1967](https://issues.apache.org/jira/browse/NUTCH-1967) - Possible SIooBE in MimeAdaptiveFetchSchedule (markus)

- [NUTCH-1957](https://issues.apache.org/jira/browse/NUTCH-1957) - FileDumper output file name collisions (Renxia Wang via mattmann)

- [NUTCH-1955](https://issues.apache.org/jira/browse/NUTCH-1955) - ByteWritable missing in NutchWritable (markus)

- [NUTCH-1956](https://issues.apache.org/jira/browse/NUTCH-1956) - Members to be public in URLCrawlDatum (markus)
 
- [NUTCH-1954](https://issues.apache.org/jira/browse/NUTCH-1954) - FilenameTooLong error appears in CommonCrawlDumper (mattmann)

- [NUTCH-1949](https://issues.apache.org/jira/browse/NUTCH-1949) - Dump out the Nutch data into the Common Crawl format (Giuseppe Totaro via lewismc)

- [NUTCH-1950](https://issues.apache.org/jira/browse/NUTCH-1950) - File name too long (Jiaheng Zhang, Chong Li via mattmann)

- [NUTCH-1921](https://issues.apache.org/jira/browse/NUTCH-1921) - Optionally disable HTTP if-modified-since header (markus)

- [NUTCH-1933](https://issues.apache.org/jira/browse/NUTCH-1933) - nutch-selenium plugin (Mo Omer, Mohammad Al-Moshin, lewismc)

- [NUTCH-827](https://issues.apache.org/jira/browse/NUTCH-827) - HTTP POST Authentication (Jasper van Veghel, yuanyun.cn, snagel, lewismc)

- [NUTCH-1724](https://issues.apache.org/jira/browse/NUTCH-1724) - LinkDBReader to support regex output filtering (markus)

- [NUTCH-1939](https://issues.apache.org/jira/browse/NUTCH-1939) - Fetcher fails to follow redirects (Leo Ye via snagel)

- [NUTCH-1913](https://issues.apache.org/jira/browse/NUTCH-1913) - LinkDB to implement db.ignore.external.links (markus, snagel)

- [NUTCH-1925](https://issues.apache.org/jira/browse/NUTCH-1925) - Upgrade to Apache Tika 1.7 (Tyler Palsulich via markus)

- [NUTCH-1323](https://issues.apache.org/jira/browse/NUTCH-1323) - AjaxNormalizer (markus)

- [NUTCH-1918](https://issues.apache.org/jira/browse/NUTCH-1918) - TikaParser specifies a default namespace when generating DOM (jnioche)

- [NUTCH-1889](https://issues.apache.org/jira/browse/NUTCH-1889) - Store all values from Tika metadata in Nutch metadata (jnioche)

- [NUTCH-865](https://issues.apache.org/jira/browse/NUTCH-865) - Format source code in unique style (lewismc)

- [NUTCH-1893](https://issues.apache.org/jira/browse/NUTCH-1893) - Parse-tika failes to parse feed files (Mengying Wang via snagel)

- [NUTCH-1920](https://issues.apache.org/jira/browse/NUTCH-1920) - Upgrade Nutch to use Java 1.7 (lewismc)

- [NUTCH-1919](https://issues.apache.org/jira/browse/NUTCH-1919) - Getting timeout when server returns Content-Length: 0 (jnioche)

- [NUTCH-1912](https://issues.apache.org/jira/browse/NUTCH-1912) - Dump tool -mimetype parameter needs to be optional to prevent NPE (Tyler Palsulich via lewismc)

- [NUTCH-1881](https://issues.apache.org/jira/browse/NUTCH-1881) - ant target resolve-default to keep test libs (snagel)

- [NUTCH-1660](https://issues.apache.org/jira/browse/NUTCH-1660) - Index filter for Page's latitude and longitude (Yasin Kılınç, lewismc)

- [NUTCH-1140](https://issues.apache.org/jira/browse/NUTCH-1140) - index-more plugin, resetTitle creates multiple values in title field (Joe Liedtke, kaveh minooie via snagel)

- [NUTCH-1904](https://issues.apache.org/jira/browse/NUTCH-1904) - Schema for Solr4 doesn't include _version_ field (mattmann)

- [NUTCH-1897](https://issues.apache.org/jira/browse/NUTCH-1897) - Easier debugging of plugin XML errors (markus)

- [NUTCH-1823](https://issues.apache.org/jira/browse/NUTCH-1823) - Upgrade to elasticsearch 1.4.1 (Phu Kieu, markus via lewismc)

- [NUTCH-1592](https://issues.apache.org/jira/browse/NUTCH-1592) - TikaParser can uppercase the element names while generating the DOM (jnioche)

- [NUTCH-1877](https://issues.apache.org/jira/browse/NUTCH-1877) - Suffix URL filter to ignore query string by default (markus via snagel)

- [NUTCH-1890](https://issues.apache.org/jira/browse/NUTCH-1890) - Major Typo in Documentation for Integrating Nutch and Solr (Boadu Akoto Charles Jnr, mattmann)

- [NUTCH-1887](https://issues.apache.org/jira/browse/NUTCH-1887) - Specify HTMLMapper to use in TikaParser (jnioche)

- [NUTCH-1884](https://issues.apache.org/jira/browse/NUTCH-1884) - NullPointerException in parsechecker and indexchecker with symlinks in file URL (Mengying Wang, snagel)

- [NUTCH-1825](https://issues.apache.org/jira/browse/NUTCH-1825) - protocol-http may hang for certain web pages (Phu Kieu via snagel)

- [NUTCH-1483](https://issues.apache.org/jira/browse/NUTCH-1483) - Can't crawl filesystem with protocol-file plugin (Rogério Pereira Araújo, Mengying Wang, snagel)

- [NUTCH-1885](https://issues.apache.org/jira/browse/NUTCH-1885) - Protocol-file should treat symbolic links as redirects (Mengying Wang, snagel)

- [NUTCH-1880](https://issues.apache.org/jira/browse/NUTCH-1880) - URLUtil should not add additional slashes for file URLs (snagel)

- [NUTCH-1879](https://issues.apache.org/jira/browse/NUTCH-1879) - Regex URL normalizer should remove multiple slashes after file: protocol (snagel)

- [NUTCH-1883](https://issues.apache.org/jira/browse/NUTCH-1883) - bin/crawl: use function to run bin/nutch and check exit value (snagel)

- [NUTCH-1865](https://issues.apache.org/jira/browse/NUTCH-1865) - Enable use of SNAPSHOT's with Nutch Ivy dependency management (lewismc)

- [NUTCH-1882](https://issues.apache.org/jira/browse/NUTCH-1882) - ant eclipse target to add output path to src/test (snagel)

- [NUTCH-1876](https://issues.apache.org/jira/browse/NUTCH-1876) - Upgrade to Crawler Commons 0.5 (jnioche)

- [NUTCH-1874](https://issues.apache.org/jira/browse/NUTCH-1874) - FileDumper comment typos ( Arthur Cinader via lewismc)

- [NUTCH-1164](https://issues.apache.org/jira/browse/NUTCH-1164) - Write JUnit tests for protocol-http (nimafl via snagel)

- [NUTCH-1868](https://issues.apache.org/jira/browse/NUTCH-1868) - Document and improve CLI for FileDumper tool (lewismc)

- [NUTCH-1869](https://issues.apache.org/jira/browse/NUTCH-1869) - Add a flag to -mimeType fiag to FileDumper (lewismc)

- [NUTCH-1867](https://issues.apache.org/jira/browse/NUTCH-1867) - CrawlDbReader: use setFloat to pass min score (lewismc, snagel)

- [NUTCH-1826](https://issues.apache.org/jira/browse/NUTCH-1826), [NUTCH-1864](https://issues.apache.org/jira/browse/NUTCH-1864) - indexchecker fails if solr.server.url not configured (lewismc, snagel)

- [NUTCH-1866](https://issues.apache.org/jira/browse/NUTCH-1866) - ant eclipse target should not delete runtime (nimafl via lewismc)

- [NUTCH-1857](https://issues.apache.org/jira/browse/NUTCH-1857) - readb -dump -format csv should use comma (lewismc)

- [NUTCH-1853](https://issues.apache.org/jira/browse/NUTCH-1853) - Add commented out WebGraph executions to ./bin/crawl (lewismc)

- [NUTCH-1844](https://issues.apache.org/jira/browse/NUTCH-1844) - testresources/testcrawl not referenced anywhere in code (mattmann)

- [NUTCH-1839](https://issues.apache.org/jira/browse/NUTCH-1839) - Improve WebGraph CLI parsing (lewismc)

- [NUTCH-1526](https://issues.apache.org/jira/browse/NUTCH-1526) - Create SegmentContentDumperTool for easily extracting out file contents from SegmentDirs (mattmann, lewismc, Julien Le Dem)

- [NUTCH-1840](https://issues.apache.org/jira/browse/NUTCH-1840) - the describe function in SolrIndexWriter is not correct (kaveh minooie via jnioche)

- [NUTCH-1837](https://issues.apache.org/jira/browse/NUTCH-1837) - Upgrade to Tika 1.6 (jnioche)

- [NUTCH-1829](https://issues.apache.org/jira/browse/NUTCH-1829) - Generator : unable to distinguish real errors (Mathieu Bouchard via jnioche)
 
- [NUTCH-1835](https://issues.apache.org/jira/browse/NUTCH-1835) - Nutch's Solr schema doesn't work with Solr 4.9 because of the RealTimeGet handler (mattmann)

- [NUTCH-1833](https://issues.apache.org/jira/browse/NUTCH-1833) - Include version number within nutch binary usage statement (Rishi Verma via mattmann)

- [NUTCH-1832](https://issues.apache.org/jira/browse/NUTCH-1832) - Make Nutch work without an indexer (mattmann)

- [NUTCH-1828](https://issues.apache.org/jira/browse/NUTCH-1828) - bin/crawl : incorrect handling of nutch errors (Mathieu Bouchard via jnioche)

- [NUTCH-1775](https://issues.apache.org/jira/browse/NUTCH-1775) - IndexingFilter: document origin of passed CrawlDatum (snagel)

- [NUTCH-1693](https://issues.apache.org/jira/browse/NUTCH-1693) - TextMD5Signature computed on textual content (Tien Nguyen Manh, markus via snagel)

- [NUTCH-1409](https://issues.apache.org/jira/browse/NUTCH-1409) - remove deprecated properties db.{default,max}.fetch.interval, generate.max.per.host.by.ip (Matthias Agethle via snagel)


## Nutch 1.9 Release Change Log - 12/08/2014 (dd/mm/yyyy)
Release Report - http://s.apache.org/1.9-release

- [NUTCH-1561](https://issues.apache.org/jira/browse/NUTCH-1561) - improve usability of parse-metatags and index-metadata (snagel)

- [NUTCH-1708](https://issues.apache.org/jira/browse/NUTCH-1708) - use same id when indexing and deleting redirects (snagel)

- [NUTCH-1818](https://issues.apache.org/jira/browse/NUTCH-1818) - Add deps-test-compile task for building plugins (jnioche)

- [NUTCH-1817](https://issues.apache.org/jira/browse/NUTCH-1817) - Remove pom.xml from source (jnioche)

- [NUTCH-926](https://issues.apache.org/jira/browse/NUTCH-926) - Redirections from META tag don't get filtered (snagel)

- [NUTCH-1422](https://issues.apache.org/jira/browse/NUTCH-1422) - Bypass signature comparison when a document is redirected (snagel)

- [NUTCH-1502](https://issues.apache.org/jira/browse/NUTCH-1502) - Test for CrawlDatum state transitions (snagel)

- [NUTCH-1804](https://issues.apache.org/jira/browse/NUTCH-1804) - Move JUnit dependency to test scope (jnioche)

- [NUTCH-1811](https://issues.apache.org/jira/browse/NUTCH-1811) - bin/nutch junit to use junit 4 test runner (snagel)

- [NUTCH-1799](https://issues.apache.org/jira/browse/NUTCH-1799) - ANT Eclipse task discovers all plugin jars automatically (jnioche)

- [NUTCH-578](https://issues.apache.org/jira/browse/NUTCH-578) - URL fetched with 403 is generated over and over again (snagel)

- [NUTCH-1776](https://issues.apache.org/jira/browse/NUTCH-1776) - Log incorrect plugin.folder file path (Diaa via snagel)

- [NUTCH-1566](https://issues.apache.org/jira/browse/NUTCH-1566) - bin/nutch to allow whitespace in paths (tejasp, snagel)

- [NUTCH-1605](https://issues.apache.org/jira/browse/NUTCH-1605) - MIME type detector recognizes xlsx as zip file (snagel)

- [NUTCH-1802](https://issues.apache.org/jira/browse/NUTCH-1802) - Move TestbedProxy to test environment (jnioche)

- [NUTCH-1803](https://issues.apache.org/jira/browse/NUTCH-1803) - Put test dependencies in a separate lib dir (jnioche)

- [NUTCH-385](https://issues.apache.org/jira/browse/NUTCH-385) - Improve description of thread related configuration for Fetcher (jnioche,lufeng)

- [NUTCH-1633](https://issues.apache.org/jira/browse/NUTCH-1633) - slf4j is provided by hadoop and should not be included in the job file (kaveh minooie via jnioche)

- [NUTCH-1787](https://issues.apache.org/jira/browse/NUTCH-1787) - update and complete API doc overview page (snagel)

- [NUTCH-1767](https://issues.apache.org/jira/browse/NUTCH-1767) - remove special treatment of "params" in relative links (snagel)

- [NUTCH-1718](https://issues.apache.org/jira/browse/NUTCH-1718) - redefine http.robots.agent as "additional agent names" (snagel, Tejas Patil, Daniel Kugel)

- [NUTCH-1794](https://issues.apache.org/jira/browse/NUTCH-1794) - IndexingFilterChecker to optionally dumpText (markus)

- [NUTCH-1590](https://issues.apache.org/jira/browse/NUTCH-1590) - [SECURITY] Frame injection vulnerability in published Javadoc (jnioche)

- [NUTCH-1793](https://issues.apache.org/jira/browse/NUTCH-1793) - HttpRobotRulesParser not configured properly (jnioche)

- [NUTCH-1647](https://issues.apache.org/jira/browse/NUTCH-1647) - protocol-http throws 'unzipBestEffort returned null' for redirected pages (jnioche)

- [NUTCH-1736](https://issues.apache.org/jira/browse/NUTCH-1736) - Can't fetch page if http response header contains Transfer-Encoding：chunked (ysc via jnioche)

- [NUTCH-1782](https://issues.apache.org/jira/browse/NUTCH-1782) - NodeWalker to return current node (markus)

- [NUTCH-1758](https://issues.apache.org/jira/browse/NUTCH-1758) - IndexChecker to send document to IndexWriters (jnioche)

- [NUTCH-1786](https://issues.apache.org/jira/browse/NUTCH-1786) - CrawlDb should follow db.url.normalizers and db.url.filters (Diaa via markus)

- [NUTCH-1757](https://issues.apache.org/jira/browse/NUTCH-1757) - ParserChecker to take custom metadata as input (jnioche)

- [NUTCH-1676](https://issues.apache.org/jira/browse/NUTCH-1676) - Add rudimentary SSL support to protocol-http (jnioche, markus)

- [NUTCH-1772](https://issues.apache.org/jira/browse/NUTCH-1772) - Injector does not need merging if no pre-existing crawldb (jnioche)

- [NUTCH-1752](https://issues.apache.org/jira/browse/NUTCH-1752) - Cache robots.txt rules per protocol:host:port (snagel)

- [NUTCH-1613](https://issues.apache.org/jira/browse/NUTCH-1613) - Timeouts in protocol-httpclient when crawling same host with >2 threads (brian44 via jnioche)

- [NUTCH-1766](https://issues.apache.org/jira/browse/NUTCH-1766) - Generator to unlock crawldb and remove tempdir if generate job fails (Diaa via jnioche)

- [NUTCH-207](https://issues.apache.org/jira/browse/NUTCH-207) - Bandwidth target for fetcher rather than a thread count (jnioche)

- [NUTCH-1182](https://issues.apache.org/jira/browse/NUTCH-1182) - fetcher to log hung threads (snagel)

- [NUTCH-1759](https://issues.apache.org/jira/browse/NUTCH-1759) - Upgrade to Crawler Commons 0.4 (jnioche)

- [NUTCH-1764](https://issues.apache.org/jira/browse/NUTCH-1764) - readdb to show command-line help if no action (-stats, -dump, etc.) given (Diaa via snagel)

- [NUTCH-1700](https://issues.apache.org/jira/browse/NUTCH-1700) - Remove deprecated code from creativecommons plugin (lewismc)

- [NUTCH-1761](https://issues.apache.org/jira/browse/NUTCH-1761) - Crawl script fails to find job file if not started from inside bin dir (David Hosking, jnioche)

- [NUTCH-1603](https://issues.apache.org/jira/browse/NUTCH-1603) - ZIP parser complains about truncated PDF file (snagel)

- [NUTCH-1720](https://issues.apache.org/jira/browse/NUTCH-1720) - Duplicate lines in HttpBase.java (Walter Tietze via jnioche)

- [NUTCH-1750](https://issues.apache.org/jira/browse/NUTCH-1750) - Improvement of Fetcher's reportStatus (jnioche)

- [NUTCH-1747](https://issues.apache.org/jira/browse/NUTCH-1747) - Use AtomicInteger as semaphore in Fetcher (jnioche)

- [NUTCH-1735](https://issues.apache.org/jira/browse/NUTCH-1735) - code dedup fetcher queue redirects (snagel)

- [NUTCH-1745](https://issues.apache.org/jira/browse/NUTCH-1745) - Upgrade to ElasticSearch 1.1.0 (jnioche)

- [NUTCH-1645](https://issues.apache.org/jira/browse/NUTCH-1645) - Junit Test Case for Adaptive Fetch Schedule class (Yasin Kılınç, lufeng, Sertac TURKEL via snagel)

- [NUTCH-1737](https://issues.apache.org/jira/browse/NUTCH-1737) - Upgrade to recent JUnit 4.x (lewismc)

- [NUTCH-1733](https://issues.apache.org/jira/browse/NUTCH-1733) - parse-html to support HTML5 charset definitions (snagel)

- [NUTCH-1671](https://issues.apache.org/jira/browse/NUTCH-1671) - indexchecker to add digest field (snagel, lufeng)


## Nutch 1.8      - 11/03/2014 (dd/mm/yyyy)
Release Report - http://s.apache.org/oHY

- [NUTCH-1706](https://issues.apache.org/jira/browse/NUTCH-1706) - IndexerMapReduce does not remove db_redir_temp (markus, snagel)

- [NUTCH-1113](https://issues.apache.org/jira/browse/NUTCH-1113) - SegmentMerger can now be safely used to merge segments (Edward Drapkin, markus, snagel)

- [NUTCH-1729](https://issues.apache.org/jira/browse/NUTCH-1729) - Upgrade to Tika 1.5 (jnioche)

- [NUTCH-1707](https://issues.apache.org/jira/browse/NUTCH-1707) - DummyIndexingWriter (markus)

- [NUTCH-1721](https://issues.apache.org/jira/browse/NUTCH-1721) - Upgrade to Crawler commons 0.3 (tejasp)

- [NUTCH-1253](https://issues.apache.org/jira/browse/NUTCH-1253) - Incompatable neko and xerces versions (snagel, lewismc)

- [NUTCH-1715](https://issues.apache.org/jira/browse/NUTCH-1715) - RobotRulesParser adds additional '*' to the robots name (tejasp)

- [NUTCH-356](https://issues.apache.org/jira/browse/NUTCH-356) - Plugin repository cache can lead to memory leak (Enrico Triolo, Doğacan Güney via markus)

- [NUTCH-1413](https://issues.apache.org/jira/browse/NUTCH-1413) - Record response time (Yasin Kılınç, Talat Uyarer, snagel)

- [NUTCH-1680](https://issues.apache.org/jira/browse/NUTCH-1680) - CrawlDbReader to dump minRetry value (markus)

- [NUTCH-1699](https://issues.apache.org/jira/browse/NUTCH-1699) - Tika Parser - Image Parse Bug (Mehmet Zahid Yüzügüldü, snagel via lewismc)

- [NUTCH-1695](https://issues.apache.org/jira/browse/NUTCH-1695) - Add NutchDocument.toString() to ease debugging (markus)

- [NUTCH-1675](https://issues.apache.org/jira/browse/NUTCH-1675) - NutchField to support long (markus)

- [NUTCH-1670](https://issues.apache.org/jira/browse/NUTCH-1670) - set same crawldb directory in mergedb parameter (lufeng via tejasp)

- [NUTCH-1080](https://issues.apache.org/jira/browse/NUTCH-1080) - Type safe members, arguments for better readability (tejasp)

- [NUTCH-1360](https://issues.apache.org/jira/browse/NUTCH-1360) - Suport the storing of IP address connected to when web crawling (lewismc, ferdy and Yasin Kılınç)

- [NUTCH-1681](https://issues.apache.org/jira/browse/NUTCH-1681) - In URLUtil.java, toUNICODE method does not work correctly (İlhami KALKAN, snagel via markus)

- [NUTCH-1668](https://issues.apache.org/jira/browse/NUTCH-1668) - Remove package org.apache.nutch.indexer.solr (jnioche)

- [NUTCH-1621](https://issues.apache.org/jira/browse/NUTCH-1621) - Remove deprecated class o.a.n.crawl.Crawler (Rui Gao via jnioche)

- [NUTCH-656](https://issues.apache.org/jira/browse/NUTCH-656) - Generic Deduplicator (jnioche, snagel)

- [NUTCH-1100](https://issues.apache.org/jira/browse/NUTCH-1100) - Avoid NPE in SOLRDedup (markus)

- [NUTCH-1666](https://issues.apache.org/jira/browse/NUTCH-1666) - Optimisation for BasicURLNormalizer (jnioche)

- [NUTCH-1656](https://issues.apache.org/jira/browse/NUTCH-1656) - ParseMeta not passed to CrawlDatum for not_modified (markus)

- [NUTCH-1606](https://issues.apache.org/jira/browse/NUTCH-1606) - Check that Factory classes use the cache in a thread safe way (jnioche)

- [NUTCH-1653](https://issues.apache.org/jira/browse/NUTCH-1653) - AbstractScoringFilter (jnioche)

- [NUTCH-1562](https://issues.apache.org/jira/browse/NUTCH-1562) - Order of execution for scoring filters (jnioche, snagel)

- [NUTCH-1640](https://issues.apache.org/jira/browse/NUTCH-1640) - Reuse ParseUtil instance in ParseSegment (Mitesh Singh Jat via jnioche)

- [NUTCH-1639](https://issues.apache.org/jira/browse/NUTCH-1639) - bin/crawl fails on mac os (various contributors via snagel)

- [NUTCH-1646](https://issues.apache.org/jira/browse/NUTCH-1646) - IndexerMapReduce to consider DB status (markus)

- [NUTCH-1636](https://issues.apache.org/jira/browse/NUTCH-1636) - Indexer to normalize and filter repr URL (Iain Lopata via snagel)

- [NUTCH-1637](https://issues.apache.org/jira/browse/NUTCH-1637) - URLUtil is missing getProtocol (markus)

- [NUTCH-1622](https://issues.apache.org/jira/browse/NUTCH-1622) - Create Outlinks with metadata (jnioche)

- [NUTCH-1629](https://issues.apache.org/jira/browse/NUTCH-1629) - Injector skips empty lines in seed files (kaveh minooie via jnioche)

- [NUTCH-911](https://issues.apache.org/jira/browse/NUTCH-911) - protocol-file to return proper protocol status (Peter Lundberg via snagel)

- [NUTCH-806](https://issues.apache.org/jira/browse/NUTCH-806) - Merge CrawlDBScanner with CrawlDBReader (jnioche)

- [NUTCH-1587](https://issues.apache.org/jira/browse/NUTCH-1587) - misspelled property "threshold" in conf/log4j.properties (snagel)

- [NUTCH-1604](https://issues.apache.org/jira/browse/NUTCH-1604) - ProtocolFactory not thread-safe (jnioche)

- [NUTCH-1595](https://issues.apache.org/jira/browse/NUTCH-1595) - Upgrade to Tika 1.4 (jnioche, markus)

- [NUTCH-1598](https://issues.apache.org/jira/browse/NUTCH-1598) - ElasticSearchIndexer to read ImmutableSettings from config (markus)

- [NUTCH-1520](https://issues.apache.org/jira/browse/NUTCH-1520) - SegmentMerger looses records (markus)

- [NUTCH-1602](https://issues.apache.org/jira/browse/NUTCH-1602) - improve the readability of metadata in readdb dump normal (lufeng)

- [NUTCH-1596](https://issues.apache.org/jira/browse/NUTCH-1596) - HeadingsParseFilter not thread safe (snagel via markus)

- [NUTCH-1597](https://issues.apache.org/jira/browse/NUTCH-1597) - HeadingsParseFilter to trim and remove exess whitespace (markus)

- [NUTCH-1601](https://issues.apache.org/jira/browse/NUTCH-1601) - ElasticSearchIndexer fails to properly delete documents (markus)

- [NUTCH-1600](https://issues.apache.org/jira/browse/NUTCH-1600) - Injector overwrite does not always work properly (markus)

- [NUTCH-1581](https://issues.apache.org/jira/browse/NUTCH-1581) - CrawlDB csv output to include metadata (markus)

- [NUTCH-1327](https://issues.apache.org/jira/browse/NUTCH-1327) - QueryStringNormalizer (markus)

- [NUTCH-1593](https://issues.apache.org/jira/browse/NUTCH-1593) - Normalize option missing in SegmentMerger's usage (markus)

- [NUTCH-1580](https://issues.apache.org/jira/browse/NUTCH-1580) - index-static returns object instead of value for index.static (Antoinette, lewismc, snagel)

- [NUTCH-1126](https://issues.apache.org/jira/browse/NUTCH-1126) - JUnit test for urlfilter-prefix (Talat UYARER via markus)


## Apache Nutch 1.7 Release - 06/20/2013 (mm/dd/yyyy)
Release report - http://s.apache.org/1zE

- [NUTCH-1585](https://issues.apache.org/jira/browse/NUTCH-1585) - Ensure duplicate tags do not exist in microformat-reltag tag set. (lewismc)

- [NUTCH-1583](https://issues.apache.org/jira/browse/NUTCH-1583) - Headings plugin to support multivalued headings (markus)

- [NUTCH-1245](https://issues.apache.org/jira/browse/NUTCH-1245) - URL gone with 404 after db.fetch.interval.max stays db_unfetched in CrawlDb (snagel)

- [NUTCH-1527](https://issues.apache.org/jira/browse/NUTCH-1527) - Elasticsearch indexer (lufeng + markus)

- [NUTCH-1475](https://issues.apache.org/jira/browse/NUTCH-1475) - Index-More Plugin -- A better fall back value for date field (James Sullivan, snagel via lewismc)

- [NUTCH-1560](https://issues.apache.org/jira/browse/NUTCH-1560) - index-metadata to add all values of multivalued metadata (snagel)

- [NUTCH-1467](https://issues.apache.org/jira/browse/NUTCH-1467) - Not able to parse mutliValued metatags (kiran via snagel)

- [NUTCH-1430](https://issues.apache.org/jira/browse/NUTCH-1430) - Freegenerator records overwrite CrawlDB records with AdaptiveFetchSchedule (markus)

- [NUTCH-1522](https://issues.apache.org/jira/browse/NUTCH-1522) - Upgrade to Tika 1.3 (jnioche)

- [NUTCH-1578](https://issues.apache.org/jira/browse/NUTCH-1578) - Upgrade to Hadoop 1.2.0 (markus)

- [NUTCH-1577](https://issues.apache.org/jira/browse/NUTCH-1577) - Add target for creating eclipse project (tejasp)

- [NUTCH-1513](https://issues.apache.org/jira/browse/NUTCH-1513) - Support Robots.txt for Ftp urls (tejasp)

- [NUTCH-1249](https://issues.apache.org/jira/browse/NUTCH-1249) and [NUTCH-1275](https://issues.apache.org/jira/browse/NUTCH-1275) - Resolve all issues flagged up by adding javac -Xlint argument (tejasp)

- [NUTCH-1053](https://issues.apache.org/jira/browse/NUTCH-1053) - Parsing of RSS feeds fails (tejasp)

- [NUTCH-956](https://issues.apache.org/jira/browse/NUTCH-956) - solrindex issues: add field tld to Solr schema (Alexis via lewismc, snagel)

- [NUTCH-1277](https://issues.apache.org/jira/browse/NUTCH-1277) - Fix [fallthrough] javac warnings (tejasp)

- [NUTCH-1514](https://issues.apache.org/jira/browse/NUTCH-1514) - Phase out the deprecated configuration properties (if possible) (tejasp)

- [NUTCH-1334](https://issues.apache.org/jira/browse/NUTCH-1334) - NPE in FetcherOutputFormat (jnioche via tejasp)

- [NUTCH-1549](https://issues.apache.org/jira/browse/NUTCH-1549) - Fix deprecated use of Tika MimeType API in o.a.n.util.MimeUtil (tejasp)

- [NUTCH-346](https://issues.apache.org/jira/browse/NUTCH-346) - Improve readability of logs/hadoop.log (Renaud Richardet via tejasp)

- [NUTCH-829](https://issues.apache.org/jira/browse/NUTCH-829) - duplicate hadoop temp files (Mike Baranczak, lewismc, tejasp)

- [NUTCH-1501](https://issues.apache.org/jira/browse/NUTCH-1501) - Harmonize behavior of parsechecker and indexchecker (snagel + lewismc)

- [NUTCH-1031](https://issues.apache.org/jira/browse/NUTCH-1031) - Delegate parsing of robots.txt to crawler-commons (tejasp)

- [NUTCH-1547](https://issues.apache.org/jira/browse/NUTCH-1547) - BasicIndexingFilter - Problem to index full title (Feng)

- [NUTCH-1389](https://issues.apache.org/jira/browse/NUTCH-1389) - parsechecker and indexchecker to report truncated content (snagel)

- [NUTCH-1419](https://issues.apache.org/jira/browse/NUTCH-1419) - parsechecker and indexchecker to report protocol status (snagel + lewismc)

- [NUTCH-1047](https://issues.apache.org/jira/browse/NUTCH-1047) - Pluggable indexing backends (jnioche)

- [NUTCH-1536](https://issues.apache.org/jira/browse/NUTCH-1536) - Ant build file has hardcoded conf dir location (zm via lewismc)

- [NUTCH-1420](https://issues.apache.org/jira/browse/NUTCH-1420) - Get rid of the dreaded � (markus via lewismc)

- [NUTCH-1521](https://issues.apache.org/jira/browse/NUTCH-1521) - CrawlDbFilter pass null url to urlNormalizers (Lufeng via lewismc)

- [NUTCH-1284](https://issues.apache.org/jira/browse/NUTCH-1284) - Add site fetcher.max.crawl.delay as log output by default (tejasp)

- [NUTCH-1453](https://issues.apache.org/jira/browse/NUTCH-1453) - Substantiate tests for IndexingFilters (lufeng via lewismc)

- [NUTCH-840](https://issues.apache.org/jira/browse/NUTCH-840) - Port tests from parse-html to parse-tika (lewismc, jnioche)

- [NUTCH-1509](https://issues.apache.org/jira/browse/NUTCH-1509) - Implement read/write in NutchField (markus)

- [NUTCH-1507](https://issues.apache.org/jira/browse/NUTCH-1507) - Remove FetcherOutput (markus)

- [NUTCH-1506](https://issues.apache.org/jira/browse/NUTCH-1506) - Add UPDATE action to NutchIndexAction (markus)

- [NUTCH-1500](https://issues.apache.org/jira/browse/NUTCH-1500) - bin/crawl fails on step solrindex with wrong path to segment (Tristan Buckner, snagel)

- [NUTCH-1274](https://issues.apache.org/jira/browse/NUTCH-1274) - Fix [cast] javac warnings (tejasp via lewismc)

- [NUTCH-1494](https://issues.apache.org/jira/browse/NUTCH-1494) - RSS feed plugin seems broken (Sourajit Basak, tejasp and lewismc)

- [NUTCH-1127](https://issues.apache.org/jira/browse/NUTCH-1127) - JUnit test for urlfilter-validator (tejasp via lewismc)

- [NUTCH-1119](https://issues.apache.org/jira/browse/NUTCH-1119) - JUnit test for index-static (tejasp via lewismc)

- [NUTCH-1510](https://issues.apache.org/jira/browse/NUTCH-1510) - Upgrade to Hadoop 1.1.1 (markus)

- [NUTCH-1118](https://issues.apache.org/jira/browse/NUTCH-1118) - JUnit test for index-basic (tejasp via lewismc)

- [NUTCH-1331](https://issues.apache.org/jira/browse/NUTCH-1331) - limit crawler to defined depth (jnioche)


## Release 1.6 - 23/11/2012

- [NUTCH-1370](https://issues.apache.org/jira/browse/NUTCH-1370) - Expose exact number of urls injected @runtime (snagel via lewismc)

- [NUTCH-1117](https://issues.apache.org/jira/browse/NUTCH-1117) - JUnit test for index-anchor (lewismc)

- [NUTCH-1451](https://issues.apache.org/jira/browse/NUTCH-1451) - Upgrade automaton jar to 1.11-8 (lewismc)

- [NUTCH-1488](https://issues.apache.org/jira/browse/NUTCH-1488) - bin/nutch to run junit from any directory (snagel via lewismc)

- [NUTCH-1493](https://issues.apache.org/jira/browse/NUTCH-1493) - Error adding field 'contentLength'='' during solrindex using index-more (Nathan Gass via lewismc)

- [NUTCH-1491](https://issues.apache.org/jira/browse/NUTCH-1491) - Strip UTF-8 non-character codepoints in title (Nathan Gass via markus)

- [NUTCH-1421](https://issues.apache.org/jira/browse/NUTCH-1421) - RegexURLNormalizer to only skip rules with invalid patterns (snagel)

- [NUTCH-1341](https://issues.apache.org/jira/browse/NUTCH-1341) - NotModified time set to now but page not modified (markus)

- [NUTCH-1215](https://issues.apache.org/jira/browse/NUTCH-1215) - UpdateDB should not require segment as input (markus)

- [NUTCH-1383](https://issues.apache.org/jira/browse/NUTCH-1383) - IndexingFiltersChecker to show error message instead of null pointer exception (snagel)

- [NUTCH-1476](https://issues.apache.org/jira/browse/NUTCH-1476) - SegmentReader getStats should set parsed = -1 if no parsing took place (snagel)

- [NUTCH-1252](https://issues.apache.org/jira/browse/NUTCH-1252) - SegmentReader -get shows wrong data (snagel)

- [NUTCH-1344](https://issues.apache.org/jira/browse/NUTCH-1344) - BasicURLNormalizer to normalize https same as http (snagel)

- [NUTCH-706](https://issues.apache.org/jira/browse/NUTCH-706) - Url regex normalizer: pattern for session id removal not to match "newsId" (Meghna Kukreja via snagel)

- [NUTCH-1415](https://issues.apache.org/jira/browse/NUTCH-1415) - release packages to contain top level folder apache-nutch-x.x (snagel)

- [NUTCH-1441](https://issues.apache.org/jira/browse/NUTCH-1441) - AnchorIndexingFilter should use plain HashSet (ferdy via lewismc)

- [NUTCH-1470](https://issues.apache.org/jira/browse/NUTCH-1470) - Ensure test files are included for runtime testing (lewismc)

- [NUTCH-1434](https://issues.apache.org/jira/browse/NUTCH-1434) - Indexer to delete robots noindex (markus)

- [NUTCH-1443](https://issues.apache.org/jira/browse/NUTCH-1443) - Solr schema version is invalid (markus)

- [NUTCH-1417](https://issues.apache.org/jira/browse/NUTCH-1417) - Remove o.a.n.metadata.Office (lewismc)

- [NUTCH-1376](https://issues.apache.org/jira/browse/NUTCH-1376) - Add description parameter to every ant task (lewismc)

- [NUTCH-1440](https://issues.apache.org/jira/browse/NUTCH-1440) - reconfigure non-existent stopwords_en.txt in schema-solr4.xml (shekhar sharma via lewismc)

- [NUTCH-1439](https://issues.apache.org/jira/browse/NUTCH-1439) - Define boost field as type float in schema-solr4.xml (shekhar sharma via lewismc)

- [NUTCH-1433](https://issues.apache.org/jira/browse/NUTCH-1433) - Upgrade to Tika 1.2 (jnioche)

- [NUTCH-1388](https://issues.apache.org/jira/browse/NUTCH-1388) - Optionally maintain custom fetch interval despite AdaptiveFetchSchedule (markus)

- [NUTCH-1430](https://issues.apache.org/jira/browse/NUTCH-1430) - Freegenerator records overwrite CrawlDB records with AdaptiveFetchSchedule (markus)

- [NUTCH-1087](https://issues.apache.org/jira/browse/NUTCH-1087) - Deprecate crawl command and replace with example script (jnioche)

- [NUTCH-1306](https://issues.apache.org/jira/browse/NUTCH-1306) - Add option to not commit and clarify existing solr.commit.size (ferdy)

- [NUTCH-1405](https://issues.apache.org/jira/browse/NUTCH-1405) - Allow to overwrite CrawlDatum's with injected entries (markus)

- [NUTCH-1412](https://issues.apache.org/jira/browse/NUTCH-1412) - Upgrade commons lang (markus)

- [NUTCH-1251](https://issues.apache.org/jira/browse/NUTCH-1251) - SolrDedup to use proper Lucene catch-all query (Arkadi Kosmynin via markus)

- [NUTCH-1407](https://issues.apache.org/jira/browse/NUTCH-1407) - BasicIndexingFilter to optionally add domain field (markus)

- [NUTCH-1408](https://issues.apache.org/jira/browse/NUTCH-1408) - RobotRulesParser main doesn't take URL's (markus)

- [NUTCH-1300](https://issues.apache.org/jira/browse/NUTCH-1300) - Indexer to filter normalize URL's (markus)

- [NUTCH-1330](https://issues.apache.org/jira/browse/NUTCH-1330) - WebGraph OutlinkDB to preserve back up (markus)

- [NUTCH-1319](https://issues.apache.org/jira/browse/NUTCH-1319) - HostNormalizer plugin (markus)

- [NUTCH-1386](https://issues.apache.org/jira/browse/NUTCH-1386) - Headings filter not to add empty values (markus)

- [NUTCH-1356](https://issues.apache.org/jira/browse/NUTCH-1356) - ParseUtil use ExecutorService instead of manually thread handling (ferdy via markus)

- [NUTCH-1352](https://issues.apache.org/jira/browse/NUTCH-1352) - Improve regex urlfilters/normalizers synchronization (ferdy via markus)

- [NUTCH-1024](https://issues.apache.org/jira/browse/NUTCH-1024) - Dynamically set fetchInterval by MIME-type (markus)

- [NUTCH-1364](https://issues.apache.org/jira/browse/NUTCH-1364) - Add a counter in Generator for malformed urls (lewismc)

- [NUTCH-1262](https://issues.apache.org/jira/browse/NUTCH-1262) - Map `duplicating` content-types to a single type (markus)

- [NUTCH-1385](https://issues.apache.org/jira/browse/NUTCH-1385) - More robust plug-in order properties in nutch-site.xml (Andy Xue via markus)

- [NUTCH-1336](https://issues.apache.org/jira/browse/NUTCH-1336) - Optionally not index db_notmodified pages (markus)

- [NUTCH-1346](https://issues.apache.org/jira/browse/NUTCH-1346) - Follow outlinks to ignore external (markus)

- [NUTCH-1320](https://issues.apache.org/jira/browse/NUTCH-1320) - IndexChecker and ParseChecker choke on IDN's (markus)

- [NUTCH-1351](https://issues.apache.org/jira/browse/NUTCH-1351) - DomainStatistics to aggregate by TLD (markus)

- [NUTCH-1381](https://issues.apache.org/jira/browse/NUTCH-1381) - Allow to override default subcollection field name (markus)

- (without Jira issue) - Commit to add configuration for separation of ant distribution targets (lewismc + jnioche)


## Release 1.5.1 - 07/10/2012

- [NUTCH-1404](https://issues.apache.org/jira/browse/NUTCH-1404) - Nutch script fails to find job file in deploy mode (sidabatra, jnioche)

- [NUTCH-1415](https://issues.apache.org/jira/browse/NUTCH-1415) - release packages to contain top level folder apache-nutch-x.x (snagel via lewismc)

- [NUTCH-1400](https://issues.apache.org/jira/browse/NUTCH-1400) - Remove developer -core option for bin/nutch (jnioche)

- [NUTCH-1384](https://issues.apache.org/jira/browse/NUTCH-1384) - Typo in ParseSegment's run-method (Matthias Agethle via markus)

- [NUTCH-1398](https://issues.apache.org/jira/browse/NUTCH-1398) - Upgrade to Hadoop 1.0.3 (jnioche)


## Release 1.5 - 04/15/2012

- [NUTCH-1208](https://issues.apache.org/jira/browse/NUTCH-1208) - Don't include KEYS file in bin distribution (jnioche)

- [NUTCH-1234](https://issues.apache.org/jira/browse/NUTCH-1234) - Upgrade to Tika 1.1 (jnioche, markus)

- [NUTCH-809](https://issues.apache.org/jira/browse/NUTCH-809) - Parse-metatags plugin (jnioche)

- [NUTCH-1310](https://issues.apache.org/jira/browse/NUTCH-1310) - Nutch to send HTTP-accept header (markus)

- [NUTCH-1305](https://issues.apache.org/jira/browse/NUTCH-1305) - Domain(blacklist)URLFilter to trim entries (markus)

- [NUTCH-1307](https://issues.apache.org/jira/browse/NUTCH-1307) - Improve formatting of ant targets for clearer project help (lewismc)

- [NUTCH-1299](https://issues.apache.org/jira/browse/NUTCH-1299) - LinkRank inverter to ignore records without Node (markus)

- [NUTCH-1258](https://issues.apache.org/jira/browse/NUTCH-1258) - MoreIndexingFilter should be able to read Content-Type from both parse metadata and content metadata (jnioche, markus)

- [NUTCH-1293](https://issues.apache.org/jira/browse/NUTCH-1293) - IndexingFiltersChecker to store detected content type in crawldatum metadata (markus)

- [NUTCH-1291](https://issues.apache.org/jira/browse/NUTCH-1291) - Fetcher to stringify exception on // unexpected exception (markus)

- [NUTCH-965](https://issues.apache.org/jira/browse/NUTCH-965) - Skip parsing for truncated documents (alexis, lewismc, ferdy)

- [NUTCH-1210](https://issues.apache.org/jira/browse/NUTCH-1210) - DomainBlacklistFilter (markus)

- [NUTCH-1193](https://issues.apache.org/jira/browse/NUTCH-1193) - Incorrect url transform to lowercase: parameter solr (Eduardo dos Santos Leggiero via lewismc)

- [NUTCH-1272](https://issues.apache.org/jira/browse/NUTCH-1272) - Wrong property name for index-static in nutch-default.xml (Daniel Baur via jnioche)

- [NUTCH-1259](https://issues.apache.org/jira/browse/NUTCH-1259) - Store detected content-type in crawldatum metadata (jnioche, markus)

- [NUTCH-1266](https://issues.apache.org/jira/browse/NUTCH-1266) - Subcollection to optionally write to configured fields (markus)

- [NUTCH-1005](https://issues.apache.org/jira/browse/NUTCH-1005) - Parse headings plugin (markus)

- [NUTCH-1264](https://issues.apache.org/jira/browse/NUTCH-1264) - Configurable indexing plugin index-metadata (jnioche)

- [NUTCH-1242](https://issues.apache.org/jira/browse/NUTCH-1242) - Allow disabling of URL Filters in ParseSegment (Edward Drapkin via markus)

- [NUTCH-1256](https://issues.apache.org/jira/browse/NUTCH-1256) - WebGraph to dump host + score (markus)

- [NUTCH-1260](https://issues.apache.org/jira/browse/NUTCH-1260) - Fetcher should log fetching of redirects (Sebastian Nagel via markus)

- [NUTCH-1255](https://issues.apache.org/jira/browse/NUTCH-1255) - Change ivy.xml of all plugins to remove "nutch.root" property (ferdy)

- [NUTCH-1248](https://issues.apache.org/jira/browse/NUTCH-1248) - Generator to select on status (markus)

- [NUTCH-1177](https://issues.apache.org/jira/browse/NUTCH-1177) - Generator to select on retry interval (markus)

- [NUTCH-1246](https://issues.apache.org/jira/browse/NUTCH-1246) - Upgrade to Hadoop 1.0.0 (jnioche)

- [NUTCH-1139](https://issues.apache.org/jira/browse/NUTCH-1139) - Indexer to delete gone documents (markus)

- [NUTCH-1244](https://issues.apache.org/jira/browse/NUTCH-1244) - CrawlDBDumper to filter by regex (markus)

- [NUTCH-1237](https://issues.apache.org/jira/browse/NUTCH-1237) - Improve javac arguements for more verbose ouput (lewismc)

- [NUTCH-1236](https://issues.apache.org/jira/browse/NUTCH-1236) - Add link to site documentation to download older versions of Nutch (lewismc)

- [NUTCH-1146](https://issues.apache.org/jira/browse/NUTCH-1146) - Prevent generation of _SUCCESS files in output (jnioche)

- [NUTCH-1232](https://issues.apache.org/jira/browse/NUTCH-1232) - Remove site field from index-basic (markus)

- [NUTCH-1239](https://issues.apache.org/jira/browse/NUTCH-1239) - Webgraph should remove deleted pages from segment input (markus)

- [NUTCH-1238](https://issues.apache.org/jira/browse/NUTCH-1238) - Fetcher throughput threshold must start before feeder finished (markus)

- [NUTCH-1138](https://issues.apache.org/jira/browse/NUTCH-1138) - remove LogUtil from trunk and nutch gora (lewismc)

- [NUTCH-1231](https://issues.apache.org/jira/browse/NUTCH-1231) - Upgrade to Tika 1.0 (markus)

- [NUTCH-1230](https://issues.apache.org/jira/browse/NUTCH-1230) - MimeType API deprecated and breaks with Tika 1.0 (markus)

- [NUTCH-1235](https://issues.apache.org/jira/browse/NUTCH-1235) - Upgrade to new Hadoop 0.20.205.0 (markus)

- [NUTCH-1217](https://issues.apache.org/jira/browse/NUTCH-1217) - Update NOTICE.txt to drop some copyrights (lewismc)

- [NUTCH-1129](https://issues.apache.org/jira/browse/NUTCH-1129) - Add freegenerator, domainstats and crawldbscanner to log4j (markus)

- [NUTCH-1184](https://issues.apache.org/jira/browse/NUTCH-1184) - Fetcher to parse and follow Nth degree outlinks (markus)

- [NUTCH-1221](https://issues.apache.org/jira/browse/NUTCH-1221) - Migrate DomainStatistics to MapReduce API (markus)

- [NUTCH-1216](https://issues.apache.org/jira/browse/NUTCH-1216) - Add trivial comment to lib/native/README.txt (lewismc)

- [NUTCH-1214](https://issues.apache.org/jira/browse/NUTCH-1214) - DomainStats tool should be named for what it's doing (markus)

- [NUTCH-1213](https://issues.apache.org/jira/browse/NUTCH-1213) - Pass additional SolrParams when indexing to Solr (ab)

- [NUTCH-1211](https://issues.apache.org/jira/browse/NUTCH-1211) - URLFilterChecker command line help doesn't inform user of STDIN requirements (mattmann)

- [NUTCH-1209](https://issues.apache.org/jira/browse/NUTCH-1209) - Output from ParserChecker Url missing a newline (mattmann)

- [NUTCH-1207](https://issues.apache.org/jira/browse/NUTCH-1207) - ParserChecker to output signature (markus)

- [NUTCH-1090](https://issues.apache.org/jira/browse/NUTCH-1090) - InvertLinks should inform when ignoring internal links (Marek Backmann via markus)

- [NUTCH-1174](https://issues.apache.org/jira/browse/NUTCH-1174) - Outlinks are not properly normalized (markus)

- [NUTCH-1203](https://issues.apache.org/jira/browse/NUTCH-1203) - ParseSegment to show number of milliseconds per parse (markus)

- [NUTCH-1185](https://issues.apache.org/jira/browse/NUTCH-1185) - Decrease solr.commit.size to 250 (markus)

- [NUTCH-1180](https://issues.apache.org/jira/browse/NUTCH-1180) - UpdateDB to backup previous CrawlDB (markus)

- [NUTCH-1173](https://issues.apache.org/jira/browse/NUTCH-1173) - DomainStats doesn't count db_not_modified (markus)

- [NUTCH-1155](https://issues.apache.org/jira/browse/NUTCH-1155) - Host/domain limit in generator is generate.max.count+1 (markus)

- [NUTCH-1061](https://issues.apache.org/jira/browse/NUTCH-1061) - Migrate MoreIndexingFilter from Apache ORO to java.util.regex (markus)

- [NUTCH-1178](https://issues.apache.org/jira/browse/NUTCH-1178) - Incorrect CSV header CrawlDatumCsvOutputFormat (markus)

- [NUTCH-1142](https://issues.apache.org/jira/browse/NUTCH-1142) - Normalization and filtering in WebGraph (markus)

- [NUTCH-1153](https://issues.apache.org/jira/browse/NUTCH-1153) - LinkRank not to log all keys and not to write Hadoop _SUCCESS file (markus)


## Release 1.4 - 11/4/2011

- [NUTCH-1195](https://issues.apache.org/jira/browse/NUTCH-1195) - Add Solr 4x (trunk) example schema (ab)

- [NUTCH-1192](https://issues.apache.org/jira/browse/NUTCH-1192) - Add '/runtime' to svn ignore (ferdy)

- [NUTCH-1097](https://issues.apache.org/jira/browse/NUTCH-1097) - application/xhtml+xml should be enabled in plugin.xml of parse-html; allow multiple mimetypes for plugin.xml (Ferdy via lewismc)

- [NUTCH-797](https://issues.apache.org/jira/browse/NUTCH-797) - Fix parse-tika and parse-html to use relative URL resolution per RFC-3986 (Robert Hohman, ab)

- [NUTCH-1154](https://issues.apache.org/jira/browse/NUTCH-1154) - Upgrade to Tika 0.10. NOTE: Tika's new RTF parser may ignore more text in malformed documents than previously - see TIKA-748 for details. (ab)

- [NUTCH-1109](https://issues.apache.org/jira/browse/NUTCH-1109) - Add Sonar targets to Ant build.xml (lewismc) 

- [NUTCH-1152](https://issues.apache.org/jira/browse/NUTCH-1152) - Upgrade SolrJ to version 3.4.0 (ab)

- [NUTCH-1136](https://issues.apache.org/jira/browse/NUTCH-1136) - Ant pmd target is broken (lewismc)

- [NUTCH-1058](https://issues.apache.org/jira/browse/NUTCH-1058) - Upgrade Solr schema to version 1.4 (markus)

- [NUTCH-1137](https://issues.apache.org/jira/browse/NUTCH-1137) - LinkDB invertlinks other options ignored when using -dir option (Sebastian Nagel, markus)

- [NUTCH-1141](https://issues.apache.org/jira/browse/NUTCH-1141) - Configurable Fetcher queue depth (jnioche)

- [NUTCH-1091](https://issues.apache.org/jira/browse/NUTCH-1091) - Remove commons logging dependency from Nutch branch and trunk (lewismc)

- [NUTCH-672](https://issues.apache.org/jira/browse/NUTCH-672) - allow unit tests to be run from bin/nutch (Todd Lipton via lewismc)

- [NUTCH-937](https://issues.apache.org/jira/browse/NUTCH-937) - Put plugins in classes/plugins in job file (Claudio Martella, Ferdy Galema, jnioche)

- [NUTCH-623](https://issues.apache.org/jira/browse/NUTCH-623) - Change plugin source directory "languageidentifier" to "language-identifier" (lewismc)

- [NUTCH-1074](https://issues.apache.org/jira/browse/NUTCH-1074) - topN is ignored with maxNumSegments and generate.max.count (Robert Thomson via markus)

- [NUTCH-1078](https://issues.apache.org/jira/browse/NUTCH-1078) - Upgrade all instances of commons logging to slf4j (with log4j backend) (lewismc)

- [NUTCH-1115](https://issues.apache.org/jira/browse/NUTCH-1115) - Option to disable fixing embedded URL parameters in DomContentUtils (markus)

- [NUTCH-1114](https://issues.apache.org/jira/browse/NUTCH-1114) - Attr file missing in domain filter (markus)

- [NUTCH-1067](https://issues.apache.org/jira/browse/NUTCH-1067) - Configure minimum throughput for fetcher (markus)

- [NUTCH-1102](https://issues.apache.org/jira/browse/NUTCH-1102) - Fetcher to rely on fetcher.parse directive (markus)

- [NUTCH-1110](https://issues.apache.org/jira/browse/NUTCH-1110) - UpdateDB must not write _success file (markus)

- [NUTCH-1105](https://issues.apache.org/jira/browse/NUTCH-1105) - Max content length option for index-basic (markus)

- [NUTCH-940](https://issues.apache.org/jira/browse/NUTCH-940) - static field plugin (Claudio Martella via lewismc)

- [NUTCH-914](https://issues.apache.org/jira/browse/NUTCH-914) - Implement Apache Project Branding Requirements (lewismc)

- [NUTCH-1095](https://issues.apache.org/jira/browse/NUTCH-1095) - remove i18n from Nutch site to archive and legacy secton of wiki (lewismc)

- [NUTCH-1101](https://issues.apache.org/jira/browse/NUTCH-1101) - Option to purge db_gone records with updatedb (markus)

- [NUTCH-1096](https://issues.apache.org/jira/browse/NUTCH-1096) - Empty (not null) ContentLength results in failure of fetch (Ferdy Galema via jnioche)

- [NUTCH-1073](https://issues.apache.org/jira/browse/NUTCH-1073) - Rename parameters 'fetcher.threads.per.host.by.ip' and 'fetcher.threads.per.host' (jnioche)

- [NUTCH-1089](https://issues.apache.org/jira/browse/NUTCH-1089) - Short compressed pages caused exception in protocol-httpclient (Simone Frenzel via jnioche)

- [NUTCH-1085](https://issues.apache.org/jira/browse/NUTCH-1085) - Nutch script does not require HADOOP_HOME (jnioche)

- [NUTCH-1075](https://issues.apache.org/jira/browse/NUTCH-1075) - Delegate language identification to Tika (jnioche)

- [NUTCH-1049](https://issues.apache.org/jira/browse/NUTCH-1049) - Add classes to bin/nutch script (markus)

- [NUTCH-1051](https://issues.apache.org/jira/browse/NUTCH-1051) - Export WebGraph node scores for Solr.ExternalFileField (markus)

- [NUTCH-1083](https://issues.apache.org/jira/browse/NUTCH-1083) - ParserChecker implements Tools (jnioche)

- [NUTCH-1082](https://issues.apache.org/jira/browse/NUTCH-1082) - IndexingFiltersChecker utility does not list multi valued fields (markus)

- [NUTCH-1004](https://issues.apache.org/jira/browse/NUTCH-1004) - Do not index empty values for title field (markus)

- [NUTCH-914](https://issues.apache.org/jira/browse/NUTCH-914) - Implement Apache Project Branding Requirements (lewismc via jnioche)

- [NUTCH-1069](https://issues.apache.org/jira/browse/NUTCH-1069) - Readlinkdb broken on Hadoop > 0.20 (markus)

- [NUTCH-1044](https://issues.apache.org/jira/browse/NUTCH-1044) - Redirected URLs and possibly all of their outlinked URLs have invalid scores (jnioche)

- [NUTCH-1028](https://issues.apache.org/jira/browse/NUTCH-1028) - Log urls when parsing (markus)

- [NUTCH-1065](https://issues.apache.org/jira/browse/NUTCH-1065) - New mvn.template (lewismc)

- [NUTCH-1072](https://issues.apache.org/jira/browse/NUTCH-1072) - Display number and size of queues in Fetcher status (jnioche)

- [NUTCH-1071](https://issues.apache.org/jira/browse/NUTCH-1071) - Crawldb update displays total number of URLs per status (jnioche)

- [NUTCH-1045](https://issues.apache.org/jira/browse/NUTCH-1045) - MimeUtil to rely on default config provided by Tika (jnioche)

- [NUTCH-1057](https://issues.apache.org/jira/browse/NUTCH-1057) - Fetcher thread time out configurable (markus)

- [NUTCH-1037](https://issues.apache.org/jira/browse/NUTCH-1037) - Option to deduplicate anchors prior to indexing (markus)

- [NUTCH-1050](https://issues.apache.org/jira/browse/NUTCH-1050) - Add segmentDir option to WebGraph (markus)

- [NUTCH-1055](https://issues.apache.org/jira/browse/NUTCH-1055) - upgrade package.html file in language identifier plugin (lewismc)

- [NUTCH-1059](https://issues.apache.org/jira/browse/NUTCH-1059) - Remove convdb command from /bin/nutch (lewismc)

- [NUTCH-1019](https://issues.apache.org/jira/browse/NUTCH-1019) - Edit comment in org.apache.nutch.crawl.Crawl to reflect removal of legacy (lewismc)

- [NUTCH-1023](https://issues.apache.org/jira/browse/NUTCH-1023) - Trivial error in error message for org.apache.nutch.crawl.LinkDbReader (lewismc)

- [NUTCH-1043](https://issues.apache.org/jira/browse/NUTCH-1043) - Add pattern for filtering .js in default url filters (jnioche)

- [NUTCH-1054](https://issues.apache.org/jira/browse/NUTCH-1054) - LinkDB optional during indexing (jnioche)

- [NUTCH-1029](https://issues.apache.org/jira/browse/NUTCH-1029) - Readdb throws EOFException (markus)

- [NUTCH-1036](https://issues.apache.org/jira/browse/NUTCH-1036) - Solr jobs should increment counters in Reporter (markus)

- [NUTCH-987](https://issues.apache.org/jira/browse/NUTCH-987) - Support HTTP auth for Solr communication (markus)

- [NUTCH-1027](https://issues.apache.org/jira/browse/NUTCH-1027) - Degrade log level of `can't find rules for scope` (markus)

- [NUTCH-783](https://issues.apache.org/jira/browse/NUTCH-783) - IndexingFiltersChecker utility (jnioche via markus)

- [NUTCH-1030](https://issues.apache.org/jira/browse/NUTCH-1030) - WebgraphDB program requires manually added directories (markus)

- [NUTCH-1011](https://issues.apache.org/jira/browse/NUTCH-1011) - Normalize duplicate slashes in URL's (markus)

- [NUTCH-993](https://issues.apache.org/jira/browse/NUTCH-993) - NullPointerException at FetcherOutputFormat.checkOutputSpecs (Christian Guegi via jnioche)

- [NUTCH-1013](https://issues.apache.org/jira/browse/NUTCH-1013) - Migrate RegexURLNormalizer from Apache ORO to java.util.regex (markus)

- [NUTCH-1016](https://issues.apache.org/jira/browse/NUTCH-1016) - Strip UTF-8 non-character codepoints and add logging for SolrWriter (markus)

- [NUTCH-1012](https://issues.apache.org/jira/browse/NUTCH-1012) - Cannot handle illegal charset $charset (markus)

- [NUTCH-1022](https://issues.apache.org/jira/browse/NUTCH-1022) - Upgrade version number of Nutch agent in conf (markus)

- [NUTCH-295](https://issues.apache.org/jira/browse/NUTCH-295) - Description for fetcher.threads.fetch property (kubes via markus)

- [NUTCH-1000](https://issues.apache.org/jira/browse/NUTCH-1000) - Add option not to commit to Solr (markus)

- [NUTCH-1006](https://issues.apache.org/jira/browse/NUTCH-1006) - MetaEquiv with single quotes not accepted (markus)

- [NUTCH-1010](https://issues.apache.org/jira/browse/NUTCH-1010) - ContentLength not trimmed (markus)


## Release 1.3 - 6/4/2011

- [NUTCH-995](https://issues.apache.org/jira/browse/NUTCH-995) - Generate POM file using the Ivy makepom task (mattmann, jnioche, Gabriele Kahlout)

- [NUTCH-1003](https://issues.apache.org/jira/browse/NUTCH-1003) - task 'package' does not reflect the new organisation of the code (jnioche)

- [NUTCH-994](https://issues.apache.org/jira/browse/NUTCH-994) - Fine tune Solr schema (markus)

- [NUTCH-997](https://issues.apache.org/jira/browse/NUTCH-997) - IndexingFitlers to store Date objects instead of Strings (jnioche)

- [NUTCH-996](https://issues.apache.org/jira/browse/NUTCH-996) - Indexer adds solr.commit.size+1 docs (markus)

- [NUTCH-983](https://issues.apache.org/jira/browse/NUTCH-983) - Upgrade SolrJ to 3.1 (markus, jnioche)

- [NUTCH-989](https://issues.apache.org/jira/browse/NUTCH-989) - Index-basic plugin and Solr schema now use date fieldType for tstamp field (markus)

- [NUTCH-888](https://issues.apache.org/jira/browse/NUTCH-888) - Remove parse-rss and add tests for rss to parse-tika (jnioche)

- [NUTCH-991](https://issues.apache.org/jira/browse/NUTCH-991) - SolrDedup must issue a commit (markus)

- [NUTCH-986](https://issues.apache.org/jira/browse/NUTCH-986) - SolrDedup fails due to date incorrect format (markus)

- [NUTCH-977](https://issues.apache.org/jira/browse/NUTCH-977) - SolrMappingReader uses hardcoded configuration parameter name for mapping file (markus)

- [NUTCH-976](https://issues.apache.org/jira/browse/NUTCH-976) - Rename properties solrindex.* to solr.* (markus)

- [NUTCH-890](https://issues.apache.org/jira/browse/NUTCH-890) - Fix IllegalAccessError with slf4j used in Solrj (markus)

- [NUTCH-891](https://issues.apache.org/jira/browse/NUTCH-891) - Subcollection plugin won't require blacklist any more (markus)

- [NUTCH-972](https://issues.apache.org/jira/browse/NUTCH-972) - CrawlDbMerger doesn't break on non-existent input (Gabriele Kahlout via jnioche)

- [NUTCH-967](https://issues.apache.org/jira/browse/NUTCH-967) - Upgrade to Tika 0.9 (jnioche)

- [NUTCH-975](https://issues.apache.org/jira/browse/NUTCH-975) - Fix missing/wrong headers in source files (markus)

- [NUTCH-963](https://issues.apache.org/jira/browse/NUTCH-963) - Add support for deleting Solr documents with STATUS_DB_GONE in CrawlDB (Claudio Martella, markus)

- [NUTCH-825](https://issues.apache.org/jira/browse/NUTCH-825) - Publish nutch artifacts to central maven repository (mattmann, jnioche)

- [NUTCH-962](https://issues.apache.org/jira/browse/NUTCH-962) - max. redirects not handled correctly: fetcher stops at max-1 redirects (Sebastian Nagel via ab)

- [NUTCH-921](https://issues.apache.org/jira/browse/NUTCH-921) - Reduce dependency of Nutch on config files (ab)

- [NUTCH-876](https://issues.apache.org/jira/browse/NUTCH-876) - Remove remaining robots/IP blocking code in lib-http (ab)

- [NUTCH-872](https://issues.apache.org/jira/browse/NUTCH-872) - Change the default fetcher.parse to FALSE (ab)

- [NUTCH-564](https://issues.apache.org/jira/browse/NUTCH-564) - External parser supports encoding attribute (Antony Bowesman, mattmann)

- [NUTCH-964](https://issues.apache.org/jira/browse/NUTCH-964) - Upgraded Xerces to 2.91, ERROR conf.Configuration - Failed to set setXIncludeAware (markus)

- [NUTCH-927](https://issues.apache.org/jira/browse/NUTCH-927) - Fetcher.timelimit.mins is invalid when depth is greater than 1 (Wade Lau via jnioche)

- [NUTCH-824](https://issues.apache.org/jira/browse/NUTCH-824) - Crawling - File Error 404 when fetching file with an hexadecimal character in the file name (Michela Becchi via jnioche)

- [NUTCH-954](https://issues.apache.org/jira/browse/NUTCH-954) - Strict application of Content-Length limit for http protocols (Alexis Detreglode via jnioche)

- [NUTCH-950](https://issues.apache.org/jira/browse/NUTCH-950) - DomainURLFilter throws NPE on bogus urls (Alexis Detreglode via jnioche)

- [NUTCH-935](https://issues.apache.org/jira/browse/NUTCH-935) - basicurlnormalizer removes unnecessary /./ in URLs (Stondet via markus)

- [NUTCH-912](https://issues.apache.org/jira/browse/NUTCH-912) - MoreIndexingFilter does not parse docx and xlsx date formats (Markus Jelsma, jnioche)

- [NUTCH-886](https://issues.apache.org/jira/browse/NUTCH-886) - A .gitignore file for Nutch (dogacan)

- [NUTCH-930](https://issues.apache.org/jira/browse/NUTCH-930) - Remove remaining dependencies on Lucene API (ab)

- [NUTCH-883](https://issues.apache.org/jira/browse/NUTCH-883) - Remove unused parameters from nutch-default.xml (jnioche) 

- [NUTCH-936](https://issues.apache.org/jira/browse/NUTCH-936) - LanguageIdentifier should not set empty lang field on NutchDocument (Markus Jelsma via jnioche)

- [NUTCH-787](https://issues.apache.org/jira/browse/NUTCH-787) - ScoringFilters should not override the injected score (jnioche)

- [NUTCH-949](https://issues.apache.org/jira/browse/NUTCH-949) - Conflicting ANT jars in classpath (jnioche)

- [NUTCH-863](https://issues.apache.org/jira/browse/NUTCH-863) - Benchmark and a testbed proxy server (ab)

- [NUTCH-844](https://issues.apache.org/jira/browse/NUTCH-844) - Improve NutchConfiguration (ab)

- [NUTCH-845](https://issues.apache.org/jira/browse/NUTCH-845) - Native hadoop libs not available through maven (ab)

- [NUTCH-843](https://issues.apache.org/jira/browse/NUTCH-843) - Separate the build and runtime environments (ab)

- [NUTCH-821](https://issues.apache.org/jira/browse/NUTCH-821) - Use ivy in nutch builds (Enis Soztutar, jnioche)

- [NUTCH-837](https://issues.apache.org/jira/browse/NUTCH-837) - Remove search servers and Lucene dependencies (ab)

- [NUTCH-836](https://issues.apache.org/jira/browse/NUTCH-836) - Remove deprecated parse plugins (jnioche)

- [NUTCH-939](https://issues.apache.org/jira/browse/NUTCH-939) - Added -dir command line option to SolrIndexer (Claudio Martella via ab)

- [NUTCH-948](https://issues.apache.org/jira/browse/NUTCH-948) - Remove Lucene dependencies (ab)


## Release 1.2 - 09/18/2010

- [NUTCH-901](https://issues.apache.org/jira/browse/NUTCH-901) - Make index-more plug-in configurable (Markus Jelsma via mattmann)

- [NUTCH-908](https://issues.apache.org/jira/browse/NUTCH-908) - Infinite Loop and Null Pointer Bugs in Searching (kubes via mattmann)

- [NUTCH-906](https://issues.apache.org/jira/browse/NUTCH-906) - Nutch OpenSearch sometimes raises DOMExceptions (Asheesh Laroia via ab)

- [NUTCH-862](https://issues.apache.org/jira/browse/NUTCH-862) - HttpClient null pointer exception (Sebastian Nagel via ab)

- [NUTCH-905](https://issues.apache.org/jira/browse/NUTCH-905) - Configurable file protocol parent directory crawling (Thorsten Scherler, mattmann, ab)

- [NUTCH-877](https://issues.apache.org/jira/browse/NUTCH-877) - Allow setting of slop values for non-quote phrase queries on query-basic plugin (kubes via jnioche)

- [NUTCH-716](https://issues.apache.org/jira/browse/NUTCH-716) - Make subcollection index filed multivalued (Dmitry Lihachev via jnioche)

- [NUTCH-878](https://issues.apache.org/jira/browse/NUTCH-878) - ScoringFilters should not override the injected score 

- [NUTCH-870](https://issues.apache.org/jira/browse/NUTCH-870) - Injector should add the metadata before calling injectedScore (jnioche via mattmann)

- [NUTCH-858](https://issues.apache.org/jira/browse/NUTCH-858) - No longer able to set per-field boosts on lucene documents (ab)

- [NUTCH-869](https://issues.apache.org/jira/browse/NUTCH-869) - Add parse-html back (jnioche)

- [NUTCH-871](https://issues.apache.org/jira/browse/NUTCH-871) - MoreIndexingFilter missing date format (Max Lynch via mattmann)

- [NUTCH-696](https://issues.apache.org/jira/browse/NUTCH-696) - Timeout for Parser (ab, jnioche)

- [NUTCH-857](https://issues.apache.org/jira/browse/NUTCH-857) - DistributedBeans should not close their RPC counterparts (kubes)
  
- [NUTCH-855](https://issues.apache.org/jira/browse/NUTCH-855) - ScoringFilter and IndexingFilter: To allow for the propagation of URL Metatags and their subsequent indexing (Scott Gonyea via mattmann)

- [NUTCH-677](https://issues.apache.org/jira/browse/NUTCH-677) - Segment merge filering based on segment content (Marcin Okraszewski via mattmann)

- [NUTCH-774](https://issues.apache.org/jira/browse/NUTCH-774) - Retry interval in crawl date is set to 0 (Reinhard Schwab via mattmann)

- [NUTCH-697](https://issues.apache.org/jira/browse/NUTCH-697) - Generate log output for solr indexer and dedup (Dmitry Lihachev, Jeroen van Vianen via mattmann)

- [NUTCH-850](https://issues.apache.org/jira/browse/NUTCH-850) - SolrDeleteDuplicates needs to clone the SolrRecord objects (jnioche)

- [NUTCH-838](https://issues.apache.org/jira/browse/NUTCH-838) - Add timing information to all Tool classes (Jeroen van Vianen, mattmann)

- [NUTCH-835](https://issues.apache.org/jira/browse/NUTCH-835) - Document deduplication failed using MD5Signature (Sebastian Nagel via ab)

- [NUTCH-831](https://issues.apache.org/jira/browse/NUTCH-831) - Allow configuration of how fields crawled by Nutch are stored / indexed / tokenized (Jeroen van Vianen via mattmann)

- [NUTCH-278](https://issues.apache.org/jira/browse/NUTCH-278) - Fetcher-status might need clarification: kbit/s instead of kb/s shown (Alex McLintock via mattmann)

- [NUTCH-833](https://issues.apache.org/jira/browse/NUTCH-833) - Website is still Lucene branded (mattmann, Alex McLintock)

- [NUTCH-832](https://issues.apache.org/jira/browse/NUTCH-832) - Website menu has lots of broken links - in particular the API docs (Alex McLintock via mattmann)


## Release 1.1 - 2010-06-06

- [NUTCH-819](https://issues.apache.org/jira/browse/NUTCH-819) - Included Solr schema.xml and solrindex-mapping.xml don't play together (ab)

- [NUTCH-818](https://issues.apache.org/jira/browse/NUTCH-818) - Bugfix : Parse-tika uses minorCodes instead of majorCodes in ParseStatus (jnioche)

- [NUTCH-816](https://issues.apache.org/jira/browse/NUTCH-816) - Add zip target to build.xml (mattmann)

- [NUTCH-732](https://issues.apache.org/jira/browse/NUTCH-732) - Subcollection plugin not working (Filipe Antunes, ab)

- [NUTCH-815](https://issues.apache.org/jira/browse/NUTCH-815) - Invalid blank line before If-Modified-Since header (Pascal Dimassimo via ab)

- [NUTCH-814](https://issues.apache.org/jira/browse/NUTCH-814) - SegmentMerger bug (Rob Bradshaw, ab)

- [NUTCH-812](https://issues.apache.org/jira/browse/NUTCH-812) - Crawl.java incorrectly uses the Generator API resulting in NPE (Phil Barnett via mattmann and ab)

- [NUTCH-810](https://issues.apache.org/jira/browse/NUTCH-810) - Upgrade to Tika 0.7 (jnioche)

- [NUTCH-785](https://issues.apache.org/jira/browse/NUTCH-785) - Copy metadata from origin URL when redirecting in Fetcher + call scfilters.initialScore on newly created URL (jnioche)

- [NUTCH-779](https://issues.apache.org/jira/browse/NUTCH-779) - Mechanism for passing metadata from parse to crawldb (jnioche)

- [NUTCH-784](https://issues.apache.org/jira/browse/NUTCH-784) - CrawlDBScanner (jnioche)

- [NUTCH-762](https://issues.apache.org/jira/browse/NUTCH-762) - Generator can generate several segments in one parse of the crawlDB (jnioche)

- [NUTCH-740](https://issues.apache.org/jira/browse/NUTCH-740) - Configuration option to override default language for fetched pages (Marcin Okraszewski via jnioche)

- [NUTCH-803](https://issues.apache.org/jira/browse/NUTCH-803) - Upgrade to Hadoop 0.20.2 (ab)

- [NUTCH-787](https://issues.apache.org/jira/browse/NUTCH-787) - Upgrade Lucene to 3.0.1. (Dawid Weiss via ab)

- [NUTCH-796](https://issues.apache.org/jira/browse/NUTCH-796) - Zero results problems difficult to troubleshoot due to lack of logging (ab)

- [NUTCH-801](https://issues.apache.org/jira/browse/NUTCH-801) - Remove RTF and MP3 parse plugins (jnioche)

- [NUTCH-798](https://issues.apache.org/jira/browse/NUTCH-798) - Upgrade to SOLR1.4 and its dependencies (jnioche)

- [NUTCH-799](https://issues.apache.org/jira/browse/NUTCH-799) - SOLRIndexer to commit once all reducers have finished (jnioche)

- [NUTCH-782](https://issues.apache.org/jira/browse/NUTCH-782) - Ability to order htmlparsefilters (jnioche)

- [NUTCH-719](https://issues.apache.org/jira/browse/NUTCH-719) - fetchQueues.totalSize incorrect in Fetcher (Steven Denny via jnioche) 

- [NUTCH-790](https://issues.apache.org/jira/browse/NUTCH-790) - Some external javadoc links are broken (siren)

- [NUTCH-766](https://issues.apache.org/jira/browse/NUTCH-766) - Tika parser (jnioche via mattmann)

- [NUTCH-786](https://issues.apache.org/jira/browse/NUTCH-786) - Improvement to the list of suffix domains (jnioche)

- [NUTCH-775](https://issues.apache.org/jira/browse/NUTCH-775) - Enhance searcher interface (siren)

- [NUTCH-781](https://issues.apache.org/jira/browse/NUTCH-781) - Update Tika to v0.6 (jnioche)

- [NUTCH-269](https://issues.apache.org/jira/browse/NUTCH-269) - CrawlDbReducer: OOME because no upper-bound on inlinks count (stack + jnioche)

- [NUTCH-655](https://issues.apache.org/jira/browse/NUTCH-655) - Injecting Crawl metadata (jnioche)

- [NUTCH-658](https://issues.apache.org/jira/browse/NUTCH-658) - Use counters to report fetching and parsing status (jnioche)

- [NUTCH-777](https://issues.apache.org/jira/browse/NUTCH-777) - Upgrading to jetty6 broke unit tests (mattmann)

- [NUTCH-767](https://issues.apache.org/jira/browse/NUTCH-767) - Update Tika to v0.5 for the MimeType detection (Julien Nioche via ab)

- [NUTCH-769](https://issues.apache.org/jira/browse/NUTCH-769) - Fetcher to skip queues for URLS getting repeated exceptions (Julien Nioche via ab)

- [NUTCH-768](https://issues.apache.org/jira/browse/NUTCH-768) - Upgrade Nutch 1.0 to use Hadoop 0.20.1, also upgrades Xerces to version 2.9.1. (kubes)
  
- [NUTCH-712](https://issues.apache.org/jira/browse/NUTCH-712) - ParseOutputFormat should catch java.net.MalformedURLException coming from normalizers (Julien Nioche via ab)

- [NUTCH-741](https://issues.apache.org/jira/browse/NUTCH-741) - Job file includes multiple copies of nutch config files (Kirby Bohling via ab)

- [NUTCH-739](https://issues.apache.org/jira/browse/NUTCH-739) - SolrDeleteDuplications too slow when using hadoop (Dmitry Lihachev via ab)

- [NUTCH-738](https://issues.apache.org/jira/browse/NUTCH-738) - Close SegmentUpdater when FetchedSegments is closed (Martina Koch, Kirby Bohling via ab)

- [NUTCH-746](https://issues.apache.org/jira/browse/NUTCH-746) - NutchBeanConstructor does not close NutchBean upon contextDestroyed, causing resource leak in the container. (Kirby Bohling via ab)

- [NUTCH-772](https://issues.apache.org/jira/browse/NUTCH-772) - Upgrade Nutch to use Lucene 2.9.1 (ab)

- [NUTCH-760](https://issues.apache.org/jira/browse/NUTCH-760) - Allow field mapping from Nutch to Solr index (David Stuart, ab)

- [NUTCH-761](https://issues.apache.org/jira/browse/NUTCH-761) - Avoid cloning CrawlDatum in CrawlDbReducer (Julien Nioche, ab)

- [NUTCH-753](https://issues.apache.org/jira/browse/NUTCH-753) - Prevent new Fetcher from retrieving the robots twice (Julien Nioche via ab)

- [NUTCH-773](https://issues.apache.org/jira/browse/NUTCH-773) - Some minor bugs in AbstractFetchSchedule (Reinhard Schwab via ab)

- [NUTCH-765](https://issues.apache.org/jira/browse/NUTCH-765) - Allow Crawl class to call Either Solr or Lucene Indexer (kubes)

- [NUTCH-735](https://issues.apache.org/jira/browse/NUTCH-735) - crawl-tool.xml must be read before nutch-site.xml when invoked using crawl command (Susam Pal via dogacan)

- [NUTCH-721](https://issues.apache.org/jira/browse/NUTCH-721) - Fetcher2 Slow (Julien Nioche via dogacan)

- [NUTCH-702](https://issues.apache.org/jira/browse/NUTCH-702) - Lazy Instanciation of Metadata in CrawlDatum (Julien Nioche via dogacan)

- [NUTCH-707](https://issues.apache.org/jira/browse/NUTCH-707) - Generation of multiple segments in multiple runs returns only 1 segment (Michael Chen, ab)

- [NUTCH-730](https://issues.apache.org/jira/browse/NUTCH-730) - NPE in LinkRank if no nodes with which to create the WebGraph (Dennis Kubes via ab)

- [NUTCH-731](https://issues.apache.org/jira/browse/NUTCH-731) - Redirection of robots.txt in RobotRulesParser (Julien Nioche via ab)

- [NUTCH-757](https://issues.apache.org/jira/browse/NUTCH-757) - RequestUtils getBooleanParameter() always returns false (Niall Pemberton via ab)

- [NUTCH-754](https://issues.apache.org/jira/browse/NUTCH-754) - Use GenericOptionsParser instead of FileSystem.parseArgs() (Julien Nioche via ab)

- [NUTCH-756](https://issues.apache.org/jira/browse/NUTCH-756) - CrawlDatum.set() does not reset Metadata if it is null (Julien Nioche via ab)

- [NUTCH-679](https://issues.apache.org/jira/browse/NUTCH-679) - Fetcher2 implementing Tool (Julien Nioche via ab)

- [NUTCH-758](https://issues.apache.org/jira/browse/NUTCH-758) - Set subversion eol-style to "native" (Niall Pemberton via ab)


## Release 1.0 - 2009-03-23

 1. [NUTCH-474](https://issues.apache.org/jira/browse/NUTCH-474) - Fetcher2 crawlDelay and blocking fix (Dogacan Guney via ab)

 2. [NUTCH-443](https://issues.apache.org/jira/browse/NUTCH-443) - Allow parsers to return multiple Parse objects.
    (Dogacan Guney et al, via ab)

 3. [NUTCH-393](https://issues.apache.org/jira/browse/NUTCH-393) - Indexer should handle null documents returned by filters.
    (Eelco Lempsink via ab)

 4. [NUTCH-456](https://issues.apache.org/jira/browse/NUTCH-456) - Parse msexcel plugin speedup (Heiko Dietze via siren)

 5. [NUTCH-446](https://issues.apache.org/jira/browse/NUTCH-446) - RobotRulesParser should ignore Crawl-delay values of other
    bots in robots.txt (Dogacan Guney via siren)

 6. [NUTCH-482](https://issues.apache.org/jira/browse/NUTCH-482) - Remove redundant plugin lib-log4j (siren)
 
 7. [NUTCH-483](https://issues.apache.org/jira/browse/NUTCH-483) - Remove redundant commons-logging jar from ontology plugin
    (siren)

 8. [NUTCH-161](https://issues.apache.org/jira/browse/NUTCH-161) - Change Plain text parser to
    use parser.character.encoding.default property for fall back encoding
    (KuroSaka TeruHiko, siren)

 9. [NUTCH-61](https://issues.apache.org/jira/browse/NUTCH-61) - Support for adaptive re-fetch interval and detection of
    unmodified content. (ab)

10. [NUTCH-392](https://issues.apache.org/jira/browse/NUTCH-392) - OutputFormat implementations should pass on Progressable.
    (cutting via ab)

11. [NUTCH-495](https://issues.apache.org/jira/browse/NUTCH-495) - Unnecessary delays in Fetcher2 (dogacan)

12. [NUTCH-443](https://issues.apache.org/jira/browse/NUTCH-443) - allow parsers to return multiple Parse object, this will speed 
    up the rss parser (dogacan via mattmann). This update is a fix and semantics
    change from the original patch for NUTCH-443. The original patch did not tell
    the  Indexer to read crawl_parse too so that it can pickup sub-urls' fetch 
    datums. This patch addresses that issue. Now, if Fetcher gets a null content, 
    instead of pushing an empty content, it filters the null content.
    
13. [NUTCH-485](https://issues.apache.org/jira/browse/NUTCH-485) - Change HtmlParseFilter 's to return ParseResult object instead of 
    Parse object. (Gal Nitzan via dogacan)

14. [NUTCH-489](https://issues.apache.org/jira/browse/NUTCH-489) - URLFilter-suffix management of the url path when the url contains 
    some query parameters. (Emmanuel Joke via dogacan)

15. [NUTCH-502](https://issues.apache.org/jira/browse/NUTCH-502) - Bug in SegmentReader causes infinite loop. 
    (Ilya Vishnevsky via dogacan)
    
16. [NUTCH-444](https://issues.apache.org/jira/browse/NUTCH-444) - Possibly use a different library to parse RSS feed for improved 
    performance and compatibility. This patch introduced a new plugin, feed,
    that includes an index filter and a parse plugin for feeds that uses ROME.
    There was discussion to remove parse-rss, in light of the feed plugin, 
    however, this patch does not explicitly remove parse-rss. (dogacan, mattmann)

17. [NUTCH-471](https://issues.apache.org/jira/browse/NUTCH-471) - Fix synchronization in NutchBean creation. 
    (Enis Soztutar via dogacan)

18. Upgrade to Lucene 2.2.0 and Hadoop 0.12.3. (ab)

19. [NUTCH-468](https://issues.apache.org/jira/browse/NUTCH-468) - Scoring filter should distribute score to all outlinks at 
    once. (dogacan)

20. [NUTCH-504](https://issues.apache.org/jira/browse/NUTCH-504) - NUTCH-443 broke parsing during fetching. (dogacan)

21. [NUTCH-497](https://issues.apache.org/jira/browse/NUTCH-497) - Extreme Nested Tags causes StackOverflowException in 
	DomContentUtils...Spider Trap. (kubes)

22. [NUTCH-434](https://issues.apache.org/jira/browse/NUTCH-434) - Replace usage of ObjectWritable with something based on 
    GenericWritable. (dogacan)

23. [NUTCH-499](https://issues.apache.org/jira/browse/NUTCH-499) - Refactor LinkDb and LinkDbMerger to reuse code. (dogacan)

24. [NUTCH-498](https://issues.apache.org/jira/browse/NUTCH-498) - Use Combiner in LinkDb to increase speed of linkdb generation.
    (Espen Amble Kolstad via dogacan)

25. [NUTCH-507](https://issues.apache.org/jira/browse/NUTCH-507) - lib-lucene-analyzers jar defintion is wrong in plugin.xml.
    (Emmanuel Joke via dogacan)

26. [NUTCH-503](https://issues.apache.org/jira/browse/NUTCH-503) - Generator exits incorrectly for small fetchlists. 
    (Vishal Shah via dogacan)

27. [NUTCH-505](https://issues.apache.org/jira/browse/NUTCH-505) - Outlink urls should be validated. (dogacan)

28. [NUTCH-510](https://issues.apache.org/jira/browse/NUTCH-510) - IndexMerger delete working dir. (Enis Soztutar via dogacan)

29. [NUTCH-513](https://issues.apache.org/jira/browse/NUTCH-513) - suffix-urlfilter.txt does not have a template. (dogacan)

30. [NUTCH-515](https://issues.apache.org/jira/browse/NUTCH-515) - Next fetch time is set incorrectly. (dogacan)

30. [NUTCH-506](https://issues.apache.org/jira/browse/NUTCH-506) - Nutch should delegate compression to Hadoop. (dogacan)

31. [NUTCH-517](https://issues.apache.org/jira/browse/NUTCH-517) - build encoding should be UTF-8. (Enis Soztutar via dogacan).

32. [NUTCH-518](https://issues.apache.org/jira/browse/NUTCH-518) - Fix OpicScoringFilter to respect scoring filter chaining.
    (Enis Soztutar via dogacan)

33. [NUTCH-516](https://issues.apache.org/jira/browse/NUTCH-516) - Next fetch time is not set when it is a 
    CrawlDatum.STATUS_FETCH_GONE. (Emmanuel Joke via dogacan)

34. [NUTCH-525](https://issues.apache.org/jira/browse/NUTCH-525) - DeleteDuplicates generates ArrayIndexOutOfBoundsException 
    when trying to rerun dedup on a segment. (Vishal Shah via dogacan)

35. [NUTCH-514](https://issues.apache.org/jira/browse/NUTCH-514) - Indexer should only index pages with fetch status SUCCESS.
    (dogacan) Note: There is a bigger problem, i.e how to deal
    with redirected pages, and this issue can be considered as a band-aid 
    for the time being. See [NUTCH-273](https://issues.apache.org/jira/browse/NUTCH-273) and [NUTCH-353](https://issues.apache.org/jira/browse/NUTCH-353) for more details. 

36. [NUTCH-533](https://issues.apache.org/jira/browse/NUTCH-533) - LinkDbMerger: url normalized is not updated in the key and 
    inlinks list. (Emmanuel Joke via dogacan)

37. [NUTCH-535](https://issues.apache.org/jira/browse/NUTCH-535) - ParseData's contentMeta accumulates unnecessary values during 
    parse. (dogacan)

38. [NUTCH-522](https://issues.apache.org/jira/browse/NUTCH-522) - Use URLValidator in the Injector. (Emmanuel Joke, dogacan)

39. [NUTCH-536](https://issues.apache.org/jira/browse/NUTCH-536) - Reduce number of warnings in nutch core. (dogacan)

40. [NUTCH-439](https://issues.apache.org/jira/browse/NUTCH-439) - Top Level Domains Indexing / Scoring. Also adds 
    domain-related utilities. (Enis Soztutar via dogacan)

41. [NUTCH-544](https://issues.apache.org/jira/browse/NUTCH-544) - Upgrade Carrot2 clustering plugin to the newest stable 
    release (2.1). (Dawid Weiss via dogacan)

42. [NUTCH-545](https://issues.apache.org/jira/browse/NUTCH-545) - Configuration and OnlineClusterer get initialized in every
    request. (Dawid Weiss via dogacan)

43. [NUTCH-532](https://issues.apache.org/jira/browse/NUTCH-532) - CrawlDbMerger: wrong computation of last fetch time. 
    (Emmanuel Joke via dogacan)

44. [NUTCH-550](https://issues.apache.org/jira/browse/NUTCH-550) - Parse fails if db.max.outlinks.per.page is -1. (dogacan)

45. [NUTCH-546](https://issues.apache.org/jira/browse/NUTCH-546) - file URL are filtered out by the crawler. (dogacan)

46. [NUTCH-554](https://issues.apache.org/jira/browse/NUTCH-554) - Generator throws IOException on invalid urls.
    (Brian Whitman via ab)

47. [NUTCH-529](https://issues.apache.org/jira/browse/NUTCH-529) - NodeWalker.skipChildren doesn't work for more than 1 child.
    (Emmanuel Joke via dogacan)

48. [NUTCH-25](https://issues.apache.org/jira/browse/NUTCH-25) - needs 'character encoding' detector.
    (Doug Cook, dogacan, Marcin Okraszewski, Renaud Richardet via dogacan)

49. [NUTCH-508](https://issues.apache.org/jira/browse/NUTCH-508) - ${hadoop.log.dir} and ${hadoop.log.file} are not propagated
    to the tasktracker. (Mathijs Homminga, Emmanuel Joke via dogacan)
    
50. [NUTCH-562](https://issues.apache.org/jira/browse/NUTCH-562) - Port mime type framework to use Tika mime detection framework.
    (mattmann)
    
51. [NUTCH-488](https://issues.apache.org/jira/browse/NUTCH-488) - Avoid parsing uneccessary links and get a more relevant outlink 
    list. (Emmanuel Joke, Marcin Okraszewski via kubes)

52. [NUTCH-501](https://issues.apache.org/jira/browse/NUTCH-501) - Implement a different caching mechanism for objects cached in
    configuration. (dogacan)

53. [NUTCH-552](https://issues.apache.org/jira/browse/NUTCH-552) - Upgrade Nutch to Hadoop 0.15.x. (kubes)

54. [NUTCH-565](https://issues.apache.org/jira/browse/NUTCH-565) - Arc File to Nutch Segments Converter. (kubes)

55. [NUTCH-547](https://issues.apache.org/jira/browse/NUTCH-547) - Redirection handling: YahooSlurp's algorithm.
    (dogacan, kubes via dogacan)

56. [NUTCH-548](https://issues.apache.org/jira/browse/NUTCH-548) - Move URLNormalizer from Outlink to ParseOutputFormat.
    (Emmanuel Joke via dogacan)

57. [NUTCH-538](https://issues.apache.org/jira/browse/NUTCH-538) - Delete unused classes under o.a.n.util. (dogacan)

58. [NUTCH-494](https://issues.apache.org/jira/browse/NUTCH-494) - FindBugs: CrawlDbReader and DeleteDuplicates. (dogacan)

59. [NUTCH-574](https://issues.apache.org/jira/browse/NUTCH-574) - Including inlink anchor text in index can create irrelevant 
   search results.  Created index-anchor plugin, removed functionality from 
    index-basic plugin. For backwards compatibility, add index-anchor plugin to 
    nutch-site.xml plugin.includes. (kubes)

60. [NUTCH-581](https://issues.apache.org/jira/browse/NUTCH-581) - DistributedSearch does not update search servers added to 
    search-servers.txt on the fly.  (Rohan Mehta via kubes)

61. [NUTCH-586](https://issues.apache.org/jira/browse/NUTCH-586) - Add option to run compiled classes without job file
    (enis via ab)

62. [NUTCH-559](https://issues.apache.org/jira/browse/NUTCH-559) - NTLM, Basic and Digest Authentication schemes for web/proxy
    server. (Susam Pal via dogacan)

63. [NUTCH-534](https://issues.apache.org/jira/browse/NUTCH-534) - SegmentMerger: add -normalize option (Emmanuel Joke via ab)

64. [NUTCH-528](https://issues.apache.org/jira/browse/NUTCH-528) - CrawlDbReader: add some new stats + dump into a CSV format
    (Emmanuel Joke via ab)

65. [NUTCH-597](https://issues.apache.org/jira/browse/NUTCH-597) - NPE in Fetcher2 (Remco Verhoef via ab)

66. [NUTCH-584](https://issues.apache.org/jira/browse/NUTCH-584) - urls missing from fetchlist (Ruslan Ermilov, ab)

67. [NUTCH-580](https://issues.apache.org/jira/browse/NUTCH-580) - Remove deprecated hadoop api calls (FS) (siren)

68. [NUTCH-587](https://issues.apache.org/jira/browse/NUTCH-587) - Upgrade to Hadoop 0.15.3 (kubes)

69. [NUTCH-604](https://issues.apache.org/jira/browse/NUTCH-604) - Upgrade to Lucene 2.3.0 (ab)

70. [NUTCH-602](https://issues.apache.org/jira/browse/NUTCH-602) - Allow configurable number of handlers for search servers
    (hartbecke via kubes)

71. [NUTCH-607](https://issues.apache.org/jira/browse/NUTCH-607) - Update build.xml to include tika jar when building war (kubes)

72. [NUTCH-608](https://issues.apache.org/jira/browse/NUTCH-608) - Upgrade nutch to use released apache-tika-0.1-incubating (mattmann)

73. [NUTCH-606](https://issues.apache.org/jira/browse/NUTCH-606) - Refactoring of Generator, run all urls through checks (kubes)

74. [NUTCH-605](https://issues.apache.org/jira/browse/NUTCH-605) - Change deprecated configuration methods for Hadoop (kubes)

75. [NUTCH-603](https://issues.apache.org/jira/browse/NUTCH-603) - Add more default url normalizations (kubes)

76. [NUTCH-611](https://issues.apache.org/jira/browse/NUTCH-611) - Upgrade Nutch to use Hadoop 0.16 (kubes)

77. [NUTCH-44](https://issues.apache.org/jira/browse/NUTCH-44) - Too many search results, limits max results returned from a 
    single search. (Emilijan Mirceski and Susam Pal via kubes)

78. [NUTCH-567](https://issues.apache.org/jira/browse/NUTCH-567) - Proper (?) handling of URIs in TagSoup. TagSoup library is
    updated to 1.2 version. (dogacan)

79. [NUTCH-613](https://issues.apache.org/jira/browse/NUTCH-613) - Empty summaries and cached pages (kubes via ab)

80. [NUTCH-612](https://issues.apache.org/jira/browse/NUTCH-612) - URL filtering was disabled in Generator when invoked
    from Crawl (Susam Pal via ab)

81. [NUTCH-601](https://issues.apache.org/jira/browse/NUTCH-601) - Recrawling on existing crawl directory (Susam Pal via ab)

82. [NUTCH-575](https://issues.apache.org/jira/browse/NUTCH-575) - NPE in OpenSearchServlet (John H. Lee via ab)

83. [NUTCH-126](https://issues.apache.org/jira/browse/NUTCH-126) - Fetching https does not work with a proxy (Fritz Elfert via ab)

84. [NUTCH-615](https://issues.apache.org/jira/browse/NUTCH-615) - Redirected URL-s fetched without setting fetchInterval.
    Guard against reprUrl being null. (Emmanuel Joke, ab)

85. [NUTCH-616](https://issues.apache.org/jira/browse/NUTCH-616) - Reset Fetch Retry counter when fetch is successful (Emmanuel
    Joke, ab)

86. [NUTCH-220](https://issues.apache.org/jira/browse/NUTCH-220) - Upgrade to PDFBox 0.7.3 (ab)

87. [NUTCH-223](https://issues.apache.org/jira/browse/NUTCH-223) - Crawl.java uses Integer.MAX_VALUE (Jeff Ritchie via ab)

88. [NUTCH-598](https://issues.apache.org/jira/browse/NUTCH-598) - Remove deprecated use of ToolBase. Use generics in Hadoop API.
    (Emmanuel Joke, dogacan, ab)

89. [NUTCH-620](https://issues.apache.org/jira/browse/NUTCH-620) - BasicURLNormalizer should collapse runs of slashes with a
    single slash. (Mark DeSpain via ab)

90. [NUTCH-500](https://issues.apache.org/jira/browse/NUTCH-500) - Add hadoop masters configuration file into conf folder. 
    (Emmanuel Joke via kubes)

91. [NUTCH-596](https://issues.apache.org/jira/browse/NUTCH-596) - ParseSegments parse content even if its not
    CrawlDatum.STATUS_FETCH_SUCCESS (dogacan)
    
92. [NUTCH-618](https://issues.apache.org/jira/browse/NUTCH-618) - Tika error "Media type alias already exists" (mattmann,kubes)

93. [NUTCH-634](https://issues.apache.org/jira/browse/NUTCH-634) - Upgrade Nutch to Hadoop 0.17.1 (Michael Gottesman, Lincoln
    Ritter, ab)

94. [NUTCH-641](https://issues.apache.org/jira/browse/NUTCH-641) - IndexSorter inorrectly copies stored fields (ab)

95. [NUTCH-645](https://issues.apache.org/jira/browse/NUTCH-645) - Parse-swf unit test failing (ab)

96. [NUTCH-642](https://issues.apache.org/jira/browse/NUTCH-642) - Unit tests fail when run in non-local mode (ab)

97. [NUTCH-639](https://issues.apache.org/jira/browse/NUTCH-639) - Change LuceneDocumentWrapper visibility from
    private to _public_ (Guillaume Smet via dogacan)

98. [NUTCH-651](https://issues.apache.org/jira/browse/NUTCH-651) - Remove bin/{start|stop}-balancer.sh from svn
    tracking. (dogacan)

99. [NUTCH-375](https://issues.apache.org/jira/browse/NUTCH-375) - Add support for Content-Encoding: deflated
    (Pascal Beis, ab)

100. [NUTCH-633](https://issues.apache.org/jira/browse/NUTCH-633) - ParseSegment no longer allow reparsing.
     (dogacan)

101. [NUTCH-653](https://issues.apache.org/jira/browse/NUTCH-653) - Upgrade to hadoop 0.18. (dogacan)

102. [NUTCH-621](https://issues.apache.org/jira/browse/NUTCH-621) - Nutch needs to declare it's crypto usage (mattmann)

103. [NUTCH-654](https://issues.apache.org/jira/browse/NUTCH-654) - urlfilter-regex's main does not work. (dogacan)

104. [NUTCH-640](https://issues.apache.org/jira/browse/NUTCH-640) - confusing description "set it to Integer.MAX_VALUE". (dogacan)
     
105. [NUTCH-662](https://issues.apache.org/jira/browse/NUTCH-662) - Upgrade Nutch to use Lucene 2.4. (kubes)

106. [NUTCH-663](https://issues.apache.org/jira/browse/NUTCH-663) - Upgrade Nutch to use Hadoop 0.19 (kubes)

107. [NUTCH-647](https://issues.apache.org/jira/browse/NUTCH-647) - Resolve URLs tool (kubes)

108. [NUTCH-665](https://issues.apache.org/jira/browse/NUTCH-665) - Search Load Testing Tool (kubes)

109. [NUTCH-667](https://issues.apache.org/jira/browse/NUTCH-667) - Input Format for working with Content in Hadoop Streaming (kubes)

110. [NUTCH-635](https://issues.apache.org/jira/browse/NUTCH-635) - LinkAnalysis Tool for Nutch. (kubes)

111. [NUTCH-646](https://issues.apache.org/jira/browse/NUTCH-646) - New Indexing Framework for Nutch. (kubes)

112. [NUTCH-668](https://issues.apache.org/jira/browse/NUTCH-668) - Domain URL Filter. (kubes)

113. [NUTCH-594](https://issues.apache.org/jira/browse/NUTCH-594) - Serve Nutch search results in multiple formats including XML and JSON. (kubes)

114. [NUTCH-442](https://issues.apache.org/jira/browse/NUTCH-442) - Integrate Solr/Nutch. (dogacan, original version by siren) 

115. [NUTCH-652](https://issues.apache.org/jira/browse/NUTCH-652) - AdaptiveFetchSchedule#setFetchSchedule doesn't calculate fetch interval correctly. (dogacan)

116. [NUTCH-627](https://issues.apache.org/jira/browse/NUTCH-627) - Minimize host address lookup (Otis Gospodnetic)

117. [NUTCH-678](https://issues.apache.org/jira/browse/NUTCH-678) - Hadoop 0.19 requires an update of jets3t. (julien nioche via dogacan)

118. [NUTCH-681](https://issues.apache.org/jira/browse/NUTCH-681) - parse-mp3 compilation problem. (Wildan Maulana via dogacan)

119. [NUTCH-676](https://issues.apache.org/jira/browse/NUTCH-676) - MapWritable is written inefficiently and confusingly. (dogacan)

120. [NUTCH-579](https://issues.apache.org/jira/browse/NUTCH-579) - Feed plugin only indexes one post per feed due to identical digest. (dogacan)

121. [NUTCH-571](https://issues.apache.org/jira/browse/NUTCH-571) - parse-mp3 plugin doesn't always index album of mp3. (Joseph Chen, dogacan)

122. [NUTCH-682](https://issues.apache.org/jira/browse/NUTCH-682) - SOLR indexer does not set boost on the document. (julien nioche via dogacan)

123. [NUTCH-279](https://issues.apache.org/jira/browse/NUTCH-279) - Additions to urlnormalizer-regex (Stefan Neufeind, ab)

124. [NUTCH-671](https://issues.apache.org/jira/browse/NUTCH-671) - JSP errors in Nutch searcher webapp (Edwin Chu via ab)

125. [NUTCH-643](https://issues.apache.org/jira/browse/NUTCH-643) - ClassCastException in PDF parser (Guillaume Smet, ab)

126. [NUTCH-636](https://issues.apache.org/jira/browse/NUTCH-636) - Httpclient plugin https doesn't work on IBM JRE (Curtis d'Entremont, ab)

127. [NUTCH-683](https://issues.apache.org/jira/browse/NUTCH-683) - NUTCH-676 broke CrawlDbMerger. (dogacan)

128. [NUTCH-631](https://issues.apache.org/jira/browse/NUTCH-631) - MoreIndexingFilter fails with NoSuchElementException (Stefan Will, siren)
     
129. [NUTCH-691](https://issues.apache.org/jira/browse/NUTCH-691) - Update jakarta poi jars to the most relevant version (Dmitry Lihachev via siren)

130. [NUTCH-563](https://issues.apache.org/jira/browse/NUTCH-563) - Include custom fields in BasicQueryFilter (Julien Nioche via siren)
     
131. [NUTCH-695](https://issues.apache.org/jira/browse/NUTCH-695) - Incorrect mime type detection by MoreIndexingFilter plugin (Dmitry Lihachev via siren)
     
132. [NUTCH-694](https://issues.apache.org/jira/browse/NUTCH-694) - Distributed Search Server fails (siren)

133. [NUTCH-626](https://issues.apache.org/jira/browse/NUTCH-626) - Fetcher2 breaks out the domain with db.ignore.external.links set at cross domain redirects (Remco Verhoef, dogacan via siren)

134. [NUTCH-247](https://issues.apache.org/jira/browse/NUTCH-247) - Robot parser to restrict (kubes, siren)

135. [NUTCH-698](https://issues.apache.org/jira/browse/NUTCH-698) - CrawlDb is corrupted after a few crawl cycles (dogacan via siren)
     
136. [NUTCH-699](https://issues.apache.org/jira/browse/NUTCH-699) - Add an "official" solr schema for solr integration (dogacan, Dmitry Lihachev via siren)

137. [NUTCH-703](https://issues.apache.org/jira/browse/NUTCH-703) - Upgrade to Hadoop 0.19.1 (ab)

138. [NUTCH-419](https://issues.apache.org/jira/browse/NUTCH-419) - Unavailable robots.txt kills fetch (Carsten Lehmann, Doug Cook via ab)
     
139. [NUTCH-700](https://issues.apache.org/jira/browse/NUTCH-700) - Neko1.9.11 goes into a loop (Julien Nioche, siren)

140. [NUTCH-669](https://issues.apache.org/jira/browse/NUTCH-669) - Consolidate code for Fetcher and Fetcher2 (siren)

141. [NUTCH-711](https://issues.apache.org/jira/browse/NUTCH-711) - Indexer failing after upgrade to Hadoop 0.19.1 (ab)

142. [NUTCH-684](https://issues.apache.org/jira/browse/NUTCH-684) - Dedup support for Solr. (dogacan)

143. [NUTCH-715](https://issues.apache.org/jira/browse/NUTCH-715) - Subcollection plugin doesn't work with default subcollections.xml file (Dmitry Lihachev via siren)
     
144. [NUTCH-722](https://issues.apache.org/jira/browse/NUTCH-722) - Nutch contains JAI jars that we cannot redistribute


## Release 0.9 - 2007-04-02

 1. Changed log4j confiquration to log to stdout on commandline tools (siren)

 2. [NUTCH-344](https://issues.apache.org/jira/browse/NUTCH-344) - Fix for thread blocking issue (Greg Kim via siren)
 
 3. [NUTCH-260](https://issues.apache.org/jira/browse/NUTCH-260) - Update hadoop version to 0.5.0 (Renaud Richardet, siren)

 4. Optionally skip pages with abnormally large values of Crawl-Delay (Dennis Kubes via ab)

 5. Change readdb -stats to use CombiningCollector (ab)

 6. [NUTCH-348](https://issues.apache.org/jira/browse/NUTCH-348) - Fix Generator to select highest scoring pages (Chris
    Schneider and Stefan Groschupf via ab)

 7. [NUTCH-347](https://issues.apache.org/jira/browse/NUTCH-347) - Adjust plugin build script not to emit warnings when copying
    dependant jars (siren)
    
 8. [NUTCH-338](https://issues.apache.org/jira/browse/NUTCH-338) - Remove the text parser as an option for parsing PDF files
    in parse-plugins.xml (Chris A. Mattmann via siren)
    
 9. [NUTCH-105](https://issues.apache.org/jira/browse/NUTCH-105) - Network error during robots.txt fetch causes file to
    be ignored (Greg Kim via siren)
    
10. [NUTCH-367](https://issues.apache.org/jira/browse/NUTCH-367) - DistributedSearch thown ClassCastException (siren)

11. [NUTCH-332](https://issues.apache.org/jira/browse/NUTCH-332) - Fix the problem of doubling scores caused by links pointing
    to the current page (e.g. anchors). (Stefan Groschupf via ab)

12. [NUTCH-365](https://issues.apache.org/jira/browse/NUTCH-365) - Flexible URL normalization (ab)

13. [NUTCH-336](https://issues.apache.org/jira/browse/NUTCH-336) - Differentiate between newly discovered pages and newly
    injected pages (Chris Schneider via ab) NOTE: this changes the
    scoring API, filter implementations need to be updated.

14. [NUTCH-337](https://issues.apache.org/jira/browse/NUTCH-337) - Fetcher ignores the fetcher.parse value (Stefan Groschupf
    via ab)

15. [NUTCH-350](https://issues.apache.org/jira/browse/NUTCH-350) - Urls blocked by http.max.delays incorrectly marked as GONE
    (Stefan Groschupf via ab)

16. [NUTCH-374](https://issues.apache.org/jira/browse/NUTCH-374) - when http.content.limit be set to -1 and  
    Response.CONTENT_ENCODING is gzip or x-gzip , it can not fetch any thing 
    (King Kong via pkosiorowski)

17. [NUTCH-383](https://issues.apache.org/jira/browse/NUTCH-383) - upgrade to Hadoop 0.7.1 and Lucene 2.0.0. (ab)

```
  ****************************** WARNING !!! ********************************
  * This upgrade breaks data format compatibility. A tool 'convertdb'       *
  * was added to migrate existing CrawlDb-s to the new format. Segment data *
  * can be partially migrated using 'mergesegs', however segments will      *
  * require re-parsing (and consequently re-indexing).                      *
  ****************************** WARNING !!! ********************************
```

18. [NUTCH-371](https://issues.apache.org/jira/browse/NUTCH-371) - DeleteDuplicates now correctly implements both parts of
    the algorithm. (ab)

19. [NUTCH-391](https://issues.apache.org/jira/browse/NUTCH-391) - ParseUtil logs file contents to log file when it cannot
    find parser (siren)

20. [NUTCH-379](https://issues.apache.org/jira/browse/NUTCH-379) - ParseUtil does not pass through the content's URL to the
    ParserFactory (Chris A. Mattmann via siren)

21. [NUTCH-361](https://issues.apache.org/jira/browse/NUTCH-361),[NUTCH-136](https://issues.apache.org/jira/browse/NUTCH-136) -
    When jobtracker is 'local' generate only one partition. (ab)

22. [NUTCH-399](https://issues.apache.org/jira/browse/NUTCH-399) - Change CommandRunner to use concurrent api from jdk (siren)

23. [NUTCH-395](https://issues.apache.org/jira/browse/NUTCH-395) - Increase fetching speed (siren)

24. [NUTCH-388](https://issues.apache.org/jira/browse/NUTCH-388) - nutch-default.xml has outdated example for urlfilter.order
    (reported by Jared Dunne)

25. [NUTCH-404](https://issues.apache.org/jira/browse/NUTCH-404) - Fix LinkDB Usage - implementation mismatch (siren)

26. [NUTCH-403](https://issues.apache.org/jira/browse/NUTCH-403) - Make URL filtering optional in Generator (siren)

27. [NUTCH-405](https://issues.apache.org/jira/browse/NUTCH-405) - Content object is not properly initialized in map method
    of ParseSegment (siren)

28. [NUTCH-362](https://issues.apache.org/jira/browse/NUTCH-362) - Remove parse-text from unsupported filetypes in
    parse-plugins.xml (siren)
    
29. [NUTCH-305](https://issues.apache.org/jira/browse/NUTCH-305) - Update crawl and url filter lists to exclude
    jpeg|JPEG|bmp|BMP, suffix-urlfilter.txt (contributed by Stefan
    Neufeind) is also updated (siren)
    
30. [NUTCH-406](https://issues.apache.org/jira/browse/NUTCH-406) - Metadata tries to write null values (mattmann)

31. [NUTCH-415](https://issues.apache.org/jira/browse/NUTCH-415) - Generator should mark selected records in CrawlDb. 
    Due to increased resource consumption this step is optional. 
    Application-level locking has been added to prevent concurrent
    modification of databases. (ab)

32. [NUTCH-416](https://issues.apache.org/jira/browse/NUTCH-416) - CrawlDatum status and CrawlDbReducer refactoring. It is
    now possible to correctly update CrawlDb from multiple segments.
    Introduce new status codes for temporary and permanent
    redirection. (ab)

33. [NUTCH-322](https://issues.apache.org/jira/browse/NUTCH-322) - Fix Fetcher to store redirected pages and to store
    protocol-level status. This also should fix NUTCH-273. (ab)

34. Change default Fetcher behavior not to follow redirects immediately.
    Instead Fetcher will record redirects as new pages to be added to CrawlDb.
    This also partially addresses NUTCH-273. (ab)

35. Detect and report when Generator creates 0-sized segments. (ab)

36. Fix Injector to preserve already existing CrawlDatum if the seed list
    being injected also contains such URL. (ab)

37. [NUTCH-425](https://issues.apache.org/jira/browse/NUTCH-425),[NUTCH-426](https://issues.apache.org/jira/browse/NUTCH-426) -
    Fix anchors pollution. Continue after skipping bad URLs. (Michael Stack via ab)

38. [NUTCH-325](https://issues.apache.org/jira/browse/NUTCH-325) - UrlFilters.java throws NPE in case urlfilter.order contains
    Filters that are not in plugin.includes (Stefan Groschupf, siren)
    
39. [NUTCH-421](https://issues.apache.org/jira/browse/NUTCH-421) - Allow predeterminate running order of indexing filters
    (Alan Tanaman, siren)

40. When indexing pages with redirection, drop all intermediate pages and index only the final page. (ab)

41. Upgrade to Hadoop 0.10.1. (ab)

42. [NUTCH-420](https://issues.apache.org/jira/browse/NUTCH-420) - Fix a bug in DeleteDuplicates where results depended on the
    order in which IndexDoc-s are processed. (Dogacan Guney via ab)

43. [NUTCH-428](https://issues.apache.org/jira/browse/NUTCH-428) - NullPointerException thrown when agent name is not
    configured properly. Changed to throw RuntimeException instead.
    (siren)

44. [NUTCH-430](https://issues.apache.org/jira/browse/NUTCH-430) - Integer overflow in HashComparator.compare (siren)

45. [NUTCH-68](https://issues.apache.org/jira/browse/NUTCH-68) - Add a tool to generate arbitrary fetchlists. (ab)

46. [NUTCH-433](https://issues.apache.org/jira/browse/NUTCH-433) - java.io.EOFException in newer nightlies in mergesegs
    or indexing from hadoop.io.DataOutputBuffer (siren)

47. [NUTCH-339](https://issues.apache.org/jira/browse/NUTCH-339) - Fetcher2: a queue-based fetcher implementation. (ab)

48. [NUTCH-390](https://issues.apache.org/jira/browse/NUTCH-390) - Javadoc warnings (mattmann)

49. [NUTCH-449](https://issues.apache.org/jira/browse/NUTCH-449) - Make junit output format configurable. (nigel via cutting)

50. [NUTCH-432](https://issues.apache.org/jira/browse/NUTCH-432) - Fix a bug where platform name with spaces would break the
    bin/nutch script. (Brian Whitman via ab)

51. Upgrade to Hadoop 0.11.2 and Lucene 2.1.0 release. (ab)

52. [NUTCH-167](https://issues.apache.org/jira/browse/NUTCH-167) - Observation of robots "noarchive" directive. (ab)

53. [NUTCH-384](https://issues.apache.org/jira/browse/NUTCH-384) - Protocol-file plugin does not allow the parse plugins
    framework to operate properly (Heiko Dietze via mattmann)

54. [NUTCH-233](https://issues.apache.org/jira/browse/NUTCH-233) - Wrong regular expression hangs reduce process forever (Stefan
    Groschupf via kubes)
    
55. [NUTCH-436](https://issues.apache.org/jira/browse/NUTCH-436) - Incorrect handling of relative paths when the embedded URL 
    path is empty (kubes)

56. Upgrade to Hadoop 0.12.1 release. (ab)

57. [NUTCH-246](https://issues.apache.org/jira/browse/NUTCH-246) - Incorrect segment size being generated due to time
    synchronization issue (Stefan Groschupf via ab)

58. Upgrade to Hadoop 0.12.2 release. (ab)

59. [NUTCH-333](https://issues.apache.org/jira/browse/NUTCH-333) - SegmentMerger and SegmentReader should use NutchJob. (Michael
    Stack and Dogacan Guney via kubes)


## Release 0.8 - 2006-07-25

 0. Totally new architecture, based on hadoop
    [http://lucene.apache.org/hadoop] (cutting)

 1. [NUTCH-107](https://issues.apache.org/jira/browse/NUTCH-107) - Typo in plugin/urlfilter-*/plugin.xml. (Stephen Cross).

 2. [NUTCH-108](https://issues.apache.org/jira/browse/NUTCH-108) - Log hosts that exceed generate.max.per.host.
    (Rod Taylor via cutting)

 3. [NUTCH-88](https://issues.apache.org/jira/browse/NUTCH-88) - Enhance ParserFactory plugin selection policy
    (jerome)

 4. [NUTCH-124](https://issues.apache.org/jira/browse/NUTCH-124) - Protocol-httpclient does not follow redirects when 
    fetching robots.txt (cutting)

 5. [NUTCH-130](https://issues.apache.org/jira/browse/NUTCH-130) - Be explicit about target JVM when building (1.4.x?)
    (stack@archive.org, cutting)

 6. [NUTCH-114](https://issues.apache.org/jira/browse/NUTCH-114) - Getting number of urls and links from crawldb
    (Stefan Groschupf via ab)

 7. [NUTCH-112](https://issues.apache.org/jira/browse/NUTCH-112) - Link in cached.jsp page to cached content is an 
    absolute link (Chris A. Mattmann via jerome)

 8. [NUTCH-135](https://issues.apache.org/jira/browse/NUTCH-135) - Http header meta data are case insensitive in the
    real world (Stefan Groschupf via jerome)

 9. [NUTCH-145](https://issues.apache.org/jira/browse/NUTCH-145) - Build of war file fails on Chinese (zh) .xml files due
    to UTF-8 BOM (KuroSaka TeruHiko via siren)

10. [NUTCH-121](https://issues.apache.org/jira/browse/NUTCH-121) - SegmentReader for mapred (Rod Taylor via ab)

11. Added support for OpenSearch (cutting)

12. [NUTCH-142](https://issues.apache.org/jira/browse/NUTCH-142) - NutchConf should use the thread context classloader
    (Mike Cannon-Brookes via pkosiorowski)

13. [NUTCH-160](https://issues.apache.org/jira/browse/NUTCH-160) - Use standard Java Regex library rather than
    org.apache.oro.text.regex (Rod Taylor via cutting)

14. [NUTCH-151](https://issues.apache.org/jira/browse/NUTCH-151) - CommandRunner can hang after the main thread exec is
    finished and has inefficient busy loop (Paul Baclace via cutting)

15. [NUTCH-174](https://issues.apache.org/jira/browse/NUTCH-174) - Problem encountered with ant during compilation

16. [NUTCH-190](https://issues.apache.org/jira/browse/NUTCH-190) - ParseUtil drops reason for failed parse
    (stack@archive.org via ab)

17. [NUTCH-169](https://issues.apache.org/jira/browse/NUTCH-169) - Remove static NutchConf (Marko Bauhardt via ab)

18. [NUTCH-194](https://issues.apache.org/jira/browse/NUTCH-194) - Nutch-169 introduced two tiny bugs (Marko Bauhardt via ab)

19. [NUTCH-178](https://issues.apache.org/jira/browse/NUTCH-178) - in search.jsp must be session creation "false"
    (YourSoft via siren)

20. [NUTCH-200](https://issues.apache.org/jira/browse/NUTCH-200) - OpenSearch Servlet ist broken
    (Marko Bauhardt via siren)

21. [NUTCH-81](https://issues.apache.org/jira/browse/NUTCH-81) - Webapp only works when deployed in root
    (AJ Banck, Michael Nebel via siren)

22. [NUTCH-139](https://issues.apache.org/jira/browse/NUTCH-139) - Standard metadata property names in the ParseData
    metadata (Chris A. Mattmann, jerome)

23. [NUTCH-192](https://issues.apache.org/jira/browse/NUTCH-192) - Meta data support for CrawlDatum
    (Stefan Groschupf via ab)
    
24. [NUTCH-52](https://issues.apache.org/jira/browse/NUTCH-52) - Parser plugin for MS Excel files
    (Rohit Kulkarni via jerome)

25. [NUTCH-53](https://issues.apache.org/jira/browse/NUTCH-53) - 	Parser plugin for Zip files
    (Rohit Kulkarni via jerome)

26. [NUTCH-137](https://issues.apache.org/jira/browse/NUTCH-137) - footer is not displayed in search result page
    (KuroSaka TeruHiko via siren)

27. [NUTCH-118](https://issues.apache.org/jira/browse/NUTCH-118) - FAQ link points to invalid URL
    (Steve Betts via siren)

28. [NUTCH-184](https://issues.apache.org/jira/browse/NUTCH-184) - Serbian (sr, Cyrilic) and Serbo-Croatian (sh, Latin)
    translation (Ivan Sekulovic via siren)

29. [NUTCH-211](https://issues.apache.org/jira/browse/NUTCH-211) - FetchedSegments leave readers open (Stefan Groschupf
    via cutting)

30. [NUTCH-140](https://issues.apache.org/jira/browse/NUTCH-140) - Add alias capability in parse-plugins.xml file that
    allows mimeType->extensionId mapping (Chris A. Mattmann via jerome)

31. [NUTCH-214](https://issues.apache.org/jira/browse/NUTCH-214) - Added Links to web site to search mailling list
    (Jake Vanderdray via jerome)

32. [NUTCH-204](https://issues.apache.org/jira/browse/NUTCH-204) - Multiple field values in HitDetails
    (Stefan Groschupf via jerome)

33. [NUTCH-219](https://issues.apache.org/jira/browse/NUTCH-219) - file.content.limit & ftp.content.limit should be changed
    to -1 to be consistent with http (jerome)
    
34. [NUTCH-221](https://issues.apache.org/jira/browse/NUTCH-221) - Prepare nutch for upcoming lucene 2.0 (siren)

35. [NUTCH-91](https://issues.apache.org/jira/browse/NUTCH-91) - Empty encoding causes exception (Michael Nebel via
    pkosiorowski)

36. [NUTCH-228](https://issues.apache.org/jira/browse/NUTCH-228) - Clustering plugin descriptor broken (Dawid Weiss via
    jerome)

37. [NUTCH-229](https://issues.apache.org/jira/browse/NUTCH-229) - Improved handling of plugin folder configuration
    (Stefan Groschupf via ab)

38. [NUTCH-206](https://issues.apache.org/jira/browse/NUTCH-206) - Search server throws InstantiationException (ab)
    
39. [NUTCH-203](https://issues.apache.org/jira/browse/NUTCH-203) - ParseSegment throws InstantiationException (Marko Bauhardt
    via ab)

40. [NUTCH-3](https://issues.apache.org/jira/browse/NUTCH-3) - Multi values of header discarded (Stefan Groschupf via ab)

41. Update to lucene 1.9.1 (cutting)

42. [NUTCH-235](https://issues.apache.org/jira/browse/NUTCH-235) - Duplicate Inlink values (ab)

43. [NUTCH-234](https://issues.apache.org/jira/browse/NUTCH-234) - Clustering extension code cleanups and a real
    JUnit test case for the current implementation (Dawid Weiss via ab)
    
44. [NUTCH-210](https://issues.apache.org/jira/browse/NUTCH-210) - Context.xml file for Nutch web application
    (Chris A. Mattmann via jerome)

45. [NUTCH-231](https://issues.apache.org/jira/browse/NUTCH-231) - Invalid CSS entries (AJ Banck via jerome)

46. [NUTCH-232](https://issues.apache.org/jira/browse/NUTCH-232) - Search.jsp has multiple search forms creating
    invalid html / incorrect focus function (jerome)
    
47. [NUTCH-196](https://issues.apache.org/jira/browse/NUTCH-196) - lib-xml and lib-log4j plugins (ab, jerome)

48. [NUTCH-244](https://issues.apache.org/jira/browse/NUTCH-244) - Inconsistent handling of property values
    boundaries / unable to set db.max.outlinks.per.page to
    infinite (jerome)
    
49. [NUTCH-245](https://issues.apache.org/jira/browse/NUTCH-245) - DTD for plugin.xml configuration files
    (Chris A. Mattmann via jerome)

50. [NUTCH-250](https://issues.apache.org/jira/browse/NUTCH-250) - Generate to log truncation caused by
    generate.max.per.host (Rod Taylor via cutting)
    
51. [NUTCH-125](https://issues.apache.org/jira/browse/NUTCH-125) - OpenOffice Parser plugin (ab)

52. Switch from using java.io.File to org.apache.hadoop.fs.Path.
    (cutting)

53. [NUTCH-240](https://issues.apache.org/jira/browse/NUTCH-240) - Scoring API: extension point, scoring filters and
    an OPIC plugin (ab)
    
54. [NUTCH-134](https://issues.apache.org/jira/browse/NUTCH-134) - Summarizer doesn't select the best snippets (jerome)

55. [NUTCH-268](https://issues.apache.org/jira/browse/NUTCH-268) - Generator and lib-http use different definitions of
    "unique host" (ab)
    
56. [NUTCH-280](https://issues.apache.org/jira/browse/NUTCH-280) - Url query causes NullPointerException (Grant Glouser
    via siren)

57. [NUTCH-285](https://issues.apache.org/jira/browse/NUTCH-285) - LinkDb Fails rename doesn't create parent directories
    (Dennis Kubes via ab)

58. [NUTCH-201](https://issues.apache.org/jira/browse/NUTCH-201) - Add support for subcollections
    (siren)

59. [NUTCH-298](https://issues.apache.org/jira/browse/NUTCH-298) - If a 404 for a robots.txt is returned a NPE is thrown
    (Stefan Groschupf via jerome)

60. [NUTCH-275](https://issues.apache.org/jira/browse/NUTCH-275) - Fetcher not parsing XHTML-pages at all (jerome)

61. [NUTCH-301](https://issues.apache.org/jira/browse/NUTCH-301) - CommonGrams loads analysis.common.terms.file for each query
    (Stefan Groschupf via jerome)

62. [NUTCH-110](https://issues.apache.org/jira/browse/NUTCH-110) - OpenSearchServlet outputs illegal xml characters
    (stack@archive.org via siren)

63. [NUTCH-292](https://issues.apache.org/jira/browse/NUTCH-292) - OpenSearchServlet: OutOfMemoryError: Java heap space
    (Stefan Neufeind via siren)

64. [NUTCH-307](https://issues.apache.org/jira/browse/NUTCH-307) - Wrong configured log4j.properties (jerome)

65. [NUTCH-303](https://issues.apache.org/jira/browse/NUTCH-303) - Logging improvements (jerome)

66. [NUTCH-308](https://issues.apache.org/jira/browse/NUTCH-308) - Maximum search time limit (ab)

67. [NUTCH-306](https://issues.apache.org/jira/browse/NUTCH-306) - DistributedSearch.Client liveAddresses concurrency
    problem (Grant Glouser via siren)

68. Update to hadoop-0.4 (Milind Bhandarkar, cutting)

69. [NUTCH-317](https://issues.apache.org/jira/browse/NUTCH-317) - Clarify what the queryLanguage argument of
    Query.parse(...) means (jerome)

70. Added alternative experimental web gui in contrib containing
    extensions like subcollection, keymatch, user preferences,
    caching, implemented mainly using tiles and jstl (siren)

71. [NUTCH-320](https://issues.apache.org/jira/browse/NUTCH-320) - DmozParser does not output list of urls to stdout
    but to a log file instead. Original functionality restored.

72. [NUTCH-271](https://issues.apache.org/jira/browse/NUTCH-271) - Add ability to limit crawling to the set of initially
    injected hosts (db.ignore.external.links) (Philippe Eugene,
    Stefan Neufeind via ab)

73. [NUTCH-293](https://issues.apache.org/jira/browse/NUTCH-293) - Support for Crawl-Delay (Stefan Groschupf via ab)

74. [NUTCH-327](https://issues.apache.org/jira/browse/NUTCH-327) - Fixed logging directory on cygwin (siren)


## Release 0.7 - 2005-08-17

 1. Added support for "type:" in queries. Search results are limited/qualified
    by mimetype or its primary type or sub type. For example,
    (1) searching with "type:application/pdf" restricts results
    to pages which were identified to be of mimetype "application/pdf".
    (2) with "type:application", nutch will return pages of
    primary type "application".
    (3) with "type:pdf", only pages of sub type "pdf" will be listed.
    (John Xing, 20050120)

 2. Added support for "date:" in queries. Last-Modified is indexed.
    Search results are restricted by lower and upper date (inclusive)
    as date:yyyymmdd-yyyymmdd. For example, date:20040101-20041231
    only returns pages with Last-Modified in year 2004.
    (John Xing, 20050122)

 3. Add URLFilter plugin interface and convert existing url filters into
    plugins. (John Xing, 20050206)

 4. Add UpdateSegmentsFromDb tool, which updates the scores and
    anchors of existing segments with the current values in the web
    db.  This is used by CrawlTool, so that pages are now only fetched
    once per crawl.  (Doug Cutting, 20050221)

 5. Moved code into org.apache.nutch sub-packages.  Changed license to
    Apache 2.0.  Removed jar files whose licenses do not permit
    redistribution by Apache.  Disabled compilation of plugins which
    require these libraries.  (Doug Cutting 20050301)

 6. Index host and title in separate fields.  Host was indexed
    previously only as a part of the URL.  Title was indexed as an
    anchor.  Now boosts for matching these fields may be adjusted
    separately from boosts for matching anchors and url.  Also: move
    site indexing to index-basic plugin to minimize the number of
    times the URL needs to be parsed; and, stop using anchor analyzer
    for anything but anchors.  (Piotr Kosiorowski via Doug Cutting
    20050323)

 7. Add servlet Cached.java that serves cached Content of any mime type.
    Slightly modified are web.xml and cached.jsp.
    (John Xing, 20050401)

 8. Add skipCompressedByteArray() to WritableUtils.java.
    (John Xing, 20050402)

 9. Fixes to jsp and static web pages.  These now use relative links,
    so that the Nutch webapp file can be used in places other than at
    the root.  Also fixed links to the about and help pages.  Bug #32.
    (Jerome Charron via cutting, 20050404)

10. Added some features to DistributedSearch: new segments can be added
    to searchservers without restarting the frontend, defective search
    servers are not queried until tey come back online, watchdog keeps
    an eye for your searchservers and writes simple statistics.
    (Sami Siren, 20050407)
    
11. Fix for bug #4 - Unbalanced quote in query eats all resources.
	(Piotr Kosiorowski, Sami Siren, 20050407)

12. Close Issue #33 - MIME content type detector (using magic char sequences).
    (Jerome Charron and Hari Kodungallur via John Xing, 20050416)

13. Add a servlet that implements A9's OpenSearch RSS web service.
    (cutting, 20050418)

14. Remove references to link analysis from tutorial, and enable
    scoring by link count when generating fetchlists and searching.
    (cutting, 20040419)

15. Make query boosts for host, title, anchor and phrase matches
    configurable.  (Piotr Kosiorowski via cutting, 20050419)

16. Add support for sorting search results and search-time deduping by
    fields other than site.

17. Automatically convert range queries into cached range filters.
    This improves the performance and scalability of, e.g., date range
    searching.

18. Several methods have been renamed due to misspellings.  The old
    methods have been deprecated and will be removed before the 1.0
    release.


## Release 0.6

 1. Added clustering-carrot2 plugin, together with introduction of clustering
    api and modification to search jsp. (Dawid Weiss via John Xing, 20040809)

 2. Make a number of changes to NDFS (Nutch Distributed File System)
    to fix bugs, add admin tools, etc.

    Also, modify all command line tools so you can indicate whether to
    use NDFS or the local filesystem.  If you indicate nothing, then
    it defaults to the local fs.

    I've used this to do a 35m page crawl via NDFS, distributed over a
    dozen machines.  (Mike Cafarella)

 3. Add support for BASE tags in HTML.  Outlinks are now correctly
    extracted when a BASE tag is present.  (cutting)

 4. Fix two bugs in result pagination.  When the last hit on a page
    was the last hit overall, the "next" button was sometimes shown
    when the "show all" button should be shown instead.  Also, in
    certain cases, the "show all" button would be shown when the
    "next" button should have been shown.  (cutting)

 5. Add config parameter "indexer.max.tokens" that determines the
    maximum number of tokens indexed per field.  (Andy Hedges via cutting)

 6. Add parser for mp3 files.  (Andy Hedges via cutting)

 7. Add RegexUrlNormalizer.  This is useful for things like stripping
    out session IDs from URLs.  To use it, add values for
    urlnormalizer.class and urlnormalizer.regex.file to your
    nutch-site.xml.  The RegexUrlNormalizer class extends the
    BasicUrlNormalizer, and does basic normalization as well.
    (Luke Baker via cutting)

 8. Added Swedish translation (Stefan Verzel via Sami Siren, 20040910)

 9. Added Polish translation (Andrzej Bialecki, 20040911)
 
10. Added 3 more language profiles to language identifier (ru,hu,pl).
	Other changes to language identifier: Porfiles converted to utf8,
	added some test cases, changed the similarity calculation.
	(Sami Siren, 20040925)

11. Added plugin parse-rtf (Andy Hedges via John Xing, 20040929)

12. Added plugin index-more and more.jsp (John Xing, 20041003)

13. Added "View as Plain Text" feature. A new op OP_PARSETEXT is introduced
    in DistributedSearch.java. text.jsp is added. (John Xing, 20041006)

14. Fixed a bug that fails cached.jsp, explain.jsp, anchors.jsp and text.jsp
    (but not search.jsp) with NullPointerException in distributed search.
    It seems that this bug appears after "hits per site" stuff is added.
    The fix is done in Hit.java, making sure String site is never null.
    Hope this fix not have bad effetct on "hits per site" code.
    (John Xing, 20041006)

15. Fixed a bug that fails fullyDelete() in FileUtil.java for
    LocalFileSystem.java. This bug also exposes possible incompleteness
    of NDFSFile.java, where a few methods are not supported, including
    delete(). Nothing changed in NDFSFile.java though. Leave it for future
    improvement (John Xing, 20041022).

16. Introduced option -noParsing to Fetcher.java and added ParseSegment.java.
    A new status code CANT_PARSE is added to FetcherOutput.java.
    Without option -noParsing , no change in fetcher behavior. With
    option -noParsing, fetcher does crawls only, no parsing is carried out.
    Then, ParseSegment.java should be used to parse in separate pass.
    (John Xing, 20041025)

17. Added ontology plugin. Currently it is used for query refinement, as
    examplified in refine-query-init.jsp and refine-query.jsp. By default,
    query refinement is disabled in search.jsp. Please check
    ./src/plugin/ontology/README.txt for further description.
    Ontology plugin certainly can be used for many other things.
    (Michael J. Pan via John Xing, 20041129)
 
18. Changed fetcher.server.delay to be a float, so that sub-second
    delays can be specified.  (cutting)

19. Added plugin.includes config parameter that determines which
    plugins are included.  By default now only http, html and basic
    indexing and search plugins are enabled, rather than all plugins.
    This should make default performance more predictable and reliable
    going forward. (cutting)

20. Cleaned up some filesystem code, including:

    - Replaced BufferedRandomAccessFile with two simpler utilties,
      NFSDataInputStream and NFSDataOutputStream.

    - Fixed the bug where SequenceFiles were no longer flushed when
      created, so that, when fetches crashed, segments were
      unreadable.  Now segments are always readable after crashes.
      Only the contents of the last buffer is lost.

    - Simplified the FSOutputStream API to not include seek().  We
      should never need that functionality.

    - Simplified LocalFileSystem's implementations of FSInputStream
      and FSOutputStream and optimized FSInputStream.seek().

    (cutting)

21. Fixed BasicUrlNormalizer to better handle relative urls.  The file
    part of a URL is normalized in the following manner:

      1. "/aa/../" will be replaced by "/" This is done step by step until
	 the url doesnÂ´t change anymore. So we ensure, that
	 "/aa/bb/../../" will be replaced by "/", too

      2. leading "/../" will be replaced by "/"

    (Sven Wende via cutting)

22. Fix Page constructors so that next fetch date is less likely to be
    misconstrued as a float.  This patches a problem in WebDBInjector,
    where new pages were added to the db with nextScore set to the
    intended nextFetch date.  This, in turn, confused link analysis.

23. In ndfs code, replace addLocalFile(), putToLocalFile() with
    copyFromLocalFile(), moveFromLocalFile(), copyToLocalFile() and
    moveToLocalFile(). (John Xing, 20041217)

24. Added new config parameter fetcher.threads.per.host.  This is used
    by the Http protocol.  When this is one behavior is as before.
    When this is greater than one then multiple threads are permitted
    to access a host at once.  Note that fetcher.server.delay is no
    longer consistently observed when this is greater than one.
    (Luke Baker via Doug Cutting)


## Release 0.5

 1. Changed plugin directory to be a list of directories.

 2. Permit Plugin to be the default plugin implementation.

 3. Added pluggable interface for network protocols in new package
    net.nutch.protocol.  Moved http code from core into a plugin.

 4. Added pluggable interface for content parsing in new package
    net.nutch.parse.  Moved html parsing code from core into a
    plugin.

 5. Fixed a bug in NutchAnalysis where 16-bit characters were not
    processed correctly.

 6. Fixed bug #971731: random summaries on result page.
    (Daniel Naber via cutting)

 7. Made Nutch logo transparent. (Daniel Naber via cutting)

 8. Added file protocol plugin.  (John Xing via cutting)

 9. Added ftp protocol plugin.  (John Xing via cutting)

10. Added pdf and msword parser plugins.  (John Xing via cutting)

11. Added pluggable indexing interface.  By default, url, content,
    anchors and title are indexed, as before, but now one can easily
    alter this to, e.g., index metadata.  A demonstration is provided
    which extracts and indexes Creative Commons license urls. (cutting)

12. Add language identification plugin. 

    The process of identification is as follows:

    1. html (html only, HTML 4.0 "lang" attribute)
    2. meta tags (html only, http-equiv, dc.language)
    3. http header (Content-Language)
    4. if all above fail "statistical analysis"

    1 & 2 are run during the fetching phase and 3 & 4 are run on
    indexing phase.

    Currently supported languages (in "statistical analysis") are
    da,de,el,en,es,fi,fr,it,nl,sv and pt. The corpus used was grabbed
    from http://www.isi.edu/~koehn/europarl/ and the profiles were
    build with tool supplied in patch.

    After indexing the language can be found from field named "lang"

    It's not 100% accurate but it's a start.
    (Sami Siren)

13. Added SegmentMergeTool and "mergesegs" command, to remove
    duplicated or otherwise not used content from several segments and
    joining them together into a single new segment.  The tool also
    optionally performs several other steps required for proper
    operation of Nutch - such as indexing segments, deleting
    duplicates, merging indices, and indexing the new single segment.
    (Andrzej Bialecki)

14. Add the ability to retrieve ParseData of a search hit. ParseData
    contains many valuable properties of a search hit.

    This is required (among others) to properly display the cached
    content because it's not possible to determine the character
    encoding from the output of the getContent() method (which returns
    byte[]). The symptoms are that for HTML pages using non-latin1 or
    non-UTF8 encodings the cached preview will almost certainly look
    broken. Using the attached patch it is possible to determine the
    character encoding from the ParseData (for HTTP: Content-Type
    metadata), and encode the content accordingly. (Andrzej Bialecki)

15. Add a pluggable query interface.  By default, the content, anchor
    and url fields are searched as before.  A sample plugin indexes
    the host name and adds a "site:" keyword to query parsing.

16. Added support for "lang:" in queries.  For example, searching with
    "lang:en" restricts results to pages which were identified to
    be in English.

17. Automatically optimize field queries to use cached Lucene filters.
    This makes, for example, searches restricted by languages or sites
    that are very common much faster.

18. Improved charset handling in jsp pages.  (jshin by cutting)

19. Permit topic filtering when injecting DMOZ pages.  (jshin by cutting)

20. When parsing crawled pages, interpret charset specifications in
    html meta tags.  (jshin by cutting)

21. Added support for "cc:licensed" in queries, which searches for documents
    released under Creative Commons licenses.  Attributes of the
    license may also be queried, with, e.g., "cc:by" for
    attribution-required licenses, "cc:nc" for non-commercial
    licenses, etc.

22. Relative paths named in plugin.folders are now searched for on the
    classpath.  This makes, e.g., deployment in a war file much simpler.

23. Modifications to Fetcher.java.

    1. Make sure it works properly with regard to creation and initialization
    of plugin instances. The problem was that multiple threads race to
    startUp() or shutDown() plugin instances. It was solved by synchronizing
    certain codes in PluginRepository.java and Extension.java.
    (Stefan Groschupf via John Xing)

    2. Added code to explictly shutDown() plugins. Otherwise FetcherThreads
    may never return (quit) if there are still data or other structures
    (e.g., persistent socket connections) associated with plugins. (John Xing)
    
    3. Fixed one type of Fetcher "hang" problems by monitoring named
    FetcherThreads. If all FetcherThreads are gone (finished),
    Fetcher.java is considered done. The problem was: there could be
    runaway threads started by external libs via FetcherThreads.
    Those threads never return, thus keep Fetcher from exiting normally.
    (John Xing)

24. Eliminate excessive hits from sites.  This is done efficiently by
    adding the site name to Hit instances, and, when needed,
    re-querying with too-frequent sites prohibited in the query.


## Release 0.4

 1. Http class refactored.  (Kevin Smith via Tom Pierce)

 2. Add Finnish translation. (Sampo Syreeni via Doug Cutting)

 3. Added Japanese translation. (Yukio Andoh via Doug Cutting)

 4. Updated Dutch translation. (Ype Kingma via Doug Cutting)

 5. Initial version of Distributed DB code.  (Mike Cafarella)

 6. Make things more tolerant of crashed fetcher output files.
    (Doug Cutting)

 7. New skin for website. (Frank Henze via Doug Cutting)

 8. Added Spanish translation. (Diego Basch via Doug Cutting)

 9. Add FTP support to fetcher.  (John Xing via Doug Cutting)

10. Added Thai translation. (Pichai Ongvasith via Doug Cutting)

11. Added Robots.txt & throttling support to Fetcher.java.  (Mike
    Cafarella)

12. Added nightly build. (Doug Cutting)

13. Default all link scores to 1.0. (Doug Cutting)

14. Permit one to keep internal links. (Doug Cutting)

15. Fixed dedup to select shortest URL. (Doug Cutting)

16. Changed index merger so that merged index is written to named
    directory, rather than to a generated name in that directory.
    (Doug Cutting)

17. Disable coordination weighting of query clauses and other minor
    scoring improvements. (Doug Cutting)

18. Added a new command, crawl, that constructs a database, injects a
    url file and performs a few rounds of generate/fetch/updatedb.
    This simplifies use for intranet sites.  Changed some defaults to
    be more intranet friendly.  (Doug Cutting)

19. Fixed a bug where Fetcher.java didn't construct correct relative
    links when a page was redirected.  (Doug Cutting)

20. Fixed a query parser problem with lookahead over plusses and minuses.
    (Doug Cutting)

21. Add support for HTTP proxy servers.  (Sami Siren via Doug Cutting)

22. Permit searching while fetching and/or indexing.
    (Sami Siren via Doug Cutting)

23. Fix a bug when throttling is disabled.  (Sami Siren via Doug Cutting)

24. Updated Bahasa Malaysia translation.  (Michael Lim via Doug Cutting)

25. Added Catalan translation.  (Xavier Guardiola via Doug Cutting)

26. Added brazilian portuguese translation.
    (A. Moreir via Doug Cutting)

27. Added a french translation.  (Julien Nioche via Doug Cutting)

28. Updated to Lucene 1.4RC3.  (Doug Cutting)

29. Add capability to boost by link count & use it in crawl tool.
    (Doug Cutting)

30. Added plugin system.  (Stefan Groschupf via Doug Cutting)

31. Add this change log file, for recording significant changes to
    Nutch.  Populate it with changes from the last few months.
