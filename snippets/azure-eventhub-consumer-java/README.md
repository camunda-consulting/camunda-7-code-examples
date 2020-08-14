# Start Camunda Process Instances based on events consumed from an Azure event hub

This plain Java project consumes events from an Azure Event Hub and start a new process instance for each event.
The client submits the event sequence number as business key and the payload as a process variable.

## The important parts
- [EventConsumer.java](src/main/java/org/camunda/azure/eventhub/consumer/EventConsumer.java) implements an event hub client usng the newer azure-**messaging**-eventhubs client library, along the lines [documented by Microsoft](https://docs.microsoft.com/en-us/azure/event-hubs/get-started-java-send-v2).
- [CamundaClient.java](src/main/java/org/camunda/client/CamundaClient.java) uses a Java [client library generated from OpenAPI](../camunda-openapi-client) to start a process instance. 

## How to use it
In Azure, create a resource group, event hub namespace, event hub instance and share access policy.
Then choose a way to generate events, e.g. 
- you could write a client following the [Microsoft documentation](https://docs.microsoft.com/en-us/azure/event-hubs/get-started-java-send-v2) on sending events
- or use a Camunda BPM to generate events from signal as part of a business process, as shown in [this example](../engine-plugin-signal-to-azure-eventhub).

Once you can send events, continue with the consumer setup. To store its checkpoints (progress information) **the consumer requires an Azure blob storage** (and a container in it) to be configured.
Update the configuration for your Azure event hub and storage container in
[EventConsumer.java](src/main/java/org/camunda/azure/eventhub/consumer/EventConsumer.java)   
- EH_CONNECTION_STRING (e.g. Endpoint=sb://something.servicebus.windows.net/;SharedAccessKeyName=demoPolicy;SharedAccessKey=aaabbbcccddd111111112222222222=)
- EH_NAME (e.g. demoeventhub)
- STORAGE_CONNECTION_STRING (e.g. DefaultEndpointsProtocol=https;AccountName=somethingblobstorage;AccountKey=aaaabbbbb/ccccddddd111222+33333444444/aaa==;EndpointSuffix=core.windows.net)
- STORAGE_CONTAINER_NAME (e.g. storagecontainer1) 

Next check out and build the [Open API client lib](../camunda-openapi-client). After cloning the project you can simply run *mvn install* in it.

Have a Camunda environment with REST API and the business process you want to start running. 
The REST API should be available on the base path configured in [CamundaClient.java](src/main/java/org/camunda/client/CamundaClient.java).
The key of the process definition you would like to start can also be adjusted there.  

Finally, run [EventConsumer.java](src/main/java/org/camunda/azure/eventhub/consumer/EventConsumer.java).
    
## License
The project is based on code licensed by Microsoft under the MIT License (MIT)
https://github.com/Azure/azure-sdk-for-java/blob/master/LICENSE.txt
