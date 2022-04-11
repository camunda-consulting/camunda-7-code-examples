package com.camunda.training;

import org.camunda.bpm.engine.delegate.*;
import org.slf4j.*;

import java.util.*;

public class RemoveConditionVariableListener implements ExecutionListener {
  private static final Logger LOG = LoggerFactory.getLogger(RemoveConditionVariableListener.class);
  private final String eventName;

  public RemoveConditionVariableListener(String eventName) {this.eventName = eventName;}

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    EventVariableUtil.EventVariableHandler eventVariableHandler = EventVariableUtil.forVariables(execution.getVariables());
    // restore inverted flags
    Set<String> flagsToInvert = (Set<String>) execution.getVariable("flagsToInvert");
    if (flagsToInvert != null) {
      LOG.info("Setting {} to true", flagsToInvert);
      flagsToInvert.forEach(flag -> execution.setVariable(flag, true));
    }
    // set own flag to false if no more event to handle
    if (!eventVariableHandler.hasNextEvent(eventName)) {
      LOG.info("No more events of type {}, setting flag to false now", eventName);
      execution.setVariable(eventName, false);
    }
    // mark event as activated
    LOG.info("Activating event {}",eventName);
    eventVariableHandler
        .getNextEvent(eventName)
        .ifPresent(e -> {
          e.getValue().eventState = CreateConditionEvent.EventState.ACTIVATED;
          execution.setVariable(e.getKey(), e.getValue());
        });
  }

  @Override
  public String toString() {
    return "RemoveConditionVariableListener{" + "eventName='" + eventName + '\'' + '}';
  }
}
