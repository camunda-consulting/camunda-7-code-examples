package com.camunda.consulting;

/**
 * Definition of process constants, just to prevent typo
 * 
 * @author jonathanlukas
 *
 */
public interface GlobalMessageCorrelationConstants
{
	interface VariableName
	{
		String RunnerVar = "runner";
	}

	interface MessageName
	{
		String GlobalIncomingMsg = "global_incoming_message";
	}

	interface DelegateName
	{
		String RedirectToActualReceiverDlg = "redirect_to_actual_receiver";
	}
}
