package org.camunda.bpm.example.events;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest(properties = "camunda.bpm.metrics.enabled=false")
@RunWith(SpringRunner.class)
public class TestSpringEvents extends AbstractProcessEngineRuleTest {

    @Autowired
    public RuntimeService runtimeService;

    @Test
    public void shouldExecuteHappyPath() {

        ProcessInstance pi = runtimeService.startProcessInstanceByKey("my-project-process");
        assertThat(pi).isStarted().isWaitingAt("say-hello-task");

        complete(task());
        assertThat(pi).hasPassed("say-hello-task").isEnded();
    }
}