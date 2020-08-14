package eventhub;

import com.camunda.consulting.eventhub.plugin.SignalToEventHubPlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BpmPlatformConfiguration {

    // @see https://docs.camunda.org/cawemo/latest/technical-guide/integrations/engine/
    @Bean
    public static ProcessEnginePlugin signalToAzureEventHubEnginePlugin() {
        SignalToEventHubPlugin plugin = new SignalToEventHubPlugin();
        // az eventhubs namespace authorization-rule keys list --resource-group dummyresourcegroup --namespace-name dummynamespace --name RootManageSharedAccessKey
        // Endpoint=sb://<FQDN>/;SharedAccessKeyName=<KeyName>;SharedAccessKey=<KeyValue>
        SignalToEventHubPlugin.setConnectionString("Endpoint=sb://yournamespace.servicebus.windows.net/;SharedAccessKeyName=demoPolicy;SharedAccessKey=...=");
        SignalToEventHubPlugin.setEventHubName("demoeventhub");
        return plugin;
    }
}