package org.camunda.bpm.demo.orderconfirmation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class DiscountRuleEntry implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @SuppressWarnings("unused")
  @Version
  private Long version;
  
  private String name;
  private int amountMin;
  private int amountMax;
  private int discount;

  public DiscountRuleEntry() { }

  public DiscountRuleEntry(String name, int amountMin, int amountMax, int discount) {
    this.name = name;
    this.amountMin = amountMin;
    this.amountMax = amountMax;
    this.discount = discount;
  }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getAmountMin() {
    return amountMin;
  }
  
  public void setAmountMin(int amountMin) {
    this.amountMin = amountMin;
  }
  
  public int getAmountMax() {
    return amountMax;
  }
  
  public void setAmountMax(int amountMax) {
    this.amountMax = amountMax;
  }
  
  public int getDiscount() {
    return discount;
  }
  
  public void setDiscount(int discount) {
    this.discount = discount;
  }

  @Override
  public String toString() {
    return "DiscountRuleEntry [id=" + id + ", name=" + name + ", amountMin=" + amountMin + ", amountMax=" + amountMax + ", discount=" + discount + "]";
  }

  
  public Long getId() {
    return id;
  }

  
  public void setId(Long id) {
    this.id = id;
  }

}
