package com.camunda.demo.skip.delegate.interceptor;

import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.context.ExecutionContext;
import org.camunda.bpm.engine.impl.delegate.DefaultDelegateInterceptor;
import org.camunda.bpm.engine.impl.delegate.DelegateInvocation;
import org.camunda.bpm.engine.impl.pvm.runtime.InterpretableExecution;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;

/**
 * @author Falko Menge
 */
public class SkipDelegateInterceptor extends DefaultDelegateInterceptor {

  private static final String SKIP_PROPERTY_NAME = "skippable";
  private static final String SKIP_VARIABLE_NAME = "skip";
  private static final Logger LOG = Logger.getLogger(SkipDelegateInterceptor.class.getName());

  @Override
  public void handleInvocation(DelegateInvocation invocation) throws Exception {
    if (!isSkipped(invocation)) {
      super.handleInvocation(invocation);
    }
  }

  private boolean isSkipped(DelegateInvocation invocation) {
    InterpretableExecution execution = getExecution(invocation);
    if (isSkipRequested(execution)) {  
      if (isSkippable(execution)) {
        LOG.info("Skipping user code (" + invocation.getClass().getSimpleName() + ") of Activity '" + execution.getCurrentActivityName() + "' (" + execution.getCurrentActivityId() + ")");
        return true;
      } else {
        LOG.warning("Variable 'skip' was true but Activity id not skippable. Not skipping user code (" + invocation.getClass().getSimpleName() + ") of Activity '" + execution.getCurrentActivityName() + "' (" + execution.getCurrentActivityId() + ")");
      }
    }
    return false;
  }

  private boolean isSkipRequested(InterpretableExecution execution) {
    if (execution != null) { 
      String skipVariableNameForActivity = SKIP_VARIABLE_NAME + "_" + execution.getCurrentActivityId();
      if (Boolean.TRUE.equals(execution.getVariable(SKIP_VARIABLE_NAME))) {
        execution.removeVariable(SKIP_VARIABLE_NAME);
        execution.setVariableLocal(skipVariableNameForActivity, true);
        return true;
      } else if (Boolean.TRUE.equals(execution.getVariable(skipVariableNameForActivity))) {
        return true;
      }
    }
    return false;
  }

  private boolean isSkippable(InterpretableExecution execution) {
    return getCamundaProperty(execution.getBpmnModelElementInstance(), SKIP_PROPERTY_NAME) != null;
  }

  private InterpretableExecution getExecution(DelegateInvocation invocation) {
    InterpretableExecution execution = invocation.getContextExecution();
    if (execution == null) {
      ExecutionContext executionContext = Context.getExecutionContext();
      if (executionContext != null) {
        execution = executionContext.getExecution();
      }
    }
    return execution;
  }

  public static String getCamundaProperty(FlowElement flowElement, String propertyName) {
    ExtensionElements extensionElements = flowElement.getExtensionElements();
    List<CamundaProperties> propertyContainers = extensionElements.getElementsQuery()
      .filterByType(CamundaProperties.class)
      .list();
    for (CamundaProperties propertyContainer : propertyContainers) {
      for (CamundaProperty property : propertyContainer.getCamundaProperties()) {
        if (propertyName.equals(property.getAttributeValue("name"))) { // in 7.2 one can use: property.getCamundaName()
          return property.getCamundaValue();
        }
      }
    }
    return null;
  }

  public static String getSkippableProperty(FlowElement flowElement) {
    return getCamundaProperty(flowElement, SKIP_PROPERTY_NAME);
  }

}
