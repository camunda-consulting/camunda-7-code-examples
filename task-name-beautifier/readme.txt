# Introduction
This demo shows how to modify the names of human tasks, during their creation in a task list.
Therefore, a TaskListener is added to all BPMN User Tasks by a BpmnParseListener,
which is added to the process engine configuration in the camunda BPM platform.

See also:
https://app.camunda.com/confluence/display/foxUserGuide/Task+Name+Beautifier

# Environment Restrictions

# Remarks to run this application
This demo must be manually "installed" on your camunda BPM platform depending on the application
server you use. Please refer to 
https://app.camunda.com/confluence/display/foxUserGuide/Task+Name+Beautifier#TaskNameBeautifier-InstallationonJBossAS7
for instructions on how to do it with JBoss AS7.

Alternatively, you can run the unit tests in the package
org.camunda.bpm.demo.outerspace.task_name_beautifier.nonarquillian
in Eclipse.

# Known Issues
- Currently the camunda BPM platform supports only one configuration extension. If you
  want to have other configuration changes as well, you need to put them into
  the same ProcessEngineConfigurationFactory.

# Improvements Backlog
