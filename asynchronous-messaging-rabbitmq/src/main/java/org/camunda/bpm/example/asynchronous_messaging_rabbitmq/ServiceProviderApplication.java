package org.camunda.bpm.example.asynchronous_messaging_rabbitmq;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class ServiceProviderApplication {

    private final static String QUEUE_NAME = "hello";
    private static final String RESPONSE_QUEUE_NAME = "helloResponse";

    public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
    
    QueueingConsumer consumer = new QueueingConsumer(channel);
    channel.basicConsume(QUEUE_NAME, true, consumer);
    
    while (true) {
      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
      String message = new String(delivery.getBody());
      System.out.println(" [x] Received '" + message + "'");
      
      channel.queueDeclare(RESPONSE_QUEUE_NAME, false, false, false, null);
      channel.basicPublish("", RESPONSE_QUEUE_NAME, null, message.getBytes());
      System.out.println(" [x] Sent '" + message + "'");

    }
  }
}
