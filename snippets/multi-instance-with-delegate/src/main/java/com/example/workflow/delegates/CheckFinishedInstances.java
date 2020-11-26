package com.example.workflow.delegates;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckFinishedInstances implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        RuntimeService runtimeService = delegateExecution.getProcessEngine().getRuntimeService();

        Boolean finished = runtimeService.createProcessInstanceQuery().processDefinitionKey("ChildProcess")
                .variableValueEquals("parentBusinessKey", delegateExecution.getBusinessKey())
                .count() == 0;

        delegateExecution.setVariable("finished", finished);
    }
}
