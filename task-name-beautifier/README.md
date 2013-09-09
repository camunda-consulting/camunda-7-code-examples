# camunda BPM example: Task Name Beautifier

**Table of Contents**  *generated with [DocToc](http://doctoc.herokuapp.com/)*

- [camunda BPM example: Task Name Beautifier](#camunda-bpm-example-task-name-beautifier)
	- [What does it demonstrate?](#what-does-it-demonstrate)
	- [Technical environment](#technical-environment)
	- [Remarks to run this application](#remarks-to-run-this-application)
	- [Introduction](#introduction)
	- [Example Process](#example-process)
	- [Task Listener](#task-listener)
	- [Adding the Task Listener to all User Tasks of all Processes](#adding-the-task-listener-to-all-user-tasks-of-all-processes)
	- [Usage with camunda BPM platform](#usage-with-camunda-bpm-platform)
	- [Installation on JBoss AS 7](#installation-on-jboss-as-7)
		- [1. Switch Process Engine Configuration in JBoss AS 7](#1-switch-process-engine-configuration-in-jboss-as-7)
		- [2. Task Name Beautifier as a JBoss Module](#2-task-name-beautifier-as-a-jboss-module)
	- [Source Code Location](#source-code-location)
	- [Steps to run the showcase](#steps-to-run-the-showcase)
	- [Known Issues](#known-issues)
	- [Improvements Backlog](#improvements-backlog)

## What does it demonstrate?
- How to dynamically modify a task during its creation in a task list
- How to add an [execution listener or task listener](http://docs.camunda.org/api-references/bpmn20/#!/concepts/listeners) globally to all processes
- How to customize the process engine configuration inside the [camunda BPM platform](http://camunda.org)

## Technical environment
- [camunda BPM platform](http://camunda.org) (tested on JBoss AS 7)

## Remarks to run this application
This demo must be manually "installed" on your [camunda BPM platform](http://camunda.org)
depending on the application server you use.
See below for instructions on how to do it with JBoss AS7.

Alternatively, you can run the unit tests in the following package in Eclipse: 

    org.camunda.bpm.example.task_name_beautifier.nonarquillian

## Introduction
This demo shows how to dynamically modify the names of human tasks, during their creation in a task list.
Therefore, a [TaskListener](http://docs.camunda.org/api-references/bpmn20/#!/concepts/listeners)
is added to all [BPMN User Tasks](http://docs.camunda.org/api-references/bpmn20/#!/tasks/user-task)
by a [BpmnParseListener](https://app.camunda.com/confluence/display/foxUserGuide/Add+your+own+BpmnParseListener),
which is added to the process engine configuration of the [camunda BPM platform](http://camunda.org).

## Example Process
The example process shown below contains a user task with a very long name. For better readability, the modeler of this process decided to add a hyphen to the task name. However, this hyphen is then also shown later in the task list of the users of this process, e.g., an entry like 'Task with terri-  bly long name'.

![BPMN Process containing a User Task with a very long name](https://raw.github.com/camunda/camunda-bpm-examples/master/task-name-beautifier/src/test/resources/process.png)

## Task Listener
In order to make this a little more beautiful, you can implement a
[TaskListener](http://docs.camunda.org/api-references/bpmn20/#!/concepts/listeners)
that changes the name of the task as it is created in the task list:
```java
public class TaskNameBeautifier implements TaskListener {

  @Override
  public void notify(DelegateTask task) {
    String name = task.getName();
    String beautifiedName = beautifyTaskName(name);
    task.setName(beautifiedName);
  }

  public String beautifyTaskName(String name) {
    String beautifiedName = name.replaceAll("(\\w)-\\s*([a-z])", "$1$2");
    return beautifiedName;
  }
 
}
```

...and add it to that user task:
```xml
<bpmn2:userTask id="UserTask_1" name="Task with terri- bly long name">
  <bpmn2:extensionElements>
    <camunda:taskListener class="org.camunda.bpm.example.task_name_beautifier.TaskNameBeautifier" event="create"/>
  </bpmn2:extensionElements>
</bpmn2:userTask>
```

## Adding the Task Listener to all User Tasks of all Processes
Doing this additional configuration for all user tasks that have a long name
is a rather cumbersome solution.
In addition to that, any process application that wants to use it,
needs to include the TaskListener class in its WAR or EAR.

Hence, it would be nice to add this Task Listener to all user tasks in a more central location. The camunda engine allows that through a so called [BpmnParseListener](https://app.camunda.com/confluence/display/foxUserGuide/Add+your+own+BpmnParseListener):
```java
public class TaskNameBeautifierBpmnParseListener extends AbstractBpmnParseListener {
   
  @Override
  public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {
    ActivityBehavior behavior = activity.getActivityBehavior();
    if (behavior instanceof UserTaskActivityBehavior) {
      ((UserTaskActivityBehavior) behavior).getTaskDefinition()
          .addTaskListener(TaskListener.EVENTNAME_CREATE,  new ClassDelegate(TaskNameBeautifier.class, null));
    }
  }

}
```

...which can be added to a standalone process engine configuration
([camunda.cfg.xml](https://raw.github.com/camunda/camunda-bpm-examples/master/task-name-beautifier/src/test/resources/camunda.cfg.xml))
like that:
```xml
  <bean id="processEngineConfiguration"
    class="org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration">
    <property name="customPostBPMNParseListeners">
      <list>
        <bean class="org.camunda.bpm.example.task_name_beautifier.TaskNameBeautifierBpmnParseListener" />
      </list>
    </property>
  </bean>
```

## Usage with camunda BPM platform

In the camunda BPM platform the process engine configuration can be extended programmatically:
```java
public class TaskNameBeautifierProcessEngineConfiguration
        extends ManagedJtaProcessEngineConfiguration {

	@Override
	protected void init() {
		initCustomPostParseListener();
		super.init();
	}

	protected void initCustomPostParseListener() {    
		// normally no parse listeners should be set, so create an own list for it
		if (getCustomPostBPMNParseListeners() == null) {
			setCustomPostBPMNParseListeners(new ArrayList<BpmnParseListener>());
		}

		// add parse listener
		getCustomPostBPMNParseListeners().add(new TaskNameBeautifierBpmnParseListener());

	}

}
```

## Installation on JBoss AS 7

### 1. Switch Process Engine Configuration in JBoss AS 7
Place the [JAR file with the configuration and parse listener class](https://raw.github.com/camunda/camunda-bpm-examples/master/task-name-beautifier/task-name-beautifier.jar)
in the directory `$JBOSS_HOME/modules/org/camunda/bpm/jboss/camunda-jboss-subsystem/main`.
Add a dependency to the JAR file in
`$JBOSS_HOME/modules/org/camunda/bpm/jboss/camunda-jboss-subsystem/main/module.xml`
so that the [camunda BPM platform](http://camunda.org)
can find the alternative `ProcessEngineConfiguration` provided by the module:
```xml
  <resources>
    <resource-root path="camunda-jboss-subsystem-7.0.0-Final.jar" />
    <resource-root path="task-name-beautifier.jar" />
  </resources>
```

Please, remember that you have to repeat this step when you upgrade your server to a newer version of camunda BPM.

To enable the configuration class,
add it to the process engine parameters in your standalone.xml or domain.xml:
```xml
    <subsystem xmlns="urn:org.camunda.bpm.jboss:1.1">
        <process-engines>
            <process-engine name="default" default="true">
                <configuration>
                    org.camunda.bpm.example.task_name_beautifier.TaskNameBeautifierProcessEngineConfiguration
                </configuration>
                ...
            </process-engine>
            ...
        </process-engines>
        ...
    </subsystem>
```

### 2. Task Name Beautifier as a JBoss Module
Since the Task Listener is added to all processes,
it needs to be in the classpath of all applications.
This can be achieved by creating a global JBoss module.
For that you create a folder `$JBOSS_HOME/modules/org/camunda/bpm/task-name-beautifier/main/`
and in there a file called
[module.xml](https://raw.github.com/camunda/camunda-bpm-examples/master/task-name-beautifier/src/test/resources/module.xml)
with the following contents:
```xml
<module xmlns="urn:jboss:module:1.0" name="org.camunda.bpm.task-name-beautifier">
  <resources>
    <resource-root path="task-name-beautifier.jar" />
  </resources>

  <dependencies>
    <module name="org.camunda.bpm.camunda-engine" />
  </dependencies>
</module>
```
Place the [JAR file with the task listener class](https://raw.github.com/camunda/camunda-bpm-examples/master/task-name-beautifier/task-name-beautifier.jar) next to it.

Finally, you can add the TaskListener to the classpath of all applications
by adding a global dependeny in your standalone.xml or domain.xml:
```xml
    <subsystem xmlns="urn:jboss:domain:ee:1.1">
        <global-modules>
            <module name="org.camunda.bpm.task-name-beautifier" slot="main"/>
            ...
        </global-modules>
        ...
    </subsystem>
```

## Source Code Location
- [GitHub](https://github.com/camunda/camunda-bpm-examples/tree/master/task-name-beautifier)
- [Download Source Code as ZIP](https://github.com/camunda/camunda-bpm-examples/archive/master.zip)
- Pre-built JAR: [task-name-beautifier.jar](https://raw.github.com/camunda/camunda-bpm-examples/master/task-name-beautifier/task-name-beautifier.jar)
- JBoss	AS7 module descriptor: [module.xml](https://raw.github.com/camunda/camunda-bpm-examples/master/task-name-beautifier/src/test/resources/module.xml)

## Steps to run the showcase
0. [Clone the project via Git](https://github.com/camunda/camunda-bpm-examples)
   **OR** [Download the sources as ZIP](https://github.com/camunda/camunda-bpm-examples/archive/master.zip)
1. For an easy start, you can import the Maven project in Eclipse and run the JUnit tests in the package:

        org.camunda.bpm.example.task_name_beautifier.nonarquillian

2. For the whole show, follow these steps:
    1. [Download camunda BPM for JBoss AS7](http://www.camunda.org/download/) and [install it](http://docs.camunda.org/guides/installation-guide/jboss/)
    2. Run a Maven build in Eclipse **OR** Build with command line:

                mvn clean install
                
    3. Follow the instructions for installing the module in JBoss AS7 described above
    4. Start the server
    5. Run the Arquillian test case, which by default connects to a camunda BPM platform running locally on JBoss AS 7
        1. Run the class `ArquillianTest` as a JUnit test in Eclipse
        **OR** Run test with Maven on the command line using:

                mvn clean install -Parq-jbossas-remote      

## Known Issues
- Currently the camunda BPM platform supports only one configuration extension. If you
  want to have other configuration changes as well, you need to put them into
  the same `ProcessEngineConfiguration` class.

## Improvements Backlog
