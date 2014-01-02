package com.camunda.demo.oop;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@Named("emailAdapter")
public class EmailAdapter {
  
  @Inject
  @Named
  private Order order;

  public void sendConfirmation() throws EmailException {
    
    Email email = new SimpleEmail();
    email.setHostName("mail.camunda.com");    
    email.setAuthentication("activitishowcase@mx.camunda.com", "28484234386345");
    email.setFrom("br@camunda.com");
    email.setSubject("Auftragsbestätigung");
    email.setMsg("Deine Bestellung wird geliefert. Und die Temperatur in " + order.getCity() + " ist " + order.getWeatherInfo());
    email.addTo(order.getEmail());
    email.send();

  }
  
 public void sendRejection() throws EmailException {
    
    Email email = new SimpleEmail();
    email.setHostName("mail.camunda.com");    
    email.setAuthentication("activitishowcase@mx.camunda.com", "28484234386345");
    email.setFrom("br@camunda.com");
    email.setSubject("Ablehnung");
    email.setMsg("Sorry - geht nicht");
    email.addTo(order.getEmail());
    email.send();

  }
}
