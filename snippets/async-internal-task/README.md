# Async Internal Task

## What is the example about?

This example combines two things:
* using the External Task pattern internally (using a community project)
* sending out a message and handling a response to complete the according task by using the external task id

## How does it work in detail?

The code contains 2 main handlers:

* `OutboundMessageHandler`: Responsible for sending out a message when an external task is available. It will create a message from the task and send it to the message broker.
* `InboundMessageHandler`: Responsible for sending the result back to the process. It will take the message from the queue and use the contained `taskId` to complete the linked task.

## What are the special features?

The lock duration is set to 1 minute, meaning that sometimes, a message is transferred two or more times to the message broker.

This is acceptable under the circumstances that the message handling is idempotent.

But still, duplicate message can arrive as response. These duplicate messages will be logged and ignored.

Also, OLEs can happen as the designed scenario is a multi-instance. As OLEs can be retried safely, this will happen async.

## How do I set it up?

* Run the docker-compose:

```shell
cd docker-compose
docker-compose up -d
```

* Run the java application:

```shell
mvn spring-boot:run
```

The Spring Boot app should connect to the postgres database as well as to the rabbitmq message broker.

Then, you can start an instance of the `ExampleProcess` by using the REST API:

```shell
curl --location 'localhost:8080/engine-rest/process-definition/key/ExampleProcess/start' \
--header 'Content-Type: application/json' \
--data '{}'
```

This will create 50 tasks to be handled. The application should log the outbound handler action and the inbound handler action.

## Which performance has been measured?

The measured performance happened on 200 instances of the process being created within 10 seconds. The handling of all 10000 created tasks took around 3 to 4 minutes.

If you try this on your own, don't be irritated with the stacktraces appearing at the end of the run. These are just the duplicate messages, the log level is `WARN`.
