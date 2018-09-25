package org.camunda.tutorials.ErrorHandeling;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class IncreaseBugetDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long buget = (Long)execution.getVariable("buget");
		
		// 500 million
		buget = buget + 500000000L;
		
		execution.setVariable("buget", buget);
		

	}

}
