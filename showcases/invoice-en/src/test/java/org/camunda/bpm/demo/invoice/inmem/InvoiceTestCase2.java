package org.camunda.bpm.demo.invoice.inmem;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.claim;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.withVariables;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.demo.invoice.test.mock.SvnDelegateMock;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InvoiceTestCase2 {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "camunda-invoice-en-collaboration";

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
  @Deployment(resources = "camunda-invoice-en-collaboration.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = "camunda-invoice-en-collaboration.bpmn")
  public void testExecution() {
	  Mocks.register("archiveService", new SvnDelegateMock());
	  
	  ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
	  
	  assertThat(processInstance)
	  	.isStarted()
	  	.task("assignApprover")
	  	.isAssignedTo("demo");
	  
	  complete(task(), withVariables("approver", "john"));
	  
	  assertThat(task("approveInvoice")).isAssignedTo("john");

	  complete(task(), withVariables("approved", false));
	  
	  assertThat(task("reviewInvoice")).isAssignedTo("demo");
	  
	  complete(task(), withVariables("clarified", "yes"));

	  assertThat(task("approveInvoice")).isAssignedTo("john");

	  complete(task(), withVariables("approved", true));
	  
	  assertThat(task("prepareBankTransfer")).hasCandidateGroup("accounting");
	  
	  claim(task(), "Jane");
	  
	  complete(task());
	
	  assertThat(processInstance).isEnded();
  }

}
