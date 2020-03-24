package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.impl.bpmn.behavior.BehaviorUtil;
import org.camunda.bpm.engine.impl.bpmn.behavior.CallActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

public class OnDemandCallActivityParseListener extends AbstractBpmnParseListener implements BpmnParseListener {

    private final Logger LOGGER = Logger.getLogger(OnDemandCallActivityParseListener.class.getName());
    @Override
    public void parseCallActivity(Element callActivityElement, ScopeImpl scope, ActivityImpl activity) {
        // Exchange the Behavior of the Call Activity to add additional functionality
        //CallActivityBehavior behavior = (CallActivityBehavior) activity.getActivityBehavior();

        // exchange behavior
        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        if (activityBehavior instanceof CallActivityBehavior) {// ignore CaseCallActivityBehavior
            CallActivityBehavior currentCallActivityBehavior = (CallActivityBehavior) activityBehavior;
            String className = BehaviorUtil.getClassName(currentCallActivityBehavior);
            Expression expression = BehaviorUtil.getExpression(currentCallActivityBehavior);

            OnDemandCallActivityBehavior onDemandCallActivityBehavior = null;

            if (className != null) {
                onDemandCallActivityBehavior = new OnDemandCallActivityBehavior(className);
            } else {
                onDemandCallActivityBehavior = new OnDemandCallActivityBehavior(expression);
            }
            onDemandCallActivityBehavior.setCallableElement(currentCallActivityBehavior.getCallableElement());
            activity.setActivityBehavior(onDemandCallActivityBehavior);

        }

    }

}
