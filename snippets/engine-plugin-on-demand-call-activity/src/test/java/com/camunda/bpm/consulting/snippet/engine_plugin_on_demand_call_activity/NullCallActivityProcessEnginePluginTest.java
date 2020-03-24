package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.exception.NullValueException;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity.TestUtil.cleanUpAndCreateEngine;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.camunda.bpm.engine.impl.test.TestHelper.assertAndEnsureCleanDbAndCache;
import static org.camunda.bpm.engine.impl.test.TestHelper.dropSchema;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class NullCallActivityProcessEnginePluginTest {

    @Rule
    public ProcessEngineRule rule;

    private static final String PROCESS_DEFINITION_KEY = "engine-plugin-on-demand-call-activity";

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }

    @Before
    public void setup() {
        rule = cleanUpAndCreateEngine("camunda_test_null_call_activity.cfg.xml", "process.bpmn", "process_child.bpmn");

        init(rule.getProcessEngine());
    }

    @Test
    public void testCallActivityNull() {
        ProcessInstance processInstance = processEngine().getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
        assertThat(processInstance).calledProcessInstance().isNull();
    }

}
