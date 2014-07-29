package com.camunda.demo.migration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.impl.bpmn.behavior.AbstractBpmnActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.behavior.CallActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.DataAssociation;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.javax.el.PropertyNotFoundException;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.pvm.PvmProcessInstance;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.camunda.bpm.engine.impl.pvm.runtime.ProcessInstanceStartContext;

public class MigrationEnabledCallActivityBehavior extends CallActivityBehavior {

  // NEW
  private Expression migrationScenarioExpression;
  
  // copied (because of limited visibility)
  private List<DataAssociation> copy_dataInputAssociations = new ArrayList<DataAssociation>();
  private List<DataAssociation> copy_dataOutputAssociations = new ArrayList<DataAssociation>();
  private Expression copy_processDefinitionExpression;

  public MigrationEnabledCallActivityBehavior(CallActivityBehavior behavior) {
    // create a new behavior
    super((String) null);

    // now copy all existing attributes - due to limited visibility do it the
    // hard way:
    copyProperty("processDefinitionKey", CallActivityBehavior.class, behavior, this, false);
    copyProperty("binding", CallActivityBehavior.class, behavior,this, false);
    copyProperty("version", CallActivityBehavior.class, behavior,this, false);
    copyProperty("dataInputAssociations", CallActivityBehavior.class, behavior,this, true);
    copyProperty("dataOutputAssociations", CallActivityBehavior.class, behavior,this, true);
    copyProperty("processDefinitionExpression", CallActivityBehavior.class, behavior,this, true);
    copyProperty("multiInstanceActivityBehavior", AbstractBpmnActivityBehavior.class, behavior, this, false);
  }

  public void addMigrationScenarioExpression(Expression expression) {
    this.migrationScenarioExpression = expression;
  }

  /**
   * copied from CallActivityBehavior
   */
  @Override
  public void execute(ActivityExecution execution) throws Exception {
    String processDefinitionKey = this.processDefinitionKey;
    String binding = this.binding;
    Integer version = this.version;
    if (copy_processDefinitionExpression != null) {
      processDefinitionKey = (String) copy_processDefinitionExpression.getValue(execution);
    }

    ProcessDefinitionImpl processDefinition = null;
    if (binding == null || CalledElementBinding.LATEST.getValue().equals(binding)) {
      processDefinition = Context.getProcessEngineConfiguration().getDeploymentCache().findDeployedLatestProcessDefinitionByKey(processDefinitionKey);
    } else if (binding != null && CalledElementBinding.DEPLOYMENT.getValue().equals(binding)) {
      processDefinition = Context
          .getProcessEngineConfiguration()
          .getDeploymentCache()
          .findDeployedProcessDefinitionByDeploymentAndKey(Context.getExecutionContext().getExecution().getProcessDefinition().getDeploymentId(),
              processDefinitionKey);
    } else if (binding != null && CalledElementBinding.VERSION.getValue().equals(binding) && version != null) {
      processDefinition = Context.getProcessEngineConfiguration().getDeploymentCache()
          .findDeployedProcessDefinitionByKeyAndVersion(processDefinitionKey, version);
    }

    // copy process variables / businessKey
    String businessKey = null;
    Map<String, Object> callActivityVariables = new HashMap<String, Object>();

    for (DataAssociation dataInputAssociation : copy_dataInputAssociations) {
      Object value = null;

      if (dataInputAssociation.getBusinessKeyExpression() != null) {
        businessKey = (String) dataInputAssociation.getBusinessKeyExpression().getValue(execution);
      } else if (dataInputAssociation.getVariables() != null) {
        Map<String, Object> variables = execution.getVariables();
        if (variables != null && !variables.isEmpty()) {
          Set<String> variableKeys = variables.keySet();
          for (String variableKey : variableKeys) {
            callActivityVariables.put(variableKey, variables.get(variableKey));
          }
        }
      } else if (dataInputAssociation.getSourceExpression() != null) {
        value = dataInputAssociation.getSourceExpression().getValue(execution);
      } else {
        value = execution.getVariable(dataInputAssociation.getSource());
      }

      if (value != null) {
        callActivityVariables.put(dataInputAssociation.getTarget(), value);
      }
    }

    // UNCHANGED TILL HERE!
    
    // now we create the process instance on a different activity steered by the expression:
    String migrationScenario = null;
    try {
      migrationScenario = (String) migrationScenarioExpression.getValue(execution);
    }
    catch (ProcessEngineException ex) {
      // might be the case if the process run in "normal" mode and some variables have not be passed id
    }
    
    if (migrationScenario==null) {
      // normal behavior
      PvmProcessInstance subProcessInstance = execution.createSubProcessInstance(processDefinition, businessKey);
      subProcessInstance.start(callActivityVariables);
    }
    else {
      // now there are two possibilities: 
      // 1. The process contains a matching Message Start Event
      // 2. The process contains a matching intermediate event
      ActivityImpl startActivity = processDefinition.findActivity("MIGRATION_SCENARIO_" + migrationScenario);
      
      // handing over the initial activity is missing in PVM API 
      ExecutionEntity subProcessInstance = (ExecutionEntity) execution.createSubProcessInstance(processDefinition, businessKey);
      // so we do it the hard way afterwards
      Field processInstanceStartContextField = ExecutionEntity.class.getDeclaredField("processInstanceStartContext");
      processInstanceStartContextField.setAccessible(true);
      processInstanceStartContextField.set(subProcessInstance, new ProcessInstanceStartContext(startActivity));
      
      // and start normally
      subProcessInstance.start(callActivityVariables);
    }
  }

  public static void copyProperty(String name, Class clazz, CallActivityBehavior from, MigrationEnabledCallActivityBehavior to, boolean addCopy) {
    try {
      // set normal field
      Field privateField = clazz.getDeclaredField(name);
      privateField.setAccessible(true);

      Object fieldValue = privateField.get(from);
      privateField.set(to, fieldValue);

      privateField.setAccessible(false);
      
      // set copy if applicable
      if (addCopy) {
        Field copyField = MigrationEnabledCallActivityBehavior.class.getDeclaredField("copy_" + name);
        copyField.setAccessible(true);
        copyField.set(to, fieldValue);
        copyField.setAccessible(false);
      }
    } catch (Exception ex) {
      throw new ProcessEngineException("Could not copy property '" + name + "' to CallActivityBehavior of migration extension due to: " + ex.getMessage(), ex);
    }
  }

}
