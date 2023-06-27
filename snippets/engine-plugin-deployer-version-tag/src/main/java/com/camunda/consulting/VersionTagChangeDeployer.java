package com.camunda.consulting;

import org.camunda.bpm.engine.impl.persistence.deploy.Deployer;
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class VersionTagChangeDeployer implements Deployer {

  @Override
  public void deploy(DeploymentEntity deployment) {
      deployment
          .getResources()
          .entrySet()
          .stream().filter(e -> e.getKey().endsWith(".bpmn"))
          .map(Map.Entry::getValue)
          .forEach(resource -> {
              BpmnModelInstance modelInstance = Bpmn.readModelFromStream(new ByteArrayInputStream(resource.getBytes()));
              modelInstance.getModelElementsByType(Process.class).forEach(process -> process.setCamundaVersionTag("my-custom-tag"));
              resource.setBytes(Bpmn.convertToString(modelInstance).getBytes());
          });
  }
}
