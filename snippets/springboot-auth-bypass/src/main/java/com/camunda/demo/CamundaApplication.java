package com.camunda.demo;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

@SpringBootApplication
@EnableProcessApplication("springboot-auth-bypass")
public class CamundaApplication {

  public static void main(String... args) {
    SpringApplication.run(CamundaApplication.class, args);
  }

  // REST API
  @Bean
  public FilterRegistrationBean basicAuthForRestApiFilter() {

    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new ProcessEngineAuthenticationFilter());
    registration.addUrlPatterns("/rest/*");
    registration.addInitParameter("authentication-provider", "com.camunda.demo.CustomHttpBasicAuthenticationProvider");
    registration.setName("camunda-rest-auth");
    return registration;

  }

  // Webapps API
  @Bean
  public FilterRegistrationBean webAppAuthFilter() {

    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new CustomWebAppAuthenticationFilter());
    registration.addUrlPatterns("/api/admin/auth/user/default/login/*");
    registration.setName("camunda-webapp-auth");
    return registration;

  }


}
