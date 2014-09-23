Absence Manager
===============
If the assignee for a Usertask is currently absent, you may want to assign that Usertask to a specified replacement. 

![Absence Manager UI][1]

The BPMN process in this application consists of one Usertask. 

![The One Task Process][2]

When it is created, there is a Tasklistener that does the following:

* Load a very simple "database" (in this case just a JSON file)
* Check if the assignee is currently present
* If not, retrieve the designated replacement
* If that replacement is absent to, check for the according replacement until someone is found who is currently present
* Reassign the Usertask to that replacement

In order to edit the JSON file, you will also find a very simple "Absence Manager" application when you point your browser to http://localhost:8080/absence/ 

*Only tested on JBoss AS 7*, for other container (e.g. Tomcat) you have to take care to include a JAX-RS implementation.

[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/task-assignment-absence/screenshot.png
[2]: https://raw.githubusercontent.com/camunda/camunda-consulting/master/snippets/task-assignment-absence/src/main/resources/absence.png