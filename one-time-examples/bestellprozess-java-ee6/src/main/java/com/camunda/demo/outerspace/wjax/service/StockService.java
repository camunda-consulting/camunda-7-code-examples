package com.camunda.demo.outerspace.wjax.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.camunda.demo.outerspace.wjax.entity.Article;
import com.camunda.demo.outerspace.wjax.entity.StockItem;

@Stateless
public class StockService {
  
  @PersistenceContext
  private EntityManager entityManager;
  
  public void persist(Article a) {
    entityManager.persist(a);
  }
  
  public void setAmountOnStock(String articleId, int amount) {
    StockItem stockItem = entityManager.find(StockItem.class, articleId);
    if (stockItem==null) {
      stockItem = new StockItem();
      stockItem.setId(articleId);
      entityManager.persist(stockItem);
    }
    stockItem.setAmountOnStock(amount);      
  }
  
  public int getAmountOnStock(String articleId) {
    StockItem stockItem = entityManager.find(StockItem.class, articleId);
    if (stockItem==null) {
      return 0;
    } else {
      return stockItem.getAmountOnStock();
    }
  }

  public void reserveGoods(String articleId, int amount) {
    StockItem stockItem = entityManager.find(StockItem.class, articleId);
    if (stockItem==null) {
      throw new IllegalStateException("Article " + articleId + " not on stock.");
    }
    if (stockItem.getAmountOnStock() < amount) {
      throw new IllegalStateException("Amount on stock of article " + articleId + " not sufficient ("+stockItem.getAmountOnStock()+"<"+amount+").");
    }
    stockItem.setAmountOnStock(stockItem.getAmountOnStock()-amount);
  }

}
