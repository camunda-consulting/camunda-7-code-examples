Order Confirmation Rules
========================

This is a sample for the [camunda BPM platform](http://camunda.org/) on [Java EE](http://www.oracle.com/technetwork/java/javaee/overview/index.html) demonstrating integration with [JBoss Drools](http://www.jboss.org/drools/).

![Order Confirmation Rules Screenshot][2]

# What can you Learn?

* How to build an simple JSF application including a simple BPMN 2.0 Process
* Can be used for demonstrating process automation with [camunda BPM platform](http://camunda.org/) on a Java EE 6 environment
* How to use [Drools](http://www.jboss.org/drools/) to implement business rules
* How to create [Arquillian](http://arquillian.org) integration tests for your process application

# The Process

![Order Confirmation Process][1]

# Getting Started

1. Download the [camunda BPM platform](http://camunda.org/) *for JBoss AS 7* from [here](http://camunda.org/download.html).
1. Install it, start it with `<CAMUNDA_BPM_PLATFORM_HOME>/server/jboss-as-7.1.3.Final/bin$ ./standalone.sh`
1. Make sure JBoss AS 7 is running by pointing your browser to `http://localhost:8080/`
1. Make sure you have the following installed *and working*:
    * [Java Platform (*JDK*) 1.6.x](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    * [Maven](http://maven.apache.org/) 3.0.x
    * [Git](http://git-scm.com/) 1.7.x
1. Clone this repository
1. Build the application with `mvn package`
1. Optional: to run the Arquillian integration test do `mvn failsafe:integration-test`
1. Copy the generated WAR artifact to the JBoss AS 7 deployment directory
1. Point your browser to `http://localhost:8080/order-confirmation-rules/` and enjoy!

# Further Resources

* See [this blog post](http://www.bpm-guide.de/2011/11/14/activiti-drools-wjax-2011/) including a screencast (in German) from talk at [WJAX](http://jax.de/) where this sample is presented.

For help please ask at the camunda BPM ["Users & Process Application Development"](http://camunda.org/community/forum.html) forum.

[1]: https://raw.github.com/rafacm/camunda-outer-space-demos/migrate-order-confirmation-rules-demo/order-confirmation-rules/src/main/webapp/resources/img/OrderConfirmation.png
[2]: https://raw.github.com/rafacm/camunda-outer-space-demos/migrate-order-confirmation-rules-demo/order-confirmation-rules/src/main/webapp/resources/img/order-confirmation-rules-screenshot.png