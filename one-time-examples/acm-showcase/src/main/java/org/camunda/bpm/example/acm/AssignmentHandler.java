package org.camunda.bpm.example.acm;

import javax.inject.Named;

import org.camunda.bpm.engine.impl.context.Context;

@Named("assign")
public class AssignmentHandler {

  // does not yet work - needs to be initialized correctly
  //@Inject
  //private BusinessProcess businessProcess;

  public String get(String defaultAssignee) {
    Object userToAssign = Context.getCurrentCaseInstance().getVariable("assign-user");
    if (userToAssign != null) {
      return (String) userToAssign;
    }
    return defaultAssignee;
  }

}
