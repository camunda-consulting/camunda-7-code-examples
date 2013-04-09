package com.camunda.fox.demo.orderconfirmation.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="ORDERCONF_CUSTOMER")
public class Customer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @SuppressWarnings("unused")
  @Version
  private Long version;

  @NotNull
  private String company;

  @NotNull
  @Pattern(regexp = ".+@.+\\.[a-z]+", message = "Please provide a vaild email")
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", company=" + company + ", email=" + email + "]";
  }
}
