package com.camunda.consulting.correlator;

import static com.camunda.consulting.GlobalMessageCorrelationConstants.MessageName.*;
import static com.camunda.consulting.GlobalMessageCorrelationConstants.VariableName.*;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This Service is called instead of the {@link RuntimeService} to correlate a
 * message and only accepts {@link MessageCorrelationRunner} implementations
 * 
 * @author jonathanlukas
 *
 */
@Service
public class GlobalMessageCorrelator
{
	private final ProcessEngine processEngine;

	@Autowired
	public GlobalMessageCorrelator(ProcessEngine processEngine)
	{
		this.processEngine = processEngine;
	}

	public ProcessInstance correlateMessage(MessageCorrelationRunner runner)
	{
		return this.processEngine
			.getRuntimeService()
			.createMessageCorrelation(GlobalIncomingMsg)
			.setVariable(RunnerVar, runner)
			.correlateStartMessage();
	}
}
