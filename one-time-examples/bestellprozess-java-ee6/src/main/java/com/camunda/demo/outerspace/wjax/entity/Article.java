package com.camunda.demo.outerspace.wjax.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WJAX_ARTICLE")
public class Article implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  private String description;
  private String imageUrl;

  /**
   * TODO: Introduce better Money handling
   */
  private Integer priceInCents;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Integer getPriceInCents() {
    return priceInCents;
  }

  public void setPriceInCents(Integer priceInCents) {
    this.priceInCents = priceInCents;
  }

}
