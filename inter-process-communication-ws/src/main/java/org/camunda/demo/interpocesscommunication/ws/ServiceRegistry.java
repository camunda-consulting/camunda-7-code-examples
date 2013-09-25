package org.camunda.demo.interpocesscommunication.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ServiceRegistry {
  
  private Map<String, String> urls = new HashMap<String, String>();
  
  @Inject
  public void init() {
    urls.put("inter-process-communication-ws-child", "http://localhost:8080/inter-process-communication-ws/ProcessInvocation?wsdl");
    urls.put("inter-process-communication-ws-parent", "http://localhost:8080/inter-process-communication-ws/ProcessCallback?wsdl");
  }

  public URL getWsdlLocation(String processDefinitionKey) {
      if (getUrls().containsKey(processDefinitionKey)) {
        String wsdlLocation = urls.get(processDefinitionKey);
        try {
          return new URL(wsdlLocation);
        } catch (MalformedURLException e) {
          throw new RuntimeException("The URL '" + wsdlLocation + "' registered for process '" + processDefinitionKey + "' seems to be malformed.", e);
        }
      } else {
        throw new RuntimeException("No service registered for process '" + processDefinitionKey + "'.");
      }
  }

  public Map<String, String> getUrls() {
    return urls;
  }

  public void setUrls(Map<String, String> urls) {
    this.urls = urls;
  }

}
