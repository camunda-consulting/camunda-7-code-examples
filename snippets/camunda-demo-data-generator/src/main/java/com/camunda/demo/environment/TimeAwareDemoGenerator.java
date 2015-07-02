package com.camunda.demo.environment;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.ConditionExpression;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.InclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.ScriptTask;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaExecutionListener;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaScript;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaTaskListener;
import org.camunda.bpm.model.xml.ModelInstance;
import org.camunda.bpm.model.xml.impl.util.IoUtil;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

/**
 * TODO: - Classloading klären für Eigene Klassen - oder geht alles per Script?
 * Groovy?
 */
public class TimeAwareDemoGenerator {

  private String processDefinitionKey;
  private int numberOfDaysInPast;
  private StatisticalDistribution timeBetweenStartsBusinessDays;
  private StatisticalDistribution timeBetweenStartsWeekend;

  // private ;
  private ProcessDefinition processDefinition;
  private ProcessEngine engine;

  private Map<String, NormalDistribution> distributions = new HashMap<String, NormalDistribution>();
  private String originalBpmn;

  public TimeAwareDemoGenerator(ProcessEngine engine) {
    this.engine = engine;
  }

  public void generateData() {
   tweakProcessDefinition();
   startMultipleProcessInstances();
   restoreOriginalProcessDefinition();
  }

  protected void restoreOriginalProcessDefinition() {
    engine.getRepositoryService().createDeployment().addString(processDefinitionKey + ".bpmn", originalBpmn).deploy();

  }
  
  protected void tweakProcessDefinition() {
    processDefinition = engine.getRepositoryService().createProcessDefinitionQuery() //
        .processDefinitionKey(processDefinitionKey) // 
        .latestVersion() // 
        .singleResult();
    BpmnModelInstance bpmn = engine.getRepositoryService().getBpmnModelInstance(processDefinition.getId());

    originalBpmn = IoUtil.convertXmlDocumentToString(bpmn.getDocument()); // do not do a validation here as it caused quite strange trouble
//    Logger.getLogger("TEST").severe("-----\n"+originalBpmn + "\n------");
    
    Collection<ModelElementInstance> serviceTasks = bpmn.getModelElementsByType(bpmn.getModel().getType(ServiceTask.class));
    Collection<ModelElementInstance> scriptTasks = bpmn.getModelElementsByType(bpmn.getModel().getType(ScriptTask.class));
    Collection<ModelElementInstance> userTasks = bpmn.getModelElementsByType(bpmn.getModel().getType(UserTask.class));
    Collection<ModelElementInstance> executionListeners = bpmn.getModelElementsByType(bpmn.getModel().getType(CamundaExecutionListener.class));
    Collection<ModelElementInstance> taskListeners = bpmn.getModelElementsByType(bpmn.getModel().getType(CamundaTaskListener.class));
    Collection<ModelElementInstance> xorGateways = bpmn.getModelElementsByType(bpmn.getModel().getType(ExclusiveGateway.class));
    Collection<ModelElementInstance> orGateways = bpmn.getModelElementsByType(bpmn.getModel().getType(InclusiveGateway.class));

    for (ModelElementInstance modelElementInstance : serviceTasks) {
      ServiceTask serviceTask = ((ServiceTask) modelElementInstance);
      serviceTask.setCamundaClass(null);
      // TODO: Wait for https://app.camunda.com/jira/browse/CAM-4178 and set to
      // null!
      // serviceTask.setCamundaDelegateExpression(null);
      serviceTask.setCamundaExpression("#{true}"); // Noop
    }

    for (ModelElementInstance modelElementInstance : userTasks) {
      UserTask userTask = ((UserTask) modelElementInstance);
      userTask.setCamundaAssignee(null);
      userTask.setCamundaCandidateGroups(null);
    }
    
    for (ModelElementInstance modelElementInstance : xorGateways) {
      ExclusiveGateway xorGateway = ((ExclusiveGateway) modelElementInstance);
      tweakGateway(xorGateway);
    }

//    Bpmn.validateModel(bpmn);
    engine.getRepositoryService().createDeployment() //
          .addModelInstance(processDefinitionKey + ".bpmn", bpmn) // 
          .deploy();
  }

  protected void tweakGateway(ExclusiveGateway xorGateway) {
    ModelInstance bpmn = xorGateway.getModelInstance();

    double probabilitySum = 0;
    // Process Variable used to store sample from distribution to decide for
    // outgoing transition
    String var = "SIM_SAMPLE_VALUE_" + xorGateway.getId();

    Collection<SequenceFlow> flows = xorGateway.getOutgoing();
    for (SequenceFlow sequenceFlow : flows) {
      double probability = Double.valueOf(readCamundaProperty(sequenceFlow, "probability"));

      ConditionExpression conditionExpression = bpmn.newInstance(ConditionExpression.class);
      conditionExpression.setTextContent("#{" + var + " >= " + probabilitySum + " && " + var + " < " + (probabilitySum + probability) + "}");
      sequenceFlow.setConditionExpression(conditionExpression);

      probabilitySum += probability;
    }

    // add execution listener to do decision based on random which corresponds
    // to configured probabilities
    // (because of expressions on outgoing sequence flows)
    CamundaExecutionListener executionListener = bpmn.newInstance(CamundaExecutionListener.class);
    executionListener.setCamundaEvent("start");
    CamundaScript script = bpmn.newInstance(CamundaScript.class);
    script.setTextContent(//
        "sample = com.camunda.demo.environment.StatisticsHelper.nextSample(" + probabilitySum + ");\n" + "execution.setVariable('" + var + "', sample);");
    script.setCamundaScriptFormat("Javascript");
    executionListener.setCamundaScript(script);

    if (xorGateway.getExtensionElements()==null) {
      ExtensionElements extensionElements = bpmn.newInstance(ExtensionElements.class);
      xorGateway.addChildElement(extensionElements);
    }
    xorGateway.getExtensionElements().addChildElement(executionListener);
  }



