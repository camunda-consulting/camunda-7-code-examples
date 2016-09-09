Simple Process Dashboard using bpmn.io
=========================

![screenshot](screenshot.png)

This example consists of a 

* Simple HTML page
* Including bpmn.io libraries and 
* JavaScript code to 
** Load the BPMN for a given model  via the Camunda BPM REST API
** Reads all relevant activities from then and brings them in a rough order (heuristic approach!)
** Visualizes all activities as columns, and the load for every process instance one row showing
*** status & runtime
*** incidents
*** allows to open a popover with more detailed information

![screenshot1](screenshot1.png)

![screenshot2](screenshot2.png)


How to use it?
=========================

* You can just open the HTML file in [src/main/webapp/](src/main/webapp) in your browser without any deployment, thererfor
  * Download the example
  * adjust the app.js to point to a Camunda BPM REST API
  * Make sure the container which runs Camunda allows for CORS (for Wildfly, e.g. [https://forum.camunda.org/t/enable-cors-on-wildfly/673/2](https://forum.camunda.org/t/enable-cors-on-wildfly/673/2)). 
  * Open the index.html in the browser of your choice (and append ?processDefinitionKey=xxx to the URL  - replace xxx with the Process Instance Id of the process instance you want to display)
* Or you can deploy a WAR file to your Camunda BPM container:
  * Build via Maven and Deploy to your container
  * Access it via your browser: [http://localhost:8080/process-dashboard/index.html?processDefinitionKey=xxx](http://localhost:8080/bpmn-io-sample/status.html?processInstanceId=xxx) - replace xxx with the Process Instance Id of the process instance you want to display
