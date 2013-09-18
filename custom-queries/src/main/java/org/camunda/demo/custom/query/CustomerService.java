package org.camunda.demo.custom.query;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerService {

  @PersistenceContext
  private EntityManager entityManager;
  
  public void persist(Customer customer) {
    entityManager.persist(customer);    
  }
  
  public void removeAll() {
    entityManager.createQuery("DELETE FROM Customer c");
  }
}
