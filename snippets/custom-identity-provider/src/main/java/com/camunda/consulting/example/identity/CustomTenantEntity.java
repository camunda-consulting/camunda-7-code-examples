package com.camunda.consulting.example.identity;

public class CustomTenantEntity extends IdentifiedEntity {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
