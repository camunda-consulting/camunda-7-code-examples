package com.camunda.demo.dmntraining.taskrouting;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String experience;
	private String region;
	private int claimsAssigned;
	
	private int score;

	public Employee() {		
	}
	
	public Employee(String name, String experience, String region, int claimsAssigned) {
		this.name = name;
		this.experience = experience;
		this.region = region;
		this.claimsAssigned = claimsAssigned;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getClaimsAssigned() {
		return claimsAssigned;
	}

	public void setClaimsAssigned(int claimsAssigned) {
		this.claimsAssigned = claimsAssigned;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", experience=" + experience
				+ ", region=" + region + ", claimsAssigned=" + claimsAssigned
				+ ", score=" + score + "]";
	}
}
