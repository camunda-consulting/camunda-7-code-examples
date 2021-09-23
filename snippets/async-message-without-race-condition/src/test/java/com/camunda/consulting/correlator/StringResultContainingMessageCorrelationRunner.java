package com.camunda.consulting.correlator;

import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;

public class StringResultContainingMessageCorrelationRunner extends AbstractResultContainingMessageCorrelationRunner<String> {

  @Override
  public MessageCorrelationResult apply(MessageCorrelationBuilder t) {
    return t.setVariable("result", this.getResult()).correlateWithResult();
  }

}
