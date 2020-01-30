package com.camunda.example.delegate;

import com.camunda.example.message.neworder.NewOrderSource;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component("sendOrderDelegate")
public class SendOrderDelegate implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Autowired
    NewOrderSource source;

    /**
     * Use Spring AMQP to send a message (the pizza order) to the queue.
     *
     * @param execution The DelegateExecution context.
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String json = (String) execution.getVariable("orderinfo");
        source.publish().send(MessageBuilder.withPayload(json).build());
        LOGGER.info("Online Order Sent to RabbitMQ: " + json);

    }

}
