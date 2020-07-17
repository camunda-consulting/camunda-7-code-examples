# Message Time Control
A small demonstration of the usage of listeners to call another process and control the time that a certain message takes.

This example uses the camunda version 7.12 CE.

## How does it work?

The core parts of this component are:

* [ControlTimeListenerStart](src/main/java/org/camunda/bpm/demo/listener/ControlTimeListenerStart.java): This class creates a process to control the time that a message takes to be done.
* [ControlTimeListenerEnd](src/main/java/org/camunda/bpm/demo/listener/ControlTimeListenerEnd.java): This class finished the process created previously.

The actual process for the message control is in the file [control-message-event](src/main/resources/control-message-event.bpmn). It has a message task and a timer, which allows it to control how much time a message from other process takes to be finished. When the message on the [process.bpmn](src/main/resources/process.bpmn) is reached, the start listeners spawns an instance of the control process. Once the message of the main process is consumed, the end listener finishes the control process instance, by consuming its message task.

## How to use it?

Simply start the "MessageControlExampleProcess" and complete the first human task. As soon as the process reaches the message task, you will see an instance of the process "MonitorMessageEvent".

### Unit Test
You can run the JUnit test [TestProcessWithControlTimeProcess](src/test/java/org/camunda/bpm/demo/TestProcessWithControlTimeProcess.java) in your IDE or using:
```bash
mvn clean test
```