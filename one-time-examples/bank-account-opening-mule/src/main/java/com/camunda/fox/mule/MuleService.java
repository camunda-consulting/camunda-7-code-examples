package com.camunda.fox.mule;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.client.MuleClient;

@Named(value="mule")
public class MuleService {

	@Inject
	MuleContext context;
	
	public void flow(String flowName, DelegateExecution execution) throws MuleException {
		
		MuleClient client = context.getClient();
		
		client.dispatch("vm://" + flowName, execution.getVariables(), null);

		// if we want to be able to write return values back into the 
		// result-variable in fox, we could alternatively use "request-respond" 
		// pattern and call/ return client.send(url, message) here. In the flow,
		// we would then have to set exchange-pattern="request-response"
		
	}
	
}
