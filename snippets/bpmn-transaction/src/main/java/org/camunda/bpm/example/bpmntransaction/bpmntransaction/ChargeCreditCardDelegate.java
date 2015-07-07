package org.camunda.bpm.example.bpmntransaction.bpmntransaction;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ChargeCreditCardDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		
		boolean bookingError = (Boolean) arg0.getVariable("chargeCardError");
		if(bookingError)
			throw new BpmnError("CHARGE_FAILURE");;
		
		
		

	}

}
