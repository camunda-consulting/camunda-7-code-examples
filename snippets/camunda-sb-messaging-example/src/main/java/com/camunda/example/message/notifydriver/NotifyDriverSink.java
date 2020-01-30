package com.camunda.example.message.notifydriver;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NotifyDriverSink {

    String channel = "notifyDriverInputChannel";

    @Input(NotifyDriverSink.channel)
    SubscribableChannel submitOrder();
}
