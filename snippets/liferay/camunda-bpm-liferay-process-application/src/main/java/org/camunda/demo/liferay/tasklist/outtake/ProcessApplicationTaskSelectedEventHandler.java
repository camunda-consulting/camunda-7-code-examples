package org.camunda.demo.liferay.tasklist.outtake;

import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.portlet.Event;
import javax.portlet.faces.event.EventNavigationResult;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.task.Task;

public class ProcessApplicationTaskSelectedEventHandler extends CdiEnabledBridgeEventHandler {

  @Inject
  private TaskService taskService;

  @Inject
  private ProcessEngine processEngine;
  
  @Inject
  private BusinessProcess businessProcess;
  
  @Inject
  private Conversation conversation;
  
  protected EventNavigationResult handleEventWithCdi(FacesContext facesContext, Event event) {
    EventNavigationResult eventNavigationResult = null;
    String eventQName = event.getQName().toString();

    if (eventQName.equals("{http://camunda.org/events}ipc.taskSelected")) {     
      // TODO: check if the task is part of this PA - otherwise ignore!      
      String taskId = (String) event.getValue();
      
      Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
      if (task == null) {
        return null;
      }
      
      // set to backing bean (via PortletContext - we do not have any other scope available here at this moment)
//      new SelectedTaskBean().setTask(task);
      
//      conversation.begin();
//      businessProcess.startTask(taskId);
      
    }
    return eventNavigationResult;

  }
  

}
