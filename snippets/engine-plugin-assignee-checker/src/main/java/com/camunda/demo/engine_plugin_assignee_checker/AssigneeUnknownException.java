package com.camunda.demo.engine_plugin_assignee_checker;

import org.camunda.bpm.engine.ProcessEngineException;

public class AssigneeUnknownException extends ProcessEngineException {

  public AssigneeUnknownException(String assignee) {
    super("Assignee '" + assignee + "' is unknown to the identity service.");
  }

  private static final long serialVersionUID = 1L;

}
