package org.camunda.hackdays2016.BanMadInkProcesses;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;


public class CompleteEnterApplicationDetailsDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

     		

	  Map<String, Object> vars = new HashMap<String, Object>();
	  
	  String busKey = (String) execution.getVariable("busKey");
	  
	  RandomGeneratorUtil randomUtil = new RandomGeneratorUtil();
	  
	  vars.put("paymentRejected", randomUtil.getRandomPaymentRejected());
	  vars.put("numberOfPayouts", randomUtil.getRandomNumberOfPayments());
	  vars.put("historyOfFraud", randomUtil.getRandomHistoryOfFraud());
	  vars.put("userDeterminedFraud", randomUtil.getRandomUserDeterminedFraud());
	  
	  Task currentTask = execution.getProcessEngineServices().getTaskService().createTaskQuery().processInstanceBusinessKey(busKey).singleResult();

	  if(currentTask.getTaskDefinitionKey().equals("Task_Select_Manual_User")){
		  execution.getProcessEngineServices().getTaskService().complete(currentTask.getId(), vars);
		  throw new BpmnError("TASK_NOT_FOUND");
	  }
	  
	  execution.getProcessEngineServices().getTaskService().complete(currentTask.getId(), vars);
	  
	}

}
