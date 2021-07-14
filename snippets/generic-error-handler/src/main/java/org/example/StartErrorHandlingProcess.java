package org.example;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.glassfish.jersey.internal.guava.Maps;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This delegate starts the error process used for error handling
 */
@Slf4j
@Component
public class StartErrorHandlingProcess implements JavaDelegate {
  public void execute(DelegateExecution execution) throws Exception {
    var input = Map.of(
        "callingProcessInstanceId", execution.getProcessInstanceId(),
        "callingActivityId", execution.getVariable("errorTaskId"));

    var pi = execution.getProcessEngine().getRuntimeService()
        .startProcessInstanceByKey("ErrorHandlingProcess",
            execution.getProcessBusinessKey(),
            input);

    log.info("Started process instance {}", pi.getId());
  }
}
