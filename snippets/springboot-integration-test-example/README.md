# Testing a Camunda Spring Boot Application

Testing a complex process with a lot of simple JavaDelegate classes can become
quite cumbersome, if you rely on Unit tests only.

The programming logic in the Delegate classes is simple:

- reading process variables
- calling the service
- writing process variables.

Unit tests would not discover any surprises in the delegates. But these
surprises will be revealed when run your process instances in production with
real business services and real life data.

## Integration tests

With the help of Spring Boot tests, you can spin up the process engine in an
integration test environment. In the best case you have real life test data,
that can be restarted after running a test suite. Today, test containers are a
big help here.

But let's take a look at the setup of the process engine and the test
implementation.

The test implementation should be quite simple: Start a process instance with
given process variables and wait until it's finished. Then make assertions on
the result. Here you can assert on process variables and activities, that should
have been passed or skipped.

If you have Delegates that are marked with asyncBefore or asyncAfter, you don't
want to execute the jobs manually as you do it in unit tests. In integration
tests, you can enable the job executor and use some asynchronous programming to
wait until the process instances is completed. It will help you to get short
runtimes for your tests and don't wait until a fixed `Thread.sleep(...)` is
over.

Here is an example covering all aspects mentioned before:
[IntegrationTest.java](src/test/java/com/camunda/consulting/springboot_integration_test_example/integration/IntegrationTest.java)

### Implementation details

```java
@SpringBootTest(properties = { "camunda.bpm.job-execution.enabled=true" })
```

starts the process engine with job executor enabled and completes the process
instances in the background.

```java
  protected void waitForProcessInstanceCompleted(ProcessInstance processInstance, Duration maxWait) {
    Awaitility.await().atMost(maxWait).untilAsserted(() -> {
      Thread.sleep(100);
      assertThat(processInstance).isEnded();
    });
  }
```

waits only a small time unit (100ms) until the process instances is ended. A
maximum time is given as a parameter. If the process is not ended during this
time, the test failed.

This method is called after starting the process instance:

```java
    waitForProcessInstanceCompleted(processInstance, Duration.ofSeconds(2));
```

### Unit Test

You can run the JUnit test
[ProcessTest](src/test/java/com/camunda/consulting/springboot_integration_test_example/ProcessTest.java)
in your IDE or using:

```bash
mvn clean test
```

### Running the application

You can also build and run the process application with Spring Boot.

#### Manually

1. Build the application using:

```bash
mvn clean package
```

2. Run the \*.jar file from the `target` directory using:

```bash
java -jar target/springboot-integration-test-example.jar
```

For a faster 1-click (re-)deployment see the alternatives below.

#### Maven Spring Boot Plugin

1. Build and deploy the process application using:

```bash
mvn clean package spring-boot:run
```

#### Your Java IDE

1. Run the project as a Java application in your IDE using CamundaApplication as
   the main class.

### Run and Inspect with Tasklist and Cockpit

Once you deployed the application you can run it using
[Camunda Tasklist](http://docs.camunda.org/latest/guides/user-guide/#tasklist)
and inspect it using
[Camunda Cockpit](http://docs.camunda.org/latest/guides/user-guide/#cockpit).

## Environment Restrictions

Built and tested against Camunda Platform version 7.19.0.

## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
