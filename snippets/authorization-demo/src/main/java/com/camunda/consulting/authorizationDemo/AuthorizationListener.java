package com.camunda.consulting.authorizationDemo;

import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.model.bpmn.instance.UserTask;

public class AuthorizationListener implements TaskListener {
  
  private static final Logger log = Logger.getLogger(AuthorizationListener.class.getName());

  @Override
  public void notify(DelegateTask delegateTask) {
    log.info("entered with " + delegateTask.getEventName());

    Authentication currentAuthentication = delegateTask.getProcessEngineServices().getIdentityService().getCurrentAuthentication();
    if (currentAuthentication != null) {
      log.info("user: " + currentAuthentication.getUserId() + ", groups: " + currentAuthentication.getGroupIds());
    } else {
      log.info("No authentication here !!!");
    }
    
    ExecutionEntity execEntity = (ExecutionEntity) delegateTask.getExecution();
    ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) execEntity.getProcessDefinition();
    String processDefinitionKey = processDefinition.getKey();
    
    log.info(processDefinitionKey + " " + delegateTask.getProcessDefinitionId() + " " + delegateTask.getTaskDefinitionKey());
    
    UserTask userTask = (UserTask) delegateTask.getBpmnModelElementInstance();
    String candidateGroup = userTask.getCamundaCandidateGroups();
    
    Resource resource = AuthorizationResources.resources.get(processDefinitionKey + "-" + candidateGroup);
    
    if (currentAuthentication != null 
        && delegateTask.getProcessEngineServices().getAuthorizationService().isUserAuthorized(
        currentAuthentication.getUserId(), 
        currentAuthentication.getGroupIds(), 
        Permissions.ACCESS, 
        resource, 
        processDefinitionKey + "-" + candidateGroup)) {
      log.info("User is authenticated");
    } else {
      String currentUserId = currentAuthentication != null ? currentAuthentication.getUserId() : "unknown";
      throw new ProcessEngineException(
          "User " + 
              currentUserId + 
              " is not allowed to complete task " + 
              delegateTask.getName() +
              " (" + delegateTask.getTaskDefinitionKey() + ")" +
              " of process definition " + 
              processDefinitionKey);
    }
    
    log.info("left from " + delegateTask.getEventName());
  }

}
