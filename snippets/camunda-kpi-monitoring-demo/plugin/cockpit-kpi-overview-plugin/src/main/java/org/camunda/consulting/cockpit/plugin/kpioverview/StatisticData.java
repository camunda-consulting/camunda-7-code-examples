package org.camunda.consulting.cockpit.plugin.kpioverview;

public class StatisticData {
	private String taskId;
	private String kpi;
	private String kpiUnit;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getKpi() {
		return kpi;
	}
	public void setKpi(String kpi) {
		this.kpi = kpi;
	}
	public String getKpiUnit() {
		return kpiUnit;
	}
	public void setKpiUnit(String kpiUnit) {
		if (kpiUnit.equals("h")) {
			kpiUnit = "hh";
		}
		this.kpiUnit = kpiUnit;
	}
}
