package org.camunda.bpm.example.multiple_versions_parallel;

import java.util.Properties;
import java.util.logging.Logger;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/**
 * This is an empty service implementation illustrating how to use a plain Java 
 * class as a BPMN 2.0 Service Task delegate.
 */
@Named("logger")
public class LoggerDelegate implements JavaDelegate {
 
  private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());
  
  public void execute(DelegateExecution execution) throws Exception {
    String processDefinitionId = execution.getProcessDefinitionId();
    ProcessDefinition processDefinition = execution.getProcessEngineServices()
      .getRepositoryService()
      .createProcessDefinitionQuery()
      .processDefinitionId(processDefinitionId)
      .singleResult();
    int processDefinitionVersion = processDefinition.getVersion();
    String versionTag = processDefinition.getVersionTag();

    Properties version = new Properties();
    version.load(this.getClass().getResourceAsStream("/version.properties"));
    String sourceVersion = version.getProperty("maven.version");

    LOGGER.info("\n\n  ... LoggerDelegate invoked by "
            + "processDefinitionId=" + processDefinition
            + "processDefinitionId=" + processDefinition
            + ", version=" + processDefinitionVersion
            + ", versionTag=" + versionTag
            + ", sourceVersion=" + sourceVersion
            + ", activtyId=" + execution.getCurrentActivityId()
            + ", activtyName='" + execution.getCurrentActivityName() + "'"
            + ", processInstanceId=" + execution.getProcessInstanceId()
            + ", businessKey=" + execution.getProcessBusinessKey()
            + ", executionId=" + execution.getId()
            + ", variables=" + execution.getVariables()
            + " \n\n");
    
  }

}
