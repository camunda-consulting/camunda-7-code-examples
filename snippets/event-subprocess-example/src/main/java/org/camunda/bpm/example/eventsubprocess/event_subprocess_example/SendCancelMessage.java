package org.camunda.bpm.example.eventsubprocess.event_subprocess_example;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendCancelMessage implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String orderBusKey = (String)execution.getVariable("orderBusKey");
		String messageType = (String)execution.getVariable("messageType");
		String orderedItem = (String)execution.getVariable("orderedItem");
		String reason = (String)execution.getVariable("reason");
		
		String messageFrom = execution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getUserId();
		
		//the variables to pass via message
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("reason", reason);
		vars.put("messageFrom", messageFrom);
		
		if(orderedItem == null || orderedItem == ""){
			
			execution.getProcessEngineServices().getRuntimeService().correlateMessage(messageType, orderBusKey, vars);
			
		}else{
			//correlation keys
			Map<String, Object> correlationKeys = new HashMap<String, Object>();
			correlationKeys.put("orderedItem", orderedItem);
			
			execution.getProcessEngineServices().getRuntimeService().correlateMessage(messageType, orderBusKey, correlationKeys, vars);
			
		}

	}

}
