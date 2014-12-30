package org.camunda.bpm.example.asynchronous_messaging_jms;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.camunda.bpm.engine.RuntimeService;

@MessageDriven(name = "CallbackServiceMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/start") })
public class StartProcessMDB implements MessageListener {

  @Inject
  private RuntimeService runtimeService;
  
  @Override
  public void onMessage(Message message) {
    try {
      String payload = ((TextMessage) message).getText();
      
      Map<String, Object> variables = new HashMap<String, Object>();
      variables.put("startMessagePayload", payload);
      
      // Alternative 1:
      // TODO: retrieve/compute processDefinitionKey from JMS message payload
//      String processDefinitionKey = "process-started-by-jms-message";
//      runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
      
      // Alternative 2:
      // TODO: retrieve/compute the BPMN Message name from the JMS message payload
      String messageName = "myStartMessage";
      runtimeService.correlateMessage(messageName, variables); // trigger Message Event with name "myStartMessage"
      
    } catch (Exception ex) {
      throw new RuntimeException("Could not process JMS message", ex);
    }
  }

}
