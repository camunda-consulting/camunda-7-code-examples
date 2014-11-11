package com.camunda.demo.skip.delegate.interceptor.nonarquillian;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
  





import com.camunda.demo.skip.delegate.interceptor.LoggerDelegate;
import com.camunda.demo.skip.delegate.interceptor.SkipDelegateInterceptor;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "skip-delegate-interceptor";

  // enable more detailed logging
  static {
//    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
//    LogFactory.useJdkLogging(); // MyBatis
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
  public void testExecution() {
    Mocks.register("logger", new LoggerDelegate());
    
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("skip", true);
    variables.put("skip_ServiceTask_2", true);
    variables.put("skip_SendTask_1", true);
    variables.put("skip_UserTask_1", true);
    
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);

    assertThat(processInstance)
      .variables()
      .doesNotContainKey("loggerInvoked");
    
    complete(task());
    
    assertThat(processInstance)
      .hasVariables("skip_ServiceTask_1", "skip_ServiceTask_2", "skip_SendTask_1", "skip_UserTask_1" ,"loggerInvoked");
    
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testRetrievalOfExtensionAttribute() {
    String processDefinitionId = processDefinitionQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).latestVersion().singleResult().getId();
    ModelElementInstance serviceTask = repositoryService().getBpmnModelInstance(processDefinitionId).getModelElementById("ServiceTask_1");
    String skippable = SkipDelegateInterceptor.getSkippableProperty((FlowElement) serviceTask);
    assertEquals("This Service Task does not require any manual correction in order to be skipped.", skippable);

    ModelElementInstance receiveTask = repositoryService().getBpmnModelInstance(processDefinitionId).getModelElementById("ReceiveTask_1");
    skippable = SkipDelegateInterceptor.getSkippableProperty((FlowElement) receiveTask);
    assertNull(skippable);
  }

}
