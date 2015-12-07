package org.camunda.bpm.example.multiple_versions_parallel;

import java.util.Properties;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class SetVersionVariableListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    Properties version = new Properties();
    version.load(this.getClass().getResourceAsStream("/version.properties"));
    execution.setVariable("processDefinitionVersion", version.getProperty("maven.version.major") + "." + version.getProperty("maven.version.minor"));
  }

}
