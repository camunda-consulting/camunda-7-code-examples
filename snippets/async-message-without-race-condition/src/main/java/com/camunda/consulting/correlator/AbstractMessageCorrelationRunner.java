package com.camunda.consulting.correlator;

public abstract class AbstractMessageCorrelationRunner implements MessageCorrelationRunner {
  private String messageName;

  @Override
  public String getMessageName() {
    return this.messageName;
  }

  public void setMessageName(String messageName) {
    this.messageName = messageName;
  }
}
