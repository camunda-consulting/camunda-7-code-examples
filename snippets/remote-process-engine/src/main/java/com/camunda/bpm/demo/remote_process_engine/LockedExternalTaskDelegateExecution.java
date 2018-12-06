package com.camunda.bpm.demo.remote_process_engine;

import org.camunda.bpm.engine.externaltask.LockedExternalTask;

/**
 * DelegateExecution implementation that can be initialized with a LockedExternalTask and
 * provides all methods required for executing a JavaDelegate as part of an external worker.
 *
 * @author Falko Menge (Camunda)
 */
public class LockedExternalTaskDelegateExecution extends AbstractExternalTaskDelegateExecution {

  private static final long serialVersionUID = 1L;
  
  protected LockedExternalTask task;

  public LockedExternalTaskDelegateExecution(LockedExternalTask task) {
    super(task.getVariables());
    this.task = task;
  }

  @Override
  public String getId() {
    return task.getExecutionId();
  }

  @Override
  public String getCurrentActivityId() {
    return task.getActivityId();
  }

  @Override
  public String getActivityInstanceId() {
    return task.getActivityInstanceId();
  }

  @Override
  public String getBusinessKey() {
    return task.getBusinessKey();
  }

  @Override
  public String getProcessBusinessKey() {
    return getBusinessKey();
  }

  @Override
  public String getProcessInstanceId() {
    return task.getProcessInstanceId();
  }

  @Override
  public String getProcessDefinitionId() {
    return task.getProcessDefinitionId();
  }

  @Override
  public String getTenantId() {
    return task.getTenantId();
  }

}
