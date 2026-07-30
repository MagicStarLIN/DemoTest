# DemoTest Java 21 Modernization Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Modernize DemoTest to a reproducible Java 21 build and fix confirmed non-`jerryMouse` security, correctness, resource-management, and concurrency defects without removing its runnable learning demos.

**Architecture:** Keep the existing single Maven module and package layout. Upgrade the build foundation first, then migrate external-library call sites, protect each reusable bug fix with JUnit Jupiter tests, and finish with repository documentation and static-analysis cleanup. Network tests use an in-process JDK HTTP server and never access public services.

**Tech Stack:** Java 21, Maven 3.9+, JUnit Jupiter 6.1.2, Apache HttpClient 5.6.2, Netty 4.2.16.Final, Fastjson2 2.0.63, SpotBugs 4.9.8.2.

## Global Constraints

- Do not modify any source file under `src/main/java/com/lcl/jerryMouse`.
- Use Java 21 through `<maven.compiler.release>21</maven.compiler.release>`.
- Keep the project as one Maven module.
- Preserve runnable `main` entry points unless the entry point itself is the confirmed defect.
- Do not rewrite Git history, force-push, or mutate the remote repository.
- Do not convert intentionally dangerous deadlock or OOM teaching examples into normal production behavior.
- Every reusable behavior fix follows red-green-refactor with a focused JUnit Jupiter test.
- Do not use external websites, real credentials, fixed ports, or unbounded sleeps in tests.

---

### Task 1: Establish the Java 21 Build and JUnit Jupiter Baseline

**Files:**
- Modify: `pom.xml`
- Modify: `src/main/java/com/lcl/utils/HttpClientUtil.java`
- Delete: `src/test/java/com/lcl/DemoTest/AppTest.java`
- Create: `src/test/java/com/lcl/DemoTest/BuildSmokeTest.java`

**Interfaces:**
- Consumes: JDK 21 and Maven 3.9 or newer.
- Produces: a fixed-version Java 21 build and a JUnit Platform test harness used by every later task.

- [ ] **Step 1: Replace the placeholder JUnit 4 test with a Jupiter smoke test**

```java
package com.lcl.DemoTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildSmokeTest {
    @Test
    void runsOnJava21OrNewer() {
        assertEquals(21, Runtime.version().feature());
    }
}
```

- [ ] **Step 2: Rewrite the POM around fixed Java 21 properties**

Use these exact final properties:

```xml
<properties>
    <maven.compiler.release>21</maven.compiler.release>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <junit.version>6.1.2</junit.version>
</properties>
```

Use the following dependencies that do not require a package/API migration:

```text
commons-io:commons-io:2.22.0
org.springframework:spring-core:7.0.8
jakarta.servlet:jakarta.servlet-api:6.1.0
org.projectlombok:lombok:1.18.46 (provided)
com.google.guava:guava:33.6.0-jre
org.jsoup:jsoup:1.23.1
com.alibaba:fastjson:2.0.63 (temporary compatibility coordinate)
org.apache.commons:commons-lang3:3.20.0
commons-codec:commons-codec:1.19.0
org.openjdk.jol:jol-core:0.17
org.junit.jupiter:junit-jupiter:6.1.2 (test)
```

Keep these migration-sensitive dependencies temporarily, with their latest
compatible major-line versions, so this baseline commit remains green:

```text
com.lmax:disruptor:3.4.4
org.apache.httpcomponents:httpclient:4.5.14
org.apache.httpcomponents:httpcore:4.4.16
org.apache.httpcomponents:httpmime:4.5.14
io.netty:netty-all:5.0.0.Alpha2
```

Remove JUnit 4, Jackson YAML, Commons CLI, both Hutool declarations, and
EasyExcel. Tasks 5 and 7 replace the temporary migration-sensitive coordinates.
Configure these exact build plugins:

```text
maven-enforcer-plugin:3.6.2
maven-clean-plugin:3.5.0
maven-resources-plugin:3.5.0
maven-compiler-plugin:3.15.0
maven-surefire-plugin:3.6.0-M1
maven-jar-plugin:3.5.1
maven-dependency-plugin:3.9.0
spotbugs-maven-plugin:4.9.8.2
```

