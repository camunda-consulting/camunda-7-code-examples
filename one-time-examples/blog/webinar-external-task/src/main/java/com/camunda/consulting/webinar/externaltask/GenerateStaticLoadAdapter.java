package com.camunda.consulting.webinar.externaltask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

public class GenerateStaticLoadAdapter implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		long numberOfInstances = 20;
		for (int i = 0; i < numberOfInstances; i++) {
			execution.getProcessEngineServices().getRuntimeService().startProcessInstanceByKey(	
					"OrderProcess",
					Variables.createVariables());
		}
		
		int secondsToWait = 10;
		execution.setVariable("waitingTime", "PT" + secondsToWait + "S");
	}

}
