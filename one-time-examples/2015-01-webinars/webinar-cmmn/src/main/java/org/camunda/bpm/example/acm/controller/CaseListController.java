package org.camunda.bpm.example.acm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.CaseInstance;

import com.camunda.demo.webinar.cmmn.domain.Application;
import com.camunda.demo.webinar.cmmn.domain.ApplicationDomainService;

@Named
@SessionScoped
public class CaseListController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private ProcessEngine processEngine;

  @Inject
  private ApplicationDomainService service;

  @Inject
  @Named
  private CaseController caseController;

  private String needle;

  private ArrayList<CaseRow> rows;
  private ArrayList<CaseRow> cachedSearchResult;

  public List<CaseRow> getRunningCases() {
    if (rows == null) {
      rows = new ArrayList<CaseRow>();
      List<CaseInstance> caseInstances = processEngine.getCaseService().createCaseInstanceQuery().active().list();
      for (CaseInstance caseInstance : caseInstances) {
        Application application = service.findApplicationByCaseId(caseInstance.getId());
        CaseDefinition caseDefinition = processEngine.getRepositoryService().getCaseDefinition(caseInstance.getCaseDefinitionId());
        rows.add(new CaseRow(caseInstance, caseDefinition, application));
      }
    }
    return rows;
  }

  public List<CaseRow> getSearchResult() {
    List<CaseRow> running = getRunningCases();
    if (cachedSearchResult == null) {
      cachedSearchResult = new ArrayList<CaseRow>();
      for (CaseRow caseRow : running) {
        try {
          if (caseRow.getCaseDefinition().getName().contains(needle)) {
            cachedSearchResult.add(caseRow);
            continue;
          }
        } catch (NullPointerException npe) {
        }
        try {
          if (caseRow.getCaseInstance().getCaseDefinitionId().contains(needle)) {
            cachedSearchResult.add(caseRow);
            continue;
          }
        } catch (NullPointerException npe) {
        }
        // search for other data...
        // try {
        // if (caseRow.getCreditApplication()....().contains(needle)) {
        // cachedSearchResult.add(caseRow);
        // continue;
        // }
        // } catch (final NullPointerException npe) {
      }
    }
    needle = null;
    return cachedSearchResult;
  }

  public String selectCase(final String caseId) {
    caseController.initByCaseId(caseId);
    return "case-form";
  }

  public String getNeedle() {
    return needle;
  }

  public void setNeedle(final String needle) {
    this.needle = needle;
  }

  public String clearSearch() {
    cachedSearchResult = null;
    return "vorgaenge";
  }

  public void clearBuffer() {
    clearSearch();
    rows = null;
  }

}
