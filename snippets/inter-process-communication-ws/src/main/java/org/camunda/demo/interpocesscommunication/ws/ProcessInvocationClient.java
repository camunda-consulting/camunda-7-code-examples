package org.camunda.demo.interpocesscommunication.ws;

import java.net.URL;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Named
public class ProcessInvocationClient {
  
  public static final String CORRELATION_ID_PREFIX = "correlationIdForInvocationOf_";
  public static final String CURRENTLY_CALLED_PROCESS_DEFINITION_KEY = "currentlyCalledProcessDefinitionKey";
  public static final String SAMPLE_PAYLOAD_PREFIX = "sample-payload-";
  
  @Inject
  ServiceRegistry serviceRegistry;
  
  public void invokeProcess(String processDefinitionKey, DelegateExecution execution) {
    // lookup service URL
    URL wsdlLocation = serviceRegistry.getWsdlLocation(processDefinitionKey);
    
    // prepare CXF client
    ProcessInvocationService service = new ProcessInvocationService_Service(wsdlLocation)
        .getProcessInvocationServicePort();
    
    // generate callback URL and correlation ID
    String callbackUrl = serviceRegistry.getWsdlLocation("inter-process-communication-ws-parent").toString();
    String correlationId = UUID.randomUUID().toString();

    // store correlation ID
    execution.setVariable(CORRELATION_ID_PREFIX + processDefinitionKey, correlationId);
    // and remember which process definition was called as sub process
    execution.setVariable(CURRENTLY_CALLED_PROCESS_DEFINITION_KEY, processDefinitionKey);
    
    // call service
    service.invokeProcess(processDefinitionKey, callbackUrl , correlationId, SAMPLE_PAYLOAD_PREFIX + correlationId);
  } 

}
