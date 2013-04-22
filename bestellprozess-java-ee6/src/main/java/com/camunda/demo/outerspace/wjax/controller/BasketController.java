package com.camunda.demo.outerspace.wjax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.camunda.demo.outerspace.wjax.entity.Article;
import com.camunda.demo.outerspace.wjax.entity.Order;
import com.camunda.demo.outerspace.wjax.entity.OrderItem;
import com.camunda.demo.outerspace.wjax.service.OrderService;

@SessionScoped
@Named("basket")
public class BasketController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private OrderService orderService;

  private String customer;

  private List<OrderItem> items = new ArrayList<OrderItem>();

  public void addItem(Article article) {
    for (OrderItem item : items) {
      if (item.getArticle().getId().equals(article.getId())) {
        item.setAmount(item.getAmount()+1);
        return;
      }
    }
    OrderItem item = new OrderItem();
    item.setAmount(1);
    item.setArticle(article);
    items.add(item);
  }
  
  /**
   * clear basket (remove all added items)
   */
  public void clear() {
    items = new ArrayList<OrderItem>();
    customer = "";
  }

  public void startOrder() {
    Order order = new Order();
    order.setCustomer(customer);
    order.getItems().addAll(getItems());
    orderService.placeOrder(order);
    clear();
  }
  
  public String getInfoString() {
    StringBuffer buf = new StringBuffer();
    for (OrderItem item : items) {
      buf.append(item.getAmount() + "x " + item.getArticle().getId());
      buf.append("; ");
    }
    return buf.toString();
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

}
