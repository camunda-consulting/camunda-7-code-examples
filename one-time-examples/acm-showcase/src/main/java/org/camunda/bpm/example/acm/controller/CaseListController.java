package org.camunda.bpm.example.acm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.acm.CaseDefinition;
import org.camunda.bpm.engine.acm.CaseInstance;
import org.camunda.bpm.example.acm.domain.CreditApplication;
import org.camunda.bpm.example.acm.domain.CreditApplicationService;

@Named
@SessionScoped
public class CaseListController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private ProcessEngine processEngine;

    @Inject
    private CreditApplicationService service;

    @Inject
    @Named
    private CaseController caseController;

    private String needle;

    private ArrayList<CaseRow> rows;
    private ArrayList<CaseRow> cachedSearchResult;

    public List<CaseRow> getRunningCases() {
        if (rows == null) {
            rows = new ArrayList<CaseRow>();
            final List<CaseInstance> caseInstances = processEngine.getCaseService().createCaseInstanceQuery().active().list();
            for (final CaseInstance caseInstance : caseInstances) {
                final CreditApplication creditApplication = service.findCreditApplicationByCaseId(caseInstance.getId());
                final CaseDefinition caseDefinition = CaseDefinition.mockCaseDefinitions.get(caseInstance.getCaseDefinitionId());
                rows.add(new CaseRow(caseInstance, caseDefinition, creditApplication));
            }
        }
        return rows;
    }

    public List<CaseRow> getSearchResult() {
        final List<CaseRow> running = getRunningCases();
        if (cachedSearchResult == null) {
            cachedSearchResult = new ArrayList<CaseRow>();
            for (final CaseRow caseRow : running) {
                try {
                    if (caseRow.getCaseDefinition().getName().contains(needle)) {
                        cachedSearchResult.add(caseRow);
                        continue;
                    }
                } catch (final NullPointerException npe) {
                }
                try {
                    if (caseRow.getCaseInstance().getCaseDefinitionId().contains(needle)) {
                        cachedSearchResult.add(caseRow);
                        continue;
                    }
                } catch (final NullPointerException npe) {
                }
                // search for other data...
//                try {
//                    if (caseRow.getCreditApplication()....().contains(needle)) {
//                        cachedSearchResult.add(caseRow);
//                        continue;
//                    }
//                } catch (final NullPointerException npe) {                
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

    /**
     * @param needle the needle to set
     */
    public void setNeedle(final String needle) {
        this.needle = needle;
    }

    public String clearSearch() {
        cachedSearchResult = null;
        return "vorgaenge";
    }

    /**
     * 
     */
    public void clearBuffer() {
        clearSearch();
        rows = null;
    }

}
