package com.camunda.fox.demo.orderconfirmation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="ORDERCONF_ORDER")
// a table is not allowed to be named "order"
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @SuppressWarnings("unused")
  @Version
  private Long version;

  /**
   * For sure: Here we would need some logic to lookup the real customer, made
   * it simple for demo
   */
  @ManyToOne
  private Customer customer = new Customer();

  private int amount;
  private int discount;

  private String discountReason;

  public Long getId() {
    return id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getDiscount() {
    return discount;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }

  public String getDiscountReason() {
    return discountReason;
  }

  public void setDiscountReason(String discountReason) {
    this.discountReason = discountReason;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", customer=" + customer + ", amount=" + amount + ", discount=" + discount + "]";
  }
}
