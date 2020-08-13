package com.camunda.com.meetup;

public class CheckIn {

	private String email;
	private boolean hotelCreditCard;
	private int yearsWithMembership;
	private boolean weekend;
	private String clubMembership;
	
	

	public boolean hotelCreditCard() {
		return hotelCreditCard;
	}

	public void setHotelCreditCard(boolean hotelCreditCard) {
		this.hotelCreditCard = hotelCreditCard;
	}

	public int getYearsWithMembership() {
		return yearsWithMembership;
	}

	public void setYearsWithMembership(int yearsWithMembership) {
		this.yearsWithMembership = yearsWithMembership;
	}

	public boolean weekend() {
		return weekend;
	}

	public void setWeekend(boolean weekend) {
		this.weekend = weekend;
	}

	public String getClubMembership() {
		return clubMembership;
	}

	public void setClubMembership(String clubMembership) {
		this.clubMembership = clubMembership;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
