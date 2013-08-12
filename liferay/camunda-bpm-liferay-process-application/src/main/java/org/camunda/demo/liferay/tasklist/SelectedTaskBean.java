package org.camunda.demo.liferay.tasklist;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import org.camunda.bpm.engine.task.Task;


/**
 * This class is a request-scoped JSF managed-bean that has
 * a getter and setter that serves as a layer of abstraction
 * over PortletSession.APPLICATION_SCOPE
 */
@ManagedBean(name="selectedTask")
@RequestScoped
public class SelectedTaskBean {

  public static final String SELECTED_TASK_KEY = "camunda.selected.task";

  public Task getTask() {
    return (Task) PortletSessionUtil.getSharedSessionAttribute(SELECTED_TASK_KEY);
  }

  public void setTask(Task task) {
    PortletSessionUtil.setSharedSessionAttribute(SELECTED_TASK_KEY, task);
  }
}

