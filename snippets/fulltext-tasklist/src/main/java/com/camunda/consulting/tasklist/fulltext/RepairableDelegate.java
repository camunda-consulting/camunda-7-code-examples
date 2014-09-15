package com.camunda.consulting.tasklist.fulltext;

import java.util.logging.Logger;

import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named
public class RepairableDelegate implements JavaDelegate {
  
  private static final Logger log = Logger.getLogger(RepairableDelegate.class.getName());

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String continue_ = (String) execution.getVariable("continue");
    if (continue_ != null && "continue".equals(continue_)) {
      log.info("failing service should continue");
    } else {
      throw new ProcessEngineException("this Service Call should provoke an incident");
    }
  }
}
