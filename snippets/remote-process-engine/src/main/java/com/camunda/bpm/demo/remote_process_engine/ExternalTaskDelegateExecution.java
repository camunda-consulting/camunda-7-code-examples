package com.camunda.bpm.demo.remote_process_engine;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.engine.RepositoryService;

/**
 * DelegateExecution implementation that can be initialized with an ExternalTask and
 * provides all methods required for executing a JavaDelegate as part of an external worker.
 *
 * @author Falko Menge (Camunda)
 */
public class ExternalTaskDelegateExecution extends AbstractExternalTaskDelegateExecution {

  private static final long serialVersionUID = 1L;
  
  protected ExternalTask task;

  public ExternalTaskDelegateExecution(ExternalTask task) {
    super(task.getAllVariables());
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
