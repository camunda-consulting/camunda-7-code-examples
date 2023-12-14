package com.camunda.consulting;

import java.util.Map;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleTaskHandler {
  
  private static final Logger log = LoggerFactory.getLogger(ExampleTaskHandler.class);

  @Bean
  @ExternalTaskSubscription(topicName = "showingExample")
  public ExternalTaskHandler exampleHandler () {
    return (externalTask, externalTaskService) -> {
      log.info("handling and completing external task {}", externalTask.getId());
      String secondTaskVariable = externalTask.getVariable("secondTaskVariable");
      log.info("Second task variable: {}", secondTaskVariable);
      externalTaskService.complete(externalTask, Map.of("randomValue", Math.random()));
    };
  }

}
