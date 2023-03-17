package com.camunda.consulting.externalTask.ejb;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;

@ExternalTaskSubscription(topicName = "example")
@ApplicationScoped
public class ExampleExternalTaskHandler implements ExternalTaskHandler {
  private static final Logger LOG = LoggerFactory.getLogger(ExampleExternalTaskHandler.class);

  @Resource(name = "DefaultManagedExecutorService")
  ManagedExecutorService executorService;

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    executorService.execute(
        () -> {
          LOG.info("Handling task {}", externalTask);
          externalTaskService.complete(externalTask);
        });
  }
}
