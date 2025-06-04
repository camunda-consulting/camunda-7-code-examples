package com.camunda.consulting.service;

import com.camunda.consulting.model.SampleUserEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Stateless
@Named(value = "sampleUserService")
public class SampleUserService {
    private static final Logger log = LoggerFactory.getLogger(SampleUserService.class);

    public List<String> getAllUsers() {
        return Arrays.asList("Name1", "Name2");
    }

    public void findUserByName(DelegateExecution delegateExecution, String executionStep) {
        // Get all process variables
        log.info("Executing findUserByName called by service task by Step: " + executionStep);
        Map<String, Object> variables = delegateExecution.getVariables();
        String name = variables.get("user").toString();
        if (name == null) {
            var userEntity = new SampleUserEntity();
            log.error("user does not exists create new one");
            userEntity.setName("John Doe");
            delegateExecution.setVariable("userId", userEntity.getId());
        } else {
            log.info("Executing without any problems for user {}", name);

            delegateExecution.setVariable("userId", 1);
        }
    }
}
