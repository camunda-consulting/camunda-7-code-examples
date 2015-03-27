package com.camunda.demo.webinar.testing;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named
public class CustomerServiceAdapter implements JavaDelegate {
  
  @Inject
  private CustomerServiceImpl customerService; 

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    // Input Mapping
    Customer customer = new Customer();
    customer.setName((String)execution.getVariable("customerName"));
    
    // Service Call
    String customerId = customerService.createCustomer(customer );
    
    // Output Mapping
    execution.setVariable("customerId", customerId);
  }

}
