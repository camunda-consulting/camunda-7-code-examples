package com.camunda.consulting.handleListObjects;

public class OrderItem {
  
  private int productId;
  private double productPrice;
  private int quantity;
  
  
  public OrderItem(int productId, double productPrice, int quantity) {
    super();
    this.productId = productId;
    this.productPrice = productPrice;
    this.quantity = quantity;
  }
  public int getProductId() {
    return productId;
  }
  public void setProductId(int productId) {
    this.productId = productId;
  }
  public double getProductPrice() {
    return productPrice;
  }
  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
