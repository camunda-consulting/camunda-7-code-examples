package com.camunda.training;

import com.camunda.training.CreateConditionEvent.*;
import org.camunda.bpm.engine.*;

import java.util.*;

public class ConditionService {
  private final RuntimeService runtimeService;

  public ConditionService(RuntimeService runtimeService) {this.runtimeService = runtimeService;}

  public void createCondition(String eventType, Object eventBody, String processInstanceId) {
    CreateConditionEvent event = new CreateConditionEvent();
    event.eventState = EventState.INITIAL;
    event.eventType = eventType;
    event.body = eventBody;
    event.creationTimestamp = new Date();
    // generate id
    String nextId = EventVariableUtil
        .forVariables(runtimeService.getVariables(processInstanceId))
        .getNextEventVariableName();
    // set event as variable
    runtimeService.setVariable(processInstanceId, nextId, event);
    // flag event type as present
    runtimeService.setVariable(processInstanceId, event.eventType, true);
  }
}
