package com.camunda.demo.gateway.or.split.items;

public class Item {

  private String id;
  private String text;
  private Boolean decision = null;
  
  public String getId() {
    return id;
  }
  public Item setId(String id) {
    this.id = id;
    return this;
  }
  public String getText() {
    return text;
  }
  public Item setText(String text) {
    this.text = text;
    return this;
  }
  public Boolean getDecision() {
    return decision;
  }
  public void setDecision(Boolean decision) {
    this.decision = decision;
  }
}
