package org.camunda.consulting.patterns.guard;

import org.camunda.bpm.engine.delegate.DelegateExecution;

/**
 * Guards with pre-conditions and post-conditions.
 * 
 * @author Simon Zambrovski, Holisticon AG
 * @author Jan Galinski, Holisticon AG
 */
public interface Guard {

  /**
   * Will be called on start. <br />
   * Intended usage of this method is to check variables required for task
   * execution.
   * 
   * @param execution
   *          delegate execution.
   * @throws IllegalStateException
   *           on all errors
   */
  void checkPreconditions(final DelegateExecution execution) throws IllegalStateException;

  /**
   * Will be called on end. <br />
   * Intended usage of this method is to check variables changed during task
   * execution.
   * 
   * @param execution
   *          delegate execution.
   * @throws IllegalStateException
   *           on all errors
   */
  void checkPostconditions(final DelegateExecution execution) throws IllegalStateException;

}
