package org.camunda.bpm.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Bean
  public JavaDelegate loggerDelegate(){
    return new JavaDelegate() {
      @Override
      public void execute(DelegateExecution execution) throws Exception {
        Thread.sleep(10000);
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("Hey I've executed the delegate");
      }
    };

  }

}