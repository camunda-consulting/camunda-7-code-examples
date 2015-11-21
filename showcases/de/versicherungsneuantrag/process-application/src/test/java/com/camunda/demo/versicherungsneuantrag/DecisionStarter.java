package com.camunda.demo.versicherungsneuantrag;

import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.engine.variable.Variables;

public class DecisionStarter {

  public static void main(String[] args) {
    Map<String, Object> variables = Variables.createVariables();
    variables.put("neuantrag", DemoData.createNeuantrag(25, true, "BMW", "X3"));

    DmnEngine dmnEngine = new DefaultDmnEngineConfiguration().buildEngine();
    
    DmnDecision decision = dmnEngine.parseDecision("risikopruefung", DecisionStarter.class.getResourceAsStream("/Risikopruefung.dmn"));   
    DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, variables);
    
    System.out.println(result.get(0).get("risiko"));
//    System.out.println(result.get(1).get("risiko"));
  }
}
