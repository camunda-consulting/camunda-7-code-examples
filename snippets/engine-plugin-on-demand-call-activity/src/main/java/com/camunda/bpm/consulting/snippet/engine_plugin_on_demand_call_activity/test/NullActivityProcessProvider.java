package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.test;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import static com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.util.OnDemandCallActivityUtil.getAsyncServiceCallVarName;

public class NullActivityProcessProvider {

    public String getChildProcessDefinitionKey(DelegateExecution execution){
            execution.setVariableLocal(getAsyncServiceCallVarName(execution), true);
            return null;
    }
}
