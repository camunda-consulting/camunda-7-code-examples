package org.camunda.tutorials.ErrorHandeling;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendUpdateMessageDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String busKey = (String)execution.getBusinessKey();
		Long buget = (Long)execution.getVariable("cost");
		
		execution.getProcessEngineServices().getRuntimeService()
			.createMessageCorrelation("buget-increased")
			.processInstanceBusinessKey(busKey)
			.setVariable("buget", buget)
			.correlate();

	}

}
