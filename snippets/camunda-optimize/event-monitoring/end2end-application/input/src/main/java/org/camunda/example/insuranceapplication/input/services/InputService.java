package org.camunda.example.insuranceapplication.input.services;

import java.time.LocalDateTime;

import org.camunda.example.insuranceapplication.input.dto.Message;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class InputService {

	 private final RabbitTemplate rabbitTemplate;

	  private final Exchange exchange;

	  public InputService(RabbitTemplate rabbitTemplate, Exchange exchange) {
	    this.rabbitTemplate = rabbitTemplate;
	    this.exchange = exchange;
	  }

	  public void createApplication(String application, String type) throws JsonProcessingException {
	    Message message = new Message();
	    message.setEventName("document.received");
	    message.setTime(LocalDateTime.now());
	    message.setData(application);
	    
	    String routingKey = "document.received";
	    ObjectMapper obj = new ObjectMapper();
	    obj.registerModule(new JavaTimeModule());
	    rabbitTemplate.convertAndSend(exchange.getName(), routingKey, obj.writeValueAsString(message));
		
	    routingKey = "document.application.new";
	    message.setEventName("document.application.new."+type);
	    message.setTime(LocalDateTime.now());
	    rabbitTemplate.convertAndSend(exchange.getName(), routingKey, obj.writeValueAsString(message));
	  }

	}
