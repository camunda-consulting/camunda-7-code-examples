package com.camunda.consulting.correlator;

import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;

public class TestStartMessageCorrelationRunner implements MessageCorrelationRunner {

  private String result;

  @Override
  public MessageCorrelationResult apply(MessageCorrelationBuilder t) {
    return t.setVariable("result", this.result).correlateWithResult();
  }

  @Override
  public String messageName() {
    return "answer_message";
  }

  public String getResult() {
    return this.result;
  }

  public void setResult(String result) {
    this.result = result;
  }

}
