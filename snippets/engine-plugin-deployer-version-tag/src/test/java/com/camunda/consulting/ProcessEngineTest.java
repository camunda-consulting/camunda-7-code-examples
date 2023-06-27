package com.camunda.consulting;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.persistence.entity.ResourceEntity;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.Resource;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@ExtendWith(ProcessEngineExtension.class)
public class ProcessEngineTest {

  @Test
  void shouldHaveChangedVersionTag() {
    Deployment deployment =
      repositoryService()
        .createDeployment()
        .name("test")
        .addClasspathResource("test.bpmn")
        .deploy();
    assertThat(deployment).isNotNull();

    List<Resource> resourceEntities = repositoryService().getDeploymentResources(deployment.getId());
    BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(resourceEntities.get(0).getBytes()));
    modelInstance.getModelElementsByType(Process.class).forEach(process -> assertThat(process.getCamundaVersionTag()).isEqualTo("my-custom-tag"));
  }

}
