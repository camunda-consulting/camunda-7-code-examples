package org.camunda.demo.liferay.tasklist;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.PortletSession;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.task.Task;


/**
 * This class is a request-scoped JSF managed-bean that has
 * a getter and setter that serves as a layer of abstraction
 * over PortletSession.APPLICATION_SCOPE
 */
@Named("selectedTask")
//@ApplicationScoped
public class SelectedTaskBean {

  public static final String SELECTED_TASK_KEY = "camunda.selected.task";
  
  @Inject
  private Conversation conversation;
  
  @Inject
  private BusinessProcess businessProcess;

  
  public void startTask() {
//    conversation.begin();
//    if (getTask()==null || getTask().getId()==null) {
//      return;
//    }
//    if (businessProcess.isTaskAssociated()) {
////      businessProcess.setTaskId(taskId)(getTask().getId());
//      businessProcess.startTask(getTask().getId());
//    }
//    else {
//    // start conversation and associate
////    conversation.begin();
//      businessProcess.startTask(getTask().getId());
//    }
  }

  public Task getTask() {
    return (Task) getSharedSessionAttribute(SELECTED_TASK_KEY);
  }

  public void setTask(Task task) {
    setSharedSessionAttribute(SELECTED_TASK_KEY, task);
  }
  
  public static Object getSharedSessionAttribute(String key) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    PortletSession portletSession = (PortletSession) externalContext.getSession(false);
    return portletSession.getAttribute(key, PortletSession.APPLICATION_SCOPE);
  }

  public static void setSharedSessionAttribute(String key, Object value) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    PortletSession portletSession = (PortletSession) externalContext.getSession(false);
    portletSession.setAttribute(key, value, PortletSession.APPLICATION_SCOPE);
  }
}

