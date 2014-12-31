package org.camunda.bpm.example.acm.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.camunda.demo.webinar.cmmn.domain.Application;
import com.camunda.demo.webinar.cmmn.domain.ApplicationDomainService;


@Named
@SessionScoped
public class NewApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    private Application application = new Application();

    @Inject
    private ApplicationDomainService service;

    @Inject
    @Named
    private CaseController caseController;

    @Inject
    @Named
    private CaseListController caseListController;

    public String saveAction() {
        String caseId = service.saveCreditApplication(application);
        caseController.initByCaseId(caseId);
        caseListController.clearBuffer();
        
        // reset
        application = new Application();
        return "case-form";
    }

    public Application getApplication() {
        return this.application;
    }

}
