package org.camunda.bpm.example.asynchronous_messaging_rabbitmq;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

@Singleton
public class ResponseConsumer {

	private static final String RESPONSE_QUEUE_NAME = "helloResponse";

	@Asynchronous
	public void consume() throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(RESPONSE_QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(RESPONSE_QUEUE_NAME, true, consumer);

		QueueingConsumer.Delivery delivery = consumer.nextDelivery();
		String message = new String(delivery.getBody());
		System.out.println(" [x] Received '" + message + "'");
	}
	
}
