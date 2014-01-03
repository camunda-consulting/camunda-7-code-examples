package org.camunda.consulting.patterns.data;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.consulting.patterns.data.ProcessDataAccessor;

@Named("myVariableAccesor")
public class ExampleProcessVariableAccessor extends ProcessDataAccessor {

  public String VARIABLE_NAME = "myVariable";

  @Inject
  public ExampleProcessVariableAccessor(BusinessProcess businessProcess) {
    super(businessProcess);
  }

  public ExampleProcessVariableAccessor(VariableScope variableScope) {
    super(variableScope);
  }

  public ExampleProcessVariableAccessor(Map<String, Object> varMap) {
    super(varMap);
  }

  public String toString() {
    return VARIABLE_NAME + " [" + getValue() + "]";
  }

  public String getValue() {
    return getVariable(VARIABLE_NAME);
  }

  public void setValue(String foo) {
    setVariable(VARIABLE_NAME, foo);
  }

}
