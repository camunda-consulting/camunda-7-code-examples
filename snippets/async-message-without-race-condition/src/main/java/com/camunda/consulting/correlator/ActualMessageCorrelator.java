package com.camunda.consulting.correlator;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camunda.consulting.delegate.RedirectToActualReceiverDelegate;

/**
 * This Service is called from {@link RedirectToActualReceiverDelegate} and
 * handles the {@link MessageCorrelationRunner} provided there
 * 
 * @author jonathanlukas
 *
 */
@Service
public class ActualMessageCorrelator
{
	private final ProcessEngine processEngine;

	@Autowired
	public ActualMessageCorrelator(ProcessEngine processEngine)
	{
		this.processEngine = processEngine;
	}

	public void correlateMessageWith(MessageCorrelationRunner runner)
	{
		runner.apply(this.processEngine.getRuntimeService().createMessageCorrelation(runner.getMessageName()));
	}
}
