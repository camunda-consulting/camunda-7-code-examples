package org.camunda.bpm.example.bpmntransaction.bpmntransaction;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ChargeCreditCardDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution context) throws Exception {
		
		boolean bookingError = (Boolean) context.getVariable("chargeCardError");
		if(bookingError)
			throw new BpmnError("CHARGE_FAILURE");;
		
		
		

	}

}