The enforcer rules must require Maven `[3.9,)` and Java `[21,22)`.

- [ ] **Step 3: Remove the EasyExcel-only collection check**

In `src/main/java/com/lcl/utils/HttpClientUtil.java`, remove the
`com.alibaba.excel.util.CollectionUtils` import and use:

```java
headers == null || headers.isEmpty()
```

- [ ] **Step 4: Verify the baseline is green**

Run:

```bash
mvn -DskipTests compile
```

Expected: dependency resolution succeeds and every main source compiles. Do not
commit this task while compilation is broken.

- [ ] **Step 5: Commit the build baseline**

```bash
git add pom.xml src/test/java/com/lcl/DemoTest src/main/java/com/lcl/utils/HttpClientUtil.java
git commit -m "build: modernize project for Java 21"
```

---

### Task 2: Remove Credentials From the Crawler Demo

**Files:**
- Modify: `src/main/java/com/lcl/Crawler/LoginSimulate.java`
- Create: `src/test/java/com/lcl/Crawler/LoginSimulateTest.java`

**Interfaces:**
- Consumes: environment values `DOUBAN_LOGIN_NAME`, `DOUBAN_LOGIN_PASSWORD`, and optional `DOUBAN_COOKIE`.
- Produces:
  - `static Map<String, String> buildLoginData(String username, String password)`
  - `setCookies(String url, String cookie)`
  - `jsoupLogin(String loginUrl, String userInfoUrl, String username, String password)`

- [ ] **Step 1: Write failing tests for explicit credentials and validation**

```java
package com.lcl.Crawler;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginSimulateTest {
    @Test
    void buildsLoginDataFromCallerSuppliedCredentials() {
        Map<String, String> data = LoginSimulate.buildLoginData("learner@example.test", "secret");
        assertEquals("learner@example.test", data.get("name"));
        assertEquals("secret", data.get("password"));
    }

    @Test
    void rejectsBlankCredentialsBeforeNetworkAccess() {
        assertThrows(IllegalArgumentException.class,
                () -> LoginSimulate.buildLoginData(" ", "secret"));
        assertThrows(IllegalArgumentException.class,
                () -> LoginSimulate.buildLoginData("learner@example.test", ""));
    }
}
```

- [ ] **Step 2: Verify the tests fail because `buildLoginData` does not exist**

Run:

```bash
mvn -Dtest=LoginSimulateTest test
```

Expected: test compilation fails with `cannot find symbol: buildLoginData`.

- [ ] **Step 3: Implement caller-supplied credentials**

Implement `buildLoginData` with `String.isBlank()` validation. Change `setCookies`
to require its Cookie argument. Change `jsoupLogin` to accept username and
password. In `main`, read:

```java
String username = System.getenv("DOUBAN_LOGIN_NAME");
String password = System.getenv("DOUBAN_LOGIN_PASSWORD");
String cookie = System.getenv("DOUBAN_COOKIE");
```

Never print those values. Remove all literal account identifiers, passwords, and
Cookie contents.

- [ ] **Step 4: Verify crawler tests and scan the current tree for secrets**

Run:

```bash
mvn -Dtest=LoginSimulateTest test
rg -n 'data\.put\("password","|dbcl2=|\.header\("Cookie","' src/main/java
```

Expected: two tests pass; the secret scan has no matches.

- [ ] **Step 5: Commit the credential cleanup**

```bash
git add src/main/java/com/lcl/Crawler/LoginSimulate.java src/test/java/com/lcl/Crawler/LoginSimulateTest.java
git commit -m "fix: remove crawler credentials from source"
```

---

### Task 3: Repair the Binary Search Tree API and Algorithms

