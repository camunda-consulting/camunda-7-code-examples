package org.camunda.bpm.demo.cockpit.plugin.bpmncollaboration;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class BpmnCollaborationEngineSpecificResource extends AbstractCockpitPluginResource {

  // TODO: Make configurable (how?)
  private static final String VARIABLE_NAME = "callingProcess";

  public BpmnCollaborationEngineSpecificResource(String engineName) {
    super(engineName);
  }

  @GET
  @Path("process-instance/{id}/linked-process-instance")
  public List<LinkedProcessInstanceDto> getLinkedProcessInstances(@PathParam("id") String processInstanceId) {
    ArrayList<LinkedProcessInstanceDto> linkedProcesses = new ArrayList<LinkedProcessInstanceDto>();
    
    // processes being started by us:    
    List<ProcessInstance> processInstances = getProcessEngine().getRuntimeService().createProcessInstanceQuery() //
      .variableValueEquals(VARIABLE_NAME, processInstanceId)
      .list();
    for (ProcessInstance pi : processInstances) {
      linkedProcesses.add( LinkedProcessInstanceDto.fromProcessInstance(pi) );
    }   
    
    // process which started us 
    String callingProcessInstanceId = (String) getProcessEngine().getRuntimeService().getVariable(processInstanceId, VARIABLE_NAME);
    if (callingProcessInstanceId!=null) {
      // At the moment we only consider running instances - extend to historic as soon as cockpit it capable of it
      ProcessInstance processInstance = getProcessEngine().getRuntimeService().createProcessInstanceQuery() //
           .processInstanceId(callingProcessInstanceId)
           .singleResult();
      if (processInstance!=null) {
        linkedProcesses.add( LinkedProcessInstanceDto.fromProcessInstance(processInstance) );
      }
      
    }
    
    // add process definition information
    for (LinkedProcessInstanceDto dto : linkedProcesses) {
      ProcessDefinition pd = getProcessEngine().getRepositoryService().getProcessDefinition(dto.getProcessDefinitionId());
      dto.setProcessDefinition(pd);
    }
    
    // done
    return linkedProcesses;
  }

}
