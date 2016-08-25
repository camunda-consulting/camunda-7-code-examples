package org.camunda.bpm.demo.executify;

import static org.camunda.bpm.demo.executify.BpmnModelInstanceHelper.*;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BusinessRuleTask;
import org.camunda.bpm.model.bpmn.instance.CallActivity;
import org.camunda.bpm.model.bpmn.instance.ConditionExpression;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.Gateway;
import org.camunda.bpm.model.bpmn.instance.InclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.LoopCardinality;
import org.camunda.bpm.model.bpmn.instance.Message;
import org.camunda.bpm.model.bpmn.instance.MessageEventDefinition;
import org.camunda.bpm.model.bpmn.instance.MultiInstanceLoopCharacteristics;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.SendTask;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.Signal;
import org.camunda.bpm.model.bpmn.instance.SignalEventDefinition;
import org.camunda.bpm.model.bpmn.instance.TimeDuration;
import org.camunda.bpm.model.bpmn.instance.TimerEventDefinition;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaIn;

public class BpmnExecutifier {
  
  private BpmnModelInstance modelInstance;
  private boolean generatePredictableConditionExpressions;
  private boolean removeDecisionRefs;
  private boolean allProcessesExecutable;

  public BpmnModelInstance executify(InputStream stream) {
    BpmnModelInstance modelInstance = Bpmn.readModelFromStream(stream);
    executify(modelInstance);
    return modelInstance;
  }
  
  public void executify(BpmnModelInstance modelInstance) {
    this.modelInstance = modelInstance;
    setIsExecutableOnProcesses();
    addMissingConditionExpressions();
    addMissingExpressionsToServiceTasks();
    addMissingExpressionsToSendTasks();
    addMissingExpressionsToBusinessRuleTasks();
    addMissingMultiInstanceConfiguration();
    addMissingTimerConfigurations();
    addMissingMessages();
    addMissingSignals();
//    Collection<Collaboration> collaborations = modelInstance.getModelElementsByType(Collaboration.class);
//    for (Collaboration collaboration : collaborations) {
//      if (collaboration.getChildElementsByType(Participant.class).isEmpty()) {
//        // TODO delete redundant collaboration
//      }
//    }
    
//    Collection<UserTask> userTasks = modelInstance.getModelElementsByType(UserTask.class);
//    for (UserTask userTask : userTasks) {
//      
//    }

    addBusinessKeyToCallActivity();
    
    Bpmn.validateModel(modelInstance);
    
  }

  private void addBusinessKeyToCallActivity() {
    Collection<CallActivity> callActivities = modelInstance.getModelElementsByType(CallActivity.class);
    interationOverAllCallActivities : for (CallActivity callActivity : callActivities) {
      ExtensionElements extensionElements = callActivity.getExtensionElements();
      if (extensionElements != null) {
        List<CamundaIn> camundaIns = extensionElements
          .getElementsQuery()
          .filterByType(CamundaIn.class)
          .list();
        for (CamundaIn camundaIn : camundaIns) {
          if (!isEmpty(camundaIn.getCamundaBusinessKey())) {
            continue interationOverAllCallActivities;
          }
        }
        CamundaIn camundaIn = extensionElements.addExtensionElement(CamundaIn.class);
        camundaIn.setCamundaBusinessKey("#{execution.processBusinessKey}");
      } else {
        extensionElements = createElement(callActivity, ExtensionElements.class);
        CamundaIn camundaIn = extensionElements.addExtensionElement(CamundaIn.class);
        camundaIn.setCamundaBusinessKey("#{execution.processBusinessKey}");
        callActivity.setExtensionElements(extensionElements);
      }
    }
  }

  private void setIsExecutableOnProcesses() {
    Collection<Process> processes = modelInstance.getModelElementsByType(Process.class);
    for (Process process : processes) {
      if (process.getAttributeValue("isExecutable") == null || allProcessesExecutable) {
        process.setExecutable(true);
      }
    }
  }

  private void addMissingConditionExpressions() {
    Collection<Gateway> gateways = modelInstance.getModelElementsByType(Gateway.class);
    for (Gateway gateway : gateways) {
      if (gateway instanceof ExclusiveGateway || gateway instanceof InclusiveGateway) {
        Collection<SequenceFlow> outgoing = gateway.getOutgoing();
        if (outgoing.size() > 1) {
          for (SequenceFlow sequenceFlow : outgoing) {
            if (!generatePredictableConditionExpressions) {
              if (isEmpty(sequenceFlow.getConditionExpression())) {
                setExpression(sequenceFlow, ConditionExpression.class, "#{true}");
              }
            } else {
              setExpression(sequenceFlow, ConditionExpression.class, "#{" + gateway.getId() + " == '" + sequenceFlow.getId() + "'}");
            }
          }
        }
      }
    }
  }

