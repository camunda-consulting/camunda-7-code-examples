package org.camunda.consulting.cockpit.plugin.kpioverview.dto;

import java.util.Date;

import org.camunda.bpm.engine.rest.dto.runtime.ActivityInstanceDto;

public class TaskStatisticHistoryDto extends ActivityInstanceDto {
	private Date startTime;
	private Date endTime;
	private String processInstanceId;
	private String definitionId;
	private int diff;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getDefinitionId() {
		return definitionId;
	}
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	public int getDiff() {
		return diff;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}
}
