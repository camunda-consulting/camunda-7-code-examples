package com.camunda.demo.environment;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.repositoryService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  // enable more detailed logging
  static {
    // LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
    // LogFactory.useJdkLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources="simulate.bpmn")
  public void testSimulationDrive() {
    TimeAwareDemoGenerator generator = new TimeAwareDemoGenerator(processEngine()) //
        .processDefinitionKey("simulate") //
        .numberOfDaysInPast(5) //
        .timeBetweenStartsBusinessDays(600.0, 100.0); // approx. every 10 minutes
    generator.generateData();
    
//    ProcessInstance pi = runtimeService().startProcessInstanceByKey("simulate");
//    assertThat(pi).task();
//    complete(task());
//    assertThat(pi).isEnded();
  }

  @Test
  public void testModelApiBug() {
    BpmnModelInstance modelInstance = Bpmn.createEmptyModel();
    Definitions definitions = modelInstance.newInstance(Definitions.class);
    definitions.setTargetNamespace("http://camunda.org/examples");
    modelInstance.setDefinitions(definitions);
    org.camunda.bpm.model.bpmn.instance.Process process = modelInstance.newInstance(org.camunda.bpm.model.bpmn.instance.Process.class);
    process.setId("test");
    definitions.addChildElement(process);

    StartEvent startEvent = modelInstance.newInstance(StartEvent.class);
    startEvent.setAttributeValue("id", "startEvent1", true);   
    process.addChildElement(startEvent);

    ServiceTask serviceTask = modelInstance.newInstance(ServiceTask.class);
    serviceTask.setAttributeValue("id", "serviceTask1", true);   
    serviceTask.setCamundaClass(null);
    // TODO: Wait for https://app.camunda.com/jira/browse/CAM-4178 and set to null!
    //serviceTask.setCamundaDelegateExpression(null);
    serviceTask.setCamundaExpression("#{true}"); // Noop
    process.addChildElement(serviceTask);
    
    createSequenceFlow(process, startEvent, serviceTask);
    
    repositoryService().createDeployment().addModelInstance("test.bpmn", modelInstance).deploy();
    runtimeService().startProcessInstanceByKey("test");
  }
  
  public SequenceFlow createSequenceFlow(org.camunda.bpm.model.bpmn.instance.Process process, FlowNode from, FlowNode to) {
    String identifier = from.getId() + "-" + to.getId();
    SequenceFlow sequenceFlow = createElement(process, identifier, SequenceFlow.class);
    process.addChildElement(sequenceFlow);
    sequenceFlow.setSource(from);
    from.getOutgoing().add(sequenceFlow);
    sequenceFlow.setTarget(to);
    to.getIncoming().add(sequenceFlow);
    return sequenceFlow;
  }
  protected <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, String id, Class<T> elementClass) {
    T element = parentElement.getModelInstance().newInstance(elementClass);
    element.setAttributeValue("id", id, true);
    parentElement.addChildElement(element);
    return element;
  }
}
