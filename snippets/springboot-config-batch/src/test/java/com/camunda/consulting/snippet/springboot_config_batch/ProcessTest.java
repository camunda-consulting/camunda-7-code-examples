package com.camunda.consulting.snippet.springboot_config_batch;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProcessTest {

  private static final String PROCESS_DEFINITION_KEY = "springboot-config-batch";

  @Autowired
  private ProcessEngine processEngine;

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(processEngine);
  }

  @Test
  public void testHappyPath() {
    List<String> processInstanceIds = Arrays.asList("23", "42");
    runtimeService().deleteProcessInstancesAsync(
        processInstanceIds, null, "Test");

    Batch batch = managementService().createBatchQuery().singleResult();
    assertEquals(100, batch.getInvocationsPerBatchJob());
  }

}
