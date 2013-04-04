package com.camunda.fox.demo.outerspace.errorhandling;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named("sysout")
public class SysoutDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("Execution " + execution.getId() + " passed by...");
	}

}
