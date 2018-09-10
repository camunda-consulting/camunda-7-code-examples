package com.camunda.demo;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableProcessApplication("springboot-rest-api-basic-auth")
public class CamundaApplication {
  public static void main(String... args) {
    SpringApplication.run(CamundaApplication.class, args);
  }

  @Bean
  public FilterRegistrationBean myFilterRegistration() {

    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new ProcessEngineAuthenticationFilter());
    registration.addUrlPatterns("/rest/*");
    registration.addInitParameter("authentication-provider", "org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
    registration.setName("camunda-auth");
    registration.setOrder(1);
    return registration;

  }

}
