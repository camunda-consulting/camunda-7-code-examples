package org.camunda.demo.liferay.tasklist;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.portlet.ActionResponse;
import javax.xml.namespace.QName;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.model.User;

//@Named("tasklist")
@ManagedBean(name = "tasklist")
@ViewScoped
public class TasklistBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private TaskService taskService;

  public String getHtml() {
    return "tasklist.xhtml";
  }

  public String getInfo() {
    User user = LiferayFacesContext.getInstance().getUser();
    return "User: " + user + "; task count: " + taskService.createTaskQuery().count();
  }

  public List<Task> getPersonalTasks() {

    User user = LiferayFacesContext.getInstance().getUser();

    // UserLocalServiceUtil.
    System.out.println("#### get personal tasks for user " + user);

    return taskService.createTaskQuery().list();
  }

  public void selectTask(Task task) {
    QName qName = new QName("http://camunda.org/events", "ipc.taskSelected");
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    ActionResponse actionResponse = (ActionResponse) externalContext.getResponse();
    actionResponse.setEvent(qName, task.getId());
  }

}
