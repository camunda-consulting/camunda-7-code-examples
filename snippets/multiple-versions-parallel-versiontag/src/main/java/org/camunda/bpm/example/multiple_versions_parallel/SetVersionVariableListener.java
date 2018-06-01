package org.camunda.bpm.example.multiple_versions_parallel;

import java.util.Properties;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class SetVersionVariableListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    Properties version = new Properties();
    version.load(this.getClass().getResourceAsStream("/version.properties"));
    String sourceVersion = version.getProperty("maven.version");

    execution.setVariable("startedWithSourceVersion", sourceVersion);
    
    
    String processDefinitionId = execution.getProcessDefinitionId();
    String versionTag = execution.getProcessEngineServices()
      .getRepositoryService()
      .createProcessDefinitionQuery()
      .processDefinitionId(processDefinitionId)
      .singleResult()
      .getVersionTag();

    execution.setVariable("startedWithVersionTag", versionTag);
  }

}
