package com.camunda.demo.dmn.order.discount;

import java.util.List;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricDetail;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
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

  private static final String PROCESS_DEFINITION_KEY = "dmn-example-order-discount";

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
  @Deployment(resources = "orderApproval.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  
  @Test
  @Deployment(resources = {"orderApproval.bpmn", "orderDiscount.dmn"})
  public void testVariableUpdateByIo() {
    Order order = new Order();
    order.setAmount(100);
    processEngine().getRuntimeService().startProcessInstanceByKey("invoice-creation", 
        Variables.createVariables().putValueTyped("order", 
            Variables.objectValue(order).serializationDataFormat(SerializationDataFormats.JSON).create()));
    
    HistoricVariableInstance historicVariableInstance = processEngine().getHistoryService().createHistoricVariableInstanceQuery().variableName("order").singleResult();
    List<HistoricDetail> updates = processEngine().getHistoryService().createHistoricDetailQuery().variableUpdates().variableInstanceId(historicVariableInstance.getId()).list();

    System.out.println(updates);
    assertEquals(2, updates.size());
    
    HistoricActivityInstance historicActivityInstance = processEngine().getHistoryService().createHistoricActivityInstanceQuery().activityInstanceId(updates.get(0).getActivityInstanceId()).singleResult();
    System.out.println(updates.get(0).getActivityInstanceId() + " # " + historicActivityInstance);
    assertNotNull(historicActivityInstance);
    
    
    HistoricActivityInstance historicActivityInstance1 = processEngine().getHistoryService().createHistoricActivityInstanceQuery().activityInstanceId(updates.get(1).getActivityInstanceId()).singleResult();
    System.out.println(updates.get(1).getActivityInstanceId() + " # " + historicActivityInstance1);
    assertNotNull(historicActivityInstance1);
    
  }

}
