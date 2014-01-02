package org.camunda.bpm.examples.noarquillian;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;

/**
 * Test case starting an in-memory database backed Process Engine just to test
 * if the process definition is deployable.
 * 
 * @author ruecker
 */
public class ParsingTestCase extends ProcessEngineTestCase {

  @Deployment(resources = "FourEyesAdvanced.bpmn")
  public void testParsingAndDeployment() {
  }

}