package org.camunda.example.insuranceapplication.input.config;

import org.camunda.example.insuranceapplication.input.services.InputService;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventProducerConfiguration {

 @Bean
 public Exchange eventExchange() {
   return new TopicExchange("eventExchange");
 }

 @Bean
 public InputService inputService(RabbitTemplate rabbitTemplate, Exchange eventExchange) {
   return new InputService(rabbitTemplate, eventExchange);
 }

}