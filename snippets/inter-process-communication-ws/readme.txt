# Introduction
This quickstart shows one way to implement an asynchronous communication between
two processes that could potentially run on different engines or even different
machines. Web Services are used as a means of communication.

The quickstart contains two processes. A parent process is started by an
Arquillian test case and uses a Service Task to invoke a Web Service that starts
a child process. After starting the child process the parent waits for it to
complete using a Receive Task. At the end of the child process a Service Task
performs the callback to the parent by calling another Web Service provided by
the parent. The business key of the parent is sent to and returned by the child
as a correlation key that identifies the process instance that is called back.

The Service Tasks could also be replaced by ExecutionListeners at the Receive
Task of the parent and the End Event of the child.

There are no transaction guarantees for the invocation of the child and the
callback of the parent. They could be introduced by adding WS-AT or switching
to JMS.

A tutorial based on this example is available at: http://docs.camunda.org/

# Environment Restrictions
CXF is required to run this quickstart. JBoss AS 7 provides it out of the box.

# Remarks to run this quickstart
There is no web interface to access the application, please refer to the
Arquillian test case to get started, which by default connects to a fox platform
running locally on JBoss AS 7.

# Known Issues:
None
