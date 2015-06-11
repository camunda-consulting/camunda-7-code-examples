package com.camunda.consulting.extendedSerialization;

import java.math.BigDecimal;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "extended-serialization";

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
    LogFactory.useJdkLogging(); // MyBatis
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
  public void testProductDeserialize() {
    
    Product product = new Product();
    product.setName("Praxishandbuch BPMN");
    product.setPrice(Money.of(CurrencyUnit.EUR, new BigDecimal("34.99")));
    product.setDateCreated(LocalDate.parse("2015-03-01"));
    
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, withVariables("product", product));
    assertThat(pi).isWaitingAt("UserTask_1");
    
    ObjectValue jsonProduct = runtimeService().getVariableTyped(pi.getProcessInstanceId(), "product", false);
    assertThat(jsonProduct.getValueSerialized()).contains("EUR 34.99").contains("2015-03-01");
    
    Object deserializedProduct = runtimeService().getVariable(pi.getProcessInstanceId(), "product");
    assertThat(deserializedProduct).isEqualToComparingFieldByField(product);
  }

}
