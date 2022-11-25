package com.camunda.consulting;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component("createProcessInstances")
public class CreateProcessInstancesDelegate implements JavaDelegate {
  private final ProcessEngine processEngine;

  @Autowired
  public CreateProcessInstancesDelegate(ProcessEngine processEngine) {
    this.processEngine = processEngine;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    IntStream.range(0, 1000)
        .forEach(
            i ->
                processEngine
                    .getRuntimeService()
                    .startProcessInstanceByKey("dummy", String.valueOf(i)));
  }
}
