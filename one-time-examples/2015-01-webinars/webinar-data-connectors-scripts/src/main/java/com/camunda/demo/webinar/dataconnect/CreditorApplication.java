package com.camunda.demo.webinar.dataconnect;

import java.io.Serializable;
import java.util.UUID;

public class CreditorApplication implements Serializable {
  
  private static final long serialVersionUID = 1L;

  private String id = UUID.randomUUID().toString();
  
  private String company;
  
  private String bankAccountNumber;
  private String bankIdentifierCode;
  private String bankName;
  
  private int rating;
  private boolean approved;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getCompany() {
    return company;
  }
  public void setCompany(String company) {
    this.company = company;
  }
  public String getBankAccountNumber() {
    return bankAccountNumber;
  }
  public void setBankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }
  public String getBankIdentifierCode() {
    return bankIdentifierCode;
  }
  public void setBankIdentifierCode(String bankIdentifierCode) {
    this.bankIdentifierCode = bankIdentifierCode;
  }
  public int getRating() {
    return rating;
  }
  public void setRating(int rating) {
    this.rating = rating;
  }
  public String getBankName() {
    return bankName;
  }
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
  public boolean isApproved() {
    return approved;
  }
  public void setApproved(boolean approved) {
    this.approved = approved;
  }

}
