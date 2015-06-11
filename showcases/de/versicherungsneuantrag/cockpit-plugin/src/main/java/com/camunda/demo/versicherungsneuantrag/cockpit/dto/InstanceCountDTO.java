package com.camunda.demo.versicherungsneuantrag.cockpit.dto;

import java.util.Date;

public class InstanceCountDTO {

  private Date date;
  private long count;
  private String name;

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
