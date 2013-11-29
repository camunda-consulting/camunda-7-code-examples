package org.camunda.bpm.demo.invoice;

import java.util.Map;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;

import org.camunda.bpm.engine.cdi.jsf.TaskForm;

@Specializes
@ConversationScoped
public class InvoiceTaskForm extends TaskForm {

  private static final long serialVersionUID = 1L;

  public void startTask() {
    Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String taskId = requestParameterMap.get("taskId");
    String callbackUrl = requestParameterMap.get("callbackUrl");
    super.startTask(taskId, callbackUrl);
  }

  public void startProcessInstanceByKeyForm() {
    Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String processDefinitionKey = requestParameterMap.get("processDefinitionKey");
    String callbackUrl = requestParameterMap.get("callbackUrl");
    super.startProcessInstanceByKeyForm(processDefinitionKey, callbackUrl);
  }

}