package com.camunda.consulting.springexample;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculateInterestService implements JavaDelegate {
  
  private static final Logger log = Logger.getLogger(CalculateInterestService.class.getName());

  public void execute(DelegateExecution execution) throws Exception {
    log.info("Spring Bean invoked.");
    log.info("next line");
  }

}
