# Push Signal to Azure Event Hub Plugin

A Plugin for [Camunda BPM](http://docs.camunda.org) that sends an event to Azure Event Hub whenever a [Throwing Signal Event](https://docs.camunda.org/manual/latest/reference/bpmn20/events/signal-events/#throwing-signal-events) is visited.
It uses the [Passing Variables](https://docs.camunda.org/manual/latest/reference/bpmn20/events/signal-events/#passing-variables) definition to specify the event's payload.

![Signal](signal.png)

## Show me the important parts
The project contains two Maven modules. 

The module **signal-to-AzureEventHub-engine-plugin** contains the process engine plugin. 
- [SignalToEventHubPlugin.java](signal-to-AzureEventHub-engine-plugin/src/main/java/com/camunda/consulting/eventhub/plugin/SignalToEventHubPlugin.java) is the main plugin class, preparing the Azure Event Hub Client and registering a parse listener.
- [ParseListener.java](signal-to-AzureEventHub-engine-plugin/src/main/java/com/camunda/consulting/eventhub/plugin/AttachEventHubProducerParseListener.java) attaches an [end execution listener](https://docs.camunda.org/manual/latest/user-guide/process-engine/delegation-code/#execution-listener) to every throwing signal event.
- [SignalToEventHubListener.java](signal-to-AzureEventHub-engine-plugin/src/main/java/com/camunda/consulting/eventhub/plugin/SignalToEventHubListener.java) collects data of the issued signal, calculates the variable in-mapping elements and creates a JSON message to be sent to EventHub. 
- [AzureEventHubClient.java](signal-to-AzureEventHub-engine-plugin/src/main/java/com/camunda/consulting/eventhub/plugin/AzureEventHubClient.java) contains a simple Azure EventHub client implementation.

The module **demo-runtime** is a simple Spring Boot Camunda environment. 
- [BpmPlatformConfiguration.java](snippets/engine-plugin-signal-to-azure-eventhub/demo-runtime/src/main/java/eventhub/BpmPlatformConfiguration.java) shows how the plugin is registered and configured.
- The process model [process.bpmn](demo-runtime/src/main/resources/process.bpmn) shows how the plugin is used and how the signal payload is configured in the variables tab of a signal.
- [SecondProcess.bpmn](demo-runtime/src/main/resources/process.bpmn) is a trivial process, which can be used if this example is combined with the [event consumer example](../azure-eventhub-consumer-java)   


## How to use it
To use an engine plugin you have to [integrate the plugin into your Camunda BPM configuration](https://docs.camunda.org/manual/latest/user-guide/process-engine/process-engine-plugins/).
In the demo-runtime this wis done using the Spring Configuration 
[BpmPlatformConfiguration.java](snippets/engine-plugin-signal-to-azure-eventhub/demo-runtime/src/main/java/eventhub/BpmPlatformConfiguration.java).
Adjust the *ConnectionString* and *EventHubName* here to the values applicable to your Azure environment.

In the root folder run:

*mvn spring-boot:run*

to build both modules and start the demo environment using the engine plugin jar. 
 
## Environment Restrictions
Built and tested against Camunda BPM version 7.13.0.

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
