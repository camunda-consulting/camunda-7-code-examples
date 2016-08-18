BPMN Transaction
=========================

Working example of the [BPMN Transaction Subprocess](https://docs.camunda.org/manual/7.5/reference/bpmn20/subprocesses/transaction-subprocess/) in action.

The process can fail when charging the credit card to showcase BPMN compensation, therefore set the variable `bookingHotelError` to true.

Built and tested against Camunda BPM version 7.5.0.

Show me the important parts!
----------------------------

![BPMN Process](src/main/resources/process.png)

How to use it?
--------------

Please choose

- Click through it using *Camunda web applications*
  - Package the project as WAR using Maven
  - Deploy it on one of our distributions: [Download Camunda community edition](https://camunda.org/download/)
  - Start a new instance using
[Camunda Tasklist](http://docs.camunda.org/latest/guides/user-guide/#tasklist): [http://localhost:8080/camunda/app/tasklist/](http://localhost:8080/camunda/app/tasklist/default/)
  - Inspect it using
[Camunda Cockpit](http://docs.camunda.org/latest/guides/user-guide/#cockpit): [http://localhost:8080/camunda/app/cockpit/](http://localhost:8080/camunda/app/cockpit/default/)
  
- Inspect the *[JUnit test case]*(src/test/java/org/camunda/bpm/example/bpmntransaction/bpmntransaction/InMemoryH2Test.java).
  - Simply import the Maven project into your favorite IDE and run the test case
  - You can see the [process test coverage tool](https://github.com/camunda/camunda-consulting/tree/master/snippets/process-test-coverage) in action: [docs/process-test-coverage/](docs/process-test-coverage/) - save it locally (including the sub folder) and open it in a web browser:

![Process Test Coverage](docs/process-test-coverage.png)

Environment Restrictions
------------------------

Built and tested against Camunda BPM version 7.5.0.


License
-------

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
