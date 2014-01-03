package org.camunda.bpm.pattern.guard;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.equalTo;
import static com.google.common.base.Predicates.or;
import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;

import com.google.common.base.Predicate;

/**
 * Guard support methods.
 * 
 * @author Simon Zambrovski, Holisticon AG
 * @author Jan Galinski, Holisticon AG
 */
public final class Guards {

  /**
   * Defines global boolean Process-variable to disable guards.
   */
  public static final String VARIABLE_SKIP_GUARDS = "runtimeSkipGuards";

  /**
   * Pre-filled Map with skip guards parameter.
   */
  @SuppressWarnings("serial")
  public static final Map<String, Object> MAP_SKIP_GUARDS = new HashMap<String, Object>() {

    {
      put(VARIABLE_SKIP_GUARDS, Boolean.TRUE);
    }
  };
  static final String VARIABLE_NAME_MUST_BE_NOT_NULL = "Variable name must be not null";
  static final String CONDITION_VIOLATED = "Condition of task '%s' is violated: ";
  private static final Predicate<String> isPrecondition = or(equalTo(ExecutionListener.EVENTNAME_START), equalTo(TaskListener.EVENTNAME_CREATE));
  private static final Predicate<String> isPostcondition = or(equalTo(ExecutionListener.EVENTNAME_END), equalTo(TaskListener.EVENTNAME_COMPLETE));

  /**
   * Hide.
   */
  private Guards() {
    // hide instance.
  }

  /**
   * Decides whether to call {@link #checkPreconditions(DelegateExecution)} or
   * {@link #checkPostconditions(DelegateExecution)}.
   * 
   * @param guard
   *          guard implementation.
   * @param eventName
   *          name of the event listened
   * @param execution
   *          the current execution
   */
  public static void dispatch(final Guard guard, final String eventName, final DelegateExecution execution) {
    // do nothing when disabled
    if (skipGuards(execution)) {
      return;
    }

    if (isPrecondition.apply(eventName)) {
      guard.checkPreconditions(execution);
    } else if (isPostcondition.apply(eventName)) {
      guard.checkPostconditions(execution);
    }
  }

  /**
   * Checks if guards should be skipped or not.
   * 
   * @param execution
   *          the current execution holding variables
   * @return <code>true</code> if VARIABLE_SKIP_GUARDS is set,
   *         <code>false</code> else
   */
  public static boolean skipGuards(final DelegateExecution execution) {
    return BooleanUtils.toBoolean((Boolean) execution.getVariable(VARIABLE_SKIP_GUARDS));
  }

  /**
   * Checks if a local variable is set.
   * 
   * @param execution
   *          current process execution
   * @param variableName
   *          name of the variable.
   */
  public static void checkIsSetLocal(final DelegateExecution execution, final String variableName) {
    checkArgument(variableName != null, VARIABLE_NAME_MUST_BE_NOT_NULL);

    final Object variableLocal = execution.getVariableLocal(variableName);
    checkState(variableLocal != null, format(CONDITION_VIOLATED + "Local variable '%s' is not set.", execution.getCurrentActivityId(), variableName));
  }

  /**
   * Checks if a variable is set.
   * 
   * @param execution
   *          current process execution
   * @param variableName
   *          name of the variable.
   */
  public static void checkIsSet(final DelegateExecution execution, final String variableName) {
    checkArgument(variableName != null, VARIABLE_NAME_MUST_BE_NOT_NULL);

    final Object variableLocal = execution.getVariableLocal(variableName);
    final Object variable = execution.getVariable(variableName);

    checkState(variableLocal != null || variable != null,
        format(CONDITION_VIOLATED + "Variable '%s' is not set.", execution.getCurrentActivityId(), variableName));
  }

  /**
   * Checks if a global variable is set.
   * 
   * @param execution
   *          current process execution
   * @param variableName
   *          name of the variable.
   */
  public static void checkIsSetGlobal(final DelegateExecution execution, final String variableName) {
    checkArgument(variableName != null, VARIABLE_NAME_MUST_BE_NOT_NULL);

    final Object variable = execution.getVariable(variableName);
    checkState(variable != null, format(CONDITION_VIOLATED + "Global variable '%s' is not set.", execution.getCurrentActivityId(), variableName));
  }

}
