package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.ProcessInstanceAssert;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@Deployment(resources = {"process.bpmn", "process_child.bpmn"})
public class ProcessUnitTest {

    @ClassRule
    @Rule
    public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

    private static final String PROCESS_DEFINITION_KEY = "engine-plugin-on-demand-call-activity";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Long sleepTime = 2000L;

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }

    @Before
    public void setup() {
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
        Thread.sleep(3000L);
        assertThat(processInstance).isEnded();
        assertThat(processInstance).job().isNull();
    }

    @Test
    public void testWithoutCallActivityFail() throws InterruptedException {
        ProcessInstance processInstance = processEngine().getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
                        withVariables("retProcess", false, "doThrowException", true));
        assertThat(processInstance).calledProcessInstance("process-child").isNull();
        Thread.sleep(3000L);
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
        //assertThat(processInstance).hasPassed("EndEventProcessEnded");
        assertThat(processInstance).isEnded();

    }

}
