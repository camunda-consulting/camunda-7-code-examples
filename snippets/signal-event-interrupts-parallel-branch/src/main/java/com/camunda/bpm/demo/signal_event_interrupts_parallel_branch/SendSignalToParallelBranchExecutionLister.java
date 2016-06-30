package com.camunda.bpm.demo.signal_event_interrupts_parallel_branch;

import java.util.Collection;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;

public class SendSignalToParallelBranchExecutionLister implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    String signalName = null;

    Collection<CamundaProperty> properties = ((CamundaProperties) execution
      .getBpmnModelElementInstance()
      .getExtensionElements()
      .getUniqueChildElementByType(CamundaProperties.class))
      .getCamundaProperties();
    for (CamundaProperty property : properties) {
      if ("signalName".equals(property.getCamundaName())) { 
        signalName = property.getCamundaValue();
      }
    }
    
    if (signalName == null || signalName.isEmpty()) {
      throw new RuntimeException("You have to set a Camunda Extension Property named 'signalName' to use this listener!");
    }
    
    RuntimeService runtimeService = execution.getProcessEngineServices()
      .getRuntimeService();
    Execution parallelBranch =
      runtimeService.createExecutionQuery()
      .processInstanceId(execution.getProcessInstanceId())
      .signalEventSubscriptionName(signalName)
      .singleResult();
    
    runtimeService.signalEventReceived(signalName, parallelBranch.getId());
  }

}
