# DemoTest Java 21 Modernization Design

## Objective

Modernize the learning and demonstration repository for Java 21 while preserving
its value as a collection of independently runnable examples. Fix confirmed
security, correctness, resource-management, concurrency, build, and repository
hygiene problems outside `src/main/java/com/lcl/jerryMouse`.

## Scope

### Included

- Standardize Maven compilation and tests on Java 21.
- Upgrade every retained direct dependency and Maven build plugin to a current,
  supported release that works with Java 21.
- Remove duplicate, dynamic, and genuinely unused dependencies.
- Replace dependencies whose maintained successor uses new coordinates or APIs,
  including Fastjson and Apache HttpClient where those dependencies remain used.
- Remove plaintext credentials and session cookies from the current source tree.
- Fix confirmed defects outside `com.lcl.jerryMouse`, especially:
  - recursive construction and thread creation in `ReactorTask`;
  - broken binary-search-tree insertion and deletion;
  - HTTP client cleanup and exception-path null dereferences;
  - shared `SimpleDateFormat` and `DecimalFormat` instances;
  - incorrect string comparison and guaranteed array bounds failures;
  - sleeping while holding the producer/consumer queue monitor.
- Replace the placeholder test with focused JUnit tests for changed behavior.
- Improve `.gitignore` and remove tracked `.DS_Store` files.
- Expand the README with prerequisites, build commands, repository organization,
  and warnings for intentionally dangerous demonstrations.

### Excluded

- No source changes under `src/main/java/com/lcl/jerryMouse`.
- No automatic Git history rewrite, force push, or remote repository mutation.
- No conversion to a Maven multi-module project.
- No broad renaming or reformatting solely to eliminate style warnings.
- No behavioral rewrite of examples intentionally demonstrating deadlock, OOM,
  blocking, or other failure modes. Such examples will instead be documented.
- No attempt to create exhaustive tests for every historical LeetCode solution or
  every class containing a `main` method.

## Build and Dependency Design

The POM will use a single Java baseline through
`maven.compiler.release=21`. Compiler and test plugins will have explicit current
versions. JUnit will use one fixed JUnit Jupiter version with test scope; the
dynamic `RELEASE` dependency and the placeholder JUnit 4 test will be removed.

Dependencies will be retained only when source compilation or a documented demo
requires them. Source code must directly declare libraries it imports rather than
relying on transitive dependencies. Duplicate Hutool declarations and unused
YAML, CLI, and multipart dependencies will be removed when source inspection
confirms they are not needed. Dependency upgrades that change APIs will be
handled in the consuming demo rather than pinned to an unsupported old release.

The `jerryMouse` package remains compilable. Its Jakarta Servlet dependency may
be upgraded, but its Java sources and behavior are outside this effort.

## Security Design

`LoginSimulate` will no longer contain an account identifier, password, or full
authenticated Cookie header. Values needed to run the demo will come from
environment variables with clear validation and redacted error messages. No
secret value will be logged.

The repository history already contains the old values. The implementation will
document that the credentials must be considered compromised and rotated. It
will not rewrite history because that would invalidate existing clones and
requires an explicit coordinated force push.

Dependencies with known unsafe versions will either be removed, upgraded, or
migrated to a maintained successor. Examples that process external data will not
enable unsafe polymorphic deserialization.

## Correctness and Resource-Management Design

Each confirmed behavior change will be protected by a focused JUnit test before
implementation:

- binary-search-tree insertion must attach nodes and preserve search order;
- deletion must handle leaf, one-child, two-child, and root cases without null
  dereferences;
- factories must treat an empty non-interned string as empty;
- utility date formatting must remain correct under concurrent calls;
- HTTP helpers must close clients and responses on both success and failure;
- reactor construction must not recursively construct or start another reactor;
- producer and consumer examples must release their monitor before simulating
  work delay.

Small demo-only failures with no reusable API, such as indexing an empty array in
a `main` method, will be removed or replaced by a valid demonstration and then
covered by compilation plus nearby unit tests where a meaningful API exists.

## Testing Strategy

Tests will use JUnit Jupiter and real code. Network behavior will be tested only
against an in-process local HTTP server; tests will not depend on public
websites, credentials, fixed ports, or timing-sensitive sleeps. Concurrency tests
will use bounded executors, latches, and timeouts.

Verification commands:

```bash
mvn clean test
mvn dependency:analyze
mvn -DskipTests spotbugs:check
```

The build must have no malformed-POM warnings. SpotBugs is not required to reach
zero because the repository intentionally contains teaching examples, but all
high-confidence findings in the changed non-`jerryMouse` code must be addressed
or documented as intentional.

## Repository Organization

Runnable demonstrations remain in `src/main/java` so their existing entry
points stay usable. Regression tests live in `src/test/java` and follow the
package of the class they protect. The README will identify examples that
intentionally deadlock, allocate until OOM, bind a port, contact a network
service, or access local files.

Tracked operating-system metadata will be removed and ignored. Machine-specific
absolute paths in actively maintained utilities will become parameters or
temporary/test paths; historical comments may retain sample paths when they
cannot affect execution.

## Acceptance Criteria

- The project builds and tests with JDK 21 using `mvn clean test`.
- Maven emits no duplicate-dependency or dynamic-version model warnings.
- `src/main/java/com/lcl/jerryMouse` has no source diff.
- The current source tree contains no plaintext password or authenticated Cookie.
- Every reusable behavior fixed by this effort has a regression test that failed
  before its implementation change and passes afterward.
- Retained dependencies are fixed-version, directly justified, and compatible
  with Java 21.
- Git contains no tracked `.DS_Store` files.
- README instructions are sufficient to build the repository and identify
  dangerous demos.
