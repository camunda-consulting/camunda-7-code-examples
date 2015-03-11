package com.camunda.consulting.extendedSerializationProcess;

import java.util.logging.Logger;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.variable.value.ObjectValue;

@Named
public class ComplexObjectLogger {

  private static final Logger log = Logger.getLogger(ComplexObjectLogger.class.getName());

  public void log(DelegateExecution execution) {
    ComplexDataObject complexObject = (ComplexDataObject) execution.getVariable("complexValue");
    log.info("Complex value: " + complexObject.getName() + " " + complexObject.getPrice() + " " + complexObject.getPurchaseDate());
    ObjectValue complexDataObjectAsJson = execution.getVariableLocalTyped("complexValue", false);
    log.info("serialized value: " + complexDataObjectAsJson.getValueSerialized());
  }
}
