package com.camunda.demo.versicherungsneuantrag.adapter;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.dmn.engine.DmnDecisionOutput;
import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class MapDmnResult implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    List<String> risks = new ArrayList<String>();
    List<String> statusRedRisks = new ArrayList<String>();
    
    //DmnDecisionOutput vs DmnDecisionResult
    Object result = execution.getVariable("risikopruefung");
    if (result instanceof DmnDecisionOutput) {
      parseOutput(risks, statusRedRisks, (DmnDecisionOutput)result);      
    } else if (result instanceof DmnDecisionResult) {
      DmnDecisionResult risikopruefung = (DmnDecisionResult) execution.getVariable("risikopruefung");    
      
      for (DmnDecisionOutput dmnDecisionOutput : risikopruefung) { 
        parseOutput(risks, statusRedRisks, dmnDecisionOutput);
      }
    }
    else if (result==null){
      // no risks :-)
    }
    else {
      throw new RuntimeException("Unknwon result type " + result.getClass().getName() + " (" + result + ")");
    }

    execution.setVariable("risks", risks);
    execution.setVariable("statusRedRisks", statusRedRisks);
  }

  protected void parseOutput(List<String> risks, List<String> statusRedRisks, DmnDecisionOutput dmnDecisionOutput) {
    risks.add((String)dmnDecisionOutput.getValue("risiko"));
    if (dmnDecisionOutput.getValue("ablehnung")!=null && (Boolean)dmnDecisionOutput.getValue("ablehnung")) {
      statusRedRisks.add((String)dmnDecisionOutput.getValue("risiko"));        
    }
  }

}
