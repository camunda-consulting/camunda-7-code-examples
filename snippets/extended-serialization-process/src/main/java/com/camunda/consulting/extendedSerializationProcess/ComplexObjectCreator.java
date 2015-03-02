package com.camunda.consulting.extendedSerializationProcess;

import java.math.BigDecimal;

import javax.inject.Named;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

@Named
public class ComplexObjectCreator {
  
  public ComplexDataObject create() {
    ComplexDataObject object = new ComplexDataObject();
    object.setName("exampleName");
    object.setPrice(Money.of(CurrencyUnit.EUR, new BigDecimal("34.99")));
    return object;
  }

}
