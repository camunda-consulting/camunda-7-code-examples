package com.camunda.consulting.springboot_integration_test_example.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.camunda.consulting.springboot_integration_test_example.service.CustomerService;

@Component
public class CustomerCreditDelegate implements JavaDelegate {
  
  CustomerService customerService;
  
  @Autowired
  public CustomerCreditDelegate(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    
    String customerId = (String) execution.getVariable("customerId");
    Double orderTotal = Double.valueOf(execution.getVariable("orderTotal").toString());
    
    Double customerCredit = customerService.getCustomerCredit(customerId);
    Double remainingAmount = customerService.deductCredit(customerId, orderTotal, customerCredit);
    
    execution.setVariable("remainingAmount", remainingAmount);
  }

}
