package com.camunda.demo.versicherungsneuantrag.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.camunda.bpm.dmn.engine.DmnDecisionOutput;
import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class MapDmnResult implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    List<String> risks = new ArrayList<String>();
    Set<String> risikobewertungen = new HashSet<String>();
    
    //DmnDecisionOutput vs DmnDecisionResult
    Object result = execution.getVariable("risikopruefung");
    if (result instanceof DmnDecisionOutput) {
      parseOutput(risks, risikobewertungen, (DmnDecisionOutput)result);      
    } else if (result instanceof DmnDecisionResult) {
      DmnDecisionResult risikopruefung = (DmnDecisionResult) execution.getVariable("risikopruefung");    
      
      for (DmnDecisionOutput dmnDecisionOutput : risikopruefung) { 
        parseOutput(risks, risikobewertungen, dmnDecisionOutput);
      }
    }
    else if (result==null){
      // no risks :-)
    }
    else {
      throw new RuntimeException("Unknwon result type " + result.getClass().getName() + " (" + result + ")");
    }

    String risikobewertung = "grün";
    if (risikobewertungen.contains("rot")) {
      risikobewertung = "rot";
    } else if (risikobewertungen.contains("gelb")) {
      risikobewertung = "gelb";
    }
    execution.setVariable("risks", Variables.objectValue(risks).serializationDataFormat(SerializationDataFormats.JSON).create());
    execution.setVariable("risikobewertung", risikobewertung);
  }

  protected void parseOutput(List<String> risks, Set<String> risikobewertungen, DmnDecisionOutput dmnDecisionOutput) {
    risks.add((String)dmnDecisionOutput.getValue("risiko"));
    if (dmnDecisionOutput.getValue("risikobewertung")!=null) {
      risikobewertungen.add(
          ((String)dmnDecisionOutput.getValue("risikobewertung")).toLowerCase());      
    }
  }

}
