package com.camunda.demo.webinar.cmmn.domain;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.variable.Variables;

@Stateless
public class ApplicationDomainService {

  public static final String CREDIT_APPLICATION_ID = "creditApplicationId";

  @PersistenceContext
  private EntityManager entityManager;
  
  @Inject
  private ProcessEngine processEngine;

  public String saveCreditApplication(final Application creditApp) {
    // persist entity
    entityManager.persist(creditApp);
    
    // flush (needed to generate the ID with some databases configurations)
    entityManager.flush();
    
    // create the case and hand over id as case variable
    CaseInstance caseInstance = processEngine.getCaseService()
        .createCaseInstanceByKey("underwriting", Variables.createVariables().putValue(CREDIT_APPLICATION_ID, creditApp.getId()));
    
    // return case id
    return caseInstance.getId();
  }

  public Application findApplicationByCaseId(final String caseId) {
    final Long applicationId = (Long) processEngine.getCaseService().getVariable(caseId, CREDIT_APPLICATION_ID);
    if (applicationId==null) {
      return null;
    }
    return entityManager.find(Application.class, applicationId);
  }

  public Application findApplicationById(final long applicationId) {
    return entityManager.find(Application.class, applicationId);
  }

  public void update(final Application application) {
    entityManager.merge(application);
  }

}
