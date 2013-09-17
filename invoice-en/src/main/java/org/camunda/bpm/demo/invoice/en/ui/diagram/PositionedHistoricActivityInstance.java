package org.camunda.bpm.demo.invoice.en.ui.diagram;

import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.repository.DiagramNode;

public class PositionedHistoricActivityInstance {

	private HistoricActivityInstance hact;
	
	public PositionedHistoricActivityInstance(
			HistoricActivityInstance hact, DiagramNode bounds) {
		this.hact = hact;
		this.bounds = bounds;
	}

	public HistoricActivityInstance getHact() {
		return hact;
	}

	public void setHact(HistoricActivityInstance hact) {
		this.hact = hact;
	}

	private DiagramNode bounds;

	public DiagramNode getBounds() {
		return bounds;
	}

	public void setBounds(DiagramNode bounds) {
		this.bounds = bounds;
	}
	
	

}
