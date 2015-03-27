package com.camunda.demo.webinar.testing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is a service you do not want to call on Scope 1!
 */
public class CustomerServiceImpl {
  
  @PersistenceContext
  private EntityManager em;
  
  public String createCustomer(Customer customer) {
    em.persist(customer);
    em.flush();
    return String.valueOf(customer.getId());    
  }

}
