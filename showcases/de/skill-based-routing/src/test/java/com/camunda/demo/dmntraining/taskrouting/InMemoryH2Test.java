package com.camunda.demo.dmntraining.taskrouting;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.execute;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.job;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = { "com.camunda.demo.dmntraining.taskrouting.LoadQualifiedEmployees" })
public class InMemoryH2Test {

	@Rule
	  public ProcessEngineRule rule = new ProcessEngineRule();


	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}


	@Test
	@Deployment(resources = { "mitarbeiterBestimmen.bpmn", "notwendigeKompetenz.dmn", "mitarbeiterErfahrung.dmn",  "mitarbeiterScore.dmn" })
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testSkillBasedRouting() throws Exception {
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("mary", 5000, "97xxx", 2)); // 5000 = "Senior"
		employees.add(new Employee("peter", 5000, "10xxx", 7));
		employees.add(new Employee("john", 5000, "10xxx", 5));
		
		Claim claimCarAccident = DemoData.createClaimCarAccident(10000, "10xxx");
		
		// John is best suited
		
		EmployeeService mockEmployeeService = PowerMockito.mock(EmployeeService.class);
		PowerMockito.whenNew(EmployeeService.class).withAnyArguments().thenReturn(mockEmployeeService);		
		
		PowerMockito.when(mockEmployeeService.getQualifiedAndAvailableEmployees(Mockito.anyList())).thenReturn(employees);
					
		ProcessInstance pi = rule.getProcessEngine().getRuntimeService().startProcessInstanceByKey(BpmConstants.DECISION_FLOW_KEY_mitarbeiterBestimmen, //
				Variables.createVariables() //
						.putValue("claim", claimCarAccident) //
		);
		String employee = (String) rule.getProcessEngine().getHistoryService().createHistoricVariableInstanceQuery() //
				.processInstanceId(pi.getId()) //
				.variableName("selectedEmployee") //
				.singleResult().getValue();

		assertEquals("john", employee);
		
		ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
		Mockito.verify(mockEmployeeService).getQualifiedAndAvailableEmployees(argument.capture());
		assertEquals(2, argument.getValue().size());
		assertEquals("KFZ", argument.getValue().get(0));
		assertEquals("Experte", argument.getValue().get(1));
	}

	

	@Test
	@Deployment(resources = { "schadenBearbeiten.bpmn", "mitarbeiterBestimmen.bpmn", "notwendigeKompetenz.dmn", "mitarbeiterErfahrung.dmn",  "mitarbeiterScore.dmn" })
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testSimulation() throws Exception {
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("mary", 5000, "97xxx", 2)); // 5000 = "Senior"
		employees.add(new Employee("peter", 5000, "10xxx", 7));
		employees.add(new Employee("john", 5000, "10xxx", 5));
		
		Claim claimCarAccident = DemoData.createClaimCarAccident(10000, "10xxx");
		
		// John is best suited
		
		EmployeeService mockEmployeeService = PowerMockito.mock(EmployeeService.class);
		PowerMockito.whenNew(EmployeeService.class).withAnyArguments().thenReturn(mockEmployeeService);		
		
		PowerMockito.when(mockEmployeeService.getQualifiedAndAvailableEmployees(Mockito.anyList())).thenReturn(employees);
					
		ProcessInstance pi = rule.getProcessEngine().getRuntimeService().startProcessInstanceByKey( //
				"schadensbearbeitungSimulation", //
				Variables.createVariables() //
						.putValue("claim", claimCarAccident) //
		);
		assertThat(pi).job();
		execute(job());
		assertThat(pi).task("UserTaskSchadenRegulieren").isAssignedTo("john");
		
	}
	
	@Test
	@Deployment(resources = { "mitarbeiterBestimmen.bpmn", "notwendigeKompetenz.dmn", "mitarbeiterErfahrung.dmn",  "mitarbeiterScore.dmn" })
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testOutput() {
		Claim claimCarAccident = DemoData.createClaimThirdPartyLiability(15000, "10xxx", "Handy");
		
		ProcessInstance pi = rule.getProcessEngine().getRuntimeService().startProcessInstanceByKey(BpmConstants.DECISION_FLOW_KEY_mitarbeiterBestimmen, //
				Variables.createVariables() //
						.putValue("claim", claimCarAccident) //
		);
		String employee = (String) rule.getProcessEngine().getHistoryService().createHistoricVariableInstanceQuery() //
				.processInstanceId(pi.getId()) //
				.variableName("selectedEmployee") //
				.singleResult().getValue();
		System.out.println(employee);
	}
}
