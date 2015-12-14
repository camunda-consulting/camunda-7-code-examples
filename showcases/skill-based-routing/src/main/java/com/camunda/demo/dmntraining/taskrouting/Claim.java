package com.camunda.demo.dmntraining.taskrouting;

import java.util.UUID;

public class Claim {
	
	private String id = UUID.randomUUID().toString();
	
	private String type;
	private long expenditure;
	private String affectedObject;
	private String region;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
