package com.camunda.consulting.it;

import com.camunda.consulting.ProcessConstants;
import com.camunda.consulting.SampleUserService;
import jakarta.inject.Inject;
import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.ProcessEngineService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(Arquillian.class)
public class ProcessUnitTest {

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive base = ShrinkWrap.create(WebArchive.class, "sample-test.war");
        var dependencies = resolveDependencies(
                "org.camunda.bpm:camunda-bpm-assert",
                "org.assertj:assertj-core"
        );
        File[] libs = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importDependencies(ScopeType.COMPILE)
                .resolve()
                .withTransitivity()
                .asFile();
        var archive = base
                .addPackages(true, "com.camunda.consulting")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("process.bpmn")
                .addAsResource("sample_task.bpmn")
                .addAsResource("META-INF/processes.xml")
                .addAsResource("META-INF/persistence.xml")
                .addClass(ProcessUnitTest.class)
                .addAsLibraries(libs)
                .addAsLibraries(dependencies);
        System.out.println(archive.toString(true));
        return archive;
    }


    private ProcessEngine processEngine;
    protected RuntimeService runtimeService;

    @Before
    public void setupBeforeTest() {
        init(processEngine);
        ProcessEngineService processEngineService = BpmPlatform.getProcessEngineService();
        processEngine = processEngineService.getDefaultProcessEngine();
        runtimeService = processEngine.getRuntimeService();

    }

    @Test
    public void testHappyPath() {
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(ProcessConstants.PROCESS_DEFINITION_KEY);
        assertThat(processInstance).isNotNull();
        BpmnAwareTests.assertThat(processInstance).isEnded();
    }


    @Test
    public void sampleUserServiceUserTask() {
        VariableMap map = Variables.putValue("user", "john");
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey("Process_Sample_Task", map);
        assertNotNull(processInstance.getProcessInstanceId());
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        assertEquals(task.getName(), "Do Something with UserId");
        taskService.complete(task.getId());
        BpmnAwareTests.assertThat(processInstance).isEnded();
    }

    protected static JavaArchive[] resolveDependencies(String... depdencyPath) {
        return Maven.configureResolver()
                .workOffline()
                .loadPomFromFile("pom.xml")
                .resolve(depdencyPath)
                .withTransitivity()
                .as(JavaArchive.class);
    }
}
