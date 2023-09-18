package com.camunda.consulting.springboot_integration_test_example.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.camunda.consulting.springboot_integration_test_example.service.CreditCardService;

@Component
public class CreditCardDelegate implements JavaDelegate {

  CreditCardService creditCardService;
  
  @Autowired
  public CreditCardDelegate(CreditCardService creditCardService) {
    this.creditCardService = creditCardService;
  }
  
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String cardNumber = (String) execution.getVariable("cardNumber");
    String cvc = (String) execution.getVariable("cvc");
    String expiryDate = (String) execution.getVariable("expiryDate");
    Double amount = Double.valueOf(execution.getVariable("remainingAmount").toString());
    creditCardService.chargeAmount(cardNumber, cvc, expiryDate, amount);
  }

}
