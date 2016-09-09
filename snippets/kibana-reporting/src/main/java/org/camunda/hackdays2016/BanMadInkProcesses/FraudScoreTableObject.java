package org.camunda.hackdays2016.BanMadInkProcesses;

import java.util.Date;

public class FraudScoreTableObject {
	
	String tableName = "fraudRating";
	String processInstanceID;
	String fraudInstanceID;
	//inputs
	boolean paymentRejected;
	int numberOfPayouts;
	boolean historyOfFraud;
	double calimAmount;
	
	//Output
	int fraudScore;

	
	//Process Variables

	String claimType;
	String region;
	boolean requiredAudit;
	String nameOfAssignedUser;
	
	
	//fake variables
	Date dateOfClaim;
	
	
	
	
	public Date getDateOfClaim() {
		return dateOfClaim;
	}

	public void setDateOfClaim(Date dateOfClaim) {
		this.dateOfClaim = dateOfClaim;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean isRequiredAudit() {
		return requiredAudit;
	}

	public void setRequiredAudit(boolean requiredAudit) {
		this.requiredAudit = requiredAudit;
	}

	public String getNameOfAssignedUser() {
		return nameOfAssignedUser;
	}

	public void setNameOfAssignedUser(String nameOfAssignedUser) {
		this.nameOfAssignedUser = nameOfAssignedUser;
	}

	public String getProcessInstanceID() {
		return processInstanceID;
	}

	public void setProcessInstanceID(String processInstanceID) {
		this.processInstanceID = processInstanceID;
	}

	public String getFraudInstanceID() {
		return fraudInstanceID;
	}

	public void setFraudInstanceID(String fraudInstanceID) {
		this.fraudInstanceID = fraudInstanceID;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isPaymentRejected() {
		return paymentRejected;
	}

	public void setPaymentRejected(boolean paymentRejected) {
		this.paymentRejected = paymentRejected;
	}

	public int getNumberOfPayouts() {
		return numberOfPayouts;
	}

	public void setNumberOfPayouts(int numberOfPayouts) {
		this.numberOfPayouts = numberOfPayouts;
	}

	public boolean isHistoryOfFraud() {
		return historyOfFraud;
	}

	public void setHistoryOfFraud(boolean historyOfFraud) {
		this.historyOfFraud = historyOfFraud;
	}

	public double getCalimAmount() {
		return calimAmount;
	}

	public void setCalimAmount(double d) {
		this.calimAmount = d;
	}

	public int getFraudScore() {
		return fraudScore;
	}

	public void setFraudScore(int fraudScore) {
		this.fraudScore = fraudScore;
	}
	

}
