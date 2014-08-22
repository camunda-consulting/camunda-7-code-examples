package com.camunda.consulting.changeJobRetry;

import java.util.logging.Logger;

import org.camunda.bpm.engine.OptimisticLockingException;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * @author Ingo Richtsmeier
 *
 */
public class RepairableDelegate implements JavaDelegate {
  
  private final static Logger log = Logger.getLogger(RepairableDelegate.class.getName());

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String fail = (String) execution.getVariable("fail");
    if (fail == null) {
      fail = "true";
    }
    log.info("execute Repairable Service Task with fail = " + fail);
    if ("true".equals(fail)) {
      throw new ProcessEngineException("Repairable Service Task should fail");
    } else if ("optimisticLockingException".equals(fail)) {
      throw new OptimisticLockingException("Service Task throws OptimisticLockingException");
    }
  }

}
