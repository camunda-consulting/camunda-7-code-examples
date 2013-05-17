package com.camunda.fox.showcase.invoice.en.ui.diagram;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.repository.DiagramNode;

public class PositionedHistoricTaskDefinition {

	//private HistoricTaskD htask;
	private DiagramNode bounds;
	ArrayList<HistoricTaskInstance> taskInstances;
	
	public PositionedHistoricTaskDefinition(HistoricTaskInstance firstInstance, DiagramNode bounds) {
		this.bounds = bounds;
		this.taskInstances = new ArrayList<HistoricTaskInstance>();
		this.taskInstances.add(firstInstance);
	}
	
	public DiagramNode getBounds() {
		return bounds;
	}

	public void setBounds(DiagramNode bounds) {
		this.bounds = bounds;
	}

	public List<HistoricTaskInstance> getTaskInstances() {
		return taskInstances;
	}

	public void setTaskInstances(ArrayList<HistoricTaskInstance> taskInstances) {
		this.taskInstances = taskInstances;
	}

	public String getTaskDefinitionKey() {
		return taskInstances.get(0).getTaskDefinitionKey();
	}

	public String getTaskName() {
		return taskInstances.get(0).getName();
	}
	
	public boolean isCurrent() {
		if (taskInstances.size()==1 && taskInstances.get(0).getEndTime()==null) return true;
		else return false;
	}
}
