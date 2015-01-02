package com.camunda.demo.webinar.cmmn.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import com.camunda.demo.webinar.cmmn.Constants;

public class CloseCaseInstanceListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    String caseInstanceId = (String) execution.getVariable(Constants.VAR_NAME_CASE_INSTANCE_ID);
    execution.getProcessEngineServices().getCaseService().closeCaseInstance(caseInstanceId);
  }

}
