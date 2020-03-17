package com.camunda.consulting.demo.incident;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.incident.DefaultIncidentHandler;
import org.camunda.bpm.engine.impl.incident.IncidentContext;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class ProcessIncidentHandler extends DefaultIncidentHandler {

	public ProcessIncidentHandler(String type){
		super(type);
	}

	@Override
	public String getIncidentHandlerType() {
		return super.getIncidentHandlerType();
	}

	@Override
	public Incident handleIncident(IncidentContext context, String message) {
		Incident incident = super.handleIncident(context, message);
		VariableMap variables = Variables.createVariables()
				.putValue("incidentId", incident.getId())
				.putValue("incidentMessage", incident.getIncidentMessage())
				.putValue("incidentExecutionId", incident.getExecutionId());

		BpmPlatform.getDefaultProcessEngine().getRuntimeService()
				.startProcessInstanceByKey("IncidentManagementProcess", variables);
		return incident;
	}
}

