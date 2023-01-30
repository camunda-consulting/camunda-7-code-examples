package com.camunda.consulting.externalTask.ejb;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ExternalTaskSubscription(topicName = "example")
@ApplicationScoped
public class ExampleExternalTaskHandler implements ExternalTaskHandler {
  private static final Logger LOG = LoggerFactory.getLogger(ExampleExternalTaskHandler.class);

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    LOG.info("Handling task {}", externalTask);
    externalTaskService.complete(externalTask);
  }
}
