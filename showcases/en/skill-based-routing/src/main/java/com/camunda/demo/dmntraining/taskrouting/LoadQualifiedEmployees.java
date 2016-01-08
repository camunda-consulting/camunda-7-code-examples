package com.camunda.demo.dmntraining.taskrouting;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

public class LoadQualifiedEmployees implements JavaDelegate {
	
	private EmployeeService service;

	@Override
	public void execute(DelegateExecution execution) throws Exception {		
		service = new EmployeeService();
		
		List<String> requiredSkills = (List<String>) execution.getVariable("skills");

		List<Employee> employees = service.getQualifiedAndAvailableEmployees(requiredSkills);
		
		execution.setVariable("employees", Variables.objectValue(employees).serializationDataFormat(SerializationDataFormats.JSON).create());
//		org.camunda.bpm.engine.variable.Variables.objectValue(employees).serializationDataFormat(org.camunda.bpm.engine.variable.Variables.SerializationDataFormats.JSON).create()
	}

}
