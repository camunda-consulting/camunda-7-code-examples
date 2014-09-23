Absence Manager
===============

If the assignee for a Usertask is currently absent, you may want to assign that Usertask to a specified replacement. 

The BPMN process in this application consists of one Usertask. When it is created, there is a Tasklistener that does the following:

* Load a very simple "database" (in this case just a JSON file)
* Check if the assignee is currently present
* If not, retrieve the designated replacement
* If that replacement is absent to, check for the according replacement until someone is found who is currently present
* Reassign the Usertask to that replacement

In order to edit the JSON file, you will also find a very simple "Absence Manager" application when you point your browser to http://localhost:8080/absence/ 

Please note that this sniplet is supposed to run on JBoss AS.
