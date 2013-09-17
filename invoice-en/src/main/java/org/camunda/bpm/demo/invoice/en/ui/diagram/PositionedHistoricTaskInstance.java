package org.camunda.bpm.demo.invoice.en.ui.diagram;

import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.repository.DiagramNode;

public class PositionedHistoricTaskInstance {

	private HistoricTaskInstance htask;
	private DiagramNode bounds;

	public PositionedHistoricTaskInstance(
		HistoricTaskInstance htask, DiagramNode bounds) {
		this.htask = htask;
		this.bounds = bounds;
	}
	
	public HistoricTaskInstance getHtask() {
		return htask;
	}

	public void setHtask(HistoricTaskInstance htask) {
		this.htask = htask;
	}

	public DiagramNode getBounds() {
		return bounds;
	}

	public void setBounds(DiagramNode bounds) {
		this.bounds = bounds;
	}

}
