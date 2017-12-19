package com.camunda.consulting.email_incident_handler_plugin.additional;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class MySampleTaskListener implements TaskListener {

  private final Logger LOGGER = Logger.getLogger(MySampleTaskListener.class.getName());

  @Override
  public void notify(DelegateTask task) {
    LOGGER.info("Event '" + task.getEventName() + "' received by Task Listener for Task:"
        + " activityId=" + task.getTaskDefinitionKey()
        + ", name='" + task.getName() + "'"
        + ", taskId=" + task.getId()
        + ", assignee='" + task.getAssignee() + "'"
        + ", candidateGroups='" + task.getCandidates() + "'");
  }

}
