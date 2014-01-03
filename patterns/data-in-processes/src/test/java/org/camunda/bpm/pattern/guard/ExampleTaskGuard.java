package org.camunda.bpm.pattern.guard;

import static org.camunda.bpm.pattern.guard.Guards.checkIsSet;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.pattern.guard.TaskGuard;

public class ExampleTaskGuard extends TaskGuard {

  @Override
  public void checkPostconditions(final DelegateExecution execution) throws IllegalStateException {
    checkIsSet(execution, "bar");
  }

  @Override
  public void checkPreconditions(final DelegateExecution execution) throws IllegalStateException {
    checkIsSet(execution, "foo");
  }
}