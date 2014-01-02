package com.camunda.fox.demo.outerspace.errorhandling.nonarquillian;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test extends ProcessEngineTestCase {

  /**
   * Just tests if the process definition is deployable.
   */
  @Deployment(resources = "open-account-errorhandling.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

}
