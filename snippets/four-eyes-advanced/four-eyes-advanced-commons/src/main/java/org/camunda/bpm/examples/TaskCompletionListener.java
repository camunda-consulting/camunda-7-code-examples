package org.camunda.bpm.examples;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.cdi.impl.util.ProgrammaticBeanLookup;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.w3c.dom.Element;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.InputStream;


/**
 * Listener to save user who completed the task as process variable. Could be added via an 
 * {@link BpmnParseListener}, then you even don't have to add it manually to the XML.
 * 
 * @author ruecker
 */
public class TaskCompletionListener implements TaskListener {

  @Override
  public void notify(DelegateTask task) {
    // With the FourEyesExtensionsParseListener we could do something like:
    String groupName =  (String) ((TaskEntity) task).getExecution().getActivity().getProperties().get(Helper.FOUR_EYES_GROUP_NAME);

    // Without we fall back to XML parsing:
    //InputStream processModelInputStream = ProgrammaticBeanLookup.lookup(ProcessEngine.class).getRepositoryService().getProcessModel(task.getProcessDefinitionId());
    //Element fourEyeExtensionElement = Helper.getUserTaskExtensions(processModelInputStream, ((TaskEntity) task).getExecution().getActivityId(), Helper.FOUR_EYES_GROUP_NAME);

    String variableName = Helper.getVariableName(groupName);
    
    // We cannot use a CDI Bean so we do a programmatic lookup from within a normal task listener created by the engine
    AuthenticationService authenticationService = ProgrammaticBeanLookup.lookup(AuthenticationService.class);
    
    String loggedInUser = authenticationService.getLoggedInUser();
    
    // check that this user has not done the last task
    String lastUser = (String) task.getExecution().getVariable(variableName);
    if (lastUser != null && lastUser.equals(loggedInUser)) {
      throw new RuntimeException("Same user has already completed the first task, cannot complete second task, violates 4-eyes-principle");
    }

    if (task.getAssignee() == null || !task.getAssignee().equals(loggedInUser)) {
      // task was not assigned/claimed before completion, fix this.
      task.setAssignee(loggedInUser);
    }    
    
    // and write a process variable for it
    task.getExecution().setVariable(variableName, loggedInUser);
  }

}
