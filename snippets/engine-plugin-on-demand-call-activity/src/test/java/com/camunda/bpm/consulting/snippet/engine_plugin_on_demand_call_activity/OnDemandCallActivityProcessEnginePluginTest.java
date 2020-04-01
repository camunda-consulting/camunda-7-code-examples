package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.TestUtil.cleanUpAndCreateEngine;
import static org.camunda.bpm.engine.impl.test.TestHelper.createSchema;
import static org.camunda.bpm.engine.impl.test.TestHelper.dropSchema;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.processEngine;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class OnDemandCallActivityProcessEnginePluginTest {

    @Rule
    public ProcessEngineRule rule;

    private static final String PROCESS_DEFINITION_KEY = "engine-plugin-on-demand-call-activity";
    private static final String PROCESS_DEFINITION_KEY_WITH_INOUTMAPPING = "engine-plugin-on-demand-call-activity-with-mapping";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Long sleepTime = 3000L;

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }

    @Before
    public void setup() {
        rule = cleanUpAndCreateEngine("camunda_on_demand_call_activity_test.cfg.xml", "process.bpmn", "process_child.bpmn", "process_with_mapping.bpmn");
        Mocks.register("childProcessProvider", new ChildProcessProvider());
        Mocks.register("loggerDelegate", new LoggerDelegate());
        init(rule.getProcessEngine());
    }

    @Test
    public void testWithCallActivity() {
        ProcessInstance processInstance = processEngine().getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY, withVariables("retProcess", true));
        //assertThat(processInstance).calledProcessInstance().hasPassed("CallLoggerTask").isEnded();
        assertThat(processInstance).isEnded();
    }

    @Test
    public void testWithoutCallActivity() throws InterruptedException {
        ProcessInstance processInstance = processEngine().getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY, withVariables("retProcess", false
                        , "doThrowException", false));
        assertThat(processInstance).calledProcessInstance("process-child").isNull();
        Thread.sleep(sleepTime);
        assertThat(processInstance).isEnded();
        assertThat(processInstance).job().isNull();
    }

    @Test
    public void testWithoutCallActivityFail() throws InterruptedException {
        ProcessInstance processInstance = processEngine().getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
                        withVariables("retProcess", false, "doThrowException", true));
        assertThat(processInstance).calledProcessInstance("process-child").isNull();
        Thread.sleep(sleepTime);
        assertThat(processInstance).isNotEnded();
        logger.info(job().toString());
    }

    @Test
    public void testWithoutCallActivityRetries() throws InterruptedException {
        ProcessInstance processInstance = processEngine().getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
                        withVariables("retProcess", false, "doThrowException", true));
        assertThat(processInstance).calledProcessInstance("process-child").isNull();
        //CHECK IF THE FIRST RETRY JOB WAS CREATED AND THEN EXECUTED
        Thread.sleep(sleepTime);
        assertThat(processInstance).isNotEnded();
        logger.info(job().toString());
        execute(job());

        //CHECK IF THE SECOND RETRY JOB WAS CREATED AND THEN EXECUTED
        Thread.sleep(sleepTime);
        assertThat(processInstance).isNotEnded();
        logger.info(job().toString());

        //MODIFY THE VARIABLE AND THEN RETRY TO SUCCESS
        runtimeService().setVariable(processInstance.getId(), "doThrowException", false);
        execute(job());
        Thread.sleep(sleepTime);
        assertThat(processInstance).hasPassed("EndEventProcessEnded");
        assertThat(processInstance).isEnded();
    }

    @Test
    public void testOnDemandCallActivityWithInputOutputMapping() throws InterruptedException {
        ProcessInstance processInstance = processEngine().getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY_WITH_INOUTMAPPING,
                        withVariables("retProcess", false,
                                "doThrowException", false,
                                "inputVar", "anInputVariable"));
        assertThat(processInstance).calledProcessInstance("process-child").isNull();
        Thread.sleep(sleepTime);
        assertThat(processInstance).hasPassed("EndEventProcessEnded");
        assertThat(processInstance).isEnded();
        assertThat(processInstance).variables().containsEntry("inputVar", "anInputVariable");
        assertThat(processInstance).variables().containsEntry("input", "anInputVariable");
        assertThat(processInstance).variables().containsEntry("outputVar", "someValue");
        assertThat(processInstance).variables().containsEntry("output", "someValue");
    }
    
    // TODO: test all operations that should normally work with a call activity (see engine test suite)

}
