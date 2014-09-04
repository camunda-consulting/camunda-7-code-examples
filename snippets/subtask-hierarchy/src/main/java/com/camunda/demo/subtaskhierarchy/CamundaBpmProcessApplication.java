package com.camunda.demo.subtaskhierarchy;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;

/**
 * Process Application exposing this application's resources the process engine.
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

  private static final String PROCESS_DEFINITION_KEY = "taskmanagement";

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access
   * the processes the application has deployed.
   */
  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {
    long processCount = processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).count();

    if (processCount == 0) {
      // start an initial process instance
      Map<String, Object> variables = new HashMap<String, Object>();
      variables.put("taskName", "Camunda BPM evaluieren");
      variables.put("taskAssignee", "demo");

      ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, "vorgang-27" , variables);

      variables = new HashMap<String, Object>();
      variables.put("subtaskName", "Evaluierungslizenz beantragen");
      variables.put("subtaskAssignee", "demo");
      processEngine.getRuntimeService().messageEventReceived("SUBTASK_WANTED", processInstance.getId(), variables);

      variables = new HashMap<String, Object>();
      variables.put("subtaskName", "Datenbank-Schema anlegen");
      variables.put("subtaskAssignee", "demo");
      processEngine.getRuntimeService().messageEventReceived("SUBTASK_WANTED", processInstance.getId(), variables);
}
  }

}
