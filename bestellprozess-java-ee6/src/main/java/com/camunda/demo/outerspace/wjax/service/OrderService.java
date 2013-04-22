package com.camunda.demo.outerspace.wjax.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.ApplicationException;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.BpmnError;

import com.camunda.demo.outerspace.wjax.entity.Order;
import com.camunda.demo.outerspace.wjax.entity.OrderItem;

@Stateless
@Named
public class OrderService {
  
  @PersistenceContext
  private EntityManager entityManager;
  
  @Inject
  private RuntimeService runtimeService;
  
  public long placeOrder(Order order) {
    entityManager.persist(order);
    
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("orderId", order.getId());
    // This could be an alternative:
    String businessKey = String.valueOf(order.getId());
    
    runtimeService.startProcessInstanceByKey("wjax2012-bestellprozess", businessKey, variables);
    return order.getId();
  }
  
  public void persist(Order o) {
    entityManager.persist(o);
  }

//  @Inject
//  private BusinessProcess businessProcess;

  @Inject @Named
  private Map<String, Object> processVariables;

  @Produces 
  @Named
  public Order order() {
    return entityManager.find(Order.class, processVariables.get("orderId"));
  }  
  
  @Inject
  private StockService stockService;
  
  public void reserveGoods() {
    for (OrderItem item : order().getItems()) {
      if (stockService.getAmountOnStock(item.getArticle().getId()) < item.getAmount()) {
        throw new NonRollbackBpmnError("goods-not-on-stock", "Article " + item.getArticle().getId() + " not on stock");
      }
      stockService.reserveGoods(item.getArticle().getId(), item.getAmount());
    }
  }
  
  @ApplicationException
  static class NonRollbackBpmnError extends BpmnError {
    private static final long serialVersionUID = 1L;
    public NonRollbackBpmnError(String errorCode, String message) {
      super(errorCode, message);
    }
  }
}
