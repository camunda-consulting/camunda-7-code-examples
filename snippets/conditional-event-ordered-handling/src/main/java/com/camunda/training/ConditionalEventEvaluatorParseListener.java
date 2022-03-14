package com.camunda.training;

import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.bpmn.behavior.IntermediateCatchEventActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ConditionalEventEvaluatorParseListener extends AbstractBpmnParseListener {
  private static final Logger LOG = LoggerFactory.getLogger(ConditionalEventEvaluatorParseListener.class);

  @Override
  public void parseBoundaryConditionalEventDefinition(
      Element element, boolean interrupting, ActivityImpl conditionalActivity
  ) {
    parseConditionalEvent(element, conditionalActivity);
  }

  @Override
  public void parseIntermediateConditionalEventDefinition(
      Element conditionalEventDefinition, ActivityImpl conditionalActivity
  ) {
    parseConditionalEvent(conditionalEventDefinition, conditionalActivity);
  }

  @Override
  public void parseConditionalStartEventForEventSubprocess(
      Element element, ActivityImpl conditionalActivity, boolean interrupting
  ) {
    parseConditionalEvent(element, conditionalActivity);
  }

  private void parseConditionalEvent(Element conditionalEventDefinition, ActivityImpl conditionalActivity) {
    ActivityImpl activityToAppendListener = findActivityToAppendListener(conditionalActivity);
    String eventName = conditionalEventDefinition
        .element("condition")
        .getText()
        .replace("${", "")
        .replace("}", "");
    activityToAppendListener
        .getListeners()
        .getOrDefault(ExecutionListener.EVENTNAME_START, new ArrayList<>())
        .stream()
        .filter(delegateListener -> FindConditionExecutionListener.class.isAssignableFrom(delegateListener.getClass()))
        .map(FindConditionExecutionListener.class::cast)
        .findFirst()
        .ifPresentOrElse(findConditionExecutionListener -> findConditionExecutionListener.addEventName(eventName),
            () -> {
              FindConditionExecutionListener listener = new FindConditionExecutionListener();
              listener.addEventName(eventName);
              activityToAppendListener.addListener(ExecutionListener.EVENTNAME_START, listener);
            }
        );
    conditionalActivity.addListener(ExecutionListener.EVENTNAME_END,new RemoveConditionVariableListener(eventName));
    LOG.info("Parsing {}", conditionalActivity);
  }

  private ActivityImpl findActivityToAppendListener(ActivityImpl conditionalActivity) {
    ActivityImpl activityToAppendListener = conditionalActivity;
    if (conditionalActivity.getActivityBehavior() instanceof IntermediateCatchEventActivityBehavior) {
      IntermediateCatchEventActivityBehavior activityBehavior =
          (IntermediateCatchEventActivityBehavior) conditionalActivity.getActivityBehavior();
      if (activityBehavior.isAfterEventBasedGateway()) {
        activityToAppendListener = (ActivityImpl) conditionalActivity.getEventScope();
      }
    }
    return activityToAppendListener;
  }
}
