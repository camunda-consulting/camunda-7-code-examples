package com.camunda.bpm.example.external_task_worker_process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.pvm.runtime.PvmExecutionImpl;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class SetParentDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    // TODO: make something like this work
//    String parentProcessInstanceId = (String) execution.getVariable("parentProcessInstanceId");
//    ProcessInstance parentProcessInstance = execution.getProcessEngineServices()
//      .getRuntimeService()
//      .createProcessInstanceQuery()
//      .processInstanceId(parentProcessInstanceId)
//      .singleResult();
//    ((ExecutionEntity) execution).setSuperExecution((PvmExecutionImpl) parentProcessInstance);
  }

}
