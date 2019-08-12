# Introduction
This example shows how to synchronize two parallel branches before they are joined.

# Environment Restrictions

# Remarks to run this application
There is no web interface to access the application. To get started refer to the
JUnit test case.

# Known Issues
- This approach doesn't work if the parallel branches are not synchronized with a parallel gateway.
- Sending messages within the same process is not allowed in BPMN. Nevertheless, it is possible using Java code in camunda BPM.

# Improvements Backlog
