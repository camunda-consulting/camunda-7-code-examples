package com.camunda.bpm.demo.subtask_checklist;

import java.util.Collection;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;

public class CreateSubTasksListener implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    CamundaProperties camundaProperties = delegateTask.getBpmnModelElementInstance().
          getExtensionElements().getElementsQuery().filterByType(CamundaProperties.class).singleResult();
    
    Collection<CamundaProperty> properties = camundaProperties.getCamundaProperties();
    for (CamundaProperty camundaProperty : properties) {
      if ("subtaskConfiguration".equals( camundaProperty.getCamundaName() )) {
        JSONArray subtasks = new JSONArray(camundaProperty.getCamundaValue());
        for (int i = 0; i < subtasks.length(); i++) {
          JSONObject subtask = subtasks.getJSONObject(i);
          String name = subtask.getString("taskName");
          String formKey = subtask.getString("taskFormKey");
          
          Task newTask = delegateTask.getProcessEngineServices().getTaskService()
            .newTask();
          newTask.setParentTaskId(delegateTask.getId());
          newTask.setName(name);
          newTask.setDescription(formKey);
          delegateTask.getProcessEngineServices().getTaskService().saveTask(newTask);
        }
      }
    }
    
    // you can read properties (like formKey) from "the outside" as will via public API:
//    Collection<CamundaProperties> elementsByType = delegateTask.getProcessEngineServices().getRepositoryService().getBpmnModelInstance(
//        delegateTask.getProcessDefinitionId()).getModelElementsByType(CamundaProperties.class);    
//    String st = elementsByType.iterator().next().getCamundaProperties().iterator().next().getCamundaValue();
  }
  
  

}
