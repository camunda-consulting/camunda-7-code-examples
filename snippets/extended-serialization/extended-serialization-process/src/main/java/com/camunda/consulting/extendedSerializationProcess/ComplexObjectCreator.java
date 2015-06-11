package com.camunda.consulting.extendedSerializationProcess;

import java.math.BigDecimal;

import javax.inject.Named;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.LocalDate;

@Named
public class ComplexObjectCreator {
  
  public ComplexDataObject create() {
    ComplexDataObject object = new ComplexDataObject();
    object.setName("exampleName");
    object.setPrice(Money.of(CurrencyUnit.EUR, new BigDecimal("34.99")));
    object.setPurchaseDate(LocalDate.now());
    return object;
  }

}
