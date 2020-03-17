package com.camunda.consulting.demo.incident;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Job;
import org.springframework.stereotype.Component;

@Component
public class SolveIncident implements JavaDelegate {

    private final RuntimeService runtimeService;
    private final ManagementService managementService;

    public SolveIncident(RuntimeService runtimeService, ManagementService managementService) {
        this.runtimeService = runtimeService;
        this.managementService = managementService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String incidentExecutionId = (String) execution.getVariable("incidentExecutionId");
        String incidentId = (String) execution.getVariable("incidentId");

        Job job = managementService.createJobQuery().executionId(incidentExecutionId).singleResult();

        managementService.setJobRetries(job.getId(), 1);
    }
}
