# Process Test Coverage

## Introduction
This tool supports in analyzing and visualizing the process test coverage of a BPMN process.

![Screenshot](screenshot.png)

The tool creates test coverage reports for:

* Single test cases: The process coverage is visualized by marking those tasks and events with a green color which have be traversed by the test case.
* Entire test suites: The process coverage is visualized by marking those tasks and events with a green color which have be traversed by any of the test suite's test cases.

## How to use it?

Have a look at the [ProcessTestCoverageTest](src/test/java/org/camunda/bpm/consulting/process_test_coverage/ProcessTestCoverageTest.java). 

# Remarks to run this application
1. mvn clean test
2. Open html files which are created in the directory target/process-test-coverage/

# Known Issues

# Improvements Backlog
