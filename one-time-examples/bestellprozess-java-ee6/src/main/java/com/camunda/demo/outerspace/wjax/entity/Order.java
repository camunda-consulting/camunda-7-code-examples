package com.camunda.demo.outerspace.wjax.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="WJAX_ORDER")
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;
    
  private String customer;
  
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
  private List<OrderItem> items = new ArrayList<OrderItem>();

  
  public String getCustomer() {
    return customer;
  }

  
  public void setCustomer(String customer) {
    this.customer = customer;
  }

  
  public Long getId() {
    return id;
  }

  
  public List<OrderItem> getItems() {
    return items;
  }
  
}
