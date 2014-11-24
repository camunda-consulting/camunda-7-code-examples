package com.camunda.bpm.demo.uel_variable_evaluator;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.el.ExpressionManager;

@Named("uel")
public class UEL {

  public Object evaluate(DelegateExecution execution, String expression) {
    ExpressionManager expressionManager = Context.getProcessEngineConfiguration().getExpressionManager();
    return expressionManager.createExpression(expression).getValue(execution);
  }
}
