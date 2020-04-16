package org.camunda.example.insuranceapplication.applicationprocessing.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class LoggerDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(execution.getCurrentActivityName() + " executed.");
	}

}
