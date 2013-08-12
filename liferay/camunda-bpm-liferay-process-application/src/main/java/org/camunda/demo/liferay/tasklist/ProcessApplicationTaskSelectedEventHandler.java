package org.camunda.demo.liferay.tasklist;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.portlet.Event;
import javax.portlet.PortletSession;
import javax.portlet.faces.event.EventNavigationResult;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.ProcessApplicationService;
import org.camunda.bpm.ProcessEngineService;
import org.camunda.bpm.application.ProcessApplicationDeploymentInfo;
import org.camunda.bpm.application.ProcessApplicationInfo;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.task.Task;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

public class ProcessApplicationTaskSelectedEventHandler extends CdiEnabledBridgeEventHandler {

  @Inject
  private TaskService taskService;

  @Inject
  private ProcessEngine processEngine;

  protected EventNavigationResult handleEventWithCdi(FacesContext facesContext, Event event) {
    EventNavigationResult eventNavigationResult = null;
    String eventQName = event.getQName().toString();

    if (eventQName.equals("{http://camunda.org/events}ipc.taskSelected")) {
      
      // check if the task is part of this PA - otherwise ignore!
      
      String taskId = (String) event.getValue();

      Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
      if (task == null) {
        return null;
      }
      
      // set to backing bean
      new SelectedTaskBean().setTask(task);      

    }
    return eventNavigationResult;

  }
  

}
