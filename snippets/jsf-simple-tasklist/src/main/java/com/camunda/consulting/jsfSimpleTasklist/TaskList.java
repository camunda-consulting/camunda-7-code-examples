package com.camunda.consulting.jsfSimpleTasklist;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;

@SessionScoped
@Named
public class TaskList extends ProcessApplicationBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private TaskService taskService;

  @Inject
  private FormService formService;
  
  private String currentUser = null;
  
  private String assignee = null;

  public void update() {
    // do nothing here, since a refresh trigger a reload of the list anyway
  }

  public List<Task> getList() {
    if (assignee != null && assignee.length() > 0) {
      return taskService.createTaskQuery().taskAssignee(assignee).initializeFormKeys().list();
    } else {
      return taskService.createTaskQuery().initializeFormKeys().list();
    }
  }

  public void unclaim(Task task) {
    taskService.claim(task.getId(), null);
  }

  public void claim(Task task) {
    taskService.claim(task.getId(), currentUser);
  }

  public String getFormKey(Task task) {
    TaskFormData taskFormData = formService.getTaskFormData(task.getId());
    if (taskFormData!=null) {
      return taskFormData.getFormKey();
    }
    else {
      // we do not want to fail just because we have tasks in the task list without form data (typically manually created tasks)
      return null;
    }
  }
  
  public String getAbsoluteFormKey(Task task) {
    String formkey = getFormKey(task);
    if (formkey.startsWith("app:")) {
      String applicationPath = getApplicationPath(task.getProcessDefinitionId());
      return applicationPath + "/" + formkey.substring(4);
    } else {
      return formkey;
    }
  }
  
  public String getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(String currentUser) {
    this.currentUser = currentUser;
  }

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

}
