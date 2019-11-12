package org.camunda;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication("migration-case-1")
public class CamundaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CamundaApplication.class.getName());

    public static void main(String... args) {
        SpringApplication.run(CamundaApplication.class, args);
    }

}
