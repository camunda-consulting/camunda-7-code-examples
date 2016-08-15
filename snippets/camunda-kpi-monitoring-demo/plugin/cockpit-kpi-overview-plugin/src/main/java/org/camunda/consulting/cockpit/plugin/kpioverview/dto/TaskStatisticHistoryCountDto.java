package org.camunda.consulting.cockpit.plugin.kpioverview.dto;

public class TaskStatisticHistoryCountDto {
	private String activityId;
	private int count;
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
