package com.example.workflow;

import com.example.workflow.config.ProcessBatchConfig;
import com.example.workflow.delegates.CheckFinishedInstances;
import com.example.workflow.delegates.CreateInstancesDelegate;
import com.example.workflow.delegates.GenerateDataDelegate;
import com.example.workflow.delegates.LoggerDelegate;
import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.feel.syntaxtree.In;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

public class WorkflowTest {

    @Rule
    public ProcessEngineRule rule = new ProcessEngineRule();

    @Before
    public void setup() {
        init(rule.getProcessEngine());
    }

    @Test
    @Deployment(resources = {"process.bpmn", "child-process.bpmn"})
    public void testHappyPath() {
        Logger log = LoggerFactory.getLogger(getClass());
        ProcessBatchConfig processBatchConfig = new ProcessBatchConfig();

        Mocks.register("generateDataDelegate", new GenerateDataDelegate(processBatchConfig));
        Mocks.register("createInstancesDelegate", new CreateInstancesDelegate(processBatchConfig));
        Mocks.register("checkFinishedInstances", new CheckFinishedInstances());

        Mocks.register("loggerDelegate", new JavaDelegate() {
            @Override
            public void execute(DelegateExecution execution) throws Exception {

            }
        });

        String businessKey = "12345";
        Integer randomDataSize = 2500;
        Integer loopNumber = (int) Math.ceil((double) randomDataSize / processBatchConfig.getBlockSize());

        ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("ParentProcesss", businessKey, withVariables("randomDataSize", randomDataSize));

        for (int i = 0; i < loopNumber; i++) {
            log.info("Index: {}", i);
            assertThat(processInstance).isWaitingAt("CreateInstancesTask");
            execute(job("CreateInstancesTask"));

            Long childInstances = runtimeService().createProcessInstanceQuery().variableValueEquals("parentBusinessKey", businessKey).count();
            Assertions.assertThat(childInstances).isLessThanOrEqualTo(processBatchConfig.getBlockSize().intValue());

            Long totalJobs = managementService().createJobQuery().count();
            Assertions.assertThat(totalJobs).isLessThanOrEqualTo(processBatchConfig.getBlockSize() + 1);

            execute(job("CreateInstancesTask"));
            jobQuery().processDefinitionKey("ChildProcess").list().forEach(BpmnAwareTests::execute);
        }
        assertThat(job()).hasActivityId("OneMinuteTimer");
        execute(job());
        assertThat(processInstance).isEnded();
    }

}
