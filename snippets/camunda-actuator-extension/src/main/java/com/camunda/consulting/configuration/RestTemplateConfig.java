package com.camunda.consulting.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  private static final Logger LOG = LoggerFactory.getLogger(RestTemplateConfig.class);

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    LOG.info("Configure RestTemplate");
    // Do any additional configuration here
    RestTemplate localBuilderVar = builder.build();
    
    LOG.info("RestTemplate configured");
    return localBuilderVar;
  }

}
