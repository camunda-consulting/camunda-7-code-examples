package com.camunda.consulting.secure_optimize.secure_engine;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class Application {
  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }
}
