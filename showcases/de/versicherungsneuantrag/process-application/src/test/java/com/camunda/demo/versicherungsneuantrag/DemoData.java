package com.camunda.demo.versicherungsneuantrag;

import java.util.Calendar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.camunda.demo.versicherungsneuantrag.model.Neuantrag;
import com.camunda.demo.versicherungsneuantrag.model.Person;

public class DemoData {
  
  public static void main(String[] args) throws JsonProcessingException {
    System.out.println(new ObjectMapper().writeValueAsString(createNeuantrag(40, false, "BMW", "318i")));    
  }
  
  public static Neuantrag createNeuantrag(int alter, boolean nurUeber25, String hersteller, String typ) {
    Neuantrag neuantrag = new Neuantrag();
    neuantrag.setAntragssteller(new Person());

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, -1 * alter);

    neuantrag.getAntragssteller().setGeburtsdatum(cal.getTime());
    neuantrag.getAntragssteller().setName("Bernd Rücker");
    neuantrag.getAntragssteller().setEmail("fm@camunda.com");
    neuantrag.getAntragssteller().setGeschlecht("Mann");
    neuantrag.setFahrzeugHersteller(hersteller);
    neuantrag.setFahrzeugTyp(typ);
    neuantrag.setVersicherungsprodukt("Camundanzia Vollkasko Plus");
    neuantrag.setFahrerUeber25(nurUeber25);
    return neuantrag;
  }
}
