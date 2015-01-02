package com.camunda.demo.webinar.cmmn.listener;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;

import com.camunda.demo.webinar.cmmn.Constants;

public class CompleteCaseAndResumeProcessListener implements CaseExecutionListener {

  @Override
  public void notify(DelegateCaseExecution caseExecution) throws Exception {
    System.out.println(" --- Completing Case ...");
    caseExecution.getProcessEngineServices().getCaseService().completeCaseExecution( caseExecution.getCaseInstanceId() );
//    caseExecution.getProcessEngineServices().getCaseService().closeCaseInstance( caseExecution.getCaseInstanceId() );

    // correlate message if there is a waiting process instance
    if (caseExecution.getProcessEngineServices().getRuntimeService().createExecutionQuery() //
        .messageEventSubscriptionName(Constants.MSG_UNDERWRITING_FINISHED) //
        .processVariableValueEquals(Constants.VAR_NAME_CASE_INSTANCE_ID, caseExecution.getCaseInstanceId())//
        .count()>0) { 
      System.out.println(" ... and sending message to waiting process ---");   
      caseExecution.getProcessEngineServices().getRuntimeService().createMessageCorrelation(Constants.MSG_UNDERWRITING_FINISHED) //
        .processInstanceVariableEquals(Constants.VAR_NAME_CASE_INSTANCE_ID, caseExecution.getCaseInstanceId()) //
        .setVariable("approved", caseExecution.getVariable("approved")) //
        .correlate();
    } else {
      System.out.println(" ... and do nothing as no waiting process instance is found ---");         
    }
  }
}
