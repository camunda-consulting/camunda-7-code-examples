package com.camunda.consulting;

import com.camunda.consulting.listeners.EmailListener;
import com.camunda.consulting.services.EmailService;
import org.apache.ibatis.annotations.Many;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

public class ProcessUnitTest {

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Mock
    private EmailService emailService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        init(processEngineRule.getProcessEngine());
    }

    @Test
    @Deployment(resources = "process.bpmn")
    public void testTriggerListener() {
        Mocks.register("emailListener", new EmailListener(emailService));
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("minutes", 2);
        ProcessInstance messageProcessInstance = runtimeService()
                .startProcessInstanceByKey("MessageTimeoutTestProcess", variables);

        assertThat(messageProcessInstance).isWaitingAt("SomeMessageTask");
        assertThat(messageProcessInstance).isWaitingAt("SomeUserTask");

        managementService().createJobQuery().list().stream().map(job -> ((JobEntity) job)).forEach(jobEntity -> {
            logger.info("JobEntity " + jobEntity.toString());
        });

        runtimeService().createEventSubscriptionQuery().list().forEach(eventSubscription -> logger.info(eventSubscription.toString()));

        execute(jobQuery().singleResult());

        runtimeService().createMessageCorrelation("SomeMessage").correlate();
        complete(task());

        assertThat(messageProcessInstance).isEnded();
        Assertions.assertThat(jobQuery().list()).isEmpty();

        Mockito.verify(emailService).send(Mockito.any());
    }

    @Test
    @Deployment(resources = "process.bpmn")
    public void testNotTriggerListener() {
        Mocks.register("emailListener", new EmailListener(emailService));
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("minutes", 2);
        ProcessInstance messageProcessInstance = runtimeService()
                .startProcessInstanceByKey("MessageTimeoutTestProcess", variables);

        assertThat(messageProcessInstance).isWaitingAt("SomeMessageTask");
        assertThat(messageProcessInstance).isWaitingAt("SomeUserTask");

        managementService().createJobQuery().list().stream().map(job -> ((JobEntity) job)).forEach(jobEntity -> {
            logger.info("JobEntity " + jobEntity.toString());
        });

        runtimeService().createEventSubscriptionQuery().list().forEach(eventSubscription -> logger.info(eventSubscription.toString()));

        runtimeService().createMessageCorrelation("SomeMessage").correlate();
        complete(task());

        assertThat(messageProcessInstance).isEnded();
        Assertions.assertThat(jobQuery().list()).isEmpty();

        //Mockito.verify(emailService).send(Mockito.any());
    }
}
