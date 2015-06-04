package com.camunda.demo.versicherungsneuantrag.model;

import java.util.Calendar;
import java.util.Date;

public class Person {

  private String name;
  private String email;
  private Date geburtsdatum;
  private String geschlecht = "Frau";

  public int getAlter() {
    return calculateAlter(new Date());
  }
  
  public int calculateAlter(Date atDate) {
    Calendar dob = Calendar.getInstance();  
    dob.setTime(geburtsdatum);  
    Calendar today = Calendar.getInstance(); 
    today.setTime(atDate);
    
    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
    if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
      age--;  
    } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
        && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
      age--;  
    }
    
    return age;
  }
  
  public Date getGeburtsdatum() {
    return geburtsdatum;
  }
  public void setGeburtsdatum(Date geburtsdatum) {
    this.geburtsdatum = geburtsdatum;
  }

  public String getGeschlecht() {
    return geschlecht;
  }

  public void setGeschlecht(String geschlecht) {
    this.geschlecht = geschlecht;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
