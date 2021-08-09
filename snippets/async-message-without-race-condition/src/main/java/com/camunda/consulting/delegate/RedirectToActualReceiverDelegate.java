package com.camunda.consulting.delegate;

import static com.camunda.consulting.GlobalMessageCorrelationConstants.DelegateName.*;
import static com.camunda.consulting.GlobalMessageCorrelationConstants.VariableName.*;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.camunda.consulting.correlator.ActualMessageCorrelator;
import com.camunda.consulting.correlator.MessageCorrelationRunner;

/**
 * This delegate is called at the end event of the globla message correlation
 * process. It is allowed to fail, retry will be done through the engine
 * 
 * @author jonathanlukas
 *
 */
@Component(RedirectToActualReceiverDlg)
public class RedirectToActualReceiverDelegate implements JavaDelegate
{
	private final ActualMessageCorrelator correlator;

	public RedirectToActualReceiverDelegate(ActualMessageCorrelator correlator)
	{
		this.correlator = correlator;
	}

	@Override
	public void execute(DelegateExecution execution) throws Exception
	{
		MessageCorrelationRunner runner = (MessageCorrelationRunner) execution.getVariable(RunnerVar);
		this.correlator.correlateMessageWith(runner);

	}

}
