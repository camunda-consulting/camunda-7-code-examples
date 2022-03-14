package com.camunda.training;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FindConditionExecutionListener implements ExecutionListener {
  private static final Logger LOG = LoggerFactory.getLogger(FindConditionExecutionListener.class);
  private final Set<String> eventNames = new HashSet<>();

  public void addEventName(String eventName) {
    eventNames.add(eventName);
  }

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    if (eventNames.size() <= 1) {
      return;
    }
    EventVariableUtil.EventVariableHandler eventVariableHandler =
        EventVariableUtil.forVariables(execution.getVariables());

    Map<String, Boolean> trueFlags = eventVariableHandler
        .getFlags(eventNames)
        .entrySet()
        .stream()
        .filter(Map.Entry::getValue)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    if (trueFlags.size() > 1) {
      String oldestEventName = eventVariableHandler.findOldestEventName(trueFlags.keySet());
      Set<String> flagsToInvert = trueFlags
          .keySet()
          .stream()
          .filter(variableName -> !variableName.equals(oldestEventName))
          .peek(variableName -> execution.setVariable(variableName, false))
          .collect(Collectors.toSet());
      execution.setVariable("flagsToInvert", Variables.objectValue(flagsToInvert, true).create());
    }
  }
}
