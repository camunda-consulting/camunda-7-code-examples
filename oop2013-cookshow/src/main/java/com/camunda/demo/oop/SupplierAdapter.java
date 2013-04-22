package com.camunda.demo.oop;

import java.util.UUID;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;

@Named("supplierAdapter")
@Stateless
@WebService
public class SupplierAdapter {

  public static final String SUPPLIER_CORRELATION_ID = "SUPPLIER_CORRELATION_ID";

  private static Logger log = Logger.getLogger(SupplierAdapter.class.getName());
  
  @Inject
  private BusinessProcess businessProcess;
  
  @Inject
  private RuntimeService runtimeService;
  
  @Inject
  @Named
  private Order order;
  
  public void sendOrder() {
    String correlationId = UUID.randomUUID().toString();
    businessProcess.setVariable(SUPPLIER_CORRELATION_ID, correlationId);
    
    log.info("\n\n\n\n### now we have sent the order.\n"+ order +" \nCorrelation id: " + correlationId + "\n\n\n\n");
  }

  public void orderConfirmationReceived(String correlationId, String somePayload) {
    ProcessInstance pi = runtimeService.createProcessInstanceQuery().variableValueEquals(SUPPLIER_CORRELATION_ID, correlationId).singleResult();
    Execution execution = runtimeService.createExecutionQuery().processInstanceId(pi.getId()).messageEventSubscriptionName("CONFIRMATION").singleResult();
    
    runtimeService.setVariable(execution.getId(), "supplierResponsePayload", somePayload);
    runtimeService.messageEventReceived("CONFIRMATION", execution.getId());
    
  }
  
}
