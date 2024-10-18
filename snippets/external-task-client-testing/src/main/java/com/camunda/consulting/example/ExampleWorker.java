package com.camunda.consulting.example;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!unitTest")
@ExternalTaskSubscription(topicName = "exampleWork")
public class ExampleWorker implements ExternalTaskHandler {
  private static final Logger LOG = LoggerFactory.getLogger(ExampleWorker.class);

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    LOG.info("Executing exampleWork");
    externalTaskService.complete(externalTask);
  }
}
