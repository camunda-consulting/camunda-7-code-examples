package com.camunda.consulting;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class KeycloakCamundaApplication {

  public static void main(String... args) {
    SpringApplication.run(KeycloakCamundaApplication.class, args);
  }

}
