Demo Data Generator
=========================

**UNDER CONSTRUCTION!***

This project can start process instances and walk them through (skipping service tasks and other delegation code) in order to get some meaningful load in the history table - normally used to showcase reporting features.

Check the version n-1 for your generated process instances!

In order to use this you have to 

Instrument your BPMN process
----------------

### Add Probabilities to all outgoing Sequence Flows on XOR-Gateways:

![Probability on Sequence Flow](decisionProbability.png)

### Add Distribution for Duration of User Tasks:

![Distribution for Task Duration](taskDuration.png)


Start the Generation
--------------------

Now you can start the generation, you have to specify:

* process definition key to start
* How many days in the past you want to have instances
* A Distribution configuring the time between two process instance starts (mean and standard deviation)

![Start the Generator](screenshot.png)

This will deploy a new version of the process definition which is tweaked for simulation (e.g. delegation code is removed, listeners steer the decisions, ...). After the simulation the original version is deployed again as newest version.

So you will have instances in the version before the latest version if you look into cockpit:

![Statistics](statistics.png)



How to use it?
--------------

Build and Deploy the app. Should work on any container (Tomcat, JBoss, ...).

Once you deployed the application you can run it using
[localhost:8080/camunda-demo-data-generator/generate](localhost:8080/camunda-demo-data-generator/). You might have to adjust the port.


Environment Restrictions
------------------------

Built and tested against Camunda BPM version 7.3.0.


Improvements Backlog
--------------------

Quite long :-) Not yet posted here.

License
-------

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
