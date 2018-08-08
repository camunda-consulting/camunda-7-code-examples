package com.camunda.demo.underwriting.listener;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.history.HistoricCaseInstance;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.pvm.runtime.ExecutionImpl;

import com.camunda.demo.underwriting.Constants;

public class CloseCaseInstanceListener implements ExecutionListener {
  
  private Logger LOG = Logger.getLogger(DisableRemainingActivitiesListener.class.getName());

  @Override
  public void notify(DelegateExecution execution) throws Exception {
//    HistoricCaseInstance caseInstance = execution.getProcessEngineServices().getHistoryService()
//      .createHistoricCaseInstanceQuery()
//      .superProcessInstanceId(execution.getProcessInstanceId())
////      .caseDefinitionKey("underwriting")
////      .completed()
//      .singleResult();
//    String caseInstanceId = caseInstance.getId();
    
    String caseInstanceId = (String) execution.getVariable("underwritingCaseInstanceId");
    execution.getProcessEngineServices().getCaseService().closeCaseInstance(caseInstanceId);
  }

}
