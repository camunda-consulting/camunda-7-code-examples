package com.camunda.demo.insuranceapplication;

import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.engine.variable.Variables;

public class DecisionStarter {

  public static void main(String[] args) {
    Map<String, Object> variables = Variables.createVariables();
    variables.put("application", DemoData.createNewApplication(25, "BMW", "318i"));

    DmnEngine dmnEngine = new DefaultDmnEngineConfiguration().buildEngine();
    
    DmnDecision decision = dmnEngine.parseDecision("riskAssessment", DecisionStarter.class.getResourceAsStream("/RiskAssessment.dmn"));   
    DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, variables);
        
    System.out.println(result.get(0).get("risk"));
  }
}
