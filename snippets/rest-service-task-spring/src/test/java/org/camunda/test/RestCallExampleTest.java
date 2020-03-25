package org.camunda.test;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.rest.CamundaBpmRestInitializer;
import org.camunda.example.CamundaApplication;
import org.camunda.example.service.RestDelegate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest(classes = CamundaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
@Import({RestDelegate.class, RestTemplate.class})
public class RestCallExampleTest {

    @Autowired
    ProcessEngine processEngine;
    @MockBean
    CamundaBpmRestInitializer restInitlzr;

    @Before
    public void setUp() {
        init(processEngine);
    }

    @Test
    public void testHappyPath() {

        ProcessInstance pi = runtimeService().startProcessInstanceByKey("RestCallExampleProcess");
        assertThat(pi).isStarted()
                .hasPassed("StartedStartEvent")
                .isWaitingAt("EnterParametersTask");

        complete(task(), withVariables("userId", 2));
        assertThat(pi).isWaitingAt("CheckResultTask")
                .hasPassed("CallRESTServiceTask")
                .hasVariables("email", "Ad");
    }
}