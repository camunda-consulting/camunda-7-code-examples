package com.camunda.bpm.demo.externaltask;

import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.task.TaskDecorator;

public class ExternalTaskBehavior extends UserTaskActivityBehavior {

  public ExternalTaskBehavior(TaskDecorator taskDecorator) {
    super(taskDecorator);
  }

}
