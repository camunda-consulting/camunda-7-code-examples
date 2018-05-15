package com.camunda.consulting.email_incident_handler_plugin.additional;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

public class MySampleParseListener extends AbstractBpmnParseListener implements BpmnParseListener {

  private final Logger LOGGER = Logger.getLogger(MySampleParseListener.class.getName());

  @Override
  public void parseStartEvent(Element startEventElement, ScopeImpl scope, ActivityImpl startEvent) {
    LOGGER.info("Parsing Start Event "
        + ", activityId=" + startEvent.getId()
        + ", activityName='" + startEvent.getName() + "'"
        + ", scopeId=" + scope.getId()
        + ", scopeName=" + scope.getName());
  }

  @Override
  public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {
    LOGGER.info("Adding Task Listener to User Task:"
        + " activityId=" + activity.getId()
        + ", activityName='" + activity.getName() + "'"
        + ", scopeId=" + scope.getId()
        + ", scopeName=" + scope.getName());
    ActivityBehavior behavior = activity.getActivityBehavior();
    if (behavior instanceof UserTaskActivityBehavior) {
      ((UserTaskActivityBehavior) behavior).getTaskDefinition().addTaskListener(TaskListener.EVENTNAME_CREATE,  new MySampleTaskListener());
    }
  }

}
