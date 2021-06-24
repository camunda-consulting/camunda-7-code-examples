# Camunda Spring Boot Application

Spring Boot Application using [Camunda](http://docs.camunda.org).

## Show me the important parts!

[Process in which an error is caused](src/main/resources/GenericErrorHandlerProcess.bpmn)   
[Process in which the error is handled](src/main/resources/ErrorHandlingProcess.bpmn)

## How does it work?

One of the service tasks may randomly throw a BPMNError. An event-based sub process catches the error and initiates the
another process for error handling. After the error has been fixed a service task modifies the original process
instance, setting the token back to the service task in which the BPMNError occurred.

### Unit Test

You can run the JUnit test [ProcessTest](src/test/java/org/example/InMemoryH2Test.java) in your IDE or using:

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

2. Run the *.jar file from the `target` directory using:

```bash
java -jar target/GenericErrorHandler.jar
```

For a faster 1-click (re-)deployment see the alternatives below.

#### Maven Spring Boot Plugin

1. Build and deploy the process application using:

```bash
mvn clean package spring-boot:run
```

#### Your Java IDE

1. Run the project as a Java application in your IDE using CamundaApplication as the main class.

### Run and Inspect with Tasklist and Cockpit

Once you deployed the application you can run it using
[Camunda Tasklist](http://docs.camunda.org/latest/guides/user-guide/#tasklist)
and inspect it using
[Camunda Cockpit](http://docs.camunda.org/latest/guides/user-guide/#cockpit).

## Environment Restrictions

Built and tested against Camunda Platform version 7.15.0.

## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

<!-- Tweet
New @Camunda example: Camunda Spring Boot Application - Spring Boot Application using [Camunda](http://docs.camunda.org). https://github.com/camunda-consulting/code/tree/master/snippets/GenericErrorHandler
-->