**Files:**
- Modify: `src/main/java/com/lcl/DataStructure/tree/nodetree/INodeTree.java`
- Modify: `src/main/java/com/lcl/DataStructure/tree/nodetree/INodeTreeimpl.java`
- Modify: `src/main/java/com/lcl/DataStructure/tree/nodetree/Node.java`
- Modify: `src/main/java/com/lcl/DataStructure/tree/nodetree/TreeTest.java`
- Create: `src/test/java/com/lcl/DataStructure/tree/nodetree/NodeTreeTest.java`

**Interfaces:**
- Produces:
  - `Node findNode(int key, Node root)`
  - `Node insertNode(int key, Node root)`
  - `Node deleteNode(int key, Node root)`
  - `int Node.getData()`
  - `Node Node.getLeftChild()`
  - `Node Node.getRightChild()`

- [ ] **Step 1: Write failing insertion, lookup, and deletion tests**

```java
package com.lcl.DataStructure.tree.nodetree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTreeTest {
    private final INodeTree tree = new INodeTreeimpl();

    @Test
    void insertsNodesOnBothSidesAndFindsThem() {
        Node root = tree.insertNode(8, null);
        root = tree.insertNode(3, root);
        root = tree.insertNode(10, root);
        assertEquals(3, root.getLeftChild().getData());
        assertEquals(10, root.getRightChild().getData());
        assertEquals(10, tree.findNode(10, root).getData());
        assertNull(tree.findNode(99, root));
    }

    @Test
    void deletesLeafOneChildAndTwoChildNodes() {
        Node root = null;
        for (int value : new int[]{8, 3, 10, 1, 6, 4, 7, 14, 13}) {
            root = tree.insertNode(value, root);
        }
        root = tree.deleteNode(1, root);
        root = tree.deleteNode(14, root);
        root = tree.deleteNode(3, root);
        assertNull(tree.findNode(1, root));
        assertNull(tree.findNode(14, root));
        assertNull(tree.findNode(3, root));
        assertNotNull(tree.findNode(4, root));
        assertNotNull(tree.findNode(6, root));
    }

    @Test
    void deletingRootReturnsTheReplacementRoot() {
        Node root = tree.insertNode(8, null);
        root = tree.insertNode(3, root);
        root = tree.deleteNode(8, root);
        assertEquals(3, root.getData());
    }
}
```

- [ ] **Step 2: Verify tests fail against the old boolean mutation API**

Run:

```bash
mvn -Dtest=NodeTreeTest test
```

Expected: test compilation fails because `insertNode`, `deleteNode`, and getters
do not exist.

- [ ] **Step 3: Implement root-returning tree operations**

Rename the interface methods to lower camel case. `insertNode` must return a new
root when passed `null`, attach new children through their parent, and leave the
tree unchanged for duplicate keys. `findNode` must use one mutually exclusive
comparison branch per iteration.

Implement deletion recursively:

```java
if (root == null) return null;
if (key < root.data) root.leftChild = deleteNode(key, root.leftChild);
else if (key > root.data) root.rightChild = deleteNode(key, root.rightChild);
else {
    if (root.leftChild == null) return root.rightChild;
    if (root.rightChild == null) return root.leftChild;
    Node successor = minimum(root.rightChild);
    root.data = successor.data;
    root.rightChild = deleteNode(successor.data, root.rightChild);
}
return root;
```

Add read-only getters to `Node` and update the commented example calls in
`TreeTest`.

- [ ] **Step 4: Run the focused and full test suites**

```bash
mvn -Dtest=NodeTreeTest test
mvn test
```

Expected: all three tree tests and the full suite pass.

- [ ] **Step 5: Commit the tree repair**

```bash
git add src/main/java/com/lcl/DataStructure/tree/nodetree src/test/java/com/lcl/DataStructure/tree/nodetree
git commit -m "fix: repair binary search tree operations"
```

---

### Task 4: Make Formatting Utilities Thread-Safe

**Files:**
- Modify: `src/main/java/com/lcl/utils/DateUtils.java`
- Modify: `src/main/java/com/lcl/utils/CommonUtil.java`
- Modify: `src/main/java/com/lcl/test/Test2.java`
- Create: `src/test/java/com/lcl/utils/DateUtilsTest.java`
- Create: `src/test/java/com/lcl/utils/CommonUtilTest.java`

