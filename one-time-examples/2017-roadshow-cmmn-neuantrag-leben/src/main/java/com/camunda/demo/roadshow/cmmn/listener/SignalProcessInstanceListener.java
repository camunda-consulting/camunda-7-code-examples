package com.camunda.demo.roadshow.cmmn.listener;

import static org.camunda.bpm.engine.impl.util.ActivityBehaviorUtil.getActivityBehavior;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.impl.ProcessEngineLogger;
import org.camunda.bpm.engine.impl.cmmn.behavior.CmmnActivityBehavior;
import org.camunda.bpm.engine.impl.cmmn.entity.runtime.CaseExecutionEntity;
import org.camunda.bpm.engine.impl.cmmn.execution.CmmnExecution;
import org.camunda.bpm.engine.impl.cmmn.operation.AbstractAtomicOperationCaseExecutionComplete;
import org.camunda.bpm.engine.impl.cmmn.operation.AtomicOperationCaseExecutionComplete;
import org.camunda.bpm.engine.impl.cmmn.operation.CmmnOperationLogger;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.pvm.delegate.SubProcessActivityBehavior;

/**
 * Workaround to advance in a waiting process instance if a case gets in the
 * terminated state.
 * 
 * Attach this listener as {@link CaseExecutionListener} to the terminate event.
 * 
 * Code copied from
 * {@link org.camunda.bpm.engine.impl.cmmn.operation.AbstractAtomicOperationCaseExecutionComplete}.
 * 
 * @author ruecker
 *
 */
public class SignalProcessInstanceListener implements CaseExecutionListener {

  @Override
  public void notify(DelegateCaseExecution ctx) throws Exception {
    new MyOperationCaseTerminate().doWhatIWant((CaseExecutionEntity) ctx);
  }

  public static class MyOperationCaseTerminate extends AbstractAtomicOperationCaseExecutionComplete {

    public void doWhatIWant(CmmnExecution execution) {
      super.postTransitionNotification(execution);
    }

    public String getCanonicalName() {
      return null;
    }

    protected void triggerBehavior(CmmnActivityBehavior behavior, CmmnExecution execution) {
    }

  }

}
