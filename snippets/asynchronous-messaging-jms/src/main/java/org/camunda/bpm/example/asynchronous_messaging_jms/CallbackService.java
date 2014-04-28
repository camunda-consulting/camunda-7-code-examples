package org.camunda.bpm.example.asynchronous_messaging_jms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;

@Named
@Stateless
public class CallbackService {

  @Inject
  private RuntimeService runtimeService;
  
  @PersistenceContext
  private EntityManager entityManager;
  
  @Resource(mappedName = "java:/queue/test")
  private Queue queue;

  @Resource(mappedName = "java:/JmsXA")
  private QueueConnectionFactory connectionFactory;  
  
  public void receiveCallback(String correlationKey) {
//    String executionId = correlationKey;
//    System.out.println("do callback on execution " + executionId);
    
    
//    Execution execution = runtimeService.createExecutionQuery()
//            .processVariableValueEquals("correllationId", correlationKey)
//            .singleResult();

//    Execution execution = runtimeService.createExecutionQuery()
//            .processInstanceBusinessKey(correlationKey)
//            .singleResult();
    
//    runtimeService.signal(execution.getId(), variables);
	  
	  Map<String,Object> correlationKeys = new HashMap<String, Object>();
	  correlationKeys.put("correllationId", correlationKey);
	  runtimeService.correlateMessage("serviceExecutionCompleted", correlationKeys);
  }
  
  public void triggerAsynchronousCallback(String asynchronousCorrelationKey) {
    try {
      Connection connection = connectionFactory.createConnection();
      Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(queue);
      TextMessage message = session.createTextMessage(asynchronousCorrelationKey);
      producer.send(message);
      producer.close();
      session.close();
      connection.close();
    }
    catch (Exception ex) {
      throw new RuntimeException("Could not send JMS message", ex);
    }
  }

}
