package com.camunda.consulting.cmmn_listener_extension;

import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.cmmn.behavior.HumanTaskActivityBehavior;
import org.camunda.bpm.engine.impl.cmmn.model.CmmnActivity;
import org.camunda.bpm.engine.impl.cmmn.model.CmmnCaseDefinition;
import org.camunda.bpm.engine.impl.cmmn.transformer.AbstractCmmnTransformListener;
import org.camunda.bpm.model.cmmn.instance.Case;
import org.camunda.bpm.model.cmmn.instance.HumanTask;
import org.camunda.bpm.model.cmmn.instance.PlanItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleCmmnTransformListener extends AbstractCmmnTransformListener {
  
  private static final Logger log = LoggerFactory.getLogger(ExampleCmmnTransformListener.class);

  @Override
  public void transformHumanTask(PlanItem planItem, HumanTask humanTask, CmmnActivity activity) {
    log.info("add listener on {}", activity);
    if(activity.getActivityBehavior() instanceof HumanTaskActivityBehavior) {
      HumanTaskActivityBehavior humanTaskActivityBehavior = (HumanTaskActivityBehavior)(activity.getActivityBehavior());
      humanTaskActivityBehavior.getTaskDefinition().addTaskListener(TaskListener.EVENTNAME_ASSIGNMENT, new ExampleTaskListener());
    }
  }

  @Override
  public void transformCase(Case element, CmmnCaseDefinition caseDefinition) {
    log.info("could add a listener to case {}", element);
  }
}
