# authenticatedUserId after Asynchronous Continuation

If you use the authenticatedUserId expression in your process, it gives you the user ID, that was attached to the thread with `identityService.setAutheticatedUserId(userId)` before.

After an asynchronous service call, the ID is not available any more (the [async continuation](http://docs.camunda.org/7.2/guides/user-guide/#process-engine-transactions-in-processes-asynchronous-continuations) uses another thread from the pool, started by the [job executor](http://docs.camunda.org/7.2/guides/user-guide/#process-engine-the-job-executor-job-execution)).   

How can you access the userId in an expression afterwards?

1. Save it as a process variable. Thats easy and straight forward. 
2. Set it to the new thread calling `identityService.setAutheticatedUserId(userId)` again.

The downside of the first approach: you have to pass the execution in your beans to access the process variable. If you want to use clean business oriented beans without any knowledge of process elements, this breaks the approach.

In the second approach you can pass the userId as a parameter into your bean, if you need it there.
Or you can use a DelegateInterceptor to deal with it (but that's out of scope of this snippet).

## How to do it?

If you call `identityService.setAuthenticatedUserId(userId)` before the process instance is started, the user id will be saved in the history. You can get it from here by calling 

    HistoricProcessInstance historicProcessInstance = execution
        .getProcessEngineServices()
        .getHistoryService()
        .createHistoricProcessInstanceQuery()
        .processInstanceId(execution.getProcessInstanceId())
        .singleResult();
    String startUserId = historicProcessInstance.getStartUserId();
     
A [subclass of the AsyncContinuationJobHandler](src/test/java/org/camunda/bpm/unittest/AuthenticatedAsyncContinuationJobHandler.java)
set the user ID from the start of the process instance to the next thread.

The new job handler must be registered in the process engine configuration. 

Here is the snippet for it:

    Map<String, JobHandler> jobHandlers = processEngineConfiguration.getJobHandlers();
    AuthenticatedAsyncContinuationJobHandler authenticatedAsyncContinuationJobHandler = new AuthenticatedAsyncContinuationJobHandler();
    jobHandlers.put(authenticatedAsyncContinuationJobHandler.getType(), authenticatedAsyncContinuationJobHandler);

You can get the process engine configuration from your process engine object or put the lines into a [process engine plugin](https://github.com/camunda/camunda-bpm-examples/tree/master/process-engine-plugin/bpmn-parse-listener) as described in another example.
  
## Other Job Handlers

Be aware that there are more than the AsyncContinuationJobHandler, for instance

* ProcessEventJobHandler
* TimerCatchIntermediateEventJobHandler
* TimerChangeJobDefinitionSuspensionStateJobHandler
* TimerChangeProcessDefinitionSuspensionStateJobHandler
* TimerExecuteNestedActivityJobHandler
* TimerStartEventJobHandler
* TimerStartEventSubprocessJobHandler

If you need the authenticatedUserId after one of these events, you have to subclass and register them, too.
 
 