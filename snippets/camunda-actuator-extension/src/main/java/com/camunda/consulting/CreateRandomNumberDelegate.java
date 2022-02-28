package com.camunda.consulting;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateRandomNumberDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(CreateRandomNumberDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    double random = Math.random();
    LOG.info("random number: {}", random);
    execution.setVariable("random", random);
    execution.setProcessBusinessKey("" + random);
  }

}
