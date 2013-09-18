package org.camunda.demo.interpocesscommunication.ws;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.jws.WebService;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;

@WebService(name = "ProcessCallbackService")
public class ProcessCallback {

  public static final String PAYLOAD_RECEIVED_FROM_CALLBACK = "payloadReceivedFromCallback";

  @Inject
  private RuntimeService runtimeService;
  
  public void invokeProcessCallback(String calledProcess, String correlationId, String payload) {
    Execution execution = runtimeService
            .createExecutionQuery()
            .variableValueEquals(ProcessInvocationClient.CORRELATION_ID_PREFIX + calledProcess, correlationId)
            .singleResult();
    
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put(PAYLOAD_RECEIVED_FROM_CALLBACK, payload);
    // remove variable for process which is currently called
    variables.put(ProcessInvocationClient.CURRENTLY_CALLED_PROCESS_DEFINITION_KEY, null);
    runtimeService.signal(execution.getId(), variables);
  }

}
