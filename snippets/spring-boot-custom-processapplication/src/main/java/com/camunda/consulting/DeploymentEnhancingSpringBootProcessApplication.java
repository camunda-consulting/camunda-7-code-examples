package com.camunda.consulting;

import lombok.extern.slf4j.*;
import org.camunda.bpm.engine.impl.cfg.*;
import org.camunda.bpm.engine.impl.persistence.entity.*;
import org.camunda.bpm.engine.impl.repository.*;
import org.camunda.bpm.engine.repository.*;
import org.camunda.bpm.model.bpmn.*;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.spring.boot.starter.*;
import org.camunda.bpm.spring.boot.starter.configuration.*;
import org.springframework.context.annotation.*;

import java.io.*;
import java.util.*;
import java.util.function.*;

import static org.camunda.bpm.spring.boot.starter.util.SpringBootProcessEngineLogger.*;

@Configuration
@Slf4j
public class DeploymentEnhancingSpringBootProcessApplication extends SpringBootProcessApplication {



  @Bean
  public static CamundaDeploymentConfiguration deploymentConfiguration() {
    return new CamundaDeploymentConfiguration() {
      @Override
      public Set<org.springframework.core.io.Resource> getDeploymentResources() {
        return Collections.emptySet();
      }

      @Override
      public void preInit(ProcessEngineConfigurationImpl configuration) {
        LOG.skipAutoDeployment();
      }

      @Override
      public String toString() {
        return "disableDeploymentResourcePattern";
      }
    };
  }

  @Override
  public void createDeployment(
      String processArchiveName, DeploymentBuilder deploymentBuilder
  ) {
    log.info("Here, we can enhance the deployment before it even touches the engine: {}", processArchiveName);
    ProcessApplicationDeploymentBuilderImpl deploymentBuilderImpl = (ProcessApplicationDeploymentBuilderImpl) deploymentBuilder;
    deploymentBuilderImpl
        .getDeployment()
        .getResources()
        .values()
        .stream()
        .filter(isBpmnModel())
        .forEach(resourceEntity -> {
          log.info("Enhancing {}", resourceEntity);
          BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(resourceEntity.getBytes()));
          handleBpmnModelInstance(modelInstance);
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          Bpmn.writeModelToStream(outputStream, modelInstance);
          resourceEntity.setBytes(outputStream.toByteArray());
        });
  }

  private Predicate<ResourceEntity> isBpmnModel() {
    return resourceEntity -> resourceEntity
        .getName()
        .endsWith(".bpmn");
  }

  private void handleBpmnModelInstance(BpmnModelInstance modelInstance) {
    // modifications on the given BpmnModelInstance object here
    Collection<StartEvent> startEvents = modelInstance.getModelElementsByType(StartEvent.class);
    startEvents.forEach(startEvent -> startEvent.setName("I changed this name programatically"));
  }
}
