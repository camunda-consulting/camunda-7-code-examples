package org.camunda.hackdays2016.BanMadInkProcesses;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;

public class CompleteEnterCandidateDetailsDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		
          		
		  Map<String, Object> vars = new HashMap<String, Object>();
		  
		  String busKey = (String) execution.getVariable("busKey");
		  
		  RandomGeneratorUtil randomUtil = new RandomGeneratorUtil();
		  
		  vars.put("claimType", randomUtil.getRandomClaimType());
		  vars.put("claimAmount", randomUtil.getRandomClaimAmount());
		  vars.put("region", randomUtil.getRandomRegion());
		  
		  Task theFirstTask = execution.getProcessEngineServices().getTaskService().createTaskQuery().processInstanceBusinessKey(busKey).singleResult();
  
		  execution.getProcessEngineServices().getTaskService().complete(theFirstTask.getId(), vars);

		

	}

}
