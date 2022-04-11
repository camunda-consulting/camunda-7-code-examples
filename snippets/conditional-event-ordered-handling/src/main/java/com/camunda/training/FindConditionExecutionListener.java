package com.camunda.training;

import org.camunda.bpm.engine.delegate.*;
import org.camunda.bpm.engine.variable.*;
import org.slf4j.*;

import java.util.*;
import java.util.stream.*;

public class FindConditionExecutionListener implements ExecutionListener {
  private static final Logger LOG = LoggerFactory.getLogger(FindConditionExecutionListener.class);
  private final Set<String> eventNames = new HashSet<>();

  public void addEventName(String eventName) {
    eventNames.add(eventName);
  }

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    if (eventNames.size() <= 1) {
      LOG.info("No more than 1 event contained in {}, skipping filtering", eventNames);
      return;
    }
    LOG.info("Start filtering for {}", eventNames);
    EventVariableUtil.EventVariableHandler eventVariableHandler = EventVariableUtil.forVariables(execution.getVariables());

    Map<String, Boolean> trueFlags = eventVariableHandler
        .getFlags(eventNames)
        .entrySet()
        .stream()
        .filter(Map.Entry::getValue)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    LOG.info("Found activated conditions: {}", trueFlags);
    if (trueFlags.size() > 1) {
      String oldestEventName = eventVariableHandler.findOldestEventName(trueFlags.keySet());
      LOG.info("Oldest event is {}", oldestEventName);
      Set<String> flagsToInvert = trueFlags
          .keySet()
          .stream()
          .filter(variableName -> !variableName.equals(oldestEventName))
          .peek(variableName -> execution.setVariable(variableName, false))
          .collect(Collectors.toSet());
      LOG.info("Setting {} to false", flagsToInvert);
      execution.setVariable(
          "flagsToInvert",
          Variables
              .objectValue(flagsToInvert, true)
              .create()
      );
    }
  }

  @Override
  public String toString() {
    return "FindConditionExecutionListener{" + "eventNames=" + eventNames + '}';
  }
}
