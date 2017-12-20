package org.camunda.bpm.demo.tasks_query_including_timeouts;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "task-query-including-timeouts";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() {
	  ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
	  assertThat(processInstance).task("UserTask_1");
	  execute(job());
	  
	  HistoricTaskInstance task = historyService().createNativeHistoricTaskInstanceQuery()
	    .sql("select TI.* from ACT_HI_ACTINST as AI "
	        + "join ACT_RE_PROCDEF as PD on AI.PROC_DEF_ID_ = PD.ID_ "
	        + "join ACT_GE_BYTEARRAY as BA on PD.DEPLOYMENT_ID_ = BA.DEPLOYMENT_ID_ and PD.RESOURCE_NAME_ = BA.NAME_ "
	        + "join ACT_HI_TASKINST as TI on AI.PROC_INST_ID_ = TI.PROC_INST_ID_ and TI.TASK_DEF_KEY_  = regexp_replace(utf8tostring( BA.BYTES_ ), '(?s).*<[^>]*boundaryEvent[^>]+id=\"' || AI.ACT_ID_ || '\"[^>]+attachedToRef=\"([^\"]+)\"[^>]*>.*', '$1') "
	        + " where AI.ACT_TYPE_ = 'boundaryTimer'")
	    .singleResult();
	  
	  assertEquals("UserTask_1", task.getTaskDefinitionKey());
  }

  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }  

}