**Interfaces:**
- Preserves existing public `DateUtils` method signatures.
- Produces:
  - `CommonUtil.formatFiveDigits(int value)`
  - `CommonUtil.formatYmd(Date date)`
  - `CommonUtil.formatHm(Date date)`

- [ ] **Step 1: Write concurrent formatting tests**

`DateUtilsTest` must submit 1,000 calls to a 12-thread executor and assert every
result for epoch `0L` equals the value produced from the same system time zone by
a literal `DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")`.

`CommonUtilTest` must assert:

```java
assertEquals("00042", CommonUtil.formatFiveDigits(42));
assertEquals("19700101", CommonUtil.formatYmd(new Date(0)));
```

The date assertion must compute the expected local date from
`Instant.EPOCH.atZone(ZoneId.systemDefault())`, not from `CommonUtil`.

- [ ] **Step 2: Verify tests fail because the new CommonUtil API is absent**

Run:

```bash
mvn -Dtest=DateUtilsTest,CommonUtilTest test
```

Expected: test compilation fails for `formatFiveDigits`, `formatYmd`, and
`formatHm`.

- [ ] **Step 3: Replace shared mutable formatters**

Use immutable `DateTimeFormatter` constants and convert legacy `Date` values with
`date.toInstant().atZone(ZoneId.systemDefault())`. Remove public
`SimpleDateFormat` and `DecimalFormat` fields. Update `Test2` to call the three
new formatting methods.

For arbitrary caller-provided patterns in `DateUtils`, create a
`DateTimeFormatter` per call. Preserve the existing empty-string behavior for
null inputs.

- [ ] **Step 4: Verify concurrency and scan for static mutable formatters**

```bash
mvn -Dtest=DateUtilsTest,CommonUtilTest test
rg -n 'static final (SimpleDateFormat|DateFormat|DecimalFormat)' src/main/java
```

Expected: tests pass and the scan has no non-intentional matches outside
explicit teaching classes.

- [ ] **Step 5: Commit the formatter migration**

```bash
git add src/main/java/com/lcl/utils/DateUtils.java src/main/java/com/lcl/utils/CommonUtil.java src/main/java/com/lcl/test/Test2.java src/test/java/com/lcl/utils
git commit -m "fix: make formatting utilities thread safe"
```

---

### Task 5: Migrate HTTP Utilities to Apache HttpClient 5

**Files:**
- Modify: `pom.xml`
- Modify: `src/main/java/com/lcl/utils/HttpClientUtil.java`
- Modify: `src/main/java/com/lcl/utils/HttpClientUtils.java`
- Modify: `src/main/java/com/lcl/Crawler/CrawlerDemo.java`
- Modify: `src/main/java/com/lcl/test/Test2.java`
- Create: `src/test/java/com/lcl/utils/HttpClientUtilTest.java`

**Interfaces:**
- Preserves:
  - `getRequest(String, Map<String, String>)`
  - `postRequest(String, Map<String, Object>)`
  - `postRequest(String, Map<String, Object>, Map<String, Object>)`
  - `postRequestJson(String, String, Map<String, Object>)`
  - `deleteRequestJson(String, String, Map<String, Object>)`
- Failure contract: throw `IllegalStateException` with the original exception as
  cause instead of printing a stack trace and returning an ambiguous empty value.

- [ ] **Step 1: Write local-server integration tests**

Create a JDK `HttpServer` on `new InetSocketAddress("localhost", 0)` in
`@BeforeEach` and stop it in `@AfterEach`. Register:

```text
/query -> returns raw query text
/form  -> returns request body
/json  -> returns request body
```

Tests must assert:

```java
assertEquals("name=codex", HttpClientUtil.getRequest(baseUrl + "/query", Map.of("name", "codex")));
assertEquals("name=codex", HttpClientUtil.postRequest(baseUrl + "/form", Map.of("name", "codex")));
assertEquals("{\"name\":\"codex\"}", HttpClientUtil.postRequestJson(baseUrl + "/json", "{\"name\":\"codex\"}", Map.of()));
assertThrows(IllegalStateException.class,
        () -> HttpClientUtil.getRequest("http://localhost:1/unreachable", Map.of()));
```

