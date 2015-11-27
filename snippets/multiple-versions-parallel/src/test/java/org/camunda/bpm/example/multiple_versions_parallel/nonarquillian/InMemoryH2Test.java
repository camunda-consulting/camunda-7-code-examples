package org.camunda.bpm.example.multiple_versions_parallel.nonarquillian;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.camunda.bpm.engine.test.Deployment;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test extends ProcessEngineTestCase {

  private static final String PROCESS_DEFINITION_KEY = "multiple-versions-parallel-v";

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
  }

  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() throws IOException {
    Properties version = new Properties();
    version.load(this.getClass().getResourceAsStream("/version.properties"));
    runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY + version.getProperty("maven.version.major") + "." + version.getProperty("maven.version.minor"));
  }

  @Deployment(resources = { "process.bpmn", "process-v7.4.bpmn" })
  public void testStartLatestVerison() {
    List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
      .processDefinitionKeyLike(PROCESS_DEFINITION_KEY + "%")
      .orderByProcessDefinitionName()
      .desc()
      .latestVersion()
      .listPage(0, 1);
    
    assertEquals(PROCESS_DEFINITION_KEY + "7.4", processDefinitions.get(0).getKey());
    
    runtimeService.startProcessInstanceById(processDefinitions.get(0).getId());
  }

}
