package org.camunda.bpm.example.acm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CreditApplication {

  @GeneratedValue
  @Id
  private long id;
  
  private String customerId;
  private String customerName;
  
  private String ratingCreditreform;
  private String ratingSchufa;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getRatingCreditreform() {
    return ratingCreditreform;
  }

  public void setRatingCreditreform(String ratingCreditreform) {
    this.ratingCreditreform = ratingCreditreform;
  }

  public String getRatingSchufa() {
    return ratingSchufa;
  }

  public void setRatingSchufa(String ratingSchufa) {
    this.ratingSchufa = ratingSchufa;
  }

}
