package com.camunda.demo.outerspace.wjax.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WJAX_STOCK_ITEM")
public class StockItem {

  @Id
  private String id;

  private int amountOnStock;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getAmountOnStock() {
    return amountOnStock;
  }

  public void setAmountOnStock(int amountOnStock) {
    this.amountOnStock = amountOnStock;
  }
}
