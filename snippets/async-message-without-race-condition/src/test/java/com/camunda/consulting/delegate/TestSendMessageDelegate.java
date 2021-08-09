package com.camunda.consulting.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Just a simple delegate that does nothing
 * 
 * @author jonathanlukas
 *
 */
public class TestSendMessageDelegate implements JavaDelegate
{

	@Override
	public void execute(DelegateExecution execution) throws Exception
	{
		// nothing to do here
	}

}
