package org.camunda.bpm.example.asynchronous_messaging_jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "CallbackServiceMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test") })
public class CallbackServiceMDB implements MessageListener {

  @Inject
  private CallbackService callbackService;

  @Override
  public void onMessage(Message message) {
    try {
      String correlationKey = ((TextMessage) message).getText();
      callbackService.receiveCallback(correlationKey);
    } catch (Exception ex) {
      throw new RuntimeException("Could not process JMS message", ex);
    }
  }

}
