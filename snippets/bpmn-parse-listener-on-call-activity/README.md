# BPMN Parse Listener - adding business key to all call activities

This example demonstrates how to use a BPMN Parse Listener as Process Engine Plugin.
We learn

* How to implement and activate a Process Engine Plugin
* How to implement a BPMN Parse Listener
* How to add the business key to every Subprocess called by a call activity

After having looked through the code, you will understand the behavior of a BPMN Parse Listener in case of

* An additional parsing to the BPMN Parser in the Process Engine
* The Process Engine Plugin configuration.

What is the idea/use case of this demo:

* Pass business keys to subprocesses that are called by call activities.