- [ ] **Step 2: Verify tests fail against the old exception contract or migration state**

```bash
mvn -Dtest=HttpClientUtilTest test
```

Expected: at least the unreachable-host test fails because the old method returns
an empty string; after Task 1 it may also fail compilation on HttpClient 4 imports.

- [ ] **Step 3: Implement HttpClient 5 classic requests with deterministic cleanup**

In `pom.xml`, remove HttpClient 4, HttpCore 4, and HttpMime 4 and add:

```text
org.apache.httpcomponents.client5:httpclient5:5.6.2
```

Use `org.apache.hc.client5.http.impl.classic.CloseableHttpClient`,
`CloseableHttpResponse`, `HttpClients`, `RequestConfig`,
`URIBuilder`, `UrlEncodedFormEntity`, and
`org.apache.hc.core5.http.io.entity.EntityUtils`.

Every one-shot method must use nested try-with-resources:

```java
try (CloseableHttpClient client = HttpClients.createDefault();
     CloseableHttpResponse response = client.execute(request)) {
    return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
} catch (Exception exception) {
    throw new IllegalStateException("HTTP request failed: " + request.getRequestUri(), exception);
}
```

Use `Timeout.ofSeconds(30)` rather than the old five-minute integer timeouts.
Migrate the pooled helper and crawler imports to HttpClient 5. Remove raw
iterators and `printStackTrace`.

- [ ] **Step 4: Run HTTP tests and SpotBugs null-dereference checks**

```bash
mvn -Dtest=HttpClientUtilTest test
mvn -DskipTests spotbugs:spotbugs
xmllint --format target/spotbugsXml.xml | rg 'NP_GUARANTEED_DEREF_ON_EXCEPTION_PATH.*HttpClientUtil'
```

Expected: four HTTP tests pass and the SpotBugs query has no match.

- [ ] **Step 5: Commit the HTTP migration**

```bash
git add pom.xml src/main/java/com/lcl/utils/HttpClientUtil.java src/main/java/com/lcl/utils/HttpClientUtils.java src/main/java/com/lcl/Crawler/CrawlerDemo.java src/main/java/com/lcl/test/Test2.java src/test/java/com/lcl/utils/HttpClientUtilTest.java
git commit -m "refactor: migrate HTTP demos to HttpClient 5"
```

---

### Task 6: Repair Reactor and Producer/Consumer Concurrency Demos

**Files:**
- Modify: `src/main/java/com/lcl/Socket/nioSocket/ReactorTask.java`
- Modify: `src/main/java/com/lcl/designmodel/producerAndConsumer/Producer.java`
- Modify: `src/main/java/com/lcl/designmodel/producerAndConsumer/Consumer.java`
- Modify: `src/main/java/com/lcl/designmodel/producerAndConsumer/Test.java`
- Create: `src/test/java/com/lcl/Socket/nioSocket/ReactorTaskTest.java`
- Create: `src/test/java/com/lcl/designmodel/producerAndConsumer/ProducerConsumerTest.java`

**Interfaces:**
- `ReactorTask` becomes `AutoCloseable`.
- Produces `ReactorTask(InetSocketAddress address)` and
  `InetSocketAddress getLocalAddress()`.
- Producer and Consumer consume a `BlockingQueue<Integer>` and terminate when
  interrupted.

- [ ] **Step 1: Write failing bounded lifecycle tests**

`ReactorTaskTest`:

```java
@Test
void constructionBindsOnceWithoutStartingRecursiveThreads() throws Exception {
    try (ReactorTask reactor = new ReactorTask(new InetSocketAddress("localhost", 0))) {
        assertTrue(reactor.getLocalAddress().getPort() > 0);
    }
}
```

`ProducerConsumerTest` must create an `ArrayBlockingQueue<>(2)`, start one
producer and one consumer, wait up to two seconds for at least one observed
consumption, interrupt both, join each for one second, and assert both threads
are no longer alive.

