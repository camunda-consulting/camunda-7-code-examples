package com.camunda.demo.environment.simulation;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.camunda.bpm.application.ProcessApplicationReference;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.BusinessRuleTask;
import org.camunda.bpm.model.bpmn.instance.ConditionExpression;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.InclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.ScriptTask;
import org.camunda.bpm.model.bpmn.instance.SendTask;
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
 * Classloading: Currently everything is done as JavaScript - so no classes are
 * necessary
 */
public class TimeAwareDemoGenerator {

  private static final Logger log = Logger.getLogger(TimeAwareDemoGenerator.class.getName());

  private String processDefinitionKey;
  private int numberOfDaysInPast;
  private StatisticalDistribution timeBetweenStartsBusinessDays;
  private String startTimeBusinessDay = "08:30";
  private String endTimeBusinessDay = "18:00";

  private StatisticalDistribution timeBetweenStartsWeekend;

  // private ;
  private ProcessDefinition processDefinition;
  private ProcessEngine engine;

  private Map<String, NormalDistribution> distributions = new HashMap<String, NormalDistribution>();
  private String originalBpmn;

  private ProcessApplicationReference processApplicationReference;

  public TimeAwareDemoGenerator(ProcessEngine engine, ProcessApplicationReference processApplicationReference) {
    this.engine = engine;
    this.processApplicationReference = processApplicationReference;
  }

  public TimeAwareDemoGenerator(ProcessEngine processEngine) {
    this.engine = processEngine;
  }

  public void generateData() {
    tweakProcessDefinition();
    synchronized (engine) {        
      ((ProcessEngineConfigurationImpl)engine.getProcessEngineConfiguration()).getJobExecutor().shutdown();
      try {
          startMultipleProcessInstances();
      } finally {
        restoreOriginalProcessDefinition();      
        ((ProcessEngineConfigurationImpl)engine.getProcessEngineConfiguration()).getJobExecutor().start();    
      }
    }
  }

  protected void restoreOriginalProcessDefinition() {
    log.info("restore original process definition");
    
    try {
      Deployment deployment = engine.getRepositoryService().createDeployment() //
          .addInputStream(processDefinitionKey + ".bpmn", new ByteArrayInputStream(originalBpmn.getBytes("UTF-8"))) //
          .deploy();
      if (processApplicationReference != null) {
        engine.getManagementService().registerProcessApplication(deployment.getId(), processApplicationReference);
      }
    } catch (Exception ex) {
      throw new RuntimeException("Could not deploy tweaked process definition",  ex);
    }
  }

