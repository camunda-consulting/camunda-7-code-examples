package org.camunda.demo.dmnTest.RugbyRules;

import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class ParseDecisionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		HashMap<String, String> chosenTeam = (HashMap<String, String>)arg0.getVariable("chosenTeam");
		
		arg0.setVariable("teamName", chosenTeam.get("chosenTeam"));
		arg0.setVariable("teamDescription", chosenTeam.get("teamDescription"));
		

	}

}