  private void addMissingExpressionsToServiceTasks() {
    Collection<ServiceTask> serviceTasks = modelInstance.getModelElementsByType(ServiceTask.class);
    for (ServiceTask serviceTask : serviceTasks) {
      if (isEmpty(serviceTask.getCamundaClass())
          && isEmpty(serviceTask.getCamundaDelegateExpression())
          && isEmpty(serviceTask.getCamundaExpression())
          && isEmpty(serviceTask.getCamundaType())) {
        serviceTask.setCamundaExpression("#{true}");
      }
    }
  }

  private void addMissingExpressionsToSendTasks() {
    Collection<SendTask> tasks = modelInstance.getModelElementsByType(SendTask.class);
    for (SendTask task : tasks) {
      if (isEmpty(task.getCamundaClass())
          && isEmpty(task.getCamundaDelegateExpression())
          && isEmpty(task.getCamundaExpression())
          && isEmpty(task.getCamundaType())) {
        task.setCamundaExpression("#{true}");
      }
    }
  }

  private void addMissingExpressionsToBusinessRuleTasks() {
    Collection<BusinessRuleTask> tasks = modelInstance.getModelElementsByType(BusinessRuleTask.class);
    for (BusinessRuleTask task : tasks) {
      if (isEmpty(task.getCamundaClass())
          && isEmpty(task.getCamundaDelegateExpression())
          && isEmpty(task.getCamundaExpression())
          && isEmpty(task.getCamundaType())
          && isEmpty(task.getCamundaDecisionRef())) {
        task.setCamundaExpression("#{true}");
      } else if (removeDecisionRefs && !isEmpty(task.getCamundaDecisionRef())) {
        task.setCamundaDecisionRef(null);
        task.setCamundaResultVariable(null);
        task.setCamundaExpression("#{true}");
      }
    }
  }

  private void addMissingMultiInstanceConfiguration() {
    Collection<MultiInstanceLoopCharacteristics> miConfigs = modelInstance.getModelElementsByType(MultiInstanceLoopCharacteristics.class);
    for (MultiInstanceLoopCharacteristics miConfig : miConfigs) {
      if (isEmpty(miConfig.getLoopCardinality())
          && isEmpty(miConfig.getCamundaCollection())
          && miConfig.getLoopDataInputRef() == null) {
        setExpression(miConfig, LoopCardinality.class, "2");
      }
    }
  }

  private void addMissingTimerConfigurations() {
    Collection<TimerEventDefinition> timers = modelInstance.getModelElementsByType(TimerEventDefinition.class);
    for (TimerEventDefinition timer : timers) {
      if (isEmpty(timer.getTimeCycle())
          && isEmpty(timer.getTimeDate())
          && isEmpty(timer.getTimeDuration())) {
        setExpression(timer, TimeDuration.class, "PT5M");
      }
    }
  }

  private void addMissingMessages() {
    Collection<MessageEventDefinition> messageEventDefinitions = modelInstance.getModelElementsByType(MessageEventDefinition.class);
    int messageCount = 0;
    for (MessageEventDefinition messageEventDefinition : messageEventDefinitions) {
      Message message = messageEventDefinition.getMessage();
      if (message == null) {
        messageCount++;
        String messageName = "message" + messageCount;
        message = createElement(modelInstance.getDefinitions(), messageName, Message.class);
        message.setName(messageName);
        messageEventDefinition.setMessage(message);
      }
    }
  }

  private void addMissingSignals() {
    Collection<SignalEventDefinition> signalEventDefinitions = modelInstance.getModelElementsByType(SignalEventDefinition.class);
    int signalCount = 0;
    for (SignalEventDefinition signalEventDefinition : signalEventDefinitions) {
      Signal signal = signalEventDefinition.getSignal();
      if (signal == null) {
        signalCount++;
        String signalName = "signal" + signalCount;
        signal = createElement(modelInstance.getDefinitions(), signalName, Signal.class);
        signal.setName(signalName);
        signalEventDefinition.setSignal(signal);
      }
    }
  }

  public void setGeneratePredictableConditionExpressions(boolean generatePredictableConditionExpressions) {
    this.generatePredictableConditionExpressions = generatePredictableConditionExpressions;
  }

  public void setRemoveDecisionRefs(boolean removeDecisionRefs) {
    this.removeDecisionRefs = removeDecisionRefs;
  }

  public void setAllProcessesExecutable(boolean allProcessesExecutable) {
    this.allProcessesExecutable = allProcessesExecutable;
  }

}
