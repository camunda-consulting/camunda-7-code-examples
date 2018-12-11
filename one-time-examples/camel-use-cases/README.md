# An example showcasing the use cases of the camunda-bpm-camel project.

This process does not serve any real-life use case but shows the various use cases possible with camunda BPM and Camel:
* Start a process instance when a file is dropped to a folder ('<USER_HOME>/camunda-bpm-demo-camel/')
* Start a process instance when a new Tweet is shared on Twitter with 'camunda' in it
* Call a Camel route ("Service") from a ServiceTask. This Service always throws an Exception which we catch with a ErrorEvent in BPMN.
* Call a Camel route ("Service") from a SendTask and retrieve the response asynchronous in a following Message Event.
* Between all steps there is a UserTask so that you can click through the example using [Tasklist](http://docs.camunda.org/latest/guides/user-guide/#tasklist).

![Process Model][1]

For details on [camunda-bpm-camel](https://github.com/camunda/camunda-bpm-camel) see the according GitHub page.


# Environment Restrictions

We used `camunda-bpm-camel-cdi` for this example - hence you need a CDI container. We tested on 
* JBoss AS 7.1.3
* camunda BPM 7.1.0-Final


# Getting Started

1. Download the [camunda BPM platform](http://camunda.org/) for JBoss AS 7 **(tested on 7.0.0-Final)** from [here](http://camunda.org/download.html).
1. Install it, start it with `<CAMUNDA_BPM_PLATFORM_HOME>/server/jboss-as-7.1.3.Final/bin$ ./standalone.sh`
1. Make sure JBoss AS 7 is running by pointing your browser to `http://localhost:8080/`
1. Make sure you have the following installed *and working*:
    * [Java Platform (*JDK*) 1.6.x](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    * [Maven](http://maven.apache.org/) 3.0.x
    * [Git](http://git-scm.com/) 1.7.x
1. Clone this repository
1. Build the application with `mvn clean package`
1. Copy the generated WAR (`target/camel-use-cases.war`) artifact to the JBoss AS 7 deployment directory `<CAMUNDA_BPM_PLATFORM_HOME>/server/jboss-as-7.1.3.Final/standalone/deployments/`
1. Start the process instance
    * tweet about `camunda` and wait for Camel to pick it up (sometimes that is a bit slow)
    * drop a text file into `<USER_HOME>/camunda-bpm-demo-camel/` (this should get picked up immediately)
1. Point your browser to `http://localhost:8080/camunda/app/tasklist/` or `http://localhost:8080/camunda/app/cockpit/` and enjoy! Note: You might see multiple process instances already started - then there were a lot of tweets with `camunda` recently so they got picked up during startup.

# Further Resources

* See [this blog post](http://blog.camunda.org/2013/09/camunda-bpm-apache-camel-integrating.html) for more information.

For help please ask at the camunda BPM ["Users & Process Application Development"](http://camunda.org/community/forum.html) forum.


[1]: https://raw.github.com/camunda/camunda-bpm-camel/master/use-cases.png
