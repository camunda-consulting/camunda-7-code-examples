package com.camunda.fox.mule;

import java.util.Map;

import javax.naming.NamingException;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class FoxService {

//	private final static Logger log = Logger.getLogger(FoxService.class.getName());
	
	private final String PROCESS_KEY = "open-account-mule";
	private RuntimeService runtimeService;
	
	public FoxService() throws NamingException {
		// TODO: this will always retrieve the runtime service from the process 
		// instance with the name "default". Since this code is currently only 
		// intended as a simple show-case, multitenancy support is not considered!
		runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
	}

	// --- Convenience methods for showcase
	
	public void startAccontOpeningProcess(Map<String, Object> variables) {
		String businessKey = (String)variables.get("ordernumber");
		runtimeService.startProcessInstanceByKey(PROCESS_KEY, businessKey, variables);
	}

	public void postidentReceived(String businessKey) {
		
		// TODO: this should be done by a 
		// transformer/processor in the flow
		businessKey = businessKey.split("-")[1].substring(0, 4);
		
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
		        .processInstanceBusinessKey(businessKey)
		        .processDefinitionKey(PROCESS_KEY).singleResult();
		runtimeService.signal(processInstance.getId());
	}
}
