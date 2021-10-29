package com.camunda.consulting;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FailingDelegate implements JavaDelegate {
  private static final Logger LOG = LoggerFactory.getLogger(FailingDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    LOG.debug("I am running!");
    throw new Exception();

  }

}
