package com.camunda.example.message.notifydriver;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The subscriber to the "notifydriver" topic is the Delivery Driver.
 * They use the notifydriver sink to establish a channel to listen for messages on that topic.
 * When a message is received, this code uses the Camunda runtime service to correlate a message.
 * In this case, the engine will send a message to the delivery driver that will start the process.
 */
@EnableBinding(NotifyDriverSink.class)
public class NotifyDriverSubscriber {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    RuntimeService runtimeService;

    @StreamListener(NotifyDriverSink.channel)
    public void handle(String order) {
        LOGGER.info("NotifyDriverSubscriber - Received: " + order);
        Map<String, Object> variables = new HashMap();
        variables.put("orderinfo", order);
        runtimeService.correlateMessage("notifyDriverMessage", "deliverDriverProcess", variables);
    }

}