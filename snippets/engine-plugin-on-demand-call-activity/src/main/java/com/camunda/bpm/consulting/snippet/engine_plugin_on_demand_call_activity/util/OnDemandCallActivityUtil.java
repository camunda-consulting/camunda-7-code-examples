package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.util;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;

import static com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.util.OnDemandCallActivityConstants.*;

public class OnDemandCallActivityUtil {

    public static String getRetriesVarName(DelegateExecution execution) {
        return RETRIES_VAR_BASE_NAME + execution.getCurrentActivityId();
    }

    public static String getAsyncServiceCallVarName(DelegateExecution execution) {
        return ASYNC_SERVICE_CALL_VAR_BASE_NAME + execution.getCurrentActivityId();
    }

    public static String getSkipVarName(DelegateExecution execution){
        return SKIP_VAR_BASE_NAME + execution.getCurrentActivityId();
    }
}
