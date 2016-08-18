package org.camunda.bpm.example.bpmntransaction.bpmntransaction;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  private static final String BPMN_PROCESS_KEY = "bpmntransaction";

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  // enable more detailed logging
  static {
//    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
//    LogFactory.useJdkLogging(); // MyBatis
  }

  @Before
  public void setup() {
	init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() {
	  ProcessInstance pi = processEngine().getRuntimeService().startProcessInstanceByKey( //
			  BPMN_PROCESS_KEY, //
			  Variables.createVariables() //
			  		.putValue("chargeCardError", false)
			  		.putValue("bookingHotelError", false));	  
	  
	  assertThat(pi).task("userTaskChooseHolidayDestination");
	  complete(task());
	  
	  assertThat(pi).task("userTaskGoOnHoliday");
	  assertThat(pi).hasPassedInOrder("serviceTaskBookHotel", "serviceTaskChargeCreditCard");

	  complete(task());
	  assertThat(pi).isEnded();
	  
	  ProcessTestCoverage.calculate(pi, processEngine());	  	  
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testErrorPath() {
	  ProcessInstance pi = processEngine().getRuntimeService().createProcessInstanceByKey(BPMN_PROCESS_KEY) 
			    .startBeforeActivity("serviceTaskBookHotel") // 
			    .setVariables( // 
			    		Variables.createVariables() //
			    			.putValue("chargeCardError", false) //
			    			.putValue("bookingHotelError", true)) //
			    .execute();
	  
	  assertThat(pi).task("userTaskUnknownError");
	  assertThat(pi).hasPassedInOrder("serviceTaskBookHotel", "boundaryEventUnknownError");
	  
	  complete(task());
	  assertThat(pi).isEnded();
	  
	  ProcessTestCoverage.calculate(pi, processEngine());	  	  
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testCancelationPath() {
	  ProcessInstance pi = processEngine().getRuntimeService().createProcessInstanceByKey(BPMN_PROCESS_KEY) 
			    .startBeforeActivity("serviceTaskBookHotel") // 
			    .setVariables( // 
			    		Variables.createVariables() //
			    			.putValue("chargeCardError", true) //
			    			.putValue("bookingHotelError", false)) //
			    .execute();
	  
	  assertThat(pi).task("userTaskCheckCancelationDetails");
	  assertThat(pi).hasPassed("serviceTaskBookHotel", "serviceTaskChargeCreditCard", "compenationServiceTaskCancelHotelReservation", "cancelEndEventTripCanceled"); 
	  
	  complete(task());
	  assertThat(pi).isEnded();
	  
	  ProcessTestCoverage.calculate(pi, processEngine());	  
  }
  
  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }    
}
