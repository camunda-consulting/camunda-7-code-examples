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
import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.delegate.BpmnError;

import com.camunda.demo.outerspace.wjax.entity.Order;
import com.camunda.demo.outerspace.wjax.entity.OrderItem;

@Stateless
@Named
public class OrderService {
  
  private static final String VAR_NAME_orderId = "orderId";

  @PersistenceContext
  private EntityManager entityManager;
  
  @Inject
  private RuntimeService runtimeService;
  
  public long placeOrder(Order order) {
    entityManager.persist(order);
    // process variable:
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put(VAR_NAME_orderId, order.getId());
    // OR businessKey (alternative!)
    String businessKey = String.valueOf(order.getId());
    
    runtimeService.startProcessInstanceByKey("wjax2012-bestellprozess", businessKey, variables);
    return order.getId();
  }
  
  @Inject
  private BusinessProcess businessProcess;

  @Produces 
  @Named
  public Order order() {
    return entityManager.find(Order.class, businessProcess.getVariable(VAR_NAME_orderId));
  }  
  
//  @Inject @Named
//  private Map<String, Object> processVariables;
  
  public void persist(Order o) {
    entityManager.persist(o);
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
