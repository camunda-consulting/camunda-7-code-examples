package org.camunda.azure.eventhub.consumer;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventProcessorClient;
import com.azure.messaging.eventhubs.EventProcessorClientBuilder;
import com.azure.messaging.eventhubs.checkpointstore.blob.BlobCheckpointStore;
import com.azure.messaging.eventhubs.models.ErrorContext;
import com.azure.messaging.eventhubs.models.EventContext;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.camunda.client.CamundaClient;

import java.util.function.Consumer;

/* @See https://docs.microsoft.com/en-us/azure/event-hubs/get-started-java-send-v2 */

@Slf4j
public class EventConsumer {

    private static final String EH_CONNECTION_STRING = "Endpoint=sb://somenamepsace.servicebus.windows.net/;SharedAccessKeyName=demoPolicy;SharedAccessKey=2QS......=";
    private static final String EH_NAME = "demoeventhub";
    private static final String STORAGE_CONNECTION_STRING = "DefaultEndpointsProtocol=https;AccountName=someblobstorage;AccountKey=HvdvP....==;EndpointSuffix=core.windows.net";
    private static final String STORAGE_CONTAINER_NAME = "storagecontainer1";

    public static final Consumer<EventContext> PARTITION_PROCESSOR = eventContext -> {
        //process event
        String eventBody = eventContext.getEventData().getBodyAsString();
        Long sequenceNumber = eventContext.getEventData().getSequenceNumber();
        log.info("Processing event from partition {} with sequence number {} with body: {}",
                eventContext.getPartitionContext().getPartitionId(), sequenceNumber, eventBody);

        //start process
        CamundaClient.startProcess(eventBody, sequenceNumber.toString());
        //store checkpoint
        if (sequenceNumber % 3 == 0) {
            eventContext.updateCheckpoint();
        }
    };

    public static final Consumer<ErrorContext> ERROR_HANDLER = errorContext ->
            log.info("Error occurred in partition processor for partition {},{}",
                    errorContext.getPartitionContext().getPartitionId(),
                    errorContext.getThrowable());


    public static void main(String[] args) throws Exception {

        //checkpoint storage
        BlobContainerAsyncClient blobContainerAsyncClient = new BlobContainerClientBuilder()
                .connectionString(STORAGE_CONNECTION_STRING)
                .containerName(STORAGE_CONTAINER_NAME)
                .buildAsyncClient();
        //eventhub client
        EventProcessorClientBuilder eventProcessorClientBuilder = new EventProcessorClientBuilder()
                .connectionString(EH_CONNECTION_STRING, EH_NAME)
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .processEvent(PARTITION_PROCESSOR)
                .processError(ERROR_HANDLER)
                .checkpointStore(new BlobCheckpointStore(blobContainerAsyncClient));

        EventProcessorClient eventProcessorClient = eventProcessorClientBuilder.buildEventProcessorClient();

        log.info("Starting event processor");
        eventProcessorClient.start();

        System.out.println("Press enter to stop.");
        System.in.read();

        eventProcessorClient.stop();
        log.info("Event processor stopped.");
    }
}