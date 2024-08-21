# Conditional Event on transient variable

## What is the purpose of this solution?

A conditional event can use the expression context to evaluate its condition, but it will not be triggered if there is no variable update.

## How does the solution work?

The solution is a scheduled function that finds all process instances with at least one active conditional event subscription and sets a transient variable to each.

This will cause all active conditional events to be re-evaluated.

## Why is the design like this?

The scheduled trigger function is implemented in a very generic way and provides a process-engine-wide functionality.

Each conditional event can then define its own condition as an expression.

This means that the trigger and the actual condition evaluation are decoupled:

![image](https://github.com/user-attachments/assets/d93da124-f44a-443b-99c1-d1ccb15e9681)


## How do I try it on my own?

* run the app by using:

```shell
mvn spring-boot:run
```

* start process instances by using:

```shell
curl --location 'localhost:8080/engine-rest/process-definition/key/ExampleProcess/start' \
--header 'Content-Type: application/json' \
--data '{}'
```

* change the value of the bean the conditional events evaluate with:

```shell
curl --location --request PUT 'localhost:8080/example-value' \
--header 'Content-Type: text/plain' \
--data 'B'
```

If you want to inspect the apps' internals, please add this to the `application.yaml`:

```yaml
logging:
  level:
    com.camunda.consulting: debug  
```
