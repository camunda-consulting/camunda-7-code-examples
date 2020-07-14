package com.camunda.consulting.test.helper;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageTimeoutClassHelper implements ExecutionListener {
	
    private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		logger.info("MessageTimeoutClassHelper has been called");
	}

}
