package org.camunda.bpm.demo.loan_request_handling.nonarquillian;

import java.util.Collection;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Collaboration;
import org.camunda.bpm.model.bpmn.instance.Message;
import org.camunda.bpm.model.bpmn.instance.MessageEventDefinition;
import org.camunda.bpm.model.bpmn.instance.Participant;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.Signal;
import org.camunda.bpm.model.bpmn.instance.SignalEventDefinition;
import org.camunda.bpm.model.bpmn.instance.UserTask;

public class ExecutableModelGenerator {
  
  private BpmnModelInstance modelInstance;

  public void makeExecutable(BpmnModelInstance modelInstance) {
    this.modelInstance = modelInstance;
    setIsExecutableOnProcesses();
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
    
    Bpmn.validateModel(modelInstance);
    
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

  private void setIsExecutableOnProcesses() {
    Collection<Process> processes = modelInstance.getModelElementsByType(Process.class);
    for (Process process : processes) {
      if (process.getAttributeValue("isExecutable") == null) {
        process.setExecutable(true);
      }
    }
  }
  
  protected <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, String id, Class<T> elementClass) {
    T element = modelInstance.newInstance(elementClass);
    element.setAttributeValue("id", id, true);
    parentElement.addChildElement(element);
    return element;
  }
  
}
