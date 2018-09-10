package org.camunda.bpm.example.multiple_versions_parallel;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class VersionHelper {

  public static String findLatestProcessDefinitionByVersionTag(String processDefinitionKey, String versionTag, ProcessEngineServices processEngine) {
    String processDefinitionId = processEngine.getRepositoryService().createProcessDefinitionQuery()
      .processDefinitionKey(processDefinitionKey)
      .versionTag(versionTag)
      .latestVersion()
      .singleResult()
      .getId();
    return processDefinitionId;
  }

  public static ProcessDefinition findLatestProcessDefinitionOfLatestVersionTag(String processDefinitionKey, RepositoryService repositoryService) {
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
      .processDefinitionKey(processDefinitionKey)
      .orderByVersionTag() // TODO what in case of version 10.0.0 and alphabetic sorting
      .desc()
      .orderByProcessDefinitionVersion()
      .desc()
      .listPage(0, 1)
      .get(0);
    return processDefinition;
  }

  public static ProcessInstance startProcessDefinitionByVersionTag(String processDefinitionKey, String versionTag, ProcessEngineServices processEngine) {
    String processDefinitionId = findLatestProcessDefinitionByVersionTag(processDefinitionKey, versionTag, processEngine);
    
    ProcessInstance processInstance = processEngine.getRuntimeService().
        startProcessInstanceById(processDefinitionId);
    return processInstance;
  }

}
