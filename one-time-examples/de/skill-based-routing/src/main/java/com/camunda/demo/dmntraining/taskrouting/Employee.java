package com.camunda.demo.dmntraining.taskrouting;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private long approvalAuthority;
	private String region;
	private int claimsAssigned;
	
	private int score;

	public Employee() {		
	}
	
	public Employee(String name, long approvalAuthority, String region, int claimsAssigned) {
		this.name = name;
		this.approvalAuthority = approvalAuthority;
		this.region = region;
		this.claimsAssigned = claimsAssigned;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Employee [name=" + name + ", approvalAuthority =" + approvalAuthority
				+ ", region=" + region + ", claimsAssigned=" + claimsAssigned
				+ ", score=" + score + "]";
	}

	public long getApprovalAuthority() {
		return approvalAuthority;
	}

	public void setApprovalAuthority(long approvalAuthority) {
		this.approvalAuthority = approvalAuthority;
	}
}
