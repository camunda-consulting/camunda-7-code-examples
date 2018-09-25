package org.camunda.tutorials.ErrorHandeling;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PurchaseMaterialsDeleagate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Long cost = (Long)execution.getVariable("cost");
		
		if (cost == null) {
			cost = 0L;
		}
		
		cost = cost + 10000000L;
		
		execution.setVariable("cost", cost);
	}

}
