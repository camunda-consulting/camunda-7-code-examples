package com.camunda.demo.engine_plugin_assignee_checker;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateTask;

public class AssigneeNotInCandidatesException extends ProcessEngineException {

  public AssigneeNotInCandidatesException(DelegateTask task) {
    super("Assignee '" + task.getAssignee() + "' is neither a candidate user nor member of any candidate group for user task:"
        + " activityId=" + task.getTaskDefinitionKey()
        + ", name='" + task.getName() + "'"
        + ", taskId=" + task.getId()
        + ", candidates='" + task.getCandidates() + "'");
  }

  private static final long serialVersionUID = 1L;

}
