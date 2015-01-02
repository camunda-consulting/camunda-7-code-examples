package com.camunda.demo.webinar.cmmn.jsf;

import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.task.Task;

import com.camunda.demo.webinar.cmmn.domain.Application;

public class TaskRow {

  private Task task;
  
  private Application creditApplication;
  private CaseInstance caseInstance;
  private CaseDefinition caseDefinition;

  public TaskRow(Task task, Application creditApplication, CaseInstance caseInstance, CaseDefinition caseDefinition) {
    super();
    this.task = task;
    this.creditApplication = creditApplication;
    this.caseInstance = caseInstance;
    this.caseDefinition = caseDefinition;
  }

  public Task getTask() {
    return task;
  }

  public Application getCreditApplication() {
    return creditApplication;
  }

  public CaseInstance getCaseInstance() {
    return caseInstance;
  }

  public CaseDefinition getCaseDefinition() {
    return caseDefinition;
  }

}
