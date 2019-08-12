package org.camunda.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetInstanceCount implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<String> workItems = (List<String>) execution.getVariable("workItems");
        execution.setVariable("instancesCount", workItems.size());
        execution.setVariable("finishedInstancesCount", 0);
        execution.setVariable("createdInstancesCount", 0);
    }
}
