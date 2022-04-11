package com.camunda.training;

import org.camunda.bpm.engine.delegate.*;
import org.camunda.bpm.engine.impl.bpmn.parser.*;
import org.camunda.bpm.engine.impl.pvm.process.*;
import org.camunda.bpm.engine.impl.util.xml.*;
import org.slf4j.*;

import java.util.*;

public class ConditionalEventEvaluatorParseListener extends AbstractBpmnParseListener {
  private static final Logger LOG = LoggerFactory.getLogger(ConditionalEventEvaluatorParseListener.class);

  @Override
  public void parseBoundaryConditionalEventDefinition(
      Element element, boolean interrupting, ActivityImpl conditionalActivity
  ) {
    //  adds listener to parent scope (Activity/Task)
    parseConditionalEvent(element, conditionalActivity);
  }

  @Override
  public void parseIntermediateConditionalEventDefinition(
      Element conditionalEventDefinition, ActivityImpl conditionalActivity
  ) {
    // adds listener to intermediate event or event-based gateway
    parseConditionalEvent(conditionalEventDefinition, conditionalActivity);
  }

  @Override
  public void parseConditionalStartEventForEventSubprocess(
      Element element, ActivityImpl conditionalActivity, boolean interrupting
  ) {
    //  adds listener to parent scope (process definition, sub process)
    parseConditionalEvent(element, conditionalActivity);
  }

  private void parseConditionalEvent(
      Element conditionalEventDefinition, ActivityImpl conditionalActivity
  ) {
    LOG.info("Parsing {}", conditionalActivity);
    String eventName = conditionalEventDefinition
        .element("condition")
        .getText()
        .replace("${", "")
        .replace("}", "");
    ScopeImpl activityToAppendBeforeListener = conditionalActivity.getEventScope();
    FindConditionExecutionListener listener = activityToAppendBeforeListener
        .getListeners()
        .getOrDefault(ExecutionListener.EVENTNAME_START, new ArrayList<>())
        .stream()
        .filter(delegateListener -> FindConditionExecutionListener.class.isAssignableFrom(delegateListener.getClass()))
        .map(FindConditionExecutionListener.class::cast)
        .findFirst()
        .orElseGet(() -> {
          FindConditionExecutionListener l = new FindConditionExecutionListener();
          activityToAppendBeforeListener.addListener(ExecutionListener.EVENTNAME_START, l);
          return l;
        });
    listener.addEventName(eventName);
    LOG.info("Appended {} to {}", listener, activityToAppendBeforeListener);
    RemoveConditionVariableListener removeConditionVariableListener = new RemoveConditionVariableListener(eventName);
    conditionalActivity.addListener(ExecutionListener.EVENTNAME_END, removeConditionVariableListener);
    LOG.info("Appended {} to {}", removeConditionVariableListener, conditionalActivity);
  }

}
