package com.camunda.bpm.demo.custom_incident;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.persistence.entity.IncidentEntity;

public class CreateCustomIncidentTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask task) {
		IncidentEntity newIncident
		  = IncidentEntity.createAndInsertIncident(
		      "myCustomIncidentType",
		      task.getExecutionId(),
		      "incident-data",
		      "A custom incident message."
		    );
		newIncident.createRecursiveIncidents();
	}

}
