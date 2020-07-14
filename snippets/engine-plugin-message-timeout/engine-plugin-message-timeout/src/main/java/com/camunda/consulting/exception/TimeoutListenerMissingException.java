package com.camunda.consulting.exception;

public class TimeoutListenerMissingException extends RuntimeException {
	
	public TimeoutListenerMissingException() {}
	
	public TimeoutListenerMissingException(String message) {
		super(message);
	}
	
}
