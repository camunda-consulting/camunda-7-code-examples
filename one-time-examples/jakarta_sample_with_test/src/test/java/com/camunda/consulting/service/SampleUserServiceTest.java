package com.camunda.consulting.service;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions;
import org.camunda.bpm.engine.test.assertions.ProcessEngineTests;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.jboss.weld.environment.se.Weld;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SampleUserServiceTest {


    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    private Weld weld;
    protected RuntimeService runtimeService = null;
    protected TaskService taskService = null;
    protected RepositoryService repositoryService = null;

    @Before
    public void setUp() {
        weld = new Weld();
        weld.initialize();
        ProcessEngineAssertions.init(processEngineRule.getProcessEngine());
        runtimeService = ProcessEngineTests.runtimeService();
        taskService = processEngineRule.getTaskService();
        repositoryService = processEngineRule.getRepositoryService();
        ProcessEngineAssertions.init(processEngineRule.getProcessEngine());
        repositoryService.createDeployment().addClasspathResource("bpmn/sample.bpmn").deploy();
    }

    @After
    public void tearDown() {
        if (weld != null) {
            weld.shutdown();
        }
    }

    @Test
    public void extensionUsageExample() {
        // Given we create a new process instance
        VariableMap map = Variables.putValue("user", "john");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_Sample", map);
        assertNotNull(processInstance.getProcessInstanceId());
    }

    @Test
    @Deployment(resources = "bpmn/sample_task.bpmn")
    public void extensionUsageSampleTask() {
        // Given we create a new process instance
        VariableMap map = Variables.putValue("user", "john");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_Sample_Task", map);
        assertNotNull(processInstance.getProcessInstanceId());

        TaskService taskService = processEngineRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        assertEquals(task.getName(), "Do Something with UserId");
    }
}