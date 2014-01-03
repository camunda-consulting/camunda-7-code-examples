package org.camunda.bpm.pattern.guard;

import java.io.Serializable;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

/**
 * Listener as a guard for services.
 * 
 * @author Simon Zambrovski, Holisticon AG
 * @author Jan Galinski, Holisticon AG
 */
public abstract class ActivityGuard implements ExecutionListener, Guard {

  public void checkPostconditions(final DelegateExecution execution) throws IllegalStateException {
    // intentionally empty
  }

  public void checkPreconditions(final DelegateExecution execution) throws IllegalStateException {
    // intentionally empty
  }

  public void notify(final DelegateExecution delegateExecution) throws Exception {
    Guards.dispatch(this, delegateExecution.getEventName(), delegateExecution);
  }

}