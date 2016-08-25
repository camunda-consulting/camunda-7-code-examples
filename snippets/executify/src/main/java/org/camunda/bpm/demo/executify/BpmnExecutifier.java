package org.camunda.bpm.demo.executify;

import java.util.Collection;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.builder.AbstractBaseElementBuilder;
import org.camunda.bpm.model.bpmn.impl.instance.LoopCardinalityImpl;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.BusinessRuleTask;
import org.camunda.bpm.model.bpmn.instance.Collaboration;
import org.camunda.bpm.model.bpmn.instance.ConditionExpression;
import org.camunda.bpm.model.bpmn.instance.Documentation;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.Expression;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.Gateway;
import org.camunda.bpm.model.bpmn.instance.InclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.LoopCardinality;
import org.camunda.bpm.model.bpmn.instance.Message;
import org.camunda.bpm.model.bpmn.instance.MessageEventDefinition;
import org.camunda.bpm.model.bpmn.instance.MultiInstanceLoopCharacteristics;
import org.camunda.bpm.model.bpmn.instance.Participant;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.SendTask;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.camunda.bpm.model.bpmn.instance.Signal;
import org.camunda.bpm.model.bpmn.instance.SignalEventDefinition;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.bpmn.instance.di.DiagramElement;
import org.camunda.bpm.model.xml.ModelInstance;
import org.camunda.bpm.model.xml.instance.DomElement;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;

public class BpmnExecutifier {
  
  private BpmnModelInstance modelInstance;

  public void executify(BpmnModelInstance modelInstance) {
    this.modelInstance = modelInstance;
    setIsExecutableOnProcesses();
    addMissingConditionExpressions();
    addMissingExpressionsToServiceTasks();
    addMissingExpressionsToSendTasks();
    addMissingExpressionsToBusinessRuleTasks();
    addMissingMultiInstanceConfiguration();
    addMissingMessages();
    addMissingSignals();
    Collection<Collaboration> collaborations = modelInstance.getModelElementsByType(Collaboration.class);
    for (Collaboration collaboration : collaborations) {
      if (collaboration.getChildElementsByType(Participant.class).isEmpty()) {
        // TODO delete redundant collaboration
      }
    }
    
//    Collection<UserTask> userTasks = modelInstance.getModelElementsByType(UserTask.class);
//    for (UserTask userTask : userTasks) {
//      
//    }
   
    // TODO call activity passes business key
    
    Bpmn.validateModel(modelInstance);
    
  }

  private void setIsExecutableOnProcesses() {
    Collection<Process> processes = modelInstance.getModelElementsByType(Process.class);
    for (Process process : processes) {
      if (process.getAttributeValue("isExecutable") == null) {
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
            if (isEmpty(sequenceFlow.getConditionExpression())) {
              ConditionExpression conditionExpression = createElement(sequenceFlow, ConditionExpression.class);
              conditionExpression.setTextContent("#{true}");
              sequenceFlow.setConditionExpression(conditionExpression);
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
      }
    }
  }

  private void addMissingMultiInstanceConfiguration() {
    Collection<MultiInstanceLoopCharacteristics> miConfigs = modelInstance.getModelElementsByType(MultiInstanceLoopCharacteristics.class);
    for (MultiInstanceLoopCharacteristics miConfig : miConfigs) {
      if (isEmpty(miConfig.getLoopCardinality())
          && isEmpty(miConfig.getCamundaCollection())
          && miConfig.getLoopDataInputRef() == null) {
        LoopCardinality loopCardinality = createElement(miConfig, LoopCardinality.class);
        loopCardinality.setTextContent("2");
        miConfig.setLoopCardinality(loopCardinality);
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

  private boolean isEmpty(String string) {
    return string == null || string.isEmpty();
  }

  private boolean isEmpty(Expression expression) {
    return expression == null || expression.getTextContent() == null || expression.getTextContent().isEmpty();
  }

  protected <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, String id, Class<T> elementClass) {
    T element = modelInstance.newInstance(elementClass);
    element.setAttributeValue("id", id, true);
    parentElement.addChildElement(element);
    return element;
  }
  
  protected <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, Class<T> elementClass) {
    T element = modelInstance.newInstance(elementClass);
    parentElement.addChildElement(element);
    return element;
  }
  
}
