package org.camunda.bpm.demo.delegate;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.stereotype.Component;

@Component
    public class UpdateParentInstanceCountDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // EXECUTED IN THE CHILD, EVERYTIME A CHILD FINISHES
        // THIS TASK SHOULD BE ASYNC BEFORE
        RuntimeService runtimeService = delegateExecution.getProcessEngineServices().getRuntimeService();
        String businessKey = (String) delegateExecution.getVariable("parentBusinessKey");
        Execution execution = runtimeService.createExecutionQuery().processInstanceBusinessKey(businessKey).list().get(0);
        Integer finishedInstancesCount = (Integer) runtimeService.getVariable(execution.getId(), "finishedInstancesCount");
        runtimeService.setVariable(execution.getId(), "finishedInstancesCount", finishedInstancesCount+1);

    }
}
