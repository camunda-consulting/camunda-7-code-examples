# External Task Tracing

The Camunda External Task Client does not support tracing out-of-the-box.

Here is an example on how this can be implemented.

## What was implemented?

The bootstrapping of the External Task Client happens via the `TracingExternalTaskClientBeanFactory`. The usual External Task Client Spring Boot Starter will recognize this and not bootstrap another client.

This factory overrides the initial creation of the External Task Client and uses a custom Builder, `TracingExternalTaskClientBuilderImpl`.

This builder overrides the creation of:

* the engine client to `TracingEngineClient` which keeps track of a `failure()` and reports this as event to the current observation
* the topic subscription manager to `TracingTopicSubscriptionManger` which keeps track of `handleExternalTask`, creates, opens and closes an observation for the process instance with the span of the external task

## What is the outcome?

When using this implementation on an External Task Subscription, it will be traced:

```log
2023-12-04T13:56:37.475+01:00  INFO 22222 --- [criptionManager] i.m.o.ObservationTextPublisher           : START - name='8d759879-92a4-11ee-8563-0242ac140003', contextualName='8d75bf8d-92a4-11ee-8563-0242ac140003', error='null', lowCardinalityKeyValues=[], highCardinalityKeyValues=[], map=[], parentObservation=null
2023-12-04T13:56:37.475+01:00  INFO 22222 --- [criptionManager] i.m.o.ObservationTextPublisher           :  OPEN - name='8d759879-92a4-11ee-8563-0242ac140003', contextualName='8d75bf8d-92a4-11ee-8563-0242ac140003', error='null', lowCardinalityKeyValues=[], highCardinalityKeyValues=[], map=[], parentObservation=null
2023-12-04T13:56:37.476+01:00  INFO 22222 --- [criptionManager] com.camunda.consulting.DemoTask          : Executing task '8d75bf8d-92a4-11ee-8563-0242ac140003'
2023-12-04T13:56:37.490+01:00  INFO 22222 --- [criptionManager] i.m.o.ObservationTextPublisher           : CLOSE - name='8d759879-92a4-11ee-8563-0242ac140003', contextualName='8d75bf8d-92a4-11ee-8563-0242ac140003', error='null', lowCardinalityKeyValues=[], highCardinalityKeyValues=[], map=[], parentObservation=null
2023-12-04T13:56:37.490+01:00  INFO 22222 --- [criptionManager] i.m.o.ObservationTextPublisher           :  STOP - name='8d759879-92a4-11ee-8563-0242ac140003', contextualName='8d75bf8d-92a4-11ee-8563-0242ac140003', error='null', lowCardinalityKeyValues=[], highCardinalityKeyValues=[], map=[], parentObservation=null
```

This is a log output of the Task `DemoTask` with the `ObservationTextPublisher` enabled.

## How can I try it out?

First, run a Camunda Engine (under port 8080), there is a prepared docker-compose file under `./docker-compose/docker-compose.yaml`:

```shell
docker-compose -f ./docker-compose/docker-compose.yaml up -d
```

Then, start the application: 

```shell
mvn spring-boot:run
```

You can then create process instances of the demo process:

```shell
curl --location 'http://localhost:8080/engine-rest/process-definition/key/demoProcess/start' \
--header 'Content-Type: application/json' \
--data '{}'
```