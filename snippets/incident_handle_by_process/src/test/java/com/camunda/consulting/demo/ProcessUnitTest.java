package com.camunda.consulting.demo;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;


/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProcessUnitTest {

    private static final String PROCESS_DEFINITION_KEY = "test_process";

    static {
        LogFactory.useSlf4jLogging();
    }

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ManagementService managementService;

    @Test
    public void whenDoFailIsTrue_ThenCreateIncident() {
        //Start the process with a variable "doFail" as true. This will make the FailLoggerDelegate to fail.
        VariableMap variables = Variables.createVariables().putValue("doFail", true);
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);

        assertThat(processInstance).isWaitingAt("ServiceTask_Logger");


        //Set the retries of the job to 1.
        Job asyncJob = job();
        managementService().setJobRetries(asyncJob.getId(), 1);

        try {
            execute(job());

        } catch (Exception e) {
            //Capture the exception from the service task job and assert if there is an incident
            Incident incident = runtimeService.createIncidentQuery().singleResult();
            assertThat(incident).isNotNull();

            //Check if the incident handling process was created
            ProcessInstance incidentManagementProcess = runtimeService.createProcessInstanceQuery()
                    .processDefinitionKey("IncidentManagementProcess").singleResult();
            assertThat(incidentManagementProcess).isStarted().isWaitingAt("CheckIncidentTask");
            assertThat(incidentManagementProcess).hasVariables("incidentId", "incidentMessage", "incidentExecutionId");

            //Update the variable of the test process and execute the task of the incident management process
            Execution currentTestProcessExecution = runtimeService.createExecutionQuery().processDefinitionKey("test_process").singleResult();
            runtimeService.setVariable(currentTestProcessExecution.getId(), "doFail", false);
            complete(task());
            assertThat(incidentManagementProcess).isEnded();

            //Execute the job of the test process one more time and check if it is executed and the process is finished without incidents
            assertThat(processInstance).isWaitingAt("ServiceTask_Logger");
            execute(job());
            assertThat(processInstance).isEnded();
            Incident newIncident = runtimeService.createIncidentQuery().singleResult();
            assertThat(newIncident).isNull();
        }
    }

}
