# Spring Event Handling for Camunda Events  

This example illustrates the propagation of Camunda events to the 
[Spring event mechanism](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#context-functionality-events) 
and how to react to such events in own 
[annotation-based Event Listener](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#context-functionality-events-annotation) 
implementations, as documented in the related [product documentation](https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration/the-spring-event-bridge/).


## The important parts
[application.properties](src/main/resources/application.properties) enabling camunda.bpm.eventing propeties for event streams: execution, history and task

[Listener implementation](src/main/java/org/camunda/bpm/example/events/CamundaEventListener.java) invoked when events occur

Listener log output is written to console and _./target/tests.log_

### Unit Test
Run the JUnit [TestSpringEvents](src/test/java/org/camunda/bpm/example/events/TestSpringEvents.java) in your IDE or using:
```
mvn clean test
```

### Step through the process manually 
You can start the server using your IDE or 
```
mvn spring-boot:run
```
and step through the process manually. After the server has started you can access the Camunda tasklist via http://localhost:8080/app/tasklist.
Use the credentials *demo / demo* to login.


## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).