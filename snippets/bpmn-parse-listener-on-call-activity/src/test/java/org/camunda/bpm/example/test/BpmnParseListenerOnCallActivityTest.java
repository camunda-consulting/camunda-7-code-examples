package org.camunda.bpm.example.test;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Test Bpmn Parse listener as process engine plugin has appended task listeners
 * and if task listeners are executed.
 */
public class BpmnParseListenerOnCallActivityTest {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  protected RuntimeService runtimeService;
  protected HistoryService historyService;

  @Before
  public void init() {
    historyService = processEngineRule.getHistoryService();
    runtimeService = processEngineRule.getRuntimeService();
  }

  @Test
  @Deployment(resources = { "bpmnParseListenerOnCallActivity.bpmn","subprocess.bpmn" })
  public void testBpmnParseListener() throws IOException {

    // start the process instance
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bpmnParseListenerOnCallActivity","1234");

    // check if process instance ended
    processInstance = runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .singleResult();
    assertThat(processInstance, is(nullValue()));
    
    // all instances with business key
    Long count2 = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey("1234").count();
    assertEquals(count2.intValue(),2);
    
    // find only the subprocess with the business key
    Long count1 = historyService.createHistoricProcessInstanceQuery()
    		.processInstanceBusinessKey("1234")
    		.processDefinitionKey("subprocess")
    		.count();
    assertEquals(count1.intValue(),1);
  }

}
