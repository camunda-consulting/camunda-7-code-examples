package com.camunda.training;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventVariableUtil {
  private static final String EVENT_PREFIX = "_event_";

  public static EventVariableHandler forVariables(Map<String, Object> variables) {
    return new EventVariableHandler(variables);
  }

  public static class EventVariableHandler {
    private final Map<String, Object> variables;

    private EventVariableHandler(Map<String, Object> variables) {
      this.variables = variables;
    }

    private Stream<Map.Entry<String, CreateConditionEvent>> getEventsAsStream() {
      return variables
          .entrySet()
          .stream()
          .filter(e -> e
              .getKey()
              .startsWith(EVENT_PREFIX))
          .map(e -> Map.entry(e.getKey(), (CreateConditionEvent) e.getValue()));
    }

    public Map<String, Boolean> getFlags(Set<String> eventNames) {
      return variables
          .entrySet()
          .stream()
          .filter(variable -> eventNames.contains(variable.getKey()))
          .collect(Collectors.toMap(Map.Entry::getKey, e -> (Boolean) e.getValue()));
    }

    public String findOldestEventName(Set<String> eventNames) {
      return getEventsAsStream()
          .map(Map.Entry::getValue)
          .filter(event -> eventNames.contains(event.eventType))
          .filter(event -> event.eventState.equals(CreateConditionEvent.EventState.INITIAL))
          .min(Comparator.comparingLong(event -> event.creationTimestamp.getTime()))
          .get().eventType;
    }

    public String getNextEventVariableName() {
      return EVENT_PREFIX + (
          variables
              .keySet()
              .stream()
              .filter(variableName -> variableName.startsWith(EVENT_PREFIX))
              .mapToLong(variableName -> Long.parseLong(variableName.substring(EVENT_PREFIX.length())))
              .max()
              .orElse(-1L) + 1L
      );
    }

    public boolean hasNextEvent(String eventName) {
      return getEventsAsStream()
          .filter(e -> e.getValue().eventState.equals(CreateConditionEvent.EventState.INITIAL))
          .filter(e -> e.getValue().eventType.equals(eventName))
          .count() > 1;
    }

    public boolean hasMoreEvents() {
      return getEventsAsStream().anyMatch(e -> e.getValue().eventState.equals(CreateConditionEvent.EventState.INITIAL));
    }

    public Optional<Map.Entry<String, CreateConditionEvent>> getNextEvent(String eventName) {
      return getEventsAsStream()
          .filter(e -> e.getValue().eventState.equals(CreateConditionEvent.EventState.INITIAL))
          .filter(e -> e.getValue().eventType.equals(eventName))
          .min(Comparator.comparingLong(e -> e.getValue().creationTimestamp.getTime()));
    }
  }
}
