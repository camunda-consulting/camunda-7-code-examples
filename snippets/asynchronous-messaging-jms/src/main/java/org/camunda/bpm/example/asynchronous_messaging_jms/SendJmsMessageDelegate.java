package org.camunda.bpm.example.asynchronous_messaging_jms;

import java.util.UUID;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * This is an empty service implementation illustrating how to use a plain Java 
 * class as a BPMN 2.0 Service Task delegate.
 */
@Named("jmsSender")
public class SendJmsMessageDelegate implements JavaDelegate {
 
  private final Logger LOGGER = Logger.getLogger(SendJmsMessageDelegate.class.getName());

  @Resource(mappedName = "java:/queue/order")
  private Queue queue;

  @Resource(mappedName = "java:/JmsXA")
  private QueueConnectionFactory connectionFactory;

  public void execute(DelegateExecution execution) throws Exception {
//    String asynchronousCorrelationKey = execution.getId();
//    String asynchronousCorrelationKey = UUID.randomUUID().toString();
    String asynchronousCorrelationKey = execution.getProcessBusinessKey();
    execution.setVariable("correlationId", asynchronousCorrelationKey);
    
    Connection connection = connectionFactory.createConnection();
    Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
    MessageProducer producer = session.createProducer(queue);
    TextMessage message = session.createTextMessage(asynchronousCorrelationKey);
    producer.send(message);
    producer.close();
    session.close();
    connection.close();

  }

}
