package com.camunda.consulting.correlator;

public abstract class AbstractResultContainingMessageCorrelationRunner<T> extends AbstractMessageCorrelationRunner {
  private T result;

  public T getResult() {
    return this.result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
