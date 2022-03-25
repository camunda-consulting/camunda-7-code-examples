package com.camunda.consulting;

import org.camunda.bpm.spring.boot.starter.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.*;

@SpringBootApplication
@Import(DeploymentEnhancingSpringBootProcessApplication.class)
public class CamundaApplication {
  public static void main(String... args) {
    SpringApplication.run(CamundaApplication.class, args);
  }
}
