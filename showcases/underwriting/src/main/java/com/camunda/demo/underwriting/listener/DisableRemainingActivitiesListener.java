package com.camunda.demo.underwriting.listener;

import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.impl.cmmn.entity.runtime.CaseExecutionEntity;
import org.camunda.bpm.engine.runtime.CaseExecution;

public class DisableRemainingActivitiesListener implements CaseExecutionListener {
  
  private Logger LOG = Logger.getLogger(DisableRemainingActivitiesListener.class.getName());

  @Override
  public void notify(DelegateCaseExecution caseExecution) throws Exception {
    LOG.info(" --- Check activities if we need to disable something ---");

    String caseInstanceId = caseExecution.getCaseInstanceId();
    List<CaseExecution> caseExecutions = caseExecution.getProcessEngineServices().getCaseService().createCaseExecutionQuery().caseInstanceId(caseInstanceId).list();
    for (CaseExecution otherCaseExecution : caseExecutions) {
      if (otherCaseExecution.isEnabled()) {
        caseExecution.getProcessEngineServices().getCaseService().disableCaseExecution(otherCaseExecution.getId());
        LOG.info(otherCaseExecution.getActivityName() + " -> enabled -> disabled");
      } else {
        LOG.info(otherCaseExecution.getActivityName() + " -> " + ((CaseExecutionEntity)otherCaseExecution).getCurrentState().toString());
      }
    }
  }
}
