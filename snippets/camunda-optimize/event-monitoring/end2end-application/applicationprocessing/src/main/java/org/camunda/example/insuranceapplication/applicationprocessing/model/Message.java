package org.camunda.example.insuranceapplication.applicationprocessing.model;

import java.time.LocalDateTime;

public class Message {
	private String eventName;
	private Object data;
	private LocalDateTime time;
	
	public Message() {
		super();
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
}
