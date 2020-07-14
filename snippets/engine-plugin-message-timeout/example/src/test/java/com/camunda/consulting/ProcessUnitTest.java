package com.camunda.consulting;

import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

public class ProcessUnitTest {

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setup() {
        init(processEngineRule.getProcessEngine());
    }

    @Test
    @Deployment(resources = "process.bpmn")
    public void testProcess() {
        Mocks.register("someBean", new ExecutionListener() {
            @Override
            public void notify(DelegateExecution execution) throws Exception {
                System.out.println("YAY WE ARE GETTING THERE!");
            }
        });
        ProcessInstance messageProcess = runtimeService().startProcessInstanceByKey("MessageTimeoutTestProcess");

        assertThat(messageProcess).isWaitingAt("SomeMessageEvent");

        runtimeService().createMessageCorrelation("SomeMessage").correlate();

        managementService().createJobQuery().list().stream().map(job -> ((JobEntity) job)).forEach(jobEntity -> {
            logger.info("JobEntity " + jobEntity.toString());
        });

        runtimeService().createEventSubscriptionQuery().list().forEach(eventSubscription -> logger.info(eventSubscription.toString()));

        execute(jobQuery().singleResult());

        runtimeService().createMessageCorrelation("SomeMessage").correlate();

        assertThat(messageProcess).isEnded();
        Assertions.assertThat(jobQuery().list()).isEmpty();
    }
}
