package com.camunda.demo.dmn.order.discount;

public class Order {
  
  private long amount;
  private int discount;
  
  public long getAmount() {
    return amount;
  }
  public void setAmount(long amount) {
    this.amount = amount;
  }
  public int getDiscount() {
    return discount;
  }
  public void setDiscount(int dicount) {
    this.discount = dicount;
  }

}
