package org.camunda.bpm.example.multiple_versions_parallel;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test extends ProcessEngineTestCase {

  private static final String PROCESS_DEFINITION_KEY = "multiple-versions-parallel-versiontag";

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
  }

  @Deployment(resources = "process.bpmn")
  public void testStartLatestVersionOfSpecificVersionTag() throws IOException {
    Properties version = new Properties();
    version.load(this.getClass().getResourceAsStream("/version.properties"));
    String processDefinitionKey = PROCESS_DEFINITION_KEY;
    String versionTag = version.getProperty("maven.version.major");
    
    ProcessInstance processInstance = VersionHelper.startProcessDefinitionByVersionTag(processDefinitionKey, versionTag, processEngine);

    Object startedWithVersionTag = processEngine.getRuntimeService().getVariable(processInstance.getId(), "startedWithVersionTag");
    assertEquals(versionTag, startedWithVersionTag);
    System.out.println(startedWithVersionTag);
  }

  public void testStartLatestProcessDefinitionOfLatestVersionTag() {
    repositoryService.createDeployment()
      .addClasspathResource("process-v7.4.bpmn")
      .deploy();
    repositoryService.createDeployment()
      .addClasspathResource("process.bpmn")
      .deploy();
    repositoryService.createDeployment()
      .addClasspathResource("process-v7.5.bpmn")
      .deploy();

    List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
    for (ProcessDefinition pD : processDefinitions) {
      System.out.println(pD.getId() + " " + pD.getDeploymentId() + " " + pD.getVersionTag());
    }
    
    ProcessDefinition processDefinition = VersionHelper.findLatestProcessDefinitionOfLatestVersionTag(PROCESS_DEFINITION_KEY, repositoryService);
    
    System.out.println(processDefinition.getId());
    assertEquals("7", processDefinition.getVersionTag());
    
    ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());

    Object startedWithVersionTag = runtimeService.getVariable(processInstance.getId(), "startedWithVersionTag");
    assertEquals("7", startedWithVersionTag);
    System.out.println(startedWithVersionTag);
  }

}
