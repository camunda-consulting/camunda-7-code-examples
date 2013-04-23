package org.camunda.bpm.example.asynchronous_messaging_rabbitmq;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class ResponseConsumerBootstrap {

	@EJB
	private ResponseConsumer consumer;

	@PostConstruct
	public void start() throws Exception {
		consumer.consume();
	}
}
