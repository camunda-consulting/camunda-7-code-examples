package com.camunda.consulting.system1;

import java.util.Map;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("system1")
public class System1Handler implements ExternalTaskHandler {
   
  private static final Logger LOG = LoggerFactory.getLogger(System1Handler.class);

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    LOG.info("Handling System 1");
    LOG.info("process variables: {}", externalTask.getAllVariables());
    externalTaskService.complete(externalTask, Map.of("system1Random", Math.random()));
  }

}
