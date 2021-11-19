package com.camunda.consulting;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Named("TaskTwoDelegate")
public class TaskTwoDelegate implements JavaDelegate {
  private static final Logger LOG = LoggerFactory.getLogger(TaskTwoDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    LOG.info("Task Two is executed");
  }
}
