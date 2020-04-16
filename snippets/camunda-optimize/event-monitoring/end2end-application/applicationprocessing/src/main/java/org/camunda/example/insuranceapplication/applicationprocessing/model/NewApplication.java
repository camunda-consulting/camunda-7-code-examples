package org.camunda.example.insuranceapplication.applicationprocessing.model;


import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewApplication {

  private String applicationNumber;

  private Person applicant;
  private String employment;

  private String category;
  private String product;
  private String corporation;

  private String contractNumber;
  private long priceIndicationInCent;
  private long premiumInCent;

  public static int counter = 0;

  public String traceid;
  
  public String getTraceid() {
	return traceid;
}

public void setTraceid(String traceid) {
	this.traceid = traceid;
}

public static String generateUUID() {
    // for demo reasons we generate something readable
    if (counter == 0) {
      counter = Calendar.getInstance().get(Calendar.MINUTE) + Calendar.getInstance().get(Calendar.SECOND);
    } else {
      counter++;
    }
    String result = "A-" + Calendar.getInstance().get(Calendar.DAY_OF_YEAR) + counter;
    return result;
  }

  public NewApplication() {
    applicationNumber = generateUUID();
  }

  public NewApplication(String applicationNumber) {
    super();
    this.applicationNumber = applicationNumber;
  }

  public String getCorporation() {
	return corporation;
  }

  public void setCorporation(String corporation) {
	this.corporation = corporation;
  }

  public String getPremium() {
    // TODO: EN/DE switch
    NumberFormat n = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    return n.format(premiumInCent / 100.0);
  }

  public void setPremium(String s) {
    // ignore - currently needed because JsonIgnore configuration not yet done
  }

  public String getPriceIndication() {
    NumberFormat n = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    return n.format(priceIndicationInCent / 100.0);
  }

  public void setPriceIndication(String s) {
    // ignore - currently needed because JsonIgnore configuration not yet done
  }

  public Person getApplicant() {
    return applicant;
  }

  public void setApplicant(Person applicant) {
    this.applicant = applicant;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getEmployment() {
    return employment;
  }

  public void setEmployment(String employment) {
    this.employment = employment;
  }

  public String getContractNumber() {
    return contractNumber;
  }

  public void setContractNumber(String contractNumber) {
    this.contractNumber = contractNumber;
  }

  public long getPremiumInCent() {
    return premiumInCent;
  }

  public void setPremiumInCent(long premiumInCent) {
    this.premiumInCent = premiumInCent;
  }

  public long getPriceIndicationInCent() {
    return priceIndicationInCent;
  }

  public void setPriceIndicationInCent(long priceIndicationInCents) {
    this.priceIndicationInCent = priceIndicationInCents;
  }

  public String getApplicationNumber() {
    return applicationNumber;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }
  
  
}
