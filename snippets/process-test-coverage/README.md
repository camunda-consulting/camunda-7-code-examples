# Process Test Coverage

## Introduction
This tool supports in analyzing and visualizing the process test coverage of a BPMN process.

![Screenshot](screenshot.png)

The tool creates test coverage reports for:

* Single test cases: The process coverage is visualized by marking those tasks and events with a green color which have be traversed by the test case.
* Entire test suites: The process coverage is visualized by marking those tasks and events with a green color which have be traversed by any of the test suite's test cases.

## How to use it?

Add this Maven Dependency to your project:

```
<dependency>
  <groupId>org.camunda.consulting.snippets</groupId>
  <artifactId>process-test-coverage</artifactId>
  <version>0.2.3</version>
  <scope>test</scope>
</dependency>
```

Have a look at the [ProcessTestCoverageTest](src/test/java/org/camunda/bpm/consulting/process_test_coverage/ProcessTestCoverageTest.java):

- In a tearDown() or @AfterClass method for the Test Class coverage
- In the actual test methods to get coverage for test cases

## Remarks to run this application
1. mvn clean test
2. Open html files which are created in the directory target/process-test-coverage/

## Known Limitations
Test cases that deploy different version of the same process (same process definition key) are currently not supported and will result in miss-leading reports. Just make sure all your processes have unique process definition keys (in BPMN XML //process@id).

## Improvements Backlog
