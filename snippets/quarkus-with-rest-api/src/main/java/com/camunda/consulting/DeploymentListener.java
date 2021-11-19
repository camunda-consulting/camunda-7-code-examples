package com.camunda.consulting;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.quarkus.engine.extension.event.CamundaEngineStartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class DeploymentListener {
  private static final Logger LOG = LoggerFactory.getLogger(DeploymentListener.class);

  private final RepositoryService repositoryService;

  @Inject
  public DeploymentListener(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  public void onProcessEngineCreated(@Observes CamundaEngineStartupEvent event) {
    LOG.info("Engine is started, deploying processes...");
    this.repositoryService
      .createDeployment()
      .addClasspathResource("example-process.bpmn")
      .name("Quarkus Deployment")
      .enableDuplicateFiltering(true)
      .deploy();
  }
}
