package com.camunda.example.message.neworder;

import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * The publisher to the "neworder" topic is the Online Ordering Service.
 * They receive an order online, then pass that order to the queue.
 * They use the neworder source to establish a channel to send messages on that topic.
 * The topic name is defined in the application.yaml file, associated as a "destination" to the channel.
 */
@EnableBinding(NewOrderSource.class)
public class NewOrderPublisher {
}
