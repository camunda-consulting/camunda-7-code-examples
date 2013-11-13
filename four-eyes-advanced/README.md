Four Eyes - BpmnParseListener example
===================================

The examples shows how to use a BpmnParseListener, which is configured as a process engine plugin, to
enable 'Four Eyes' authentication by parsing and evaluating some extension elements in the bpmn process model
and adding a task listener implicitly to the user tasks where it is required.

Introduction
------------

* $CAMUNDA_HOME = install directory of camunda bpm platform
* $FOUR_EYES_HOME = root directory of the 'Four Eyes'-example maven project

Requirements
------------

* Maven
* Internet Access
* camunda bpm 7.0.0-Final JBoss distribution

How to use
----------

1. Download the camunda bpm 7.0.0-Final JBoss distribution and unpack it into a directory.
   We call this directory from now on $CAMUNDA_HOME.
2. Go to '$FOUR_EYES_HOME' and compile the 'Four Eyes'-example using 'mvn clean install'.
3. Get the compiled jars from '$FOUR_EYES_HOME/four-eyes-advanced-commons/target/' and
   '$FOUR_EYES_HOME/four-eyes-advanced-process-engine-plugin/target/' and copy them to
   '$CAMUNDA_HOME/server/jboss-as-${version.jboss}/modules/org/camunda/bpm/jboss/camunda-jboss-subsystem/main/'.
4. Open the 'module.xml' in the former directory and add following two lines between the '<resources>' elements.


        <resource-root path="four-eyes-advanced-process-engine-plugin-1.0.0-SNAPSHOT.jar" />
        <resource-root path="four-eyes-advanced-commons-1.0.0-SNAPSHOT.jar" />


5. Now open the 'standalone.xml' located at '$CAMUNDA_HOME/server/jboss-as-${version.jboss}/standalone/configuration/',
   search for '<subsystem xmlns="urn:org.camunda.bpm.jboss:1.1">' in it and add the following lines to it
   to register the 'Four Eyes'-Process Engine Plugin.

    ```xml
<subsystem xmlns="urn:org.camunda.bpm.jboss:1.1">
  <process-engines>
    <process-engine>
      <plugins>
        <plugin>
          <class>
            org.camunda.bpm.examples.FourEyesAdvancedProcessEnginePlugin
          </class>
        </plugin>
       </plugins>
    <process-engine>
  <process-engines>
</subsystem>
    ```


   Hint: You can see the changed ```modules.xml``` at ```$FOUR_EYES_HOME/four-eyes-process-engine-plugin/src/main/modules```
    and the ```standalone.xml``` at ```$FOUR_EYES_HOME/four-eyes-process-engine-plugin/src/main/configuration```.

6. Now you are good to go to execute the arquillian test located at
   '$FOUR_EYES_HOME/four-eyes-advanced-process-application/src/test/java/org/camunda/bpm/examples/FourEyesAdvancedTest.java'.
   To do so, just enter '$FOUR_EYES_HOME/four-eyes-advanced-process-application' and execute
   'mvn clean install -Pjboss -Djboss.location=$CAMUNDA_HOME/server/jboss-as-${version.jboss}'.
   You should now see the JBoss starting and executing the test successfully when you installed the libs in the JBoss modules directory
   and adjusted the standalone.xml by adding the four eyes process engine plugin to the engine configuration.