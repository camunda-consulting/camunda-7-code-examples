package com.camunda.consulting.jsfSimpleTasklist;

import javax.inject.Inject;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.ProcessApplicationService;
import org.camunda.bpm.application.ProcessApplicationInfo;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RepositoryService;

public class ProcessApplicationBean {

  @Inject
  private ManagementService managementService;
  @Inject
  private RepositoryService repositoryService;

  public ProcessApplicationBean() {
    super();
  }

  public String getApplicationPath(String processDefinitionId) {
    String deploymentId = repositoryService.getProcessDefinition(processDefinitionId).getDeploymentId();
  
    // get the name of the process application that made the deployment
    String processApplicationName = managementService.getProcessApplicationForDeployment(deploymentId);
  
    if (processApplicationName == null) {
      // no a process application deployment
      return null;
    } else {
      ProcessApplicationService processApplicationService = BpmPlatform.getProcessApplicationService();
      ProcessApplicationInfo processApplicationInfo = processApplicationService.getProcessApplicationInfo(processApplicationName);
      return processApplicationInfo.getProperties().get(ProcessApplicationInfo.PROP_SERVLET_CONTEXT_PATH);
    }
  }

}