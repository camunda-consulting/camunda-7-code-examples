package com.camunda.fox.jms;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

import com.camunda.fox.model.dto.Order;

/**
 * This class provides a simple way of delivering JMS messages to the orderQueue.
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 */
@Named
public class OrderJmsService {

	private static final Logger log = Logger.getLogger(OrderJmsService.class.getCanonicalName());
	
	@Resource(mappedName = "java:/queue/order")
	private Queue queue;

	@Resource(mappedName = "java:/JmsXA")
	private QueueConnectionFactory connectionFactory;
	
	public void deliverMessageToOrderQueue(Order order) {
		try {
			log.info("=======================");
			log.info("delivering object message containing order #" + order.getOrdernumber() + " to " + queue.getQueueName());
			log.info("=======================");
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(queue);
			
			ObjectMessage message = session.createObjectMessage(order);
			producer.send(message);
			producer.close();
			session.close();
			connection.close();
			log.info("=======================");
			log.info("successfully delivered object message containing order #" + order.getOrdernumber() + " to " + queue.getQueueName());
			log.info("=======================");
		} catch (Exception ex) {
			log.info("=======================");
			log.severe("Something went wrong while delivering order to orderQueue");
			log.info("=======================");
			throw new RuntimeException("Could not send JMS message", ex);
		}
	}
	
}
