package com.camunda.consulting.springexample.config;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camunda.consulting.springexample.CalculateInterestService;
import com.camunda.consulting.springexample.Starter;

@Configuration
public class CamundaSpringConfig {
  
  private static final Logger log = Logger.getLogger(CamundaSpringConfig.class.getName());
  
  @Bean
  public Starter getStarter() throws Exception {
    log.info("get Starter Bean");
    return new Starter();
  }
  
  @Bean
  public CalculateInterestService calculateInterestService() {
    log.info("get Calculate Interest Bean");
    return new CalculateInterestService();
  }

}
