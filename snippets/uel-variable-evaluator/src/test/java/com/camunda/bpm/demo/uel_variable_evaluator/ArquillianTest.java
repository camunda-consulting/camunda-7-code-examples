package com.camunda.bpm.demo.uel_variable_evaluator;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.processInstanceQuery;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTest {
  
  private static final String PROCESS_DEFINITION_KEY = "uel-variable-evaluator";

  @Deployment
  public static WebArchive createDeployment() {
    // resolve given dependencies from Maven POM
    File[] libs = Maven.resolver()
      .offline(false)
      .loadPomFromFile("pom.xml")
      .importRuntimeAndTestDependencies().resolve().withTransitivity().asFile();

    return ShrinkWrap
            .create(WebArchive.class, "uel-variable-evaluator.war")
            // add needed dependencies
            .addAsLibraries(libs)
            // prepare as process application archive for camunda BPM Platform
            .addAsResource("META-INF/processes.xml", "META-INF/processes.xml")
            // enable CDI
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            // add your own classes (could be done one by one as well)
            .addPackages(false, "com.camunda.bpm.demo.uel_variable_evaluator") // not recursive to skip package 'nonarquillian'
            // add process definition
            .addAsResource("process.bpmn")
            // add process image for visualizations
            .addAsResource("process.png")
            // now you can add additional stuff required for your test case
    ;
  }

  @Inject
  private ProcessEngine processEngine;

  @Inject
  private LoggerDelegate logger;

  @Before
  public void setup() {
	init(processEngine);
  }

  /**
   * Tests that the process is executable and reaches its end.
   */
  @Test
  public void testProcessExecution() throws Exception {
    Map <String, Object> variables = new HashMap<String, Object>();
    variables.put("expression", "${logger.execute(execution)}");
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance)
      .hasVariables("loggerInvoked");
  }

  /**
   * Helper to delete all running process instances, which might disturb our Arquillian Test case
   * Better run test cases in a clean environment, but this is pretty handy for demo purposes
   */
  private void cleanUpRunningProcessInstances() {
    List<ProcessInstance> runningInstances = processInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).list();
    for (ProcessInstance processInstance : runningInstances) {
      runtimeService().deleteProcessInstance(processInstance.getId(), "deleted to have a clean environment for Arquillian");
    }
  }  
}
