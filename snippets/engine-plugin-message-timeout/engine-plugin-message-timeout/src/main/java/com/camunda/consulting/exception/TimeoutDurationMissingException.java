package com.camunda.consulting.exception;

public class TimeoutDurationMissingException extends RuntimeException {
	
	public TimeoutDurationMissingException() {}
	
	public TimeoutDurationMissingException(String message) {
		super(message);
	}
	
}
