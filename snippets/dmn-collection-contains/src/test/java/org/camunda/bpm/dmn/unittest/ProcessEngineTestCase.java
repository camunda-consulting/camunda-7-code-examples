package org.camunda.bpm.dmn.unittest;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class ProcessEngineTestCase {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  static {
    // enable more detailed logging
    // LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
    // LogFactory.useJdkLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "Example.dmn")
  public void customerWithOneHobby() {
//    processEngine().getRepositoryService().createDeployment().addClasspathResource("Example.dmn").deploy();
    
    Customer customer = new Customer();
    customer.getHobbies().add("hiking");

    VariableMap variables = Variables.createVariables().putValue("customer", customer);

    DmnDecisionTableResult results = processEngine().getDecisionService().evaluateDecisionTableByKey("orderDecision", variables);

    assertThat(results).hasSize(1);

    assertThat(results.getSingleResult()).containsOnly(entry("result", "Hiking is great"));
  }

}
