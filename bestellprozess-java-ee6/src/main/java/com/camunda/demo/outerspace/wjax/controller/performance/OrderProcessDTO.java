package com.camunda.demo.outerspace.wjax.controller.performance;

import java.io.Serializable;

public class OrderProcessDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String processInstanceId;
  private long orderId;
  private String customer;
  private String latestActivityId;
  private String latestActivityName;

  public String getLatestActivityId() {
    return latestActivityId;
  }

  public void setLatestActivityId(String latestActivityId) {
    this.latestActivityId = latestActivityId;
  }

  public String getLatestActivityName() {
    return latestActivityName;
  }

  public void setLatestActivityName(String latestActivityName) {
    this.latestActivityName = latestActivityName;
  }

  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

}
