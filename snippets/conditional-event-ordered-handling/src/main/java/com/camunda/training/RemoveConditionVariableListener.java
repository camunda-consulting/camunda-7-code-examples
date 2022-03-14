package com.camunda.training;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class RemoveConditionVariableListener implements ExecutionListener {
  private static final Logger LOG = LoggerFactory.getLogger(RemoveConditionVariableListener.class);
  private final String eventName;

  public RemoveConditionVariableListener(String eventName) {this.eventName = eventName;}

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    EventVariableUtil.EventVariableHandler eventVariableHandler =
        EventVariableUtil.forVariables(execution.getVariables());
    // restore inverted flags
    Set<String> flagsToInvert = (Set<String>) execution.getVariable("flagsToInvert");
    if (flagsToInvert != null) {
      flagsToInvert.forEach(flag -> execution.setVariable(flag, true));
    }
    // set own flag to false if no more event to handle
    if (!eventVariableHandler.hasNextEvent(eventName)) {
      execution.setVariable(eventName, false);
    }
    // mark event as activated
    eventVariableHandler
        .getNextEvent(eventName)
        .ifPresent(e -> {
          e.getValue().eventState = CreateConditionEvent.EventState.ACTIVATED;
          execution.setVariable(e.getKey(), e.getValue());
        });
  }
}
