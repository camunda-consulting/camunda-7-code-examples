package com.camunda.consulting;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("exampleDelegate")
public class ExampleDelegate implements JavaDelegate {
  private static final Logger LOG = LoggerFactory.getLogger(ExampleDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    LOG.info("I was invoked, yaydiday!");

  }

}
