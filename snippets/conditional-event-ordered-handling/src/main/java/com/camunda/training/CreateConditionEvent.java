package com.camunda.training;

import java.io.Serializable;
import java.util.Date;

public class CreateConditionEvent implements Serializable {
  public Object body;
  public EventState eventState;
  public Date creationTimestamp;
  public String eventType;

  @Override
  public String toString() {
    return "CreateConditionEvent{" + "body=" + body + ", handled=" + eventState + ", creationTimestamp=" + creationTimestamp + ", eventType='" + eventType + '\'' + '}';
  }

  public enum EventState {
    INITIAL, ACTIVATED, HANDLED
  }
}
