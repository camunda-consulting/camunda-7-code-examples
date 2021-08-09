package com.camunda.consulting.correlator;

import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;

/**
 * That runner that performs the actual message correlation Invoked by the
 * {@link ActualMessageCorrelator}
 * 
 * @author jonathanlukas
 *
 */
public class TestMessageCorrelationRunner implements MessageCorrelationRunner
{
	private String result;

	@Override
	public MessageCorrelationResult apply(MessageCorrelationBuilder t)
	{
		return t.setVariable("result", this.result).correlateWithResult();
	}

	@Override
	public String messageName()
	{
		return "test_receive_message";
	}

	public String getResult()
	{
		return this.result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

}
