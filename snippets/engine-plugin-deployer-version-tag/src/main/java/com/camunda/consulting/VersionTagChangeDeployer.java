package com.camunda.consulting;

import org.camunda.bpm.engine.impl.persistence.deploy.Deployer;
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class VersionTagChangeDeployer implements Deployer {

  private static Logger log = LoggerFactory.getLogger(VersionTagChangeDeployer.class);

  @Override
  public void deploy(DeploymentEntity deployment) {
    deployment
      .getResources()
      .entrySet()
      .stream()
      .filter(e -> e.getKey().endsWith(".bpmn"))
      .peek(e -> log.info("About to change version tag for file {}", e.getKey()))
      .map(Map.Entry::getValue)
      .forEach(resource -> {
        BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(resource.getBytes()));
        modelInstance.getModelElementsByType(Process.class).forEach(process -> process.setCamundaVersionTag("my-custom-tag"));
        resource.setBytes(Bpmn.convertToString(modelInstance).getBytes());
    });
  }
}
