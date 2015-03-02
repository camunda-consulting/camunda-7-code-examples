package com.camunda.consulting.extendedSerializationProcess.nonarquillian;

import java.math.BigDecimal;

import javax.annotation.meta.When;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.camunda.bpm.extension.mockito.Expressions;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.consulting.extendedSerializationProcess.ComplexDataObject;
import com.camunda.consulting.extendedSerializationProcess.ComplexObjectCreator;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "extended-serialization-process";

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
  public void testSerializationOfComplexObject() {
    ComplexObjectCreator complexObjectCreator = Expressions.registerMockInstance(ComplexObjectCreator.class);
    ComplexDataObject complexTestValue = new ComplexDataObject();
    complexTestValue.setName("test name");
    complexTestValue.setPrice(Money.of(CurrencyUnit.EUR, new BigDecimal("34.99")));
    when(complexObjectCreator.create()).thenReturn(complexTestValue);
    
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    assertThat(pi).isWaitingAt("UserTask_1").hasVariables("complexValue");
    
    ObjectValue complexValueTyped = runtimeService().getVariableTyped(pi.getProcessInstanceId(), "complexValue", false);
    assertThat(complexValueTyped.getValueSerialized()).contains("EUR 34.99");
  }

}
