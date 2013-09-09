package org.camunda.bpm.example.twitter;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.runtime.ProcessInstance;

@Named
@SessionScoped
public class CreateTweetController implements Serializable {
  private static final long serialVersionUID = 1L;

  private Tweet newTweet = new Tweet();

  @Inject
  @Named("camunda.taskForm")
  private TaskForm taskForm;

  @Inject
  private RuntimeService runtimeService;

  @Inject
  private Instance<Conversation> conversationInstance;

  public void startProcess() throws IOException {
    // set the process variable
    HashMap<String, Object> variables = new HashMap<String, Object>();
    variables.put("tweet", newTweet);
    // and tell the framework that we are done:

    // overwrite behavior of TaskForm to avoid bug https://app.camunda.com/jira/browse/CAM-792:
    ProcessInstance instance = runtimeService.startProcessInstanceById(
            taskForm.getProcessDefinition().getId(), //
            variables);

    // End the conversation
    conversationInstance.get().end();

    resetForm();

    // and redirect
    FacesContext.getCurrentInstance().getExternalContext().redirect(taskForm.getUrl());
  }

  public void resetForm() {
    newTweet = new Tweet();
  }

  public Tweet getNewTweet() {
    return newTweet;
  }

}
