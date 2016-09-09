package com.camunda.demo.environment.simulation;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.repositoryService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

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
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.demo.environment.simulation.TimeAwareDemoGenerator;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class DemoDataGeneratorTest {

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
  @Deployment(resources = "simulate.bpmn")
  public void testSimulationDrive() {
    TimeAwareDemoGenerator generator = new TimeAwareDemoGenerator(processEngine()) //
        .processDefinitionKey("simulate") //
        .numberOfDaysInPast(1) //
        .timeBetweenStartsBusinessDays(6000.0, 100.0); // every 6000 seconds
    generator.generateData();

    // ProcessInstance pi =
    // runtimeService().startProcessInstanceByKey("simulate");
    // assertThat(pi).task();
    // complete(task());
    // assertThat(pi).isEnded();
  }
  
  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = {"InsuranceApplication.bpmn", "ApplicationCheck.cmmn"})
  public void testInsuranceApplicationWithCmmn() {
    TimeAwareDemoGenerator generator = new TimeAwareDemoGenerator(processEngine()) //
        .processDefinitionKey("insurance-application") //
        .numberOfDaysInPast(1) //
        .timeBetweenStartsBusinessDays(6000.0, 100.0); // every 6000 seconds
    generator.generateData();
    
    // everything should be finished as case instance gets completed
    assertEquals(0, processEngine().getRuntimeService().createProcessInstanceQuery().processDefinitionKey("insurance-application").count());
  }

  @Test
  public void testModelApiUtf8Bug() throws UnsupportedEncodingException {
    BpmnModelInstance modelInstance = Bpmn.readModelFromStream(this.getClass().getResourceAsStream("/umlauts.bpmn"));

    Collection<ModelElementInstance> serviceTasks = modelInstance.getModelElementsByType(modelInstance.getModel().getType(ServiceTask.class));
    assertEquals(1, serviceTasks.size());
    ServiceTask serviceTask = (ServiceTask) serviceTasks.iterator().next();
    serviceTask.setCamundaExpression("#{true}");

    String xmlString = Bpmn.convertToString(modelInstance);
    org.camunda.bpm.engine.repository.Deployment deployment = processEngine().getRepositoryService().createDeployment() //
        .addInputStream("umlauts.bpmn", new ByteArrayInputStream(xmlString.getBytes("UTF-8")))
        .deploy();

    processEngine().getRepositoryService().deleteDeployment(deployment.getId(), true);
  }

  @Test
  public void testModelApiUtf8BugUsingTweak() {
    org.camunda.bpm.engine.repository.Deployment deployment = processEngine().getRepositoryService().createDeployment() //
        .addClasspathResource("rechnungseingang.bpmn") //
        .deploy();

    TimeAwareDemoGenerator generator = new TimeAwareDemoGenerator(processEngine());
    generator.processDefinitionKey("rechnungseingang").numberOfDaysInPast(5);
    generator.tweakProcessDefinition();
    processEngine().getRepositoryService().deleteDeployment(deployment.getId(), true);
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
    // TODO: Wait for https://app.camunda.com/jira/browse/CAM-4178 and set to
    // null!
    // serviceTask.setCamundaDelegateExpression(null);
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
