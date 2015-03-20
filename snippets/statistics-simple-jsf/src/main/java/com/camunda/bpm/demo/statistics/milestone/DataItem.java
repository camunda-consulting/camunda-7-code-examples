package com.camunda.bpm.demo.statistics.milestone;

public class DataItem {
  
  private String id;
  private String name;
  private long value;
  
  public void incValue() {
    value++;
  }
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public long getValue() {
    return value;
  }
  public void setValue(long value) {
    this.value = value;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

}
