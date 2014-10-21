package com.camunda.consulting.history.changeHistoryOutput;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*; 

import java.util.List;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ExclusiveGatewayHistoryTest {
  
  @Rule 
  public ProcessEngineRule rule = new ProcessEngineRule();

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
    LogFactory.useJdkLogging(); // MyBatis
  }
  
  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "processWithGateway.bpmn")
  public void runThoughExclusiveGateway() {
    ProcessInstance pi = runtimeService()
      .startProcessInstanceByKey(
          "processWithGateway", 
          withVariables("approved", "true"));
    assertThat(pi).isWaitingAt("UserTask_1");
  }
  
  @Test
  @Deployment(resources = "processWithGateway.bpmn")
  public void supressExclusiveGatewayHistory() {
    runtimeService()
      .startProcessInstanceByKey(
          "processWithGateway", 
          withVariables("approved", "true"));
    List<HistoricActivityInstance> historicActivities = historyService().createHistoricActivityInstanceQuery().list();
    assertThat(historicActivities)
      .extracting("activityType")
      .doesNotContain("exclusiveGateway");
  }
}
