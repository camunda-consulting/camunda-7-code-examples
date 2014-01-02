package org.camunda.demo.custom.query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Own domain entity class
 */
@Entity
@Table(name="CUSTOMER")
public class Customer {

  @Id
  @GeneratedValue
  @Column(name="ID_")
  private long id;
  
  @Column(name="NAME_")
  private String name;
  
  @Column(name="REGION_")
  private String region;
  
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getRegion() {
    return region;
  }
  
  public void setRegion(String region) {
    this.region = region;
  }
}
