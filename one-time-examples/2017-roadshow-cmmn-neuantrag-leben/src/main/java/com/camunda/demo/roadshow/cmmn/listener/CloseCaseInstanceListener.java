package com.camunda.demo.roadshow.cmmn.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;


public class CloseCaseInstanceListener implements ExecutionListener {
  
//  private Logger LOG = Logger.getLogger(CloseCaseInstanceListener.class.getName());

  @Override
  public void notify(DelegateExecution execution) throws Exception {
//    HistoricCaseInstance caseInstance = execution.getProcessEngineServices().getHistoryService()
//      .createHistoricCaseInstanceQuery()
//      .superProcessInstanceId(execution.getProcessInstanceId())
////      .caseDefinitionKey("underwriting")
////      .completed()
//      .singleResult();
//    String caseInstanceId = caseInstance.getId();
    
    String caseInstanceId = (String) execution.getVariable("completedCaseInstanceId");
    execution.getProcessEngineServices().getCaseService().closeCaseInstance(caseInstanceId);
    execution.removeVariable("completedCaseInstanceId");
  }

}
