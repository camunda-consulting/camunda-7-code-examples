package org.camunda.consulting.patterns.data;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.consulting.patterns.data.ProcessDataAccessor;

@Named("exampleProcessData")
public class ExampleProcessDataAccessor extends ProcessDataAccessor {

  public enum VARIABLE_NAMES {
    foo, bar, hello, world;
  }

  @Inject
  public ExampleProcessDataAccessor(BusinessProcess businessProcess) {
    super(businessProcess);
  }

  public ExampleProcessDataAccessor(VariableScope variableScope) {
    super(variableScope);
  }

  public ExampleProcessDataAccessor(Map<String, Object> varMap) {
    super(varMap);
  }

  @Override
  public String toString() {
    return "ExampleVariables [foo=" + getFoo() + ", hello=" + getHello() + "]";
  }

  public String getFoo() {
    return getVariable(VARIABLE_NAMES.foo.toString());
  }

  public void setFoo(String foo) {
    setVariable(VARIABLE_NAMES.foo.toString(), foo);
  }

  public String getHello() {
    return getVariable(VARIABLE_NAMES.hello.toString());
  }

  public void setHello(String hello) {
    setVariable(VARIABLE_NAMES.hello.toString(), hello);
  }

  public String getWorld() {
    return getVariable(VARIABLE_NAMES.world.toString());
  }

  public void setWorld(String world) {
    setVariable(VARIABLE_NAMES.world.toString(), world);
  }

  public String getBar() {
    return getVariable(VARIABLE_NAMES.bar.toString());
  }

  public void setBar(String bar) {
    setVariable(VARIABLE_NAMES.bar.toString(), bar);
  }
}
