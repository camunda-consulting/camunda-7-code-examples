camunda BPM platform - Order Confirmation with Drools
=====================================================

# Overview

This is a sample for the [camunda BPM platform](http://camunda.org/) demonstrating integration with [Drools](http://www.jboss.org/drools/).

# What does it demonstrate?

* How to build an simple JSF application including a simple BPMN 2.0 Process
* Can be used for demonstrating Process Automation with camunda BPM platform on a Java EE 6 environment
* How to use [Drools](http://www.jboss.org/drools/) to implement business rules
* How to create [Arquillian](http://arquillian.org) integration tests for your process application

# The Process

![Order Confirmation Process][1]

# Getting Started

1. Download the camunda BPM Platform *for JBoss AS 7* from [here](http://camunda.org/download.html).
1. Install it, start it with `<CAMUNDA_BPM_PLATFORM_HOME>/server/jboss-as-7.1.3.Final/bin$ ./standalone.sh`
1. Make sure JBoss AS 7 is running by pointing your browser to `http://localhost:8080/`
1. Make sure you have the following installed *and working*:
    * [Java Platform (*JDK*) 1.6.x](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    * [Maven](http://maven.apache.org/) 3.0.x
    * [Git](http://git-scm.com/) 1.7.x
1. Clone this repository
1. Build the application with `mvn package` and deploy it with `mvn jboss-as:deploy`
1. Point your browser to `http://localhost:8080/order-confirmation-rules/` and enjoy!

# Further Resources

* See blog post including German screencast from talk on conference about it: [http://www.bpm-guide.de/2011/11/14/activiti-drools-wjax-2011/](http://www.bpm-guide.de/2011/11/14/activiti-drools-wjax-2011/)

For help please ask at the camunda BPM ["Users & Process Application Development"](http://camunda.org/community/forum.html) forum.

[1]: http://cloud.github.com/downloads/plexiti/the-job-announcement-fox/the-job-announcement-showcase-splash-screen-v3.png