  protected void tweakProcessDefinition() {
    log.info("tweak process definition " + processDefinitionKey);

    processDefinition = engine.getRepositoryService().createProcessDefinitionQuery() //
        .processDefinitionKey(processDefinitionKey) //
        .latestVersion() //
        .singleResult();
    if (processDefinition==null) {
      throw new RuntimeException("Process with key '" + processDefinitionKey + "' not found.");
    }
    // store original process application reference
    if (processApplicationReference==null) {
      processApplicationReference = ((ProcessEngineConfigurationImpl)engine.getProcessEngineConfiguration()).getProcessApplicationManager().getProcessApplicationForDeployment(processDefinition.getDeploymentId());
    }
    
    BpmnModelInstance bpmn = engine.getRepositoryService().getBpmnModelInstance(processDefinition.getId());

    originalBpmn = IoUtil.convertXmlDocumentToString(bpmn.getDocument());
    // do not do a validation here as it caused quite strange trouble
    log.finer("-----\n" + originalBpmn + "\n------");

    Collection<ModelElementInstance> serviceTasks = bpmn.getModelElementsByType(bpmn.getModel().getType(ServiceTask.class));
    Collection<ModelElementInstance> sendTasks = bpmn.getModelElementsByType(bpmn.getModel().getType(SendTask.class));
    Collection<ModelElementInstance> businessRuleTasks = bpmn.getModelElementsByType(bpmn.getModel().getType(BusinessRuleTask.class));
    Collection<ModelElementInstance> scriptTasks = bpmn.getModelElementsByType(bpmn.getModel().getType(ScriptTask.class));
    Collection<ModelElementInstance> userTasks = bpmn.getModelElementsByType(bpmn.getModel().getType(UserTask.class));
    Collection<ModelElementInstance> executionListeners = bpmn.getModelElementsByType(bpmn.getModel().getType(CamundaExecutionListener.class));
    Collection<ModelElementInstance> taskListeners = bpmn.getModelElementsByType(bpmn.getModel().getType(CamundaTaskListener.class));
    Collection<ModelElementInstance> xorGateways = bpmn.getModelElementsByType(bpmn.getModel().getType(ExclusiveGateway.class));
    Collection<ModelElementInstance> orGateways = bpmn.getModelElementsByType(bpmn.getModel().getType(InclusiveGateway.class));

    Collection<ModelElementInstance> scripts = bpmn.getModelElementsByType(bpmn.getModel().getType(CamundaScript.class));

    for (ModelElementInstance modelElementInstance : serviceTasks) {
      ServiceTask serviceTask = ((ServiceTask) modelElementInstance);
      serviceTask.setCamundaClass(null);
      // TODO: Wait for https://app.camunda.com/jira/browse/CAM-4178 and set
      // to null!
      // serviceTask.setCamundaDelegateExpression(null);
      // Workaround:
      serviceTask.removeAttributeNs("http://activiti.org/bpmn", "delegateExpression");
      serviceTask.removeAttributeNs("http://camunda.org/schema/1.0/bpmn", "delegateExpression");
      
      serviceTask.setCamundaExpression("#{true}"); // Noop      
    }
    for (ModelElementInstance modelElementInstance : sendTasks) {
      SendTask serviceTask = ((SendTask) modelElementInstance);
      serviceTask.setCamundaClass(null);
      serviceTask.removeAttributeNs("http://activiti.org/bpmn", "delegateExpression");      
      serviceTask.removeAttributeNs("http://camunda.org/schema/1.0/bpmn", "delegateExpression");      
      serviceTask.setCamundaExpression("#{true}"); // Noop      
    }
    for (ModelElementInstance modelElementInstance : businessRuleTasks) {
      BusinessRuleTask businessRuleTask = (BusinessRuleTask) modelElementInstance;
      businessRuleTask.removeAttributeNs("http://activiti.org/bpmn", "decisionRef"); // DMN ref from 7.4 on
      businessRuleTask.removeAttributeNs("http://camunda.org/schema/1.0/bpmn", "decisionRef"); // DMN ref from 7.4 on
      businessRuleTask.setCamundaClass(null);
      businessRuleTask.removeAttributeNs("http://activiti.org/bpmn", "delegateExpression");      
      businessRuleTask.removeAttributeNs("http://camunda.org/schema/1.0/bpmn", "delegateExpression");
      
      businessRuleTask.setCamundaExpression("#{true}"); // Noop      
    }
    for (ModelElementInstance modelElementInstance : executionListeners) {
      CamundaExecutionListener executionListener = (CamundaExecutionListener) modelElementInstance;
//      executionListener.setCamundaClass(null);
      executionListener.removeAttributeNs("http://activiti.org/bpmn", "class");      
      executionListener.removeAttributeNs("http://camunda.org/schema/1.0/bpmn", "class");      

      executionListener.removeAttributeNs("http://activiti.org/bpmn", "delegateExpression");      
      executionListener.removeAttributeNs("http://camunda.org/schema/1.0/bpmn", "delegateExpression");      
      executionListener.setCamundaExpression("#{true}"); // Noop      
    }
    for (ModelElementInstance modelElementInstance : scripts) {
      CamundaScript script = (CamundaScript) modelElementInstance;
//      executionListener.setCamundaClass(null);
      script.setTextContent(""); // java.lang.System.out.println('x');
      script.setCamundaScriptFormat("javascript");
      script.removeAttributeNs("http://activiti.org/bpmn", "resource");
      script.removeAttributeNs("http://camunda.org/schema/1.0/bpmn", "resource");
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

    // Bpmn.validateModel(bpmn);
    String xmlString = Bpmn.convertToString(bpmn);
    try {
      engine.getRepositoryService().createDeployment() //
  //        .addString(processDefinitionKey + ".bpmn", xmlString) //
  //        .addModelInstance(processDefinitionKey + ".bpmn", bpmn) //
          .addInputStream(processDefinitionKey + ".bpmn", new ByteArrayInputStream(xmlString.getBytes("UTF-8")))
          .deploy();
    } catch (Exception ex) {
      throw new RuntimeException("Could not deploy tweaked process definition",  ex);
    }
  }

  protected void tweakGateway(ExclusiveGateway xorGateway) {
    ModelInstance bpmn = xorGateway.getModelInstance();

    double probabilitySum = 0;
    // Process Variable used to store sample from distribution to decide for
    // outgoing transition
    String var = "SIM_SAMPLE_VALUE_" + xorGateway.getId();

    Collection<SequenceFlow> flows = xorGateway.getOutgoing();
    if (flows.size() > 1) { // if outgoing flows = 1 it is a joining gateway
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

      if (xorGateway.getExtensionElements() == null) {
        ExtensionElements extensionElements = bpmn.newInstance(ExtensionElements.class);
        xorGateway.addChildElement(extensionElements);
      }
      xorGateway.getExtensionElements().addChildElement(executionListener);
    }
  }

  protected void copyTimeField(Calendar calFrom, Calendar calTo, int... calendarFieldConstant) {
    for (int i = 0; i < calendarFieldConstant.length; i++) {
      calTo.set(calendarFieldConstant[i], calFrom.get(calendarFieldConstant[i]));
    }
  }

  private boolean isInTimeFrame(Calendar cal, String startTime, String endTime) {
    try {
      // TODO: maybe cache?
      Date startDate = new SimpleDateFormat("HH:mm").parse(startTime);
      Date endDate = new SimpleDateFormat("HH:mm").parse(endTime);
      Calendar startCal = Calendar.getInstance();
      startCal.setTime(startDate);
      copyTimeField(cal, startCal, Calendar.YEAR, Calendar.DAY_OF_YEAR);

      Calendar endCal = Calendar.getInstance();
      endCal.setTime(endDate);
      copyTimeField(cal, endCal, Calendar.YEAR, Calendar.DAY_OF_YEAR);

      return (startCal.before(cal) && cal.before(endCal));
    } catch (ParseException ex) {
      throw new RuntimeException("Could not parse time format: '" + startTime + "' or '" + endTime + "'", ex);
    }
  }

  protected void startMultipleProcessInstances() {
    try {
      log.info("start multiple process instances for " + numberOfDaysInPast + " workingdays in the past");

      // for all desired days in past
      for (int i = numberOfDaysInPast; i >= 0; i--) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1 * i);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);

        int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);

