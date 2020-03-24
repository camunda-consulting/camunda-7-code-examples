package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.test;

import com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.OnDemandCallActivityBehavior;
import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.bpmn.behavior.BehaviorUtil;
import org.camunda.bpm.engine.impl.bpmn.behavior.CallActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.core.variable.mapping.value.ParameterValueProvider;
import org.camunda.bpm.engine.impl.el.ElValueProvider;
import org.camunda.bpm.engine.impl.el.Expression;
import org.camunda.bpm.engine.impl.el.ExpressionManager;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

import static com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.util.OnDemandCallActivityConstants.ASYNC_SERVICE_CALL_VAR_BASE_NAME;
import static com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.util.OnDemandCallActivityUtil.getAsyncServiceCallVarName;
import static com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.util.OnDemandCallActivityUtil.getSkipVarName;

public class NullCallActivityTestParseListener extends AbstractBpmnParseListener implements BpmnParseListener {

    private ProcessEngineConfigurationImpl configuration;

    public NullCallActivityTestParseListener(ProcessEngineConfigurationImpl configuration){
        this.configuration = configuration;
    }

    @Override
    public void parseCallActivity(Element callActivityElement, ScopeImpl scope, ActivityImpl activity) {
        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        ExpressionManager expressionManager = configuration.getExpressionManager();

        if (activityBehavior instanceof CallActivityBehavior) {// ignore CaseCallActivityBehavior
            CallActivityBehavior callActivityBehavior = (CallActivityBehavior) activityBehavior;

            String expressionText = "${execution.setVariableLocal(\""+ ASYNC_SERVICE_CALL_VAR_BASE_NAME +"\".concat(execution.getCurrentActivityId()), true)}";
            //String expressionText = "${execution.setVariableLocal(\""+ ASYNC_SERVICE_CALL_VAR_BASE_NAME +"\", true)}";
            Expression expression = expressionManager.createExpression(expressionText);
            callActivityBehavior.getCallableElement().setDefinitionKeyValueProvider(new ElValueProvider(expression));
        }


    }
}
