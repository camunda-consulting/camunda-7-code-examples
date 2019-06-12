## Camunda Crash Course

This is an event in which developers new to Camunda can spend 3 hours with a Camunda expert (from DevRel or Consulting) to build and deploy an executable process model.


* Laptop with full Admin rights
 * There is no requirement for a specific OS
* Java8 JDK  or higher installed
 * This is simply to be able to run the platform on the application server.
* NodeJS installed
 * This is so that we can run the JavaScript External Task Worker.
 * This will not require knowledge of a specific programming language.


### Agenda

* Short Into to BPMN
 * Short into into Camunda BPM
* Describe Remove Engine architecture.
 * Because that's what you'll be building
* Download and Install the Camunda modeler
* Download and Install the Camunda tomcat distro
* Demo the Camunda modeler and build a process
 * The process need to have have at least 2 service tasks
 * the process should have at least one XOR gateway after a service task
 * There should be at least one User Task
 * (Optional) Error Boundary event
 * (Optional) Parallel Gateway
* Add the execution semantics to the model
* Demo Camunda platform 
* Deploy process to Camunda via the modeler (REST).
* Start the process - and have an instance waiting at an external task.
* Explain the external task pattern.
* Using the JavaScript External Task Worker client - build a JS worker
* Pass variable back to the instance from the worker
 * (Optional) Throw a BPMN error under certain circumstances.
 * Create second worker.