package com.camunda.demo.engine_plugin_assignee_checker;

import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

/**
 * {@link BpmnParseListener} that adds a {@link TaskListener} to each User Task.
 * 
 * @author Falko Menge (Camunda)
 */
public class AssigneeCheckerParseListener extends AbstractBpmnParseListener implements BpmnParseListener {

  @Override
  public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {
    ActivityBehavior behavior = activity.getActivityBehavior();
    if (behavior instanceof UserTaskActivityBehavior) {
      ((UserTaskActivityBehavior) behavior).getTaskDefinition().addTaskListener(TaskListener.EVENTNAME_ASSIGNMENT,  new AssigneeCheckerTaskListener());
    }
  }

}
