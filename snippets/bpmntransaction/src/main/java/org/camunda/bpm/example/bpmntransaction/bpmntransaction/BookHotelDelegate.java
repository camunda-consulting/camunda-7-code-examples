package org.camunda.bpm.example.bpmntransaction.bpmntransaction;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class BookHotelDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution exec) throws Exception {
		
		
		//Map<String, String> booking  = new 
		HotelBookingForm thisBooking = new HotelBookingForm("NicePlace", 2, true);
		ObjectValue thisBookingValue = Variables.objectValue(thisBooking)
										.serializationDataFormat(Variables.SerializationDataFormats.JAVA)
										.create();
		
		exec.setVariable("ThisBooking", thisBookingValue);
		
		boolean bookingError = (Boolean) exec.getVariable("bookingHotelError");
		if(bookingError)
			throw new BpmnError("THIS_IS_NOT_GOOD");

	}

}
