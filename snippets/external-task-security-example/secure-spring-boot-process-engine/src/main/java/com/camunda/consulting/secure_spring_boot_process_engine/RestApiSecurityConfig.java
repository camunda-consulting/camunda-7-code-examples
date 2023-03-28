package com.camunda.consulting.secure_spring_boot_process_engine;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestApiSecurityConfig {
  
  @Bean
  public FilterRegistrationBean<ProcessEngineAuthenticationFilter> processEngineAuthenticationFilter() {
    FilterRegistrationBean<ProcessEngineAuthenticationFilter> registration = new FilterRegistrationBean<>();
    registration.setName("camunda-auth");
    registration.setFilter(this.getProcessEngineAuthenticationFilter());
    registration
      .addInitParameter("authentication-provider", HttpBasicAuthenticationProvider.class.getName());
    registration.addUrlPatterns("/engine-rest/*");
    return registration;
  }

  @Bean
  public ProcessEngineAuthenticationFilter getProcessEngineAuthenticationFilter() {
    return new ProcessEngineAuthenticationFilter();
  }
}
