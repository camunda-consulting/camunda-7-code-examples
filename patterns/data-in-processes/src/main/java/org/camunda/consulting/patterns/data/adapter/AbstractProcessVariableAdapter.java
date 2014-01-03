package org.camunda.consulting.patterns.data.adapter;

import java.io.Serializable;
import java.util.Map;

/**
 * Adapter interface for get/set Variables. Use to hide concrete implementation.
 * 
 * @author Jan Galinski, Holisticon AG
 */
public abstract class AbstractProcessVariableAdapter {

  /**
   * Type- and null-safe cast of variable-value.
   */
  @SuppressWarnings("unchecked")
  public static final <T extends Serializable> T castValue(final Object value) {
    return value != null ? (T) value : null;
  }

  /**
   * Retrieve variable value from adapter.
   */
  public abstract <T extends Serializable> T getVariable(String name);

  /**
   * Sets value for variable on adapter.
   */
  public abstract <T extends Serializable> void setVariable(String name, T value);

  public abstract Map<String, Object> getVariablesMap();

}
