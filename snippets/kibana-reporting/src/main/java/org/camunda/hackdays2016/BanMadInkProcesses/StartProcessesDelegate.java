package org.camunda.hackdays2016.BanMadInkProcesses;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;

public class StartProcessesDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		RandomGeneratorUtil randomUtil = new RandomGeneratorUtil();
		
		// randomly generated ID
		  String busKey = randomUtil.generateID();
		  execution.setVariable("busKey", busKey);
		  // process variables
		  Map<String, Object> vars = new HashMap<String, Object>();
		  vars.put("candidateName", randomUtil.getRandomName());
		
		  execution.getProcessEngineServices().getRuntimeService().startProcessInstanceByKey("InsurencePaymentCheck", busKey, vars);
		  
		  

	}
	

	
	

}