- [ ] **Step 2: Verify the tests fail against recursive construction and non-interruptible loops**

```bash
mvn -Dtest=ReactorTaskTest,ProducerConsumerTest test
```

Expected: Reactor test fails because the required constructor and lifecycle API
are absent; producer/consumer test fails because interruptions are swallowed.

- [ ] **Step 3: Implement bounded lifecycle behavior**

`ReactorTask` must bind the supplied address, configure nonblocking mode,
register `OP_ACCEPT`, and never create or start another `ReactorTask` in its
constructor. `run()` owns the selector loop; `close()` closes the selector and
server channel.

Producer and Consumer must use `BlockingQueue.put`/`take`, perform simulated
delay after the queue operation without holding an external monitor, restore the
interrupt flag, and return from `run()` on interruption. Use
`ThreadLocalRandom.current().nextInt(1000)`.

- [ ] **Step 4: Run concurrency tests and relevant SpotBugs checks**

```bash
mvn -Dtest=ReactorTaskTest,ProducerConsumerTest test
mvn -DskipTests spotbugs:spotbugs
xmllint --format target/spotbugsXml.xml | rg 'SC_START_IN_CTOR|IL_INFINITE_RECURSIVE_LOOP|SWL_SLEEP_WITH_LOCK_HELD'
```

Expected: tests pass; no matches refer to `ReactorTask`, `Producer`, or
`Consumer`. Findings from explicitly named deadlock teaching demos may remain.

- [ ] **Step 5: Commit concurrency repairs**

```bash
git add src/main/java/com/lcl/Socket/nioSocket/ReactorTask.java src/main/java/com/lcl/designmodel/producerAndConsumer src/test/java/com/lcl/Socket/nioSocket src/test/java/com/lcl/designmodel/producerAndConsumer
git commit -m "fix: bound concurrency demo lifecycles"
```

---

### Task 7: Fix Small Deterministic Demo Failures and Java 21 API Compatibility

**Files:**
- Modify: `pom.xml`
- Modify: `src/main/java/com/lcl/guava/TestGuava.java`
- Modify: Disruptor demos under `src/main/java/com/lcl/disruptor`
- Modify: `src/main/java/com/lcl/designmodel/simpleFactory/ShapeFactory.java`
- Modify: `src/main/java/com/lcl/leetcode/Generate.java`
- Modify: `src/main/java/com/lcl/test/Test8.java`
- Modify: `src/main/java/com/lcl/arithmetic/RSA/Base64Coded.java`
- Modify: `src/main/java/com/lcl/nettyTest/TimeClient.java`
- Modify: `src/main/java/com/lcl/nettyTest/TimeServer.java`
- Modify: `src/main/java/com/lcl/nettyTest/TimeClientHandler.java`
- Modify: `src/main/java/com/lcl/nettyTest/TimeServerHandler.java`
- Modify: Netty handler classes under `src/main/java/com/lcl/nettyTest/echo`
- Create: `src/test/java/com/lcl/designmodel/simpleFactory/ShapeFactoryTest.java`
- Create: `src/test/java/com/lcl/leetcode/GenerateTest.java`
- Create: `src/test/java/com/lcl/test/Test8Test.java`

**Interfaces:**
- Preserves `ShapeFactory.getShape(String)`.
- Makes `Test8.handleNumbers(String)` package-visible for testing.
- Migrates Netty handlers from removed `ChannelHandlerAdapter` usage to
  `ChannelInboundHandlerAdapter` or `SimpleChannelInboundHandler`.

- [ ] **Step 1: Write focused failing tests**

```java
assertNull(ShapeFactory.getShape(new String("")));
assertInstanceOf(Circle.class, ShapeFactory.getShape("circle"));
assertEquals(List.of(1, 4, 6, 4, 1), new Generate().solution(5));
assertEquals("壹", new Test8().handleNumbers("1.0"));
```

- [ ] **Step 2: Verify failures**

