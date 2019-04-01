package com.camunda.demo;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ResumeInstanceDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {

    RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();

    runtimeService.createProcessInstanceModification(execution.getProcessInstanceId()).startBeforeActivity("Task_DoThat").execute();

    // The resume activity could also be resolved dynamically, based on a variable that could be set by a user.

  }
}
