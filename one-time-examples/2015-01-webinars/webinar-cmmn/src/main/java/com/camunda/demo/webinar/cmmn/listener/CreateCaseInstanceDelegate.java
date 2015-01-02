package com.camunda.demo.webinar.cmmn.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

import com.camunda.demo.webinar.cmmn.Constants;

public class CreateCaseInstanceDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    VariableMap variables = Variables.createVariables().putValue(Constants.VAR_NAME_PROCESS_INSTANCE_ID, execution.getProcessInstanceId());
    CaseInstance caseInstance = execution.getProcessEngineServices().getCaseService().createCaseInstanceByKey(Constants.CASE_DEFITION_KEY_UNDERWRITING, variables);
    execution.setVariable(Constants.VAR_NAME_CASE_INSTANCE_ID, caseInstance.getCaseInstanceId());
  }

}
