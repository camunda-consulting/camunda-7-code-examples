package org.camunda.bpm.example.parselistener;

import org.camunda.bpm.engine.impl.bpmn.behavior.CallableElementActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.behavior.ServiceTaskJavaDelegateActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.core.model.CallableElement;
import org.camunda.bpm.engine.impl.core.model.CallableElementParameter;
import org.camunda.bpm.engine.impl.core.variable.mapping.InputParameter;
import org.camunda.bpm.engine.impl.core.variable.mapping.IoMapping;
import org.camunda.bpm.engine.impl.core.variable.mapping.value.ConstantValueProvider;
import org.camunda.bpm.engine.impl.core.variable.mapping.value.ParameterValueProvider;
import org.camunda.bpm.engine.impl.el.ElValueProvider;
import org.camunda.bpm.engine.impl.el.Expression;
import org.camunda.bpm.engine.impl.el.ExpressionManager;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

/**
 * BPMN Parse Listener to add the 
 * business key to all call activities automatically
 *
 */
public class CallActivityBusinessKeyParseListener extends AbstractBpmnParseListener {
	
	@Override
	public void parseCallActivity(Element callActivityElement, ScopeImpl scope, ActivityImpl activity) {
		ExpressionManager expressionManager = Context.getProcessEngineConfiguration().getExpressionManager();
		Expression expression = expressionManager.createExpression("#{execution.processBusinessKey}");
		ElValueProvider p = new ElValueProvider(expression);
		CallableElementActivityBehavior callableElementActivityBehavior = (CallableElementActivityBehavior) activity.getActivityBehavior();
		CallableElement callableElement = (CallableElement) callableElementActivityBehavior.getCallableElement();
		callableElement.setBusinessKeyValueProvider(p);
		callableElementActivityBehavior.setCallableElement(callableElement);
	}
}
