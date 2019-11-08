package com.camunda.bpm.example.external_task_worker_process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CompleteExternalTaskInParentProcessDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String externalTaskId = (String) execution.getVariable("externalTaskId");
    String workerId = (String) execution.getVariable("workerId");
    execution.getProcessEngineServices()
      .getExternalTaskService()
      .complete(externalTaskId, workerId, execution.getVariables());
  }

}
