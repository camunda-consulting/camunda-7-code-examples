package org.camunda.bpm.demo.orderconfirmation.bean;

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
public class TaskList implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private TaskService taskService;

  @Inject
  private FormService formService;

  private String currentUser = null;

  public void update() {
    // do nothing here, since a refreh trigger a reload of the list anyway
  }

  public List<Task> getList() {
    if (currentUser != null && currentUser.length() > 0) {
      return taskService.createTaskQuery().taskAssignee(currentUser).list();
    } else {
      return taskService.createTaskQuery().list();
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

  public String getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(String currentUser) {
    this.currentUser = currentUser;
  }

}
