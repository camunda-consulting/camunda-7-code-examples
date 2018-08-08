package com.camunda.demo.underwriting.jsf;

import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.task.Task;

import com.camunda.demo.underwriting.domain.Application;

public class TaskRow {

  private Task task;
  
  private Application application;
  private CaseInstance caseInstance;
  private CaseDefinition caseDefinition;

  public TaskRow(Task task, Application application, CaseInstance caseInstance, CaseDefinition caseDefinition) {
    super();
    this.task = task;
    this.application = application;
    this.caseInstance = caseInstance;
    this.caseDefinition = caseDefinition;
  }

  public Task getTask() {
    return task;
  }

  public Application getApplication() {
    return application;
  }

  public CaseInstance getCaseInstance() {
    return caseInstance;
  }

  public CaseDefinition getCaseDefinition() {
    return caseDefinition;
  }

}
