package com.camunda.demo.insuranceapplication;

import java.util.Map;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.impl.DmnEngineConfigurationImpl;
import org.camunda.bpm.engine.variable.Variables;

public class DecisionStarter {

  public static void main(String[] args) {
    Map<String, Object> variables = Variables.createVariables();
    variables.put("application", DemoData.createNewApplication(25, "BMW", "318i"));

    DmnEngine dmnEngine = new DmnEngineConfigurationImpl().buildEngine();
    
    DmnDecision decision = dmnEngine.parseDecision("RiskAssessment.dmn");   
    DmnDecisionResult result = dmnEngine.evaluate(decision, variables);
        
    System.out.println(result.get(0).get("risk"));
    System.out.println(result.get(1).get("risk"));
  }
}
