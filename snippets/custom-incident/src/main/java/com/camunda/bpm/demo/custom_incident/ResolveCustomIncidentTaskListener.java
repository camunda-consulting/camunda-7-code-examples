package com.camunda.bpm.demo.custom_incident;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.persistence.entity.IncidentEntity;
import org.camunda.bpm.engine.runtime.Incident;

public class ResolveCustomIncidentTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask task) {
	    List<Incident> incidents = Context
	            .getCommandContext()
	            .getIncidentManager()
	            .findIncidentsByExecution(task.getExecutionId());
	    for (Incident incident : incidents) {
			((IncidentEntity) incident).resolve();
		}
	}

}