```bash
mvn -Dtest=ShapeFactoryTest,GenerateTest,Test8Test test
```

Expected: the empty-string factory test exposes reference comparison,
`GenerateTest` confirms the intended Pascal-row contract, and `Test8Test` fails
because `"."` is treated as an any-character regular expression.

- [ ] **Step 3: Implement deterministic fixes**

- Replace the temporary Fastjson compatibility coordinate with
  `com.alibaba.fastjson2:fastjson2:2.0.63` and migrate `TestGuava` to
  `com.alibaba.fastjson2.JSON`.
- Upgrade Disruptor to `com.lmax:disruptor:4.0.0` and adapt its demo call sites
  to the 4.x API.
- Replace Netty 5 alpha with `io.netty:netty-all:4.2.16.Final`.
- Replace `shapeType == ""` with `shapeType.isBlank()`.
- Remove the guaranteed empty-array write from `Generate.main`.
- Split decimal strings with `split("\\.", -1)`.
- Use `StandardCharsets.UTF_8` for every changed `getBytes`, `new String`, reader,
  writer, and Scanner construction.
- Replace boxed primitive parsing with `Integer.parseInt`.
- Update Netty 5-alpha-era handler base classes and callback signatures to Netty
  4.2.16.Final while preserving message behavior and shutdown logic.

- [ ] **Step 4: Run focused tests and compile every demo**

```bash
mvn -Dtest=ShapeFactoryTest,GenerateTest,Test8Test test
mvn -DskipTests compile
```

Expected: focused tests pass and all main sources compile on Java 21.

- [ ] **Step 5: Commit deterministic demo fixes**

```bash
git add pom.xml src/main/java/com/lcl/guava/TestGuava.java src/main/java/com/lcl/disruptor src/main/java/com/lcl/designmodel/simpleFactory/ShapeFactory.java src/main/java/com/lcl/leetcode/Generate.java src/main/java/com/lcl/test/Test8.java src/main/java/com/lcl/arithmetic/RSA/Base64Coded.java src/main/java/com/lcl/nettyTest src/test/java/com/lcl/designmodel/simpleFactory src/test/java/com/lcl/leetcode src/test/java/com/lcl/test
git commit -m "fix: update deterministic demos for Java 21"
```

---

### Task 8: Remove Machine-Specific IO and Close Resources

**Files:**
- Modify: `src/main/java/com/lcl/utils/FileUtils.java`
- Modify: `src/main/java/com/lcl/io/TestNIO.java`
- Modify: `src/main/java/com/lcl/utils/ImageUtil.java`
- Create: `src/test/java/com/lcl/utils/FileUtilsTest.java`
- Create: `src/test/java/com/lcl/io/TestNIOTest.java`

**Interfaces:**
- `FileUtils.readFromFile(Path path, Charset charset)` returns `List<String>` and
  throws `UncheckedIOException` on failure.
- `TestNIO.read(Path path)` returns file text.
- `TestNIO.write(Path path, String content)` writes UTF-8 text.
- `ImageUtil.drawImage(Path output)` and
  `ImageUtil.drawImage1(Path output)` write generated images.
- `ImageUtil.drawTransparent(Path input, Path output)` reads and writes
  caller-supplied paths instead of fixed `/Users/...` locations.

- [ ] **Step 1: Write temporary-directory IO tests**

Use JUnit `@TempDir Path tempDir`:

```java
Path file = tempDir.resolve("demo.txt");
TestNIO.write(file, "学习 Java 21");
assertEquals("学习 Java 21", TestNIO.read(file));
assertEquals(List.of("第一行", "第二行"),
        FileUtils.readFromFile(writeLines(file), StandardCharsets.UTF_8));
```

`writeLines` is a private test helper that uses `Files.writeString` with the
literal `"第一行\n第二行\n"`.

- [ ] **Step 2: Verify tests fail because Path-based APIs do not exist**

```bash
mvn -Dtest=FileUtilsTest,TestNIOTest test
```

Expected: test compilation fails for the new Path-based methods.

- [ ] **Step 3: Implement NIO Path APIs**

