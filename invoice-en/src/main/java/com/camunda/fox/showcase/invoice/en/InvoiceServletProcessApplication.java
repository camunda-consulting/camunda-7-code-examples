package com.camunda.fox.showcase.invoice.en;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.PreUndeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.ProcessApplicationDeploymentInfo;
import org.camunda.bpm.application.ProcessApplicationInfo;
import org.camunda.bpm.application.ProcessApplicationRegistration;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.ProcessDefinition;

@ProcessApplication("invoice-en")
public class InvoiceServletProcessApplication extends ServletProcessApplication {

  protected List<ProcessApplicationRegistration> processApplicationRegistrations = new ArrayList<ProcessApplicationRegistration>();

  @PostDeploy
  public void registerProcessApplication(ProcessEngine processEngine, ProcessApplicationInfo processApplicationInfo) {
      // First: Get the information which deployments are registered for the current process application
      List<ProcessApplicationDeploymentInfo> deploymentInfos = processApplicationInfo.getDeploymentInfo();
      for (ProcessApplicationDeploymentInfo processApplicationDeploymentInfo : deploymentInfos) {

          // Second: Get all processes contained in the current deployments
          List<ProcessDefinition> allProcessesForDeployment = //
          processEngine.getRepositoryService().createProcessDefinitionQuery() //
                  .deploymentId(processApplicationDeploymentInfo.getDeploymentId()) //
                  .list();
          for (ProcessDefinition registeredProcessDefinition : allProcessesForDeployment) {

              // Third: Not get all version of every process
              List<ProcessDefinition> allVersionsOfThisProcessKey = //
              processEngine.getRepositoryService().createProcessDefinitionQuery() //
                      .processDefinitionKey(registeredProcessDefinition.getKey()) //
                      .list();

              for (ProcessDefinition pd : allVersionsOfThisProcessKey) {
                  // Fourth: register the deployment if not already done (for the current one it is already done)
                  if (processEngine.getManagementService().getProcessApplicationForDeployment(pd.getDeploymentId()) == null) {
                      processApplicationRegistrations.add(processEngine.getManagementService()
                              .registerProcessApplication(pd.getDeploymentId(), getReference()));
                  }
              }
          }
      }
  }

  @PreUndeploy
  public void unregisterProcessApplicaiton(ProcessEngine processEngine) {
      for (ProcessApplicationRegistration registration : processApplicationRegistrations) {
          processEngine.getManagementService().unregisterProcessApplication(registration.getDeploymentId(), true);
      }
  }

}
