package com.camunda.demo.outerspace.wjax.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="WJAX_ORDER_ITEM")
public class OrderItem implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @GeneratedValue
  @Id
  private long id;
  
  private int amount;
  
  @OneToOne
  private Article article;

  
  public int getAmount() {
    return amount;
  }

  
  public void setAmount(int amount) {
    this.amount = amount;
  }

  
  public Article getArticle() {
    return article;
  }

  
  public void setArticle(Article article) {
    this.article = article;
  }


  
  public long getId() {
    return id;
  }

}
