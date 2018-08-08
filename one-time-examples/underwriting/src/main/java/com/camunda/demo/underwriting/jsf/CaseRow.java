package com.camunda.demo.underwriting.jsf;

import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.CaseInstance;

import com.camunda.demo.underwriting.domain.Application;

public class CaseRow {

  private CaseInstance caseInstance;
  private CaseDefinition caseDefinition;
  private Application creditApplication;

  public CaseRow(CaseInstance caseInstance, CaseDefinition caseDefinition, Application creditApplication) {
    this.caseInstance = caseInstance;
    this.caseDefinition = caseDefinition;
    this.creditApplication = creditApplication;
  }

  public CaseInstance getCaseInstance() {
    return caseInstance;
  }

  public Application getCreditApplication() {
    return creditApplication;
  }

  public CaseDefinition getCaseDefinition() {
    return caseDefinition;
  }

}
