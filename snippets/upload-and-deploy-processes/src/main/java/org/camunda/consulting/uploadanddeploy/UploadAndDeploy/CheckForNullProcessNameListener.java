package org.camunda.consulting.uploadanddeploy.UploadAndDeploy;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class CheckForNullProcessNameListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {

		String procName = (String) execution.getVariable("processName");
		
		if(procName == null){
			execution.setVariable("processSelected", false);
		}else{
			execution.setVariable("processSelected", true);
		}
		
	}

}
