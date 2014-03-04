package org.camunda.bpm.example.acm.controller;

import org.camunda.bpm.engine.acm.CaseDefinition;
import org.camunda.bpm.engine.acm.CaseInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.example.acm.domain.CreditApplication;

public class TaskRow {

  private Task task;
  private CreditApplication creditApplication;
  private CaseInstance caseInstance;
  private CaseDefinition caseDefinition;

  public TaskRow(Task task, CreditApplication creditApplication, CaseInstance caseInstance, CaseDefinition caseDefinition) {
    super();
    this.task = task;
    this.creditApplication = creditApplication;
    this.caseInstance = caseInstance;
    this.caseDefinition = caseDefinition;
  }

  public Task getTask() {
    return task;
  }

  public CreditApplication getCreditApplication() {
    return creditApplication;
  }

  public CaseInstance getCaseInstance() {
    return caseInstance;
  }

  public CaseDefinition getCaseDefinition() {
    return caseDefinition;
  }

}
