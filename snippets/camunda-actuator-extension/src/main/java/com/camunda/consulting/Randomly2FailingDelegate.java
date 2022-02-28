package com.camunda.consulting;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Randomly2FailingDelegate implements JavaDelegate {
  
  private static final Logger LOG = LoggerFactory.getLogger(Randomly2FailingDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    double random = (double) execution.getVariable("randomTwo");
    double randomWaitTime = Math.random();
    long sleepTime = (long)(randomWaitTime * 40000);
    LOG.info("Task takes {} ms", sleepTime);
    Thread.sleep(sleepTime);
    if (random > 0.6 && random < 1) {
      throw new RuntimeException("Number higher than 0.6");
    } else {
      LOG.info("Task completed");
    }

  }

}
