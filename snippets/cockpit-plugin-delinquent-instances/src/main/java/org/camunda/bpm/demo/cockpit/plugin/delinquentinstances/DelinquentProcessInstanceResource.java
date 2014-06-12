package org.camunda.bpm.demo.cockpit.plugin.delinquentinstances;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.task.Task;

public class DelinquentProcessInstanceResource extends AbstractCockpitPluginResource {

  public DelinquentProcessInstanceResource(String engineName) {
    super(engineName);
  }

  @GET
  @Path("process-instance")
  public List<ExtendedProcessInstanceDto> getDelinquentProcessInstances() {
    Map<String, ExtendedProcessInstanceDto> delinquentProcessInstances = new HashMap<String, ExtendedProcessInstanceDto>();
    
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -3);
    
    List<Task> delinquentTasks = getProcessEngine().getTaskService()
    		.createTaskQuery()
    		.taskCreatedBefore(calendar.getTime())
    		.orderByTaskCreateTime()
    		.desc()
    		.list();

    for (Task task : delinquentTasks) {
      if (!delinquentProcessInstances.containsKey(task.getProcessInstanceId())) {	
    	  ExtendedProcessInstanceDto dto = ExtendedProcessInstanceDto.fromTask(task);
    	  dto.setStartTime(task.getCreateTime());
          
    	  ProcessDefinition pd = getProcessEngine().getRepositoryService().getProcessDefinition(dto.getProcessDefinitionId());
          dto.setProcessDefinition(pd);

          delinquentProcessInstances.put(task.getProcessInstanceId(), dto);
      } else {
    	  ExtendedProcessInstanceDto dto = delinquentProcessInstances.get(task.getProcessInstanceId());
    	  if (dto.getStartTime().after(task.getCreateTime())) {
        	  dto.setStartTime(task.getCreateTime());
    	  }
      }
    }   
    
    ArrayList<ExtendedProcessInstanceDto> list = new ArrayList<ExtendedProcessInstanceDto>(delinquentProcessInstances.values());
    Collections.sort(list, new Comparator<ExtendedProcessInstanceDto>() {
		@Override
		public int compare(ExtendedProcessInstanceDto o1, ExtendedProcessInstanceDto o2) {
			return o1.getStartTime().compareTo(o2.getStartTime());
		}
	});
	return list;
  }

}