  protected void startMultipleProcessInstances() {
    // for all desired days in past
    for (int i = numberOfDaysInPast; i >= 0; i--) {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.DAY_OF_YEAR, -1 * i);
      int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);

      // now lets start process instances on that day
      if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        // weekend
      } else {
        while (cal.get(Calendar.DAY_OF_YEAR) == dayOfYear) {
          // business day (OK - simplified - do not take holidays into account)
          double time = timeBetweenStartsBusinessDays.nextSample();
          cal.add(Calendar.SECOND, (int) Math.round(time));
          ClockUtil.setCurrentTime(cal.getTime());

          runSingleProcessInstance();

          // Housekeeping for safety (as the run might have changed the clock)
          ClockUtil.setCurrentTime(cal.getTime());
        }
      }
    }

    ClockUtil.reset();
  }

  protected void runSingleProcessInstance() {
    ProcessInstance pi = engine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey);
    boolean piRunning = true;

    while (piRunning) {
      List<org.camunda.bpm.engine.task.Task> tasks = engine.getTaskService().createTaskQuery().processInstanceId(pi.getId()).list();
      List<EventSubscription> messages = engine.getRuntimeService().createEventSubscriptionQuery().processInstanceId(pi.getId()).eventType("message").list();
      // TODO:
      // engine.getRuntimeService().createEventSubscriptionQuery().processInstanceId(pi.getId()).eventType("signal").list();

      handleTasks(pi, tasks);
      handleMessages(pi, messages);

      // do queries again if we have changed anything in the process instance
      // for the moment we do not query processInstance.isEnded as we are not
      // sure
      // if we have yet tackled all situations (read: we are sure we haven't
      // yet).
      // This will at least not lead to endless loops
      piRunning = (tasks.size() > 0 || messages.size() > 0);
      
      // TODO: Stop when we reach the NOW time (might leave open tasks - but that is OK!)
    }

  }

  protected void handleTasks(ProcessInstance pi, List<org.camunda.bpm.engine.task.Task> tasks) {
    for (org.camunda.bpm.engine.task.Task task : tasks) {
      String id = task.getTaskDefinitionKey();

      if (!distributions.containsKey(id)) {
        NormalDistribution distribution = createDistributionForElement(pi, id);
        distributions.put(id, distribution);
      }

      Calendar cal = Calendar.getInstance();
      cal.setTime(task.getCreateTime());
      double timeToWait = distributions.get(task.getTaskDefinitionKey()).sample();
      cal.add(Calendar.SECOND, (int) Math.round(timeToWait));
      ClockUtil.setCurrentTime(cal.getTime());

      engine.getTaskService().complete(task.getId());
    }
  }

  protected void handleMessages(ProcessInstance pi, List<EventSubscription> messages) {
    for (EventSubscription eventSubscription : messages) {
      String id = eventSubscription.getActivityId();
      if (!distributions.containsKey(id)) {
        NormalDistribution distribution = createDistributionForElement(pi, id);
        distributions.put(id, distribution);
      }

      Calendar cal = Calendar.getInstance();
      cal.setTime(eventSubscription.getCreated());
      double timeToWait = distributions.get(id).sample();
      cal.add(Calendar.SECOND, (int) Math.round(timeToWait));
      ClockUtil.setCurrentTime(cal.getTime());

      engine.getRuntimeService().createMessageCorrelation(eventSubscription.getEventName()).processInstanceId(pi.getId()).correlate();
    }
  }

  protected NormalDistribution createDistributionForElement(ProcessInstance pi, String id) {
    BaseElement taskElement = engine.getRepositoryService().getBpmnModelInstance(pi.getProcessDefinitionId()).getModelElementById(id);
    double durationMean = Double.parseDouble(readCamundaProperty(taskElement, "durationMean"));
    double durationStandardDeviation = Double.parseDouble(readCamundaProperty(taskElement, "durationSd"));

    NormalDistribution distribution = new NormalDistribution(durationMean, durationStandardDeviation);
    return distribution;
  }


  private String readCamundaProperty(BaseElement modelElementInstance, String propertyName) {
    Collection<CamundaProperty> properties = modelElementInstance.getExtensionElements().getElementsQuery() //
        .filterByType(CamundaProperties.class) //
        .singleResult() //
        .getCamundaProperties();
    for (CamundaProperty property : properties) {
      // in 7.1 one has to use: property.getAttributeValue("name")
      if (propertyName.equals(property.getCamundaName())) {
        return property.getCamundaValue();
      }
    }
    return null;
  }

  public TimeAwareDemoGenerator timeBetweenStartsBusinessDays(double mean, double standardDeviation) {
    timeBetweenStartsBusinessDays = new StatisticalDistribution(mean, standardDeviation);
    return this;
  }

  public TimeAwareDemoGenerator processDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
    return this;
  }

  public TimeAwareDemoGenerator numberOfDaysInPast(int numberOfDaysInPast) {
    this.numberOfDaysInPast = numberOfDaysInPast;
    return this;
  }

  public ProcessDefinition getProcessDefinition() {
    return processDefinition;
  }

}
