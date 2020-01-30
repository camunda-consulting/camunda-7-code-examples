package com.camunda.example.message.neworder;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The subscriber to the "neworder" topic is the Pizza Restaurant.
 * They use the neworder sink to establish a channel to listen for messages on that topic.
 * When a message is received, this code uses the Camunda runtime service to correlate a message.
 * In this case, the engine will send a message to the restaurant that will start the process.
 */
@EnableBinding(NewOrderSink.class)
public class NewOrderSubscriber {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    RuntimeService runtimeService;

    @StreamListener(NewOrderSink.channel)
    public void handle(String order) {
        LOGGER.info("NewOrderSubscriber - Received: " + order);
        Map<String, Object> variables = new HashMap();
        variables.put("orderinfo", order);
        runtimeService.correlateMessage("newOrderMessage", "pizzaRestaurantProcess", variables);
    }

}