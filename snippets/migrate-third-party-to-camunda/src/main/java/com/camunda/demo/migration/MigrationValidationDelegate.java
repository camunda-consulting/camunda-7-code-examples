package com.camunda.demo.migration;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MigrationValidationDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String migrationScenario = (String) execution.getVariable("migrationScenario");
    if (migrationScenario!=null) {
      throw new RuntimeException("ServiceTasks should not be executed from a migration Scenario");
    }

    if ("ServiceTaskWithError".equals(execution.getCurrentActivityId())) {
      Boolean throwError = (Boolean) execution.getVariable("throwError");
      if (throwError!=null && throwError) {
        throw new BpmnError("ERROR");
      }
    }
  }

}
