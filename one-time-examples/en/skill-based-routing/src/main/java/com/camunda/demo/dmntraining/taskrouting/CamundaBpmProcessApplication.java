package com.camunda.demo.dmntraining.taskrouting;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access 
   * the processes the application has deployed. 
   */
  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {
		Claim claim = new Claim();
		claim.setType("Car Accident");
		claim.setExpenditure(1000);
		
		processEngine.getRuntimeService().startProcessInstanceByKey(BpmConstants.DECISION_FLOW_KEY_determineEmployee, //
				Variables.createVariables() //
						.putValue("claim", claim));
  }

}
