package org.camunda.bpm.pattern.guard;

import java.io.Serializable;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/**
 * Listener as a guard for tasks.
 * 
 * @author Simon Zambrovski, Holisticon AG
 * @author Jan Galinski, Holisticon AG
 */
public class TaskGuard implements TaskListener, Guard {

  public void checkPostconditions(final DelegateExecution execution) throws IllegalStateException {
    // intentionally empty
  }

  public void checkPreconditions(final DelegateExecution execution) throws IllegalStateException {
    // intentionally empty
  }

  public void notify(final DelegateTask delegateTask) {
    Guards.dispatch(this, delegateTask.getEventName(), delegateTask.getExecution());
  }

}
