package com.camunda.consulting.history.changeHistoryOutput;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.history.HistoricDetail;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class FilterVariableHistoryPluginTest {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "change-history-output";

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
    LogFactory.useJdkLogging(); // MyBatis
  }
  
  @Before
  public void setup() {
	  init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing to do here, we just want to check for exceptions during deployment
  }
  
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void onlyOrderIdHistoryVariablesCreate() {
    runtimeService().startProcessInstanceByKey(
        PROCESS_DEFINITION_KEY, 
        withVariables("var1", "value1", "var2", "value2", "orderId", "456"));
    List<HistoricVariableInstance> histVars = historyService().createHistoricVariableInstanceQuery().list();
    assertThat(histVars).hasSize(1);
    HistoricVariableInstance orderId = histVars.get(0);
    HistoricVariableInstanceEntity checkOrderVariable = createHistoryVariable("orderId", "456");
    assertThat(orderId).isEqualToComparingOnlyGivenFields(checkOrderVariable, "name", "textValue");
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void noHistoricVariableDetails() {
    runtimeService().startProcessInstanceByKey(
        PROCESS_DEFINITION_KEY, 
        withVariables("var1", "value1", "var2", "value2", "orderId", "234"));
    List<HistoricDetail> histDetails = historyService().createHistoricDetailQuery().list();
    assertThat(histDetails).isEmpty();
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void setUserTaskVariables() {
    runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    Task firstTask = taskQuery().singleResult();
    claim(firstTask, "john");
    complete(firstTask, withVariables("var1", "value1", "var2", "value2", "orderId", "678"));
    List<HistoricVariableInstance> histVars = historyService().createHistoricVariableInstanceQuery().list();
    assertThat(histVars).hasSize(1);
    HistoricVariableInstance orderId = histVars.get(0);
    HistoricVariableInstanceEntity checkOrderVariable = createHistoryVariable("orderId", "678");
    assertThat(orderId).isEqualToComparingOnlyGivenFields(checkOrderVariable, "name", "textValue");
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void changeProcessVariables() {
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(
        PROCESS_DEFINITION_KEY, 
        withVariables("var1", "value1", "var2", "value2", "orderId", "890"));
    runtimeService().setVariables(pi.getId(), withVariables("var1", "valueNew", "orderId", "789"));
    List<HistoricVariableInstance> histVars = historyService().createHistoricVariableInstanceQuery().list();
    assertThat(histVars).hasSize(1);
    HistoricVariableInstance orderId = histVars.get(0);
    HistoricVariableInstanceEntity checkOrderVariable = createHistoryVariable("orderId", "789");
    assertThat(orderId).isEqualToComparingOnlyGivenFields(checkOrderVariable, "name", "textValue");
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void noDetailsAfterChange() {
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(
        PROCESS_DEFINITION_KEY, 
        withVariables("var1", "value1", "var2", "value2", "orderId", "234"));
    runtimeService().setVariables(pi.getId(), withVariables("var1", "valueNew", "orderId", "456"));
    List<HistoricDetail> histDetails = historyService().createHistoricDetailQuery().list();
    assertThat(histDetails).isEmpty();
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void deleteHistoricVariable() {
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(
        PROCESS_DEFINITION_KEY, 
        withVariables("var1", "value1", "var2", "value2", "orderId", "567"));
    Collection<String> varNames =  Arrays.asList("var1", "orderId");
    runtimeService().removeVariables(pi.getId(), varNames );
    List<HistoricVariableInstance> histVars = historyService().createHistoricVariableInstanceQuery().list();
    assertThat(histVars).isEmpty();
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void noDetailsAfterDelete() {
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(
        PROCESS_DEFINITION_KEY, 
        withVariables("var1", "value1", "var2", "value2", "orderId", "123"));
    List<String> varNames = Arrays.asList("var1", "orderId");
    runtimeService().removeVariables(pi.getId(), varNames);
    List<HistoricDetail> histDetails = historyService().createHistoricDetailQuery().list();
    assertThat(histDetails).isEmpty();
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void createLocalVariable() {
    runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    Task firstTask = taskQuery().singleResult();
    taskService().setVariablesLocal(firstTask.getId(), withVariables("var1", "value1", "var2", "value2", "orderId", "678"));
    List<HistoricVariableInstance> histVars = historyService().createHistoricVariableInstanceQuery().list();
    assertThat(histVars).hasSize(1);
    HistoricVariableInstance orderId = histVars.get(0);
    HistoricVariableInstanceEntity checkOrderId = createHistoryVariable("orderId", "678");
    assertThat(orderId).isEqualToComparingOnlyGivenFields(checkOrderId, "name", "textValue");
  }

  protected HistoricVariableInstanceEntity createHistoryVariable(String name, String textValue) {
    HistoricVariableInstanceEntity checkOrderVariable = new HistoricVariableInstanceEntity();
    checkOrderVariable.setName(name);
    checkOrderVariable.setTextValue(textValue);
    return checkOrderVariable;
  }

}
