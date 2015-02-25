package com.camunda.consulting.extendedSerialization;

import java.math.BigDecimal;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.spin.DataFormats;
import org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.consulting.extendedSerialization.serializer.MoneyDeserializer;
import com.camunda.consulting.extendedSerialization.serializer.MoneySerializer;

import spinjar.com.fasterxml.jackson.databind.ObjectMapper;
import spinjar.com.fasterxml.jackson.databind.module.SimpleModule;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

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
    
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, withVariables("product", product));
    assertThat(pi).isWaitingAt("UserTask_1");
    
    ObjectValue jsonProduct = runtimeService().getVariableTyped(pi.getProcessInstanceId(), "product", false);
    assertThat(jsonProduct.getValueSerialized()).contains("EUR 34.99");
    
    Object deserializedProduct = runtimeService().getVariable(pi.getProcessInstanceId(), "product");
    assertThat(deserializedProduct).isEqualToComparingFieldByField(product);
  }

}
