package org.camunda.demo.interpocesscommunication.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Named
public class ProcessCallbackClient {

  public void invokeProcessCallback(String payload, DelegateExecution execution) throws MalformedURLException {
    // lookup service URL
    URL wsdlLocation = new URL((String) execution.getVariable(ProcessInvocation.CALLBACK_URL));
    // prepare CXF client
    ProcessCallbackService service = new ProcessCallbackService_Service(wsdlLocation)
        .getProcessCallbackServicePort();
    // restore correlation information
    String calledProcess = "inter-process-communication-ws-child";
    String correlationId = (String) execution.getVariable(ProcessInvocation.CALLBACK_CORRELATION_ID);
    // call service
    service.invokeProcessCallback(calledProcess, correlationId, payload);
  }

}
