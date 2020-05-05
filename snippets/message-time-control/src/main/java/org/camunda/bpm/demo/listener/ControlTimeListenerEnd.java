package org.camunda.bpm.demo.listener;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class ControlTimeListenerEnd implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        RuntimeService runtimeService = delegateExecution.getProcessEngine().getRuntimeService();

        String controlProcessInstanceId = (String) delegateExecution.getVariable("controlProcessInstanceId");
        runtimeService.createMessageCorrelation("MessageCancelControl")
                .processInstanceId(controlProcessInstanceId).correlate();
    }
}
