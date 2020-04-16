package org.camunda.migration;


import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("suspendProcesses")
public class SuspendProcesses implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(SuspendProcesses.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        ProcessEngine processEngine = execution.getProcessEngine();

        String origProcessDefKey = (String) execution.getVariable("origProcessDefKey");
        String destProcessDefKey = (String) execution.getVariable("destProcessDefKey");

        processEngine.getRuntimeService().suspendProcessInstanceByProcessDefinitionKey(origProcessDefKey);

        if(destProcessDefKey != null && !destProcessDefKey.equalsIgnoreCase(origProcessDefKey)){
            processEngine.getRuntimeService().suspendProcessInstanceByProcessDefinitionKey(destProcessDefKey);
        }
    }
}
