package org.camunda.bpm.example.bpmntransaction.bpmntransaction;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CancelBookingDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		
		
		
		HotelBookingForm thisBooking = (HotelBookingForm) exec.getVariable("thisBooking");
		exec.removeVariable("thisBooking");
		exec.setVariable("cancelledBooking", thisBooking);
//		
		
		
	}

}
