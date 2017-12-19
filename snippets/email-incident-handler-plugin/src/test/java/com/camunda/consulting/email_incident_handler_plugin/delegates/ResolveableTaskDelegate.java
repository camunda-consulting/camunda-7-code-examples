package com.camunda.consulting.email_incident_handler_plugin.delegates;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResolveableTaskDelegate implements JavaDelegate {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(ResolveableTaskDelegate.class);
  
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    LOGGER.info("execute resolveable Logger");
    Boolean shouldFail = (Boolean) execution.getVariable("shouldFail");
    if (shouldFail == true) {
      throw new ProcessEngineException("my custom error");
    }
    LOGGER.info("resolvable logger finished");
  }

}
