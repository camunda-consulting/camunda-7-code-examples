package com.camunda.consulting;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Stateless
@Named(value = "sampleUserService")
public class SampleUserService {
    private static final Logger log = LoggerFactory.getLogger(SampleUserService.class);

    public void findUserByName(DelegateExecution delegateExecution, String executionStep) {
        // Get all process variables
        log.info("Executing findUserByName called by service task by Step: " + executionStep);
        Map<String, Object> variables = delegateExecution.getVariables();
        String name = variables.get("user").toString();
        if (name == null) {
            log.error("user does not exists create new one");
            delegateExecution.setVariable("userId", "test");
        } else {
            log.info("Executing without any problems for user {}", name);

            delegateExecution.setVariable("userId", 1);
        }
    }
}
