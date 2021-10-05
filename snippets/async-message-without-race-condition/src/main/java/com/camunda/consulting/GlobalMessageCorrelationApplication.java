package com.camunda.consulting;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.camunda.consulting.simulator.SimulationExecutor;
import com.camunda.consulting.simulator.SimulatorPlugin;

@SpringBootApplication
@EnableProcessApplication
public class GlobalMessageCorrelationApplication {
  public static void main(String[] args) {
    SpringApplication.run(GlobalMessageCorrelationApplication.class, args);
    SimulationExecutor.execute(DateTime.now().minusMonths(1).toDate(), DateTime.now().toDate());
  }

  @Bean
  public SimulatorPlugin simulatorPlugin() {
    return new SimulatorPlugin();
  }
}
