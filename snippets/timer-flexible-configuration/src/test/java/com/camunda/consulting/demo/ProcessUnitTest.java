package com.camunda.consulting.demo;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;


/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProcessUnitTest {

  private static final String PROCESS_DEFINITION_KEY = "tcs-demo";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Test
  public void testHappyPath() {

    Map<String, Object> vars = new HashMap<>();

    List<String> timers = new ArrayList<>();
    timers.add("PT1H");
    timers.add("PT2H");
    timers.add("PT3H");

    String timerConfig = "R/PT1H,PT2H";

    vars.put("timerConfig", timers);

    // Either: Drive the process by API and assert correct behavior by camunda-bpm-assert, e.g.:
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, vars);

    assertThat(processInstance).isWaitingAt("userTask");

    System.out.println(jobQuery().list());
    execute(job()); // timer
    execute(job(jobQuery().messages())); // async after
    assertThat(externalTask()).hasTopicName("sendReminder");
    complete(externalTask());

    System.out.println(jobQuery().list());
    execute(job()); // timer
    execute(job(jobQuery().messages())); // async after
    assertThat(externalTask()).hasTopicName("sendReminder");
    complete(externalTask());

    System.out.println(jobQuery().list());
    execute(job()); // timer
    execute(job(jobQuery().messages())); // async after
    assertThat(externalTask()).hasTopicName("sendReminder");
    complete(externalTask());

    System.out.println(jobQuery().list());

  }

}