        // now lets start process instances on that day
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
          // weekend
        } else {
          while (cal.get(Calendar.DAY_OF_YEAR) == dayOfYear) {
            // business day (OK - simplified - do not take holidays into
            // account)
            double time = timeBetweenStartsBusinessDays.nextSample();
            cal.add(Calendar.SECOND, (int) Math.round(time));
            if (isInTimeFrame(cal, startTimeBusinessDay, endTimeBusinessDay)) {
              ClockUtil.setCurrentTime(cal.getTime());

              System.out.print(".");
              runSingleProcessInstance();

              // Housekeeping for safety (as the run might have changed the
              // clock)
              ClockUtil.setCurrentTime(cal.getTime());
            }
          }
        }
      }

    } finally {
      ClockUtil.reset();
    }
  }

  protected void runSingleProcessInstance() {
    ProcessInstance pi = engine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey);
    boolean piRunning = true;

    while (piRunning) {
      List<org.camunda.bpm.engine.task.Task> tasks = engine.getTaskService().createTaskQuery().processInstanceId(pi.getId()).list();
//      List<EventSubscription> messages = engine.getRuntimeService().createEventSubscriptionQuery().processInstanceId(pi.getId()).eventType("message").list();
      List<Job> jobs = engine.getManagementService().createJobQuery().processInstanceId(pi.getId()).list();
      // TODO:
      // engine.getRuntimeService().createEventSubscriptionQuery().processInstanceId(pi.getId()).eventType("signal").list();

      handleTasks(pi, tasks);
//      handleMessages(pi, messages);
      handleJobs(jobs);

      // do queries again if we have changed anything in the process instance
      // for the moment we do not query processInstance.isEnded as we are not
      // sure
      // if we have yet tackled all situations (read: we are sure we haven't
      // yet).
      // This will at least not lead to endless loops
      piRunning = (tasks.size() > 0 
          //|| messages.size() > 0 
          || jobs.size() > 0);

      // TODO: Stop when we reach the NOW time (might leave open tasks - but
      // that is OK!)
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
      if (timeToWait <= 0) {
        timeToWait = 1;
      }
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

  protected void handleJobs(List<Job> jobs) {
    for (Job job : jobs) {
      if (engine.getManagementService().createJobQuery().jobId(job.getId()).count()==1) {          
        engine.getManagementService().executeJob(job.getId());
      }
      else {
        System.out.println("COULD NOT EXECUTE JOB " + job);
      }
    }
  }

  protected NormalDistribution createDistributionForElement(ProcessInstance pi, String id) {
    try {
      BaseElement taskElement = engine.getRepositoryService().getBpmnModelInstance(pi.getProcessDefinitionId()).getModelElementById(id);
      double durationMean = Double.parseDouble(readCamundaProperty(taskElement, "durationMean"));
      double durationStandardDeviation = Double.parseDouble(readCamundaProperty(taskElement, "durationSd"));
  
      NormalDistribution distribution = new NormalDistribution(durationMean, durationStandardDeviation);
      return distribution;
    }
    catch (Exception ex) {
      throw new RuntimeException("Could not read distribution for element '" + id + "' of process definition '" + pi.getProcessDefinitionId() + "'", ex);
    }
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
