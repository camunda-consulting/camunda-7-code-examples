package org.camunda.bpm.example.acm.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.acm.CaseDefinition;
import org.camunda.bpm.example.acm.DroolsCaseHandlingRuleManagerImpl;
import org.camunda.bpm.example.acm.domain.CreditApplication;
import org.camunda.bpm.example.acm.domain.CreditApplicationService;


@Named
@SessionScoped
public class NewCreditApplication implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private CreditApplication creditApplication = new CreditApplication();

    @Inject
    private CreditApplicationService service;

    @Inject
    @Named
    private CaseController caseController;

    @Inject
    @Named
    private CaseListController caseListController;

    public String saveAction() {
        CaseDefinition.ruleManager = new DroolsCaseHandlingRuleManagerImpl();
        final String caseId = service.saveCreditApplication(creditApplication);
        creditApplication = new CreditApplication();
        caseController.initByCaseId(caseId);
        caseListController.clearBuffer();
        return "case-form";
    }

    public CreditApplication getCreditApplication() {
        return this.creditApplication;
    }

}
