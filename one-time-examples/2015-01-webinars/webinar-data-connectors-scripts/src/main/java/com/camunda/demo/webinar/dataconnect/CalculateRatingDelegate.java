package com.camunda.demo.webinar.dataconnect;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class CalculateRatingDelegate implements JavaDelegate {
  
  private static Logger log = Logger.getLogger(CalculateRatingDelegate.class.getName());

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    // Transformation is done automatically!
    CreditorApplication application = (CreditorApplication) execution.getVariable("application");

    // You can get the "typed" variable:
    ObjectValue typedValue = execution.getVariableTyped("application");
    // and get more information from it if you like:
    log.info("Typed Variable application values: " 
       + "\n    Type: " + typedValue.getType()
       + "\n    Value: " + typedValue.getValue()
       + "\n    SerializationDataFormat: " + typedValue.getSerializationDataFormat()
       + "\n    ValueSerialized: " + typedValue.getValueSerialized());
    
    // BTW: When doing queries you can decide if values gets deserialized
    // important e.g. for Tasklist/REST queries using the JSON anyway
    
    // now lets make up some rating and set it
    application.setRating(42);
  }

}
