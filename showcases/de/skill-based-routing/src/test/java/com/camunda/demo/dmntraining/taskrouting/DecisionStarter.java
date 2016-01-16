package com.camunda.demo.dmntraining.taskrouting;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionRuleResult;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.engine.variable.Variables;

public class DecisionStarter {

  public static void main(String[] args) {
    Claim someClaimObject = new Claim();

    DmnEngine dmnEngine = new DefaultDmnEngineConfiguration().buildEngine();    
    DmnDecision decision = dmnEngine.parseDecision("notwendigeKompetenz", DecisionStarter.class.getResourceAsStream("/notwendigeKompetenz.dmn"));   
	DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, 
    		Variables.createVariables().putValue("claim", someClaimObject));
	// Multiple Hit Policiy, daher potentiell Liste von Ergebnissen:
    for (DmnDecisionRuleResult outputRow : result) {		
    	// Es gibt nur eine Output Spalte (singleEntry):
    	System.out.println("Notwendige Kompetenz: " + outputRow.getSingleEntry());
	}
  }
}
