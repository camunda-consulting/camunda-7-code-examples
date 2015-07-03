package com.camunda.demo.rechnungseingang.cockpit.dto;

import java.util.ArrayList;
import java.util.List;

public class InstanceCountSeries {

  private String name;
  private List<Long> data = new ArrayList<Long>();

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public List<Long> getData() {
    return this.data;
  }

  public void setData(final List<Long> data) {
    this.data = data;
  }

}
