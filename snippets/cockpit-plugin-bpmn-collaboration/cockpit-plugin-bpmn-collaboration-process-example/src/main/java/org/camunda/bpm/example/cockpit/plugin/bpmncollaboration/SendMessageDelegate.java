package org.camunda.bpm.example.cockpit.plugin.bpmncollaboration;

import java.util.Collections;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.RuntimeServiceImpl;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutorImpl;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ExecutionQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class SendMessageDelegate implements JavaDelegate {
  
  private static final String VARIABLE_CALLING_PROCESS = "callingProcess";
  private static final String VARIABLE_CALLED_PROCESS = "calledProcess";
  
  private FixedValue messageName;
  private FixedValue startProcess;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    RuntimeServiceImpl runtimeService = new RuntimeServiceImpl();
    runtimeService.setCommandExecutor(new CommandExecutorImpl());
    
    String messageNameStr = (String)messageName.getValue(execution);
    
    Map<String, Object> variables = Collections.<String, Object>singletonMap(VARIABLE_CALLING_PROCESS, execution.getProcessInstanceId());
    if ("true".equals(((String)startProcess.getValue(execution)).toLowerCase())) {
      ProcessInstance pi = runtimeService.startProcessInstanceByMessage(messageNameStr, variables);
      execution.setVariable(VARIABLE_CALLED_PROCESS, pi.getId());
    }
    else {
      // Either we are the calling or called process instance - check:
      String callingProcessInstanceId = (String) execution.getVariable(VARIABLE_CALLING_PROCESS);
      String calledProcessInstanceId = (String) execution.getVariable(VARIABLE_CALLED_PROCESS);
      if (calledProcessInstanceId != null && callingProcessInstanceId!=null) {
        throw new RuntimeException("cannot use this simple example delegate when you are calling and called process at the same time. Use your keyboard to hack a better delegate!");
      }
      if (calledProcessInstanceId == null && callingProcessInstanceId==null) {
        throw new RuntimeException("Neither calling nor called process instance id is set in process variable - cannot correlate.");
      }
      
      String processInstanceId = null;
      if (calledProcessInstanceId!=null) {
        processInstanceId = calledProcessInstanceId;
      } else {
        processInstanceId = callingProcessInstanceId;        
      }
      
      // Make sure to load the correct execution in case of Event Based Gateway
      Execution correlatedExecution = runtimeService.createExecutionQuery() //
          .processInstanceId(processInstanceId) //
          .messageEventSubscriptionName(messageNameStr)
          .singleResult();
      
      if (correlatedExecution==null) {
        throw new RuntimeException("no process instance with id '"+processInstanceId+"' found waiting for message '"+messageNameStr+"'");
      }
      
      // query correct process instance / correlation
      runtimeService.messageEventReceived(messageNameStr, correlatedExecution.getId());
    }
  }

}
