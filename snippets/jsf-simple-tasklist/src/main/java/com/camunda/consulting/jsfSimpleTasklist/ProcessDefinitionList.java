package com.camunda.consulting.jsfSimpleTasklist;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;


@SessionScoped
@Named("startList")
public class ProcessDefinitionList extends ProcessApplicationBean implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Inject
  private RepositoryService repositoryService;
  
  @Inject
  private FormService formService;
  
  @Inject
  private RuntimeService runtimeService;
  
  public List<ProcessDefinition> getList() {
    return repositoryService.createProcessDefinitionQuery().latestVersion().list();
  }
  
  public String getAbsoluteStartFormKey(ProcessDefinition processDefinition) {
    String startFormKey = "";
    if (processDefinition.hasStartFormKey()) {
      startFormKey = formService.getStartFormKey(processDefinition.getId());
    }
    
    if (startFormKey.startsWith("app:")) {
      String applicationPath = getApplicationPath(processDefinition.getId());
      return applicationPath + "/" + startFormKey.substring(4); 
    }
    return startFormKey;
  }
  
}
