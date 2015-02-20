package com.camunda.demo.underwriting.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.CaseInstance;

import com.camunda.demo.underwriting.Constants;
import com.camunda.demo.underwriting.domain.Application;

@Named
public class CaseListController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private ProcessEngine processEngine;

  @Inject
  @Named
  private CaseController caseController;

  private ArrayList<CaseRow> rows;

  public List<CaseRow> getRunningCases() {
    if (rows == null) {
      rows = new ArrayList<CaseRow>();
      List<CaseInstance> caseInstances = processEngine.getCaseService().createCaseInstanceQuery().active().list();
      for (CaseInstance caseInstance : caseInstances) {
        Application application = (Application) processEngine.getCaseService().getVariable(caseInstance.getId(), Constants.VAR_NAME_APPLICATION);
        CaseDefinition caseDefinition = processEngine.getRepositoryService().getCaseDefinition(caseInstance.getCaseDefinitionId());
        rows.add(new CaseRow(caseInstance, caseDefinition, application));
      }
    }
    return rows;
  }

  public String selectCase(final String caseId) {
    caseController.initByCaseInstanceId(caseId);
    return "case-form";
  }


}
