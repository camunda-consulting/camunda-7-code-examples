package org.camunda.bpm.pattern.data;

import java.util.HashMap;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.pattern.data.test.ProcessDataAccessorTestCase;

public class ExampleProcessDataAccessorTest extends ProcessDataAccessorTestCase {

  private static final String PROCESS_BPMN_FILE = "test-process.bpmn";
  private static final String PROCESS_KEY = "test-process";

  @Deployment(resources = PROCESS_BPMN_FILE)
  public void testCdiProcess() {
    ExampleDelegate exampleDelegate = new ExampleDelegate();
    registerNamedDelegateMock("serviceTask", exampleDelegate, ExampleProcessDataAccessor.class);

    ExampleProcessDataAccessor variables = new ExampleProcessDataAccessor(new HashMap<String, Object>());
    variables.setFoo("hallo");
    variables.setBar("hallo");
    variables.setHello("test");
    variables.setWorld("xxx");

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, variables.getVariablesMap());
    System.out.println("Started testProcess process instance id=" + processInstance.getId() + ", " + variables.toString());

    taskService.complete(taskService.createTaskQuery().singleResult().getId());

    System.out.println("Task completed");

    assertProcessEnded(processInstance.getId());
  }

}
