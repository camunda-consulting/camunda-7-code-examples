package org.camunda.demo.camel.jms;

//import java.util.logging.Logger;
//
//import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
//import javax.jms.Connection;
//import javax.jms.MessageProducer;
//import javax.jms.ObjectMessage;
//import javax.jms.Queue;
//import javax.jms.QueueConnectionFactory;
//import javax.jms.Session;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.CdiCamelContext;
import org.camunda.demo.camel.dto.Order;


/**
 * This class provides a simple way of delivering JMS messages to the orderQueue.
 * 
 * Note: For simplicity this was switched to use the Camel SEDA component - but you can easily switch it back to JMS if you want to.
 */
@Named
public class OrderJmsService {

//	private static final Logger log = Logger.getLogger(OrderJmsService.class.getCanonicalName());
	
//	@Resource(mappedName = "java:/queue/order")
//	private Queue queue;
//
//	@Resource(mappedName = "java:/JmsXA")
//	private QueueConnectionFactory connectionFactory;
	
	@Inject
  private CdiCamelContext camelCtx;
//	
	public void deliverMessageToOrderQueue(Order order) {
   
   ProducerTemplate tpl = camelCtx.createProducerTemplate();
   tpl.sendBody("jms:orderQueue", order);
   
//		try {
//			log.info("=======================");
//			log.info("delivering object message containing order #" + order.getOrdernumber() + " to " + queue.getQueueName());
//			log.info("=======================");
//			Connection connection = connectionFactory.createConnection();
//			Session session = connection.createSession(false,
//					Session.AUTO_ACKNOWLEDGE);
//			MessageProducer producer = session.createProducer(queue);
//			
//			ObjectMessage message = session.createObjectMessage(order);
//			producer.send(message);
//			producer.close();
//			session.close();
//			connection.close();
//			log.info("=======================");
//			log.info("successfully delivered object message containing order #" + order.getOrdernumber() + " to " + queue.getQueueName());
//			log.info("=======================");
//		} catch (Exception ex) {
//			log.info("=======================");
//			log.severe("Something went wrong while delivering order to orderQueue");
//			log.info("=======================");
//			throw new RuntimeException("Could not send JMS message", ex);
//		}
	}
	
}
