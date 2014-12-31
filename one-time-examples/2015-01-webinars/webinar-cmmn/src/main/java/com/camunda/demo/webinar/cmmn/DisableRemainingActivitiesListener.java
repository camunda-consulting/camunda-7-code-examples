package com.camunda.demo.webinar.cmmn;

import java.util.List;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.impl.cmmn.entity.runtime.CaseExecutionEntity;
import org.camunda.bpm.engine.impl.cmmn.execution.CaseExecutionState;
import org.camunda.bpm.engine.runtime.CaseExecution;

public class DisableRemainingActivitiesListener implements CaseExecutionListener {

  @Override
  public void notify(DelegateCaseExecution caseExecution) throws Exception {
    System.out.println(" --- Disabling some stuff ---");

    String caseInstanceId = caseExecution.getCaseInstanceId();
    List<CaseExecution> caseExecutions = caseExecution.getProcessEngineServices().getCaseService().createCaseExecutionQuery().caseInstanceId(caseInstanceId).list();
    for (CaseExecution otherCaseExecution : caseExecutions) {
      if (otherCaseExecution.isEnabled()) {
        caseExecution.getProcessEngineServices().getCaseService().disableCaseExecution(otherCaseExecution.getId());
      }
      if (otherCaseExecution.isAvailable()) {
//        caseExecution.getProcessEngineServices().getCaseService().disableCaseExecution(otherCaseExecution.getId());
      }
      System.out.println(otherCaseExecution.getActivityName() + " -> " + ((CaseExecutionEntity)otherCaseExecution).getCurrentState().toString());
    }
    
    // Maybe to early because task completion is not yet done?
//    caseExecution.getProcessEngineServices().getCaseService().completeCaseExecution(    caseExecution.getCaseInstanceId() );
    
  }
}
