package org.camunda.example.insuranceapplication.applicationprocessing.delegates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.springframework.stereotype.Component;

@Component
public class MapDMNResult implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    
    if(execution.hasVariable("risks")) {
      execution.removeVariable("risks");
      execution.removeVariable("riskLevel");
    }
    
    List<String> risks = new ArrayList<String>();
    Set<String> riskLevels = new HashSet<String>();

    Object oDMNresult = execution.getVariable("riskDMNresult");
    if (!(oDMNresult instanceof List<?>)) {
      throw new RuntimeException("DMN did not return list");
    }
    for (Object oResult : (List<?>) oDMNresult) {
      if (!(oResult instanceof Map<?, ?>)) {
        throw new RuntimeException("DMN result list does not contain map");
      }
      Map<?, ?> result = (Map<?, ?>) oResult;
      risks.add(result.containsKey("risk") ? (String) result.get("risk") : "");
      if (result.get("riskLevel") != null) {
        riskLevels.add(((String) result.get("riskLevel")).toLowerCase());
      }
    }

    String accumulatedRiskLevel = "green";
    if (riskLevels.contains("rot") || riskLevels.contains("red")) {
      accumulatedRiskLevel = "red";
    } else if (riskLevels.contains("gelb") || riskLevels.contains("yellow")) {
      accumulatedRiskLevel = "yellow";
    }
    
    execution.setVariable("risks", Variables.objectValue(risks).serializationDataFormat(SerializationDataFormats.JSON).create());
    execution.setVariable("riskLevel", accumulatedRiskLevel);
  }

}