package com.camunda.consulting.system2;

import java.util.Map;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("system2")
public class System2Handler implements ExternalTaskHandler {
  
  private static final Logger LOG = LoggerFactory.getLogger(System2Handler.class);

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
    LOG.info("Handling System 2");
    LOG.info("process variables: {}", externalTask.getAllVariables());    
    externalTaskService.complete(externalTask, Map.of("system2Random", Math.random()));
  }

}
