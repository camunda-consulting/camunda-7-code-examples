package com.camunda.consulting.extendedSerializationProcess;

import java.io.Serializable;

import org.joda.money.Money;
import org.joda.time.LocalDate;

public class ComplexDataObject implements Serializable {
  
  private static final long serialVersionUID = 1L;
  private String name; 
  private Money price;
  private LocalDate purchaseDate;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Money getPrice() {
    return price;
  }
  public void setPrice(Money price) {
    this.price = price;
  }
  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }
  public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

}
