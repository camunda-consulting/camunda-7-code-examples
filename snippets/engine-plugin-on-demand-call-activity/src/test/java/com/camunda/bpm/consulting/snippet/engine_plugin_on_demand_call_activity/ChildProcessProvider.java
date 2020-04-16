package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.camunda.bpm.engine.variable.VariableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.util.OnDemandCallActivityUtil.getAsyncServiceCallVarName;
import static com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.util.OnDemandCallActivityUtil.getSkipVarName;

public class ChildProcessProvider {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String getChildProcessDefinitionKey(DelegateExecution execution) {
        Boolean retProcess = (Boolean) execution.getVariable("retProcess");
        logger.info("Running childProcessProvider...");
        
        // example on how to skip execution completely, e.g. during a retry after some manual fix
        if (execution.hasVariable(getSkipVarName(execution))) {
          execution.setVariable(getSkipVarName(execution), null);
          return null;
        }
        
        if (retProcess) {
            return "process-child"; // process definition key
        } else {
            VariableMap inputVariables = execution.getVariablesTyped();
            VariableMap inputVariablesLocal = execution.getVariablesLocalTyped();

            String executionId = execution.getId();

            Boolean doThrowException = (Boolean) execution.getVariable("doThrowException");

            // TODO handle exceptions during request creation? Only needed during reactive REST calls
            
            // Publish a task to a scheduled executor. This method returns after the task has
            // been put into the executor. The actual service implementation (lambda) will not yet
            // be invoked:
            RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
            CompletableFuture.runAsync(() -> { // simulates the sending of a non-blocking REST request
                // the code inside this lambda runs in a separate thread outside the TX
                // this will not work: execution.setVariable("foo", "bar");
                // THE EXECUTION IS NOT THREAD-SAFE
                try {
                    logger.info("Do throw exception: "+doThrowException);
                    if (doThrowException) {
                      throw new Exception("Exception!");
                    }
                    Logger logger = LoggerFactory.getLogger(getClass());
                    logger.info("Executing async block...");
                    Map<String, Object> newVariables = new HashMap<>();
                    newVariables.put("outputVar", "someValue");
                    //TODO: IS RUNTIME SERVICE THREAD SAFE? => Thorben says yes!
                    runtimeService.signal(executionId, newVariables);
                } catch (Exception e) {
                  e.printStackTrace();
                  runtimeService.signal(executionId, null, e, null);
                  // sketch for self healing
//                  try {
//                    // synchronously call self-healing µs
//                    if (isIgnore) {
//                      runtimeService.signal(executionId, newVariables);
//                    } else if (isRetry) {
//                      // rule with define number of retries and delay
//                      runtimeService.signal(executionId, null, e, null);                      
//                    } else {
//                      // fallout => incident without retries 
//                      e.printStackTrace();
//                      runtimeService.signal(executionId, null, e, null);
//                    }
//                  } catch (Exception selfHealingException) {
//                    runtimeService.signal(executionId, null, selfHealingException, null); // maybe indicate that delayed retry by Camunda is needed 
//                  }
                }
                //INCIDENT AND BPMN ERROR
            }, CompletableFuture.delayedExecutor(250L, TimeUnit.MILLISECONDS));
            // TODO prepare REST request using input variables

            execution.setVariableLocal(getAsyncServiceCallVarName(execution), true);
            return null;
        }
    }
}
