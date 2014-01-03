package org.camunda.consulting.patterns.guard;

import static org.camunda.consulting.patterns.guard.Guards.checkIsSet;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.consulting.patterns.guard.ActivityGuard;

public class ExampleActivityGuard extends ActivityGuard {

  @Override
  public void checkPreconditions(final DelegateExecution execution) throws IllegalStateException {
    checkIsSet(execution, "hello");
  }

  @Override
  public void checkPostconditions(final DelegateExecution execution) throws IllegalStateException {
    checkIsSet(execution, "world");
  }

}