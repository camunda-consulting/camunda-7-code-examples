package com.camunda.demo.environment;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ResourceEntity;

public class ReplaceExistingBpmnXmlCommand { 
  //implements Command<byte[]> {
//
//  private String processDefinitionKey;
//
//  @Override
//  public T execute(CommandContext commandContext) {
//    
//    ProcessDefinitionEntity pd = commandContext.getProcessDefinitionManager().findLatestProcessDefinitionByKey(processDefinitionKey);
//    
//    ResourceEntity resource = commandContext.getResourceManager().findResourceByDeploymentIdAndResourceName(
//        pd.getDeploymentId(),
//        pd.getResourceName());
//    
//    byte[] originalProcess = resource.getBytes();
//    
//    resource.setBytes(bytes);
//    
//    commandContext.get
//    
//    commandContext.getDbEntityManager().getDbEntityCache().putPersistent(Pd);
//    
//    return null;
//  }

}
