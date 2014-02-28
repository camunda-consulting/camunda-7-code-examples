package org.camunda.bpm.example.acm.controller;

import org.camunda.bpm.engine.acm.CaseDefinition;
import org.camunda.bpm.engine.acm.CaseInstance;
import org.camunda.bpm.example.acm.domain.CreditApplication;

public class CaseRow {

  private CaseInstance caseInstance;
  private CaseDefinition caseDefinition;
  private CreditApplication creditApplication;

  public CaseRow(CaseInstance caseInstance, CaseDefinition caseDefinition, CreditApplication creditApplication) {
    this.caseInstance = caseInstance;
    this.caseDefinition = caseDefinition;
    this.creditApplication = creditApplication;
  }

  public CaseInstance getCaseInstance() {
    return caseInstance;
  }

  public CreditApplication getCreditApplication() {
    return creditApplication;
  }

  public CaseDefinition getCaseDefinition() {
    return caseDefinition;
  }

}
