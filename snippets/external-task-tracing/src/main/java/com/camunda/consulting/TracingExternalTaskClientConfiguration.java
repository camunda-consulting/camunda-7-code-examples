package com.camunda.consulting;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class TracingExternalTaskClientConfiguration {

  @Bean
  public ObservationRegistry observationRegistry(){
    ObservationRegistry registry = ObservationRegistry.create();
    registry.observationConfig().observationHandler(new ObservationTextPublisher());
    return registry;
  }

  @Bean
  public ObservationHandler<Observation.Context> myHandler() {
    return new ObservationTextPublisher();
  }
}
