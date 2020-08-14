package com.camunda.consulting.eventhub.plugin;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.camunda.spin.json.SpinJsonNode;

@Slf4j
public class AzureEventHubClient {

    private static String connectionString, eventHubName;
    @Setter
    private static EventHubClientBuilder clientBuilder;
    @Setter
    private static EventHubProducerClient producer;

    public static void setConfig(String connStr, String ehName) {
        eventHubName = ehName;
        connectionString = connStr;
    }

    public static void init() {
        if (null == clientBuilder) {
            log.info("Initializing EventHubClientBuilder with connection string: \n {} \n and event hub name: {}", connectionString, eventHubName);
            clientBuilder = new EventHubClientBuilder().connectionString(connectionString, eventHubName);
        }
        producer = clientBuilder.buildProducerClient();
    }

    public static EventHubProducerClient getProducer() {
        if (null == producer) init();
        return producer;
    }

    public static void sendMessage(SpinJsonNode message) {

        // @see https://docs.microsoft.com/en-us/azure/event-hubs/get-started-java-send-v2
        //TODO: consider leveraging batches (per execution) instead of sending individually
        EventDataBatch batch = getProducer().createBatch();
        batch.tryAdd(new EventData(message.toString()));
        // batch.tryAdd(new EventData("Second event"));
        producer.send(batch);

        //TODO: smarter handling of connections
        producer.close();
        producer = null;
    }
}
