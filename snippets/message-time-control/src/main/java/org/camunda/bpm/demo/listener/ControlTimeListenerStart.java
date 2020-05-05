package org.camunda.bpm.demo.listener;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ControlTimeListenerStart implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        RuntimeService runtimeService = delegateExecution.getProcessEngine().getRuntimeService();

        String processInstanceId = delegateExecution.getProcessInstanceId();
        String currentActivityId = delegateExecution.getCurrentActivityId();
        Date timerDate = DateTime.now().plusMinutes(1).toDate();

        VariableMap variables = Variables.createVariables().putValue("processInstanceId", processInstanceId)
                .putValue("currentActivityID", currentActivityId).putValue("timerDate", timerDate);

        ProcessInstance controlProcessInstance = runtimeService.startProcessInstanceByKey("MonitorMessageEvent", variables);
        delegateExecution.setVariableLocal("controlProcessInstanceId", controlProcessInstance.getId());

    }
}
