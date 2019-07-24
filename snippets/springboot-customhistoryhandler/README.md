# Custom History Event Listener
## Introduction
This project contains a working example to implement custom history event handlers for Camunda BPM.

|Minimum Requirements| Version|
|---|---|
|Camunda BPM|`7.11.0`|
|Camunda Spring Boot Starter|`3.3.1`|
|Spring Boot|`2.1.5.RELEASE`|

This version of `camunda-spring-boot-starter` ships with Spring Eventing integration and simplifies handler
registration. However, this update breaks how custom history event handlers were registered in this example
before.

If you require using an older version of Camunda see tree version [69d79ce](https://github.com/sebwarnke/camunda-examples/tree/69d79ce937a8173d12ee5d771b8aa39222366e08).

## How it works, generally
### Creating a Custom History Event Handler
Every history event handler must implement the interface `HistoryEventHandler` that requires you to define
two methods; `handleEvent(HistoryEvent historyEvent)` and `handleEvents(List<HistoryEvent> historyEvents)`.

As already mentioned above, Camunda Spring Boot Starter now contains integration with SpringEventing. To 
make your handler work with that you just need to do two things.
1. Mark your handler class as `@Component`
2. Mark your `handleEvent()` method as `@EventListener`

_Example:_
```java
@Component
public class CustomHistoryEventHandler implements HistoryEventHandler {

  @Override
  @EventListener
  public void handleEvent(HistoryEvent historyEvent) { }

  @Override
  public void handleEvents(List<HistoryEvent> historyEvents) { }
}
```

## How to run this project?
Using Maven:
```
mvn clean spring-boot:run
```

## What it does
The class `CustomHistoryEventHandler` checks for events indicating
- start of an activity
- end of an activity
- something has happened to an External Service Task

```java
if (historyEvent.getEventType().equals(HistoryEventTypes.ACTIVITY_INSTANCE_START.getEventName()) || historyEvent.getEventType()
        .equals(HistoryEventTypes.ACTIVITY_INSTANCE_END.getEventName()))
```

Then matching events are filtered to only match User Tasks, Timer Events and Message Catch Events.

```java
List<String> relevantActivityTypes = new ArrayList<>();
relevantActivityTypes.add(ActivityTypes.TASK_USER_TASK);
relevantActivityTypes.add(ActivityTypes.INTERMEDIATE_EVENT_MESSAGE);
relevantActivityTypes.add(ActivityTypes.INTERMEDIATE_EVENT_TIMER);
```