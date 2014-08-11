package org.camunda.showcase.engine.impl.bpmn.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.bpmn.behavior.AbstractBpmnActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.parser.DataAssociation;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.pvm.PvmProcessInstance;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.camunda.bpm.engine.impl.pvm.delegate.SubProcessActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.camunda.bpm.engine.repository.CaseDefinition;

/**
 * Implementation of BPMN 2.0 call activity looking for a process definition or case plan 
 * as the calledElement to start.
 * 
 * @author Ingo Richtsmeier
 *
 */
public class CmmnCallActivityBehavior extends AbstractBpmnActivityBehavior implements SubProcessActivityBehavior {
  
  private static final Logger log = Logger.getLogger(CmmnCallActivityBehavior.class.getName());

  protected String definitionKey;
  protected List<DataAssociation> dataInputAssociations = new ArrayList<DataAssociation>();
  protected List<DataAssociation> dataOutputAssociations = new ArrayList<DataAssociation>();
  
  public CmmnCallActivityBehavior(String definitionKey) {
    log.info("create CmmnCallActivityBehavior");
    this.definitionKey = definitionKey;
  }
  
  public void addDataInputAssociation(DataAssociation dataInputAssociation) {
    this.dataInputAssociations.add(dataInputAssociation);
  }

  public void addDataOutputAssociation(DataAssociation dataOutputAssociation) {
    this.dataOutputAssociations.add(dataOutputAssociation);
  }
  
  @Override
  public void execute(ActivityExecution execution) throws Exception {
    log.info("executing CmmnActivityBehavior");
    String definitionKey = this.definitionKey;
    // look for process definitions first
    ProcessDefinitionImpl processDefinition = null;
    CaseDefinition caseDefinition = null;
    try {
      processDefinition = Context
          .getProcessEngineConfiguration()
          .getDeploymentCache()
          .findDeployedLatestProcessDefinitionByKey(definitionKey);
    } catch (ProcessEngineException e) {
      log.info("unable to find process model " + definitionKey + ", looking for case definition");
      caseDefinition = Context
          .getProcessEngineConfiguration()
          .getDeploymentCache()
          .findDeployedLatestCaseDefinitionByKey(definitionKey);
    }
    // copy process variables / businessKey
    String businessKey = null;
    Map<String, Object> callActivityVariables = new HashMap<String, Object>();

    for (DataAssociation dataInputAssociation : dataInputAssociations) {
      Object value;

      if (dataInputAssociation.getBusinessKeyExpression() != null) {
        // set business key
        businessKey = (String) dataInputAssociation.getBusinessKeyExpression().getValue(execution);
      }
      else if (dataInputAssociation.getVariables() != null) {
        // set all variables
        Map<String, Object> variables = execution.getVariables();
        if (variables != null && !variables.isEmpty()) {
          Set<String> variableKeys = variables.keySet();
          for (String variableKey : variableKeys) {
            callActivityVariables.put(variableKey, variables.get(variableKey));
          }
        }
      }
      else {
        // set single variable
        if (dataInputAssociation.getSourceExpression() != null) {
          value = dataInputAssociation.getSourceExpression().getValue(execution);
        } else {
          value = execution.getVariable(dataInputAssociation.getSource());
        }

        callActivityVariables.put(dataInputAssociation.getTarget(), value);
      }
    }

    if (processDefinition != null) {
      log.info("start process instance here");
      PvmProcessInstance subProcessInstance = execution.createSubProcessInstance(processDefinition, businessKey);
      subProcessInstance.start(callActivityVariables);
    } else if (caseDefinition != null) {
      log.info("should start case instance here");
      Context
        .getProcessEngineConfiguration()
        .getCaseService()
        .withCaseDefinitionByKey(definitionKey)
        .setVariables(callActivityVariables)
//        .businessKey(businessKey)
        .create();
    }
  }

  @Override
  public void completing(DelegateExecution execution, DelegateExecution subProcessInstance) throws Exception {
    log.info("shuold copy variables from subProcess or case to current process instance");
  }

  @Override
  public void completed(ActivityExecution execution) throws Exception {
    log.info("leaving called element " + definitionKey);
    leave(execution);
  }

}
