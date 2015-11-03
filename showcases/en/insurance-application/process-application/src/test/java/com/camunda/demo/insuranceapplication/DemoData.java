package com.camunda.demo.insuranceapplication;

import java.util.Calendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.camunda.demo.insuranceapplication.model.Application;
import com.camunda.demo.insuranceapplication.model.Car;
import com.camunda.demo.insuranceapplication.model.Person;

public class DemoData {
  
  public static void main(String[] args) throws JsonProcessingException {
    System.out.println(new ObjectMapper().writeValueAsString(createNewApplication(40, "BMW", "318i")));    
  }
  
  public static Application createNewApplication(int alter, String hersteller, String typ) {
    Application application = new Application();
    application.setApplicant(new Person());
    application.setCar(new Car());

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, -1 * alter);

    application.getApplicant().setDateOfBirth(cal.getTime());
    application.getApplicant().setName("Bernd Rücker");
    application.getApplicant().setEmail("br@camunda.com");
    application.getApplicant().setGender("male");
    application.getCar().setManufacturer(hersteller);
    application.getCar().setType(typ);
    application.setInsuranceProduct("Camundanzia Vollkasko Plus");
    return application;
  }
}
