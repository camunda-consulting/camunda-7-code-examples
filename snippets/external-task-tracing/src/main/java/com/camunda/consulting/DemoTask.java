package com.camunda.consulting;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ExternalTaskSubscription("demo")
@Component
public class DemoTask implements ExternalTaskHandler {
  private static final Logger LOG = LoggerFactory.getLogger(DemoTask.class);

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    LOG.info("Executing task '{}'", externalTask.getId());
    //externalTaskService.handleFailure(externalTask, ":(", "Something bad", 0, 0);
    externalTaskService.complete(externalTask);
  }
}
