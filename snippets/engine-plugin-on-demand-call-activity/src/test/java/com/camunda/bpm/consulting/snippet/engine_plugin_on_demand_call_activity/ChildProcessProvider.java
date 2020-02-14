package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.variable.VariableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ChildProcessProvider {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String getChildProcessDefinitionKey(DelegateExecution execution) {
        Boolean retProcess = (Boolean) execution.getVariable("retProcess");
        logger.info("Running childProcessProvider...");
        if (retProcess) {
            return "process-child";
        } else {
            VariableMap inputVariables = execution.getVariablesTyped();
            VariableMap inputVariablesLocal = execution.getVariablesLocalTyped();

            String executionId = execution.getId();

            Boolean doThrowException = (Boolean) execution.getVariable("doThrowException");

            // Publish a task to a scheduled executor. This method returns after the task has
            // been put into the executor. The actual service implementation (lambda) will not yet
            // be invoked:
            RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
            CompletableFuture.runAsync(() -> { // simulates the sending of a non-blocking REST request
                // the code inside this lambda runs in a separate thread outside the TX
                // this will not work: execution.setVariable("foo", "bar");
                try {
                    if(!doThrowException) {
                        Logger logger = LoggerFactory.getLogger(getClass());
                        logger.info("Executing async block...");
                        Map<String, Object> newVariables = new HashMap<>();
                        newVariables.put("foo", "bar");
                        //TODO: IS RUNTIME SERVICE THREAD SAFE?
                        runtimeService.signal(executionId, newVariables);
                    }
                    else{
                        runtimeService.signal(executionId, null, new Exception("Exception!"), null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    runtimeService.signal(executionId, null, e, null);
                }
                //INCIDENT AND BPMN ERROR
            }, CompletableFuture.delayedExecutor(250L, TimeUnit.MILLISECONDS));
            // TODO prepare REST request using input variables

            //TODO TRY TO CREATE AN SKIP EXCEPTION
            return null;
        }
    }
}
