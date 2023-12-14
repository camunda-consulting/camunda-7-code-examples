package com.camunda.consulting;

import java.util.List;

import org.camunda.bpm.client.backoff.BackoffStrategy;
import org.camunda.bpm.client.task.ExternalTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalTaskClientConfiguration implements BackoffStrategy {

  private static final Logger LOG = LoggerFactory.getLogger(ExternalTaskClientConfiguration.class);

  @Override
  public void reconfigure(List<ExternalTask> externalTasks) {
    LOG.info("fixed backoff");
  }

  @Override
  public long calculateBackoffTime() {
    return 500;
  }
  
}
