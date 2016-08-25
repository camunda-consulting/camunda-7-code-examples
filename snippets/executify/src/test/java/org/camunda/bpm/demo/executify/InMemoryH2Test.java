package org.camunda.bpm.demo.executify;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
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

  private static final String PROCESS_DEFINITION_KEY = "executify";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @Test
  public void makeModelDeployable() throws IOException {
    InputStream stream = this.getClass().getResourceAsStream("/models/request-process.bpmn");
    BpmnModelInstance modelInstance = Bpmn.readModelFromStream(stream);
    new BpmnExecutifier().executify(modelInstance);
    String bpmn = Bpmn.convertToString(modelInstance);
    FileUtils.writeStringToFile(new File("src/test/resources/models/request-process.executable.bpmn"), bpmn);
    processEngine().getRepositoryService()
      .createDeployment()
      .addString("request-process.bpmn", bpmn)
      .deploy();
  }
  
}
