package com.camunda.bpm.demo.subtask_checklist;

import java.util.Collection;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.Query;
import org.camunda.bpm.model.bpmn.impl.instance.camunda.CamundaPropertiesImpl;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.spin.json.SpinJsonNode;

import static org.camunda.spin.Spin.*;

public class CreateSubTasksListener implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    CamundaProperties camundaProperties = delegateTask.getBpmnModelElementInstance().
          getExtensionElements().getElementsQuery().filterByType(CamundaProperties.class).singleResult();
    
    Collection<CamundaProperty> properties = camundaProperties.getCamundaProperties();
    for (CamundaProperty camundaProperty : properties) {
      if ("subtaskConfiguration".equals( camundaProperty.getCamundaName() )) {
        SpinJsonNode json = JSON(camundaProperty.getCamundaValue());
        for (SpinJsonNode subtask : json.prop("subtasks").elements()) {
          String name = subtask.prop("taskName").stringValue();
          String formKey = subtask.prop("taskFormKey").stringValue();
          
          Task newTask = delegateTask.getProcessEngineServices().getTaskService()
            .newTask();
          newTask.setParentTaskId(delegateTask.getId());
          newTask.setName(name);
          newTask.setDescription(formKey);
          delegateTask.getProcessEngineServices().getTaskService().saveTask(newTask);
          delegateTask.getProcessEngineServices().getTaskService().setVariableLocal(newTask.getId(), "parentTaskId", delegateTask.getId());
        }
      }
    }
    
    Collection<CamundaProperties> elementsByType = delegateTask.getProcessEngineServices().getRepositoryService().getBpmnModelInstance(
        delegateTask.getProcessDefinitionId()).getModelElementsByType(CamundaProperties.class);
    
    String st = elementsByType.iterator().next().getCamundaProperties().iterator().next().getCamundaValue();
    System.out.println(st);
    System.out.println(
      JSON(st)
      .prop("subtasks")
    );
  }
  
  

}
