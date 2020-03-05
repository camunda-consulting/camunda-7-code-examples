package com.camunda.example.message.notifydriver;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface NotifyDriverSource {

    @Output("notifyDriverOutputChannel")
    MessageChannel publish();

}