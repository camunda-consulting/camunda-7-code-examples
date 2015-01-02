package com.camunda.demo.webinar.cmmn.jsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import com.camunda.demo.webinar.cmmn.Constants;
import com.camunda.demo.webinar.cmmn.domain.Application;


@Named
@SessionScoped
public class NewApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    private Application application = new Application();
    
    @Inject
    private ProcessEngine processEngine;

    @Inject
    @Named
    private CaseController caseController;

    @Inject
    @Named
    private CaseListController caseListController;

    public String saveAction() {
        CaseInstance caseInstance = processEngine.getCaseService().createCaseInstanceByKey( //
            "underwriting", 
            Variables.createVariables().putValueTyped( //
                Constants.VAR_NAME_APPLICATION, //
                Variables.objectValue(application).serializationDataFormat(SerializationDataFormats.JSON).create()));
        
        caseController.initByCaseInstanceId(caseInstance.getId());
        caseListController.clearBuffer();
        
        // reset
        application = new Application();
        return "case-form";
    }

    public Application getApplication() {
        return this.application;
    }

}
