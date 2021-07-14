package org.company.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class FailingDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    
    if (execution.hasVariable("fail")) {
      throw new RuntimeException((String)execution.getVariable("fail"));
    }
  }
}
