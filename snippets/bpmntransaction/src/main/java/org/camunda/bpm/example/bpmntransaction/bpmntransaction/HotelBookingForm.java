package org.camunda.bpm.example.bpmntransaction.bpmntransaction;

import java.io.Serializable;

public final class HotelBookingForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String hotelName;
	private int numberOfRooms;
	private boolean includeBreakfast;
	
	
	public HotelBookingForm(String hotelName, int numberOfRooms,
			boolean includeBreakfast) {
		super();
		this.hotelName = hotelName;
		this.numberOfRooms = numberOfRooms;
		this.includeBreakfast = includeBreakfast;
	}


	public String getHotelName() {
		return hotelName;
	}


	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}


	public int getNumberOfRooms() {
		return numberOfRooms;
	}


	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}


	public boolean isIncludeBreakfast() {
		return includeBreakfast;
	}


	public void setIncludeBreakfast(boolean includeBreakfast) {
		this.includeBreakfast = includeBreakfast;
	}
	
	

}
