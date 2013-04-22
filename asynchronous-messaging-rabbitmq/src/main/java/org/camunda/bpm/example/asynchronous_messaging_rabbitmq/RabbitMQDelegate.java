package org.camunda.bpm.example.asynchronous_messaging_rabbitmq;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Named("rabbitMQ")
public class RabbitMQDelegate implements JavaDelegate {
  
//  @Inject
//  private ResponseConsumer consumer; 

  private final static String QUEUE_NAME = "hello";
  
  @Override
  public void execute(DelegateExecution execution) throws Exception {

    System.out.println("RabbitMQDelegate has been invoked");

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Hello World!";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");
    
    channel.close();
    connection.close();

  }

}
