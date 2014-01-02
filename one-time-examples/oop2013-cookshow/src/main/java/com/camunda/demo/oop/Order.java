package com.camunda.demo.oop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_OOP")
public class Order {

  @GeneratedValue
  @Id
  private long id;
  
  private String email;
  private String orderDetails;
  private String zip;
  private String city;
  private long amount;
  
  private String weatherInfo;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(String orderDetails) {
    this.orderDetails = orderDetails;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  
  public long getId() {
    return id;
  }

  
  public String getWeatherInfo() {
    return weatherInfo;
  }

  
  public void setWeatherInfo(String weatherInfo) {
    this.weatherInfo = weatherInfo;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", email=" + email + ", orderDetails=" + orderDetails + ", zip=" + zip + ", city=" + city + ", amount=" + amount
            + ", weatherInfo=" + weatherInfo + "]";
  }

}
