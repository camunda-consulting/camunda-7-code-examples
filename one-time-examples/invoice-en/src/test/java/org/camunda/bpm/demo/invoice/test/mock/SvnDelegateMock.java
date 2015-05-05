package org.camunda.bpm.demo.invoice.test.mock;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


@SuppressWarnings("cdi-ambiguous-name")
@Named("archiveService")
@ApplicationScoped
public class SvnDelegateMock implements JavaDelegate{

	private boolean called = false;
	
	public void execute(DelegateExecution execution) throws Exception {
		called = true;
	}
	
	public void reset() {
		called = false;
	}
	
	public boolean isCalled() {
		return called;
	}
}
