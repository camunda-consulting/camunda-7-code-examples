package com.camunda.demo.webinar.testing.scope1;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.execute;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.job;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.camunda.demo.webinar.testing.Customer;
import com.camunda.demo.webinar.testing.CustomerServiceAdapter;
import com.camunda.demo.webinar.testing.CustomerServiceImpl;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(PowerMockRunner.class)
//@PrepareForTest( fullyQualifiedNames = {"com.camunda.demo.webinar.testing.*"} )
public class MockitoTestCase {

	@Rule
	ProcessEngineRule processEngineRule = new ProcessEngineRule();
	
	// enable more detailed logging
	static {
		LogUtil.readJavaUtilLoggingConfigFromClasspath();
	}

  @After
  public void tearDown() throws Exception {
    // visualize coverage of test suite
    ProcessTestCoverage.calculate(processEngineRule.getProcessEngine());
  }
	
	@Test
	@Deployment( resources="process.bpmn")
	public void testPathCWithServiceInvocation() throws Exception{
		
    // not actually a mock, but create the class to work without CDI/Spring and @Named beans:
		CustomerServiceAdapter adapter = new CustomerServiceAdapter();
    Mocks.register("customerServiceAdapter", adapter); 
		
    // register Mock for CustomerService
		CustomerServiceImpl mockCustomerService = Mockito.mock(CustomerServiceImpl.class);
		// and set it on our JavaDelegate
		Whitebox.setInternalState(adapter, "customerService", mockCustomerService);
		
		// This could be used if you do an "new CustomerServiceImpl()" somewhere in your code.
		// PowerMockito.whenNew(CustomerServiceImpl.class).withAnyArguments().thenReturn(mockCustomerService);
		
		// Tell the mock to return a fixed value
		Mockito.when(mockCustomerService.createCustomer(Mockito.any(Customer.class))).thenReturn("123-456");		
		
		ProcessInstance processInstance = processEngineRule.getProcessEngine().getRuntimeService().startProcessInstanceByKey( //
		    "webinar-testing", //
		    Variables.createVariables().putValue("customerName", "Bernd"));

		assertThat(processInstance).task();
		complete(task(), Variables.createVariables().putValue("path", "C"));
		execute(job());

    Mockito.verify(mockCustomerService, Mockito.times(1)).createCustomer(Mockito.any(Customer.class));
    assertThat(processInstance).isEnded();
    
    // check that we wrote the correct process variable    
    // do this via API as the assert for HistoricVariables is not yet implemented in camunda-bpm-assert (see https://github.com/camunda/camunda-bpm-assert/issues/42)
    HistoricVariableInstance customerId = processEngine().getHistoryService().createHistoricVariableInstanceQuery().variableName("customerId").singleResult();
    assertNotNull(customerId);
    assertEquals("123-456", customerId.getValue());
    
    // visualize path in process instance
    ProcessTestCoverage.calculate(processInstance.getId(), processEngineRule.getProcessEngine());    
	}

	
}
