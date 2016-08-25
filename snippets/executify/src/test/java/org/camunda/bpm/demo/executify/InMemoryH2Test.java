package org.camunda.bpm.demo.executify;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
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

  private static final String INPUT_DIR = "/models/";
  private static final String OUTPUT_DIR = "src/test/resources/models/";

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @Test
  public void makeModelDeployable() throws IOException {
    String fileName = "request-process.bpmn";
    InputStream stream = getClass().getResourceAsStream(INPUT_DIR + fileName);
    BpmnModelInstance modelInstance = new BpmnExecutifier().executify(stream);
    String xml = Bpmn.convertToString(modelInstance);
    writeToFile(fileName, xml);
    repositoryService()
      .createDeployment()
      .addString(fileName, xml)
      .deploy();
  }

  @Test
  public void makeModelsDeployable() throws IOException {
    String[] fileNames = {"ApplicationCheck.cmmn", "InsuranceApplication.bpmn", "RiskAssessment.dmn"};
    Map<String,InputStream> models = new HashMap<String, InputStream> ();
    for (String fileName: fileNames) {
      models.put(fileName, getClass().getResourceAsStream(INPUT_DIR + fileName));
    }
    Executify executify = new Executify();
    executify.setGeneratePredictableConditionExpressions(true);
    executify.setRemoveDecisionRefs(true);
    List<ExecutableModel> executableModels = executify.makeExecutable(models);
    
    for (ExecutableModel executableModel : executableModels) {
      writeToFile(executableModel.getFilename(), executableModel.getXmlString());
    }

    DeploymentBuilder deployment = repositoryService().createDeployment();
    for (ExecutableModel executableModel : executableModels) {
      executableModel.addToDeployment(deployment);
    }
    deployment.deploy();
  }  

  private void writeToFile(String fileName, String xml) throws IOException {
    FileUtils.writeStringToFile(new File(OUTPUT_DIR + fileName.replace(".bpmn", ".executable.bpmn").replace(".cmmn", ".executable.cmmn").replace(".dmn", ".executable.dmn")), xml);
  }
}

