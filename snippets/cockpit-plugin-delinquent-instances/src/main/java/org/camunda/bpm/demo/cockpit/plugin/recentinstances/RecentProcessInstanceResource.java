package org.camunda.bpm.demo.cockpit.plugin.recentinstances;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class RecentProcessInstanceResource extends AbstractCockpitPluginResource {

  public RecentProcessInstanceResource(String engineName) {
    super(engineName);
  }

  @GET
  @Path("process-instance")
  public List<ExtendedProcessInstanceDto> getRecentProcessInstances() {
    ArrayList<ExtendedProcessInstanceDto> recentProcessInstances = new ArrayList<ExtendedProcessInstanceDto>();
    
    // processes being started by us:    
    List<ProcessInstance> processInstances = getProcessEngine().getRuntimeService() //
        .createProcessInstanceQuery() //
        .orderByProcessInstanceId().desc() //
        .listPage(0, 20);
    for (ProcessInstance pi : processInstances) {
      ExtendedProcessInstanceDto dto = ExtendedProcessInstanceDto.fromProcessInstance(pi);
      
      ProcessDefinition pd = getProcessEngine().getRepositoryService().getProcessDefinition(dto.getProcessDefinitionId());
      dto.setProcessDefinition(pd);
      
      HistoricProcessInstance historicProcessInstance = getProcessEngine().getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
      if (historicProcessInstance!=null) {
        dto.setStartTime(historicProcessInstance.getStartTime());
      }
      
      recentProcessInstances.add( dto );
    }   
    
    return recentProcessInstances;
  }

}
