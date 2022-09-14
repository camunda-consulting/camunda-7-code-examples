# Failure Handling in Sequential Multi-Instance Subprocesses
Multi-instance subprocesses are a great way of performing tasks for each element in a list, i.e., for each order item in an order.
However, during each execution of the subprocess errors may occur:
technical errors, such as an unavailable, service and business errors, such as an item being out of stock.
This example discusses how these types of errors can be handled.

## The process
The process consists of a simple sequence of activities:
1. A user enters the number of desired list entries, an index for a business error, and an index for a technical error.
2. Afterwards, a service task is called to create a generic list
3. A sequential subprocess iterates over the list: If the index matches the technical failure index, an exception is thrown; if it matches the business failure index, a BPMN error is raised.

## Error Handling
### Desired Behavior
When a technical failure occurs, the engine is supposed to retry the service call.
In case of a business error, the error is caught and the entry is flagged as faulty.

### Implementation
#### Transaction Boundaries
The technical error occurs for a specific instance of the multi-instance subprocess.
Only the respective instance shall be retried if the execution fails.
For this purpose, we need to introduce suitable transaction boundaries, by setting the following properties for `call a service`:

| Property | Value |
|----------|-------|
| Asynchronus Before | true |
| Exclusive | true |

### Java Delegate
Let's have a look at the execute method of the java delegate `CallSomServiceDelegate`:
```JAVA
public void execute(DelegateExecution execution) throws Exception {
    Integer loopCounter = (Integer) execution.getVariable(LOOP_COUNTER_VARIABLE_NAME);
    long failureIndex = (Long) execution.getVariable(FAILURE_INDEX_VARIABLE_NAME);
    long businessErrorIndex = (Long) execution.getVariable(BUSINESS_ERROR_INDEX_NAME);
    long loop = loopCounter;
    // Get the item Variable
    String item = (String) execution.getVariable(LOOP_ELEMENT_VARIABLE);
    // Execute some services call; here simulated with Thread.sleep.
    // In case loop count == failureIndex throw an exception to simulate a failure.
    try {
      logger.info("Start Processing item {}", item);
      Thread.sleep(200);
      if (loop == failureIndex) {
        logger.info("Failed item {}", item);
        throw new ProcessEngineException("Failure in Process");
      } else if (loop == businessErrorIndex) {
        logger.info("A business error occurred for item {}", item);
        throw new BpmnError("BusinessError", "Flag " + item + ", since it cannot be processed.");
      }
      logger.info("End Processing item {}", item);
    } catch (InterruptedException e) {}
  }
```
In case the index is supposed to cause a technical error, an exception is thrown.
The engine will retry this service automatically according to the given specification.
If the index matches the business error index, a BpmnError is raised.
It takes two parameters:
an error code, which must match the error code in the process model's boundary event, and
an optional error message.