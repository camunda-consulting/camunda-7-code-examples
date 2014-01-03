package org.camunda.bpm.pattern.data.adapter;

import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.runtime.Execution;

public class BusinessProcessVariableAdapter extends AbstractProcessVariableAdapter {

  private final BusinessProcess businessProcess;

  @Inject
  public BusinessProcessVariableAdapter(final BusinessProcess businessProcess) {
    this.businessProcess = businessProcess;
  }

  @Override
  public <T extends Serializable> T getVariable(final String name) {
    return businessProcess.getVariable(name);
  }

  @Override
  public <T extends Serializable> void setVariable(final String name, final T value) {
    businessProcess.setVariable(name, value);
  }

  @Override
  public Map<String, Object> getVariablesMap() {
    if (!businessProcess.isAssociated()) {
      throw new ProcessEngineException("cannot query process variables from not associated business process.");
    }
    Execution execution = businessProcess.getExecution();
    ExecutionEntity executionEntity = (ExecutionEntity) execution;
    return executionEntity.getVariables();
  }

}
