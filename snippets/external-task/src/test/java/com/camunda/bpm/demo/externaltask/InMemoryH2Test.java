package com.camunda.bpm.demo.externaltask;

import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "external-task";

  // enable more detailed logging
  static {
//    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
//    LogFactory.useJdkLogging(); // MyBatis
  }

  @Before
  public void setup() {
	init(rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "simple.bpmn")
  public void testSimple() {    
    ProcessInstance pi = runtimeService().startProcessInstanceByKey("simpleExternalTask");
    
    assertThat(pi).task().hasCandidateGroup("testWorker");
    
    long expectedTimeInMillis = System.currentTimeMillis() + 5*60*1000;    
    claim(task(), "worker1");    
    long actualTimeInMillis = ((Date)taskService().getVariable(task().getId(), "externalTaskLockTime")).getTime();
    
    // OK - this is a bid brave - max 1 second time
    assertTrue(actualTimeInMillis - expectedTimeInMillis > 0 && actualTimeInMillis - expectedTimeInMillis < 1000);
    
    complete(task());    
    assertThat(pi).isEnded();   
  }

}
