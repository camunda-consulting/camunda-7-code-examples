# Camunda External Task Client EJB

The provided Java implementations for external task clients are Java SE and Spring as well as Spring Boot.

In an EJB environment, this will not help a lot.

So, here is a snippet that shows how an external task client can be bootstrapped using EJB and handler functions can be registered as beans just like in Spring Boot (yey)!

## What is contained

### Test setup

inside [docker](./docker), you will find files that help you to set up the test environment:

* a Wildfly Application Server 22.0.1.Final having mapped ports `8080` and `9990`
* a Camunda Run distro (version does not matter) having a mapped port `8083`

The Wildfly is used to deploy the External Task Client while the Camunda Run is used to host the Engine Rest API that the external task client will execute tasks from.
The BPMN process as well as the `default.yml` configuration are mapped into the Camunda Run container to have a test process in place.

### Java Application

The Java project is built using [Maven](./pom.xml). It builds to a `.war` artifact.

When looking at the dependencies, you will realize that there is not much required actually:

* The JavaEE API version 8 (probably, not every aspect is required so this can be replaced by individual API dependencies)
* The Camunda External Task Client Java SE

This project has copied classes from the External Task Client Spring implementations:

* [`@ExternalTaskSubscription`](./src/main/java/com/camunda/consulting/externalTask/ejb/ExternalTaskSubscription.java): Annotation that can be used on beans implementing `ExternalTaskHandler`
* [`SubscriptionConfiguration`](./src/main/java/com/camunda/consulting/externalTask/ejb/SubscriptionConfiguration.java): Bean representation of the details provided by the [`@ExternalTaskSubscription`](./src/main/java/com/camunda/consulting/externalTask/ejb/ExternalTaskSubscription.java)

Other relevant components to function:

* [`ExternalTaskClientFactory`](./src/main/java/com/camunda/consulting/externalTask/ejb/ExternalTaskClientFactory.java): A bean that provides a bean `ExternalTaskClient` which can be injected elsewhere.
* [`HandlerRegistrationProcessor`](./src/main/java/com/camunda/consulting/externalTask/ejb/HandlerRegistrationProcessor.java): The bean that uses the `ExternalTaskClient` to subscribe all `ExternalTaskHandler` implementations being annotated with `@ExternalTaskSubscription` by using the `BeanManager`.

Example implementation:

* [`ExampleExternalTaskHandler`](./src/main/java/com/camunda/consulting/externalTask/ejb/ExampleExternalTaskHandler.java): A subscription to the topic *example*

## How to run it

First, navigate to `./docker`:

```bash
cd docker
```

Then, run the docker-compose:

```bash
docker-compose up -d
```

Hint: The `-d` flag will keep your session disconnected.

Now, you can head back to the root dir:

```bash
cd ..
```

Now, deploy your EJB External Task Client using:

```bash
mvn clean wildfly:deploy
```

The External Task Client is bootstrapped and connects to the Engine Rest API running on the Camunda Run container.

Finally, you can test a process instance run by using:

```bash
curl --location --request POST 'http://localhost:8083/engine-rest/process-definition/key/exampleProcess/start' \
--header 'Content-Type: application/json' \
--data-raw ''
```

Hint: The External Task Client has a very long backoff period (60 seconds) by default. This may cause delay between task creation and execution. You can configure this by alternating the [`ExternalTaskClientFactory`](./src/main/java/com/camunda/consulting/externalTask/ejb/ExternalTaskClientFactory.java).
