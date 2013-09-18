package org.camunda.demo.custom.query;


/**
 * Custom DTO class example to store results of task variables
 * only returning string values, other types are not (yet) mapped
 * via MyBatis but could be added easily 
 */
public class ProcessVariableDTO {

  private String id;
  private String name;
  private String value;

  public String getId() {
    return id;
  }

  public String getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void setName(String name) {
    this.name = name;
  }

}
