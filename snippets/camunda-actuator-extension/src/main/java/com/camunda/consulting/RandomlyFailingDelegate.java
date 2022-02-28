package com.camunda.consulting;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RandomlyFailingDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(RandomlyFailingDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    double random = (double) execution.getVariable("random");
    double randomWaitTime = Math.random();
    long sleepTime = (long)(randomWaitTime * 30000);
    LOG.info("Task takes {} ms", sleepTime);
    Thread.sleep(sleepTime);
    if (random > 0.7 && random < 1) {
      throw new RuntimeException("Number higher than 0.7");
    } else {
      double random2 = Math.random();
      LOG.info("second random variable {}", random2);
      execution.setVariable("randomTwo", random2);
      execution.setProcessBusinessKey("" + random2);
    }
  }

}
