Introduction
=============================
This showcase shows an invoice inbound process including a JSF + JQuery web-gui.
It not only deploys a process model from the camunda modeler but as well process models from other BPMN 2.0 modeling tools (currently Signavio).


Environment Restrictions
=============================
We used JSF for this example, hence you need a Java EE container providing JSF. We tested on JBoss 7 (the one included in the distribution 
on http://www.camunda.org/download/).

If you want to run the example on a plain tomcat you have to exchange the JSF task forms (or provide a JSF implementation). You might want to have
a look into the example shipped with the distribution which implements the same process using simple HTML forms: 
https://github.com/camunda/camunda-bpm-platform/tree/master/examples.


Remarks to run this example
=============================
After deploying the invoice-en.war file to your camunda BPM platform you can start the process via the tasklist: http://localhost:8080/camunda/app/tasklist/.