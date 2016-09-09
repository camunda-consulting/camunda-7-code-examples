package org.camunda.hackdays2016.BanMadInkProcesses;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;

public class CompleteDecideOnFurtherFraudCheckDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		  Map<String, Object> vars = new HashMap<String, Object>();
		  
		  String busKey = (String) execution.getVariable("busKey");
		  
		  RandomGeneratorUtil randomUtil = new RandomGeneratorUtil();
		  
		  vars.put("audit", randomUtil.randomBoolean(60));
		  
		 Execution processExectuion =  execution.getProcessEngineServices().getRuntimeService().createExecutionQuery().processInstanceBusinessKey(busKey).singleResult();
		 int fraudNumber = (Integer) execution.getProcessEngineServices().getRuntimeService().getVariable(processExectuion.getId(), "fraudScore");
		 
		 if(fraudNumber < 0){
			 vars.put("audit", randomUtil.randomBoolean(95));
		 }else{
			 vars.put("audit", randomUtil.randomBoolean(10));
		 }
		 
		 Task currentTask = execution.getProcessEngineServices().getTaskService().createTaskQuery().processInstanceBusinessKey(busKey).singleResult();

		 execution.getProcessEngineServices().getTaskService().complete(currentTask.getId(), vars);

		

	}

}
