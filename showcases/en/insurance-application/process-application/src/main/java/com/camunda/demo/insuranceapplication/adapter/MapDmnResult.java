package com.camunda.demo.insuranceapplication.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class MapDmnResult implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    List<String> risks = new ArrayList<String>();
    Set<String> riskAssessments = new HashSet<String>();
    
    //DmnDecisionOutput vs DmnDecisionResult
    List<Map<String, Object>> resultList = (List<Map<String, Object>>) execution.getVariable("riskAssessmentResult");
    for (Map<String, Object> result : resultList) {
      risks.add((String)result.get("risk"));
      if (result.get("riskAssessment")!=null) {
        riskAssessments.add(((String)result.get("riskAssessment")).toLowerCase());      
      }
    }

    String riskAssessment = "green";
    if (riskAssessments.contains("red")) {
      riskAssessment = "red";
    } else if (riskAssessments.contains("yellow")) {
      riskAssessment = "yellow";
    }
    execution.setVariable("risks", Variables.objectValue(risks).serializationDataFormat(SerializationDataFormats.JSON).create());
    execution.setVariable("riskAssessment", riskAssessment);
  }

}
