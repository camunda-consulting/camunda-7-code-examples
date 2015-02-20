package com.camunda.demo.underwriting.jsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import com.camunda.demo.underwriting.Constants;
import com.camunda.demo.underwriting.domain.Application;


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
        processEngine.getRuntimeService().startProcessInstanceByKey(
//        CaseInstance caseInstance = processEngine.getCaseService().createCaseInstanceByKey( //
            "underwriting", 
            Variables.createVariables().putValueTyped( //
                Constants.VAR_NAME_APPLICATION, //
                Variables.objectValue(application).serializationDataFormat(SerializationDataFormats.JSON).create()));
        
        // cannot yet jump into case as it was not created yet!
        //caseController.initByCaseInstanceId(caseInstance.getId());
        
        // reset
        application = new Application();
        return "tasklist";
//        return "case-form";
    }

    public Application getApplication() {
        return this.application;
    }

}
