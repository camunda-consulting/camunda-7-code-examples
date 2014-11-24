package com.camunda.consulting.authorizationDemo;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.task.TaskDefinition;
import org.camunda.bpm.engine.impl.util.xml.Element;

public class AuthorizationParseListener extends AbstractBpmnParseListener {
  
  private static final Logger log = Logger.getLogger(AuthorizationParseListener.class.getName());

  public AuthorizationParseListener() {
    log.info("AuthorizationParseListener created");
  }

  @Override
  public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {
    log.info("register AuthorizationListener");
    TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activity.getActivityBehavior()).getTaskDefinition();
    taskDefinition.addTaskListener(TaskListener.EVENTNAME_COMPLETE, new AuthorizationListener());
  }

}
