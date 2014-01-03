# camunda BPM example: Get Servlet Context by Process Definition

## What does it demonstrate?
- How to obtain a Servlet Context Path by a given Process Definition Id

## Technical environment
- [camunda BPM platform](http://camunda.org) (tested on JBoss AS 7)

## Introduction
This demo shows how to dynamically retrieve
the Servlet Context Path of a given process definition.
That is especially useful for building generic task lists
that display tasks from multiple Web applications containing BPMN processes.
If each Web application ships with its own forms for BPMN User Tasks,
a central task list needs to load or link to these forms
and therefore needs to know the corresponding Servlet Context.

The provided Java class
[ProcessApplicationHelper](https://github.com/camunda/camunda-consulting/blob/master/snippets/get-servlet-context-by-process-definition/src/main/java/org/example/get_servlet_context_by_process_definition/ProcessApplicationHelper.java)
contains two different ways to retrieve the Servlet Context.
The first one requires the process engine,
into which the process definition has been deployed,
to be known upfront.
The second one searches through all process engines on a server.

## Source Code Location
- [GitHub](https://github.com/camunda/camunda-consulting/tree/master/snippets/task-name-beautifier)
- [Download Source Code as ZIP](https://github.com/camunda/camunda-consulting/archive/master.zip)

## Steps to run the application
There is no web interface to access the application. To get started refer to the
Arquillian test case, which by default connects to a camunda BPM Platform running
locally on JBoss AS 7.

0. [Clone the project via Git](https://github.com/camunda/camunda-consulting)
   **OR** [Download the sources as ZIP](https://github.com/camunda/camunda-consulting/archive/master.zip)
1. [Download camunda BPM for JBoss AS7](http://www.camunda.org/download/) and [install it](http://docs.camunda.org/guides/installation-guide/jboss/)
2. Import the Maven project in Eclipse and run a Maven build **OR** Build with command line:

        mvn clean install
        
3. Start the server
4. Run the Arquillian test case, which by default connects to a camunda BPM platform running locally on JBoss AS 7
    1. Run the class `ArquillianTest` as a JUnit test in Eclipse
    **OR** Run test with Maven on the command line using:

            mvn clean install -Parq-jbossas-remote

## Known Issues

## Improvements Backlog
