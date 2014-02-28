package org.camunda.bpm.example.acm.domain;

import java.util.Collections;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.acm.CaseInstance;

@Stateless
public class CreditApplicationService {

  public static final String CREDIT_APPLICATION_ID = "creditApplicationId";

  @PersistenceContext
  private EntityManager entityManager;
  
  @Inject
  private ProcessEngine processEngine;

  public String saveCreditApplication(final CreditApplication creditApp) {
    entityManager.persist(creditApp);
    final String caseManager = null;
    final Map<String, Object> variables = Collections.singletonMap(CREDIT_APPLICATION_ID, (Object) creditApp.getId());
    final CaseInstance caseInstance = processEngine.getCaseService().createCaseInstance("creditApplication", null, caseManager, variables);
    return caseInstance.getId();
  }

  public CreditApplication findCreditApplicationByCaseId(final String caseId) {
    final long creditApplicationId = (Long) processEngine.getCaseService().getVariable(caseId, CREDIT_APPLICATION_ID);
    return entityManager.find(CreditApplication.class, creditApplicationId);
  }

  public CreditApplication findCreditApplicationById(final long creditApplicationId) {
    return entityManager.find(CreditApplication.class, creditApplicationId);
  }

  public void update(final CreditApplication creditApplication) {
    entityManager.merge(creditApplication);
  }

}
