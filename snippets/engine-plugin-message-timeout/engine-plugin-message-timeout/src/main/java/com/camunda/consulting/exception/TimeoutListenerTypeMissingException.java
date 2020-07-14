package com.camunda.consulting.exception;

public class TimeoutListenerTypeMissingException extends RuntimeException {
	
	public TimeoutListenerTypeMissingException() {}
	
	public TimeoutListenerTypeMissingException(String message) {
		super(message);
	}
	
}
