package com.camunda.consulting.simulator;

public interface ProcessInstanceExecutor<D> {

    void executeInstance(D payload);
}
