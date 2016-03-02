package com.camunda.demo.task.escalation;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.execute;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.job;

import java.util.Date;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UserTaskOverdueTest {

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();
	
	// enable more detailed logging
	static {
		LogFactory.useJdkLogging();
	}
	
	@Test
	@Deployment(resources = "test.bpmn")
	public void testTaskEscalated() {
		Date dueDate = new Date();
		VariableMap variables = Variables.createVariables().putValue("dueDate", dueDate);
		ProcessInstance pi = processEngine().getRuntimeService().startProcessInstanceByKey("test", variables);
		
		assertThat(pi).task();
		assertThat(pi).job()
			.hasDueDate(dueDate);
		execute(job());
	}
	

}
