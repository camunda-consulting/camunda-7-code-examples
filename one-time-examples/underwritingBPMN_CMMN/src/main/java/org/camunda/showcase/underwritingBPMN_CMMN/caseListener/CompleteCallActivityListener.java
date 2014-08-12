package org.camunda.showcase.underwritingBPMN_CMMN.caseListener;

import static org.assertj.core.api.Assertions.fail;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.showcase.engine.impl.bpmn.behavior.CmmnCallActivityBehavior;

public class CompleteCallActivityListener implements CaseExecutionListener {

  @Override
  public void notify(DelegateCaseExecution caseExecution) throws Exception {
    String callActivityExecutionId =  (String) caseExecution.getVariable("callActivityId");

    if (callActivityExecutionId != null) {
      ExecutionEntity callExecution = (ExecutionEntity) Context
          .getProcessEngineConfiguration()
          .getRuntimeService()
          .createExecutionQuery()
          .executionId(callActivityExecutionId)
          .singleResult();
      CmmnCallActivityBehavior callActivityBehavior = (CmmnCallActivityBehavior) callExecution.getActivity().getActivityBehavior();
      try {
        callActivityBehavior.completed(callExecution);
      } catch (Exception e) {
        fail(e.getMessage());
      }
    }
  }
}
