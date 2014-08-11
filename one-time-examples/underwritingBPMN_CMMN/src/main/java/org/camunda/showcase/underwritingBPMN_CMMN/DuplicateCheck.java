package org.camunda.showcase.underwritingBPMN_CMMN;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DuplicateCheck implements JavaDelegate {
  
  private static final Logger log = Logger.getLogger(DuplicateCheck.class.getName());  

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.info("duplicate check");
    if (execution.hasVariable("applier")) {
      String applier = (String) execution.getVariable("applier");
      if ("john duplicate".equals(applier)) {
        log.info("duplicate application, throw error");
        throw new BpmnError("duplicateError");
      }
    }
  }

}
