package org.camunda.bpm.demo.cockpit.plugin.bpmncollaboration;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractPluginResource;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class BpmnCollaborationEngineSpecificResource extends AbstractPluginResource {

  // TODO: Make configurable (how?)
  private static final String VARIABLE_NAME = "callingProcess";

  public BpmnCollaborationEngineSpecificResource(String engineName) {
    super(engineName);
  }

  @GET
  @Path("process-instance/{id}/linked-process-instance")
  public List<ProcessInstanceDto> getLinkedProcessInstances(@PathParam("id") String processInstanceId) {
    ArrayList<ProcessInstanceDto> linkedProcesses = new ArrayList<ProcessInstanceDto>();
    
    // processes being started by us:
    
    // TODO: discuss if we use the process instance id here
    String variableValue = processInstanceId;
    
    List<ProcessInstance> processInstances = getProcessEngine().getRuntimeService().createProcessInstanceQuery() //
      .variableValueEquals(VARIABLE_NAME, variableValue)
      .list();
    for (ProcessInstance pi : processInstances) {
      linkedProcesses.add( ProcessInstanceDto.fromProcessInstance(pi) );
    }
    
    
    // process which started us 
    String callingProcessInstanceId = (String) getProcessEngine().getRuntimeService().getVariable(processInstanceId, VARIABLE_NAME);
    if (callingProcessInstanceId!=null) {
      ProcessInstance processInstance = getProcessEngine().getRuntimeService().createProcessInstanceQuery() //
           .processInstanceId(callingProcessInstanceId)
           .singleResult();
      linkedProcesses.add( ProcessInstanceDto.fromProcessInstance(processInstance) );
    }
    
    // done
    return linkedProcesses;
  }

}
