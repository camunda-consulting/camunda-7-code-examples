package com.camunda.consulting.snippet.springboot_config_batch;

import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.camunda.bpm.spring.boot.starter.configuration.impl.AbstractCamundaConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordering.DEFAULT_ORDER + 1)
public class CamundaConfiguration extends AbstractCamundaConfiguration {

  @Override
  public void preInit(SpringProcessEngineConfiguration processEngineConfiguration) {
    super.preInit(processEngineConfiguration);
    processEngineConfiguration.setInvocationsPerBatchJob(100);
  }
}
