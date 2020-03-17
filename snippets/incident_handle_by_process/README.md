# Incident Handler with Process

This is a small project that demonstrates how to create an incident handler that starts a process which handles incidents in a "generic fashion".

Lots of improvements can be made by adding more of the incident detail into the actual incident management process.

This is using the camunda version 7.11 CE.

## How does it work?

The core parts of this component are:

[ProcessIncidentHandler](src/main/java/com/camunda/consulting/demo/incident/ProcessIncidentHandler.java): This class creates a process with key "IncidentManagementProcess" everytime a failedJob incident occurs.
[SolveIncident](src/main/java/com/camunda/consulting/demo/incident/SolveIncident.java): This class is a delegate called from within the "IncidentManagementProcess" that fixes the incident.

The actual process for the incident management is in the file [incident-management](src/main/resources/incident-management.bpmn). It has a user task with a form and a service task to fix the incident.

## How to use it?

Simply start the "test_process" with a variable "doFail" with the value "true". Any other process created that generates an incident should execute this process engine plugin as well.

### Unit Test
You can run the JUnit test [ProcessUnitTest](src/test/java/com/camunda/consulting/demo/ProcessUnitTest.java) in your IDE or using:
```bash
mvn clean test
```

## References

https://docs.camunda.org/manual/7.11/user-guide/process-engine/incidents/
https://github.com/camunda-consulting/code/tree/master/snippets/email-incident-handler-plugin