Use `Files.readAllLines`, `Files.readString`, and `Files.writeString` with
explicit `Charset`. Remove `FilePathPreFix` and all fixed executable local paths.
Change `TestNIO.main` to accept the first CLI argument as a path and print a
usage message when absent. Implement the three exact `ImageUtil` signatures
listed above and make `ImageUtil.main` require input and output CLI arguments.

- [ ] **Step 4: Verify IO behavior and scan executable absolute paths**

```bash
mvn -Dtest=FileUtilsTest,TestNIOTest test
rg -n 'new (File|FileInputStream|FileOutputStream)\("/Users/|Path\.of\("/Users/' src/main/java
```

Expected: IO tests pass; executable absolute-path matches are removed outside
explicitly documented historical comments.

- [ ] **Step 5: Commit portable IO changes**

```bash
git add src/main/java/com/lcl/utils/FileUtils.java src/main/java/com/lcl/io/TestNIO.java src/main/java/com/lcl/utils/ImageUtil.java src/test/java/com/lcl/utils/FileUtilsTest.java src/test/java/com/lcl/io/TestNIOTest.java
git commit -m "refactor: make IO demos portable"
```

---

### Task 9: Repository Hygiene, Documentation, and Final Verification

**Files:**
- Modify: `.gitignore`
- Modify: `README.md`
- Delete: all tracked `.DS_Store` files
- Modify only if verification identifies a non-`jerryMouse` regression:
  non-`jerryMouse` sources and tests changed by Tasks 1-8

**Interfaces:**
- Produces documented commands `mvn clean test`, `mvn dependency:analyze`, and
  `mvn spotbugs:check`.

- [ ] **Step 1: Remove tracked operating-system metadata**

Delete these tracked files:

```text
.DS_Store
src/.DS_Store
src/main/.DS_Store
src/main/java/.DS_Store
src/main/java/com/.DS_Store
src/main/java/com/lcl/.DS_Store
src/main/java/com/lcl/test/.DS_Store
```

Some entries may already be absent; resolve the exact tracked set with:

```bash
git ls-files | rg '(^|/)\.DS_Store$'
```

Add to `.gitignore`:

```gitignore
.DS_Store
**/.DS_Store
/target/
/.idea/
*.iml
```

- [ ] **Step 2: Expand README**

Document:

- JDK 21 and Maven 3.9 prerequisites;
- build, test, dependency-analysis, and SpotBugs commands;
- package categories for algorithms, data structures, concurrency, networking,
  design patterns, and utilities;
- required crawler environment variables without example secrets;
- warnings for `DeadLockDemo`, `testdemos.oom`, socket servers, crawler demos,
  mail demos, and any example that loops indefinitely;
- statement that `jerryMouse` is an incomplete learning servlet container and
  was excluded from this modernization.

- [ ] **Step 3: Run the full verification suite**

```bash
mvn clean test
mvn dependency:analyze
mvn -DskipTests spotbugs:check
git diff --check
git status --short
```

Expected:

- `mvn clean test` exits 0 with all new tests passing.
- Maven emits no duplicate-dependency or dynamic-version model warnings.
- Dependency analysis has no undeclared production dependency. Lombok may be
  reported unused because bytecode analysis does not model annotation
  processors; document that exception.
- SpotBugs may remain nonzero for intentional teaching examples, but no
  high-confidence finding remains in code changed by Tasks 1-8.
- `git diff --check` exits 0.

- [ ] **Step 4: Prove the excluded package was not modified**

```bash
git diff 4161907 -- src/main/java/com/lcl/jerryMouse
```

Expected: no output.

- [ ] **Step 5: Commit documentation and hygiene**

```bash
git add .gitignore README.md
git add -u
git commit -m "docs: document modernized learning demos"
```

- [ ] **Step 6: Record final evidence**

Capture the exact test count, remaining SpotBugs categories, dependency-analysis
exceptions, and final commit list in the completion report. Explicitly remind
the repository owner that old crawler credentials remain in Git history and
must be rotated even though current source is clean.
