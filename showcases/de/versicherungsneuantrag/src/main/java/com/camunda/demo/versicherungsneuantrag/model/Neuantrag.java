package com.camunda.demo.versicherungsneuantrag.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Neuantrag {

  private Person antragssteller;
  private String fahrzeugHersteller;
  private String fahrzeugTyp;
  
  private boolean fahrerUeber25;
  
  private String versicherungsprodukt;
  
  private String vertragsnummer;
  private long beitragInCent;
  
  public String getFahrerUeber25String() {
    if (fahrerUeber25) {
      return "ja";          
    } else {
      return "nein";
    }
  }
  
  public String getBeitrag() {
    NumberFormat n = NumberFormat.getCurrencyInstance(Locale.GERMANY); 
    return n.format(beitragInCent / 100.0);
  }

  public Person getAntragssteller() {
    return antragssteller;
  }

  public void setAntragssteller(Person antragssteller) {
    this.antragssteller = antragssteller;
  }


  public boolean isFahrerUeber25() {
    return fahrerUeber25;
  }

  public void setFahrerUeber25(boolean fahrerUeber25) {
    this.fahrerUeber25 = fahrerUeber25;
  }

  public String getVersicherungsprodukt() {
    return versicherungsprodukt;
  }

  public void setVersicherungsprodukt(String versicherungsprodukt) {
    this.versicherungsprodukt = versicherungsprodukt;
  }

  public String getFahrzeugHersteller() {
    return fahrzeugHersteller;
  }

  public void setFahrzeugHersteller(String fahrzeugHersteller) {
    this.fahrzeugHersteller = fahrzeugHersteller;
  }

  public String getFahrzeugTyp() {
    return fahrzeugTyp;
  }

  public void setFahrzeugTyp(String fahrzeugTyp) {
    this.fahrzeugTyp = fahrzeugTyp;
  }

  public String getVertragsnummer() {
    return vertragsnummer;
  }

  public void setVertragsnummer(String vertragsnummer) {
    this.vertragsnummer = vertragsnummer;
  }

  public long getBeitragInCent() {
    return beitragInCent;
  }

  public void setBeitragInCent(long beitragInCent) {
    this.beitragInCent = beitragInCent;
  }
}
