package com.camunda.consulting;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@ExtendWith(ProcessEngineExtension.class)
public class ProcessEngineTest {

  @AfterEach
  void cleanUp() {
    identityService().clearAuthentication();
  }

  @Test
  void shouldDeployWithoutAuthentication() {
    Deployment deployment =
        repositoryService()
            .createDeployment()
            .name("test")
            .addClasspathResource("test.bpmn")
            .deploy();
    assertThat(deployment).isNotNull();
  }

  @Test
  void shouldNotDeployWithAuthenticationForWrongTenant() {
    identityService().setAuthentication("testUser", List.of(), List.of("tenant1"));
    assertThatThrownBy(
            () ->
                repositoryService()
                    .createDeployment()
                    .tenantId("tenant2")
                    .name("testDeployment")
                    .addClasspathResource("test.bpmn")
                    .deploy())
        .isInstanceOf(ProcessEngineException.class)
        .message()
        .isEqualTo("You don't have access to tenant 'tenant2'.");
  }

  @Test
  void shouldDeployWithAuthenticationForRightTenant() {
    identityService().setAuthentication("testUser", List.of(), List.of("tenant1"));
    Deployment deployment =
        repositoryService()
            .createDeployment()
            .tenantId("tenant1")
            .name("test")
            .addClasspathResource("test.bpmn")
            .deploy();
    assertThat(deployment).isNotNull();
  }
}
