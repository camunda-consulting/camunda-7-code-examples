package com.camunda.demo.insuranceapplication.model;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

public class Application {

  private String number = nextApplicationNummer();

  private Person applicant;
  private Car car;

  private String insuranceProduct;

  private String contractNumber;

  private long priceIndicationInCents;
  private long feeInCent;

  public static int counter = 0;

  public static String nextApplicationNummer() {
    // Achtung: Nur für Demo-Zwecke für eine lesbare Nummer - in real: UUID!
    if (counter == 0) {
      counter = Calendar.getInstance().get(Calendar.MINUTE) + Calendar.getInstance().get(Calendar.SECOND);
    } else {
      counter++;
    }
    String result = "A-" + Calendar.getInstance().get(Calendar.DAY_OF_YEAR) + counter;
    return result;
  }

  public String getFee() {
    NumberFormat n = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    return n.format(feeInCent / 100.0);
  }

  public void setFee(String s) {
    // ignore - currently needed because JsonIgnore configuration not yet done
  }

  public String getPriceIndication() {
    NumberFormat n = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    return n.format(priceIndicationInCents / 100.0);
  }

  public void setPriceIndication(String s) {
    // ignore - currently needed because JsonIgnore configuration not yet done
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Person getApplicant() {
    return applicant;
  }

  public void setApplicant(Person applicant) {
    this.applicant = applicant;
  }

  public String getInsuranceProduct() {
    return insuranceProduct;
  }

  public void setInsuranceProduct(String insuranceProduct) {
    this.insuranceProduct = insuranceProduct;
  }

  public String getContractNumber() {
    return contractNumber;
  }

  public void setContractNumber(String contractId) {
    this.contractNumber = contractId;
  }

  public long getPriceIndicationInCents() {
    return priceIndicationInCents;
  }

  public void setPriceIndicationInCents(long priceIndicationInCents) {
    this.priceIndicationInCents = priceIndicationInCents;
  }

  public long getFeeInCent() {
    return feeInCent;
  }

  public void setFeeInCent(long feeInCent) {
    this.feeInCent = feeInCent;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

}
