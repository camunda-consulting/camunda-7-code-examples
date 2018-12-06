package com.camunda.bpm.demo.remote_process_engine;

import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowElement;

/**
 * Abstract DelegateExecution that can be used for as base for DelegateExecution
 * implementations for executing a JavaDelegate as part of an External Task.
 * 
 * It contains all methods that have to be implemented with additional REST API
 * calls or wrapper classes that emulate the Camunda Java API. 
 *
 * @author Falko Menge (Camunda)
 */
public abstract class AbstractExternalTaskDelegateExecution extends SimpleVariableScope implements DelegateExecution {

  private static final long serialVersionUID = 1L;

  protected RepositoryService repositoryService;

  public AbstractExternalTaskDelegateExecution() {
    super();
  }

  public AbstractExternalTaskDelegateExecution(Map<String, ? extends Object> variables) {
    super(variables);
  }

  @Override
  public String getCurrentActivityName() {
    return getBpmnModelElementInstance().getName();
  }

  @Override
  public FlowElement getBpmnModelElementInstance() {
    return getBpmnModelInstance().getModelElementById(getCurrentActivityId());
  }

  @Override
  public BpmnModelInstance getBpmnModelInstance() {
    // TODO get XML from REST API and cache it locally 
    // see BPMN Model API
    return repositoryService.getBpmnModelInstance(getProcessDefinitionId());
  }

  public void setRepositoryService(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  @Override
  public ProcessEngineServices getProcessEngineServices() {
    throw new UnsupportedOperationException();
    // TODO return own wrapper that talks to REST API for selected operations
  }

  @Override
  public ProcessEngine getProcessEngine() {
    throw new UnsupportedOperationException();
    // TODO return own wrapper that talks to REST API for selected operations
  }

  @Override
  public String getParentId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getParentActivityInstanceId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getEventName() {
    throw new UnsupportedOperationException("This DelegateExecution implementation is not meant to be used for ExecutionListeners");
  }

  @Override
  public String getCurrentTransitionId() {
    throw new UnsupportedOperationException("This DelegateExecution implementation is not meant to be used for ExecutionListeners");
  }

  @Override
  public DelegateExecution getProcessInstance() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DelegateExecution getSuperExecution() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isCanceled() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Incident createIncident(String incidentType, String configuration) {
    throw new UnsupportedOperationException();
    // TODO via REST
  }

  @Override
  public Incident createIncident(String incidentType, String configuration, String message) {
    throw new UnsupportedOperationException();
    // TODO via REST
  }

  @Override
  public void resolveIncident(String incidentId) {
    throw new UnsupportedOperationException();
    // TODO via REST
  }

  @Override
  public void setProcessBusinessKey(String businessKey) {
    throw new UnsupportedOperationException();
    // TODO via REST API, if that is supported by Camunda one day
  }

  @Override
  public void setVariable(String variableName, Object value, String activityId) {
    throw new UnsupportedOperationException();
  }

}