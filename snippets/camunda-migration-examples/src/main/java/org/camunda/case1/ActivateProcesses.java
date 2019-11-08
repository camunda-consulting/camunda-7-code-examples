package org.camunda.case1;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("case1Activate")
public class ActivateProcesses implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(ActivateProcesses.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        ProcessEngine processEngine = execution.getProcessEngine();

        String processDefKey = (String) execution.getVariable("processDefKey");
        processEngine.getRuntimeService().activateProcessInstanceByProcessDefinitionKey(processDefKey);
    }
}
