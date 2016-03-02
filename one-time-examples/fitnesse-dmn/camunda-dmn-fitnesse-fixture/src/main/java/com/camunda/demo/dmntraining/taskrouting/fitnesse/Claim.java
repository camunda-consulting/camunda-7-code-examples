package com.camunda.demo.dmntraining.taskrouting.fitnesse;

public class Claim {
	
	private String type;
	private long expenditure;
	private String region;
	private String affectedObject;

	public Claim(String type, long expenditure, String region, String affectedObject) {
		super();
		this.type = type;
		this.expenditure = expenditure;
		this.region = region;
		this.affectedObject = affectedObject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(long expenditure) {
		this.expenditure = expenditure;
	}

	public String getAffectedObject() {
		return affectedObject;
	}

	public void setAffectedObject(String affectedObject) {
		this.affectedObject = affectedObject;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
