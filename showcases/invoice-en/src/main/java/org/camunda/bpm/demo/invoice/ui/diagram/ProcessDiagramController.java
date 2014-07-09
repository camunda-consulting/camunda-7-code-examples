package org.camunda.bpm.demo.invoice.ui.diagram;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.repository.DiagramLayout;
import org.camunda.bpm.engine.repository.DiagramNode;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Comment;


@Named
public class ProcessDiagramController {

  @Inject
  private RuntimeService runtimeService;

  @Inject
  private RepositoryService repositoryService;

  @Inject
  private HistoryService historyService;

  @Inject
  private BusinessProcess businessProcess;

  @Inject
  private TaskService taskService;
  
 
  private ProcessInstance getCurrentProcessInstance() {
    return businessProcess.getProcessInstance();
  }

  public List<PositionedHistoricActivityInstance> getTraversedFlowNodes() {
    ArrayList<PositionedHistoricActivityInstance> alist = new ArrayList<PositionedHistoricActivityInstance>();

    ProcessInstance processInstance = getCurrentProcessInstance();

    if (processInstance != null) {
      DiagramLayout processDiagramLayout = repositoryService.getProcessDiagramLayout(processInstance.getProcessDefinitionId());
      
      List<HistoricActivityInstance> hlist = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list();

      for (HistoricActivityInstance hact : hlist) {
        if (hact.getEndTime() != null) {
           PositionedHistoricActivityInstance pact = new PositionedHistoricActivityInstance(hact, processDiagramLayout.getNode(hact.getActivityId()));
          alist.add(pact);
          
        }
      }
    }

    return alist;
  }

  public List<PositionedHistoricTaskInstance> getTraversedTasks() {
	  	ArrayList<PositionedHistoricTaskInstance> alist = new ArrayList<PositionedHistoricTaskInstance>();
	    ProcessInstance processInstance = getCurrentProcessInstance();

	    if (processInstance != null) {
	      DiagramLayout processDiagramLayout = repositoryService.getProcessDiagramLayout(processInstance.getProcessDefinitionId());
	      List<HistoricTaskInstance> hlist = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).orderByHistoricActivityInstanceStartTime().asc().list();
	      for (HistoricTaskInstance htask : hlist) {
	    	  if (htask.getEndTime() != null) {
	        	PositionedHistoricTaskInstance ptask = new PositionedHistoricTaskInstance(htask, processDiagramLayout.getNode(htask.getTaskDefinitionKey()));
	        	alist.add(ptask);
	        }
	      }
	    }
	    return alist;
	  }

  public List<PositionedHistoricTaskDefinition> getTraversedTaskDefinitions() {
	  	ArrayList<PositionedHistoricTaskDefinition> alist = new ArrayList<PositionedHistoricTaskDefinition>();

	    ProcessInstance processInstance = getCurrentProcessInstance();

	    if (processInstance != null) {
	      DiagramLayout processDiagramLayout = repositoryService.getProcessDiagramLayout(processInstance.getProcessDefinitionId());
	      
	      //order by taskName as workaround because orderByTaskDefinitionKey is not working. This is not really safe!
	      List<HistoricTaskInstance> hlist = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();

	      int i=0;
	      for (HistoricTaskInstance htask : hlist) {
	    	  if (htask.getEndTime() != null) {
	    		  if (i>0) {
		    		  if (alist.get(i-1).getTaskDefinitionKey().equals(htask.getTaskDefinitionKey())) {
		        		alist.get(i-1).taskInstances.add(htask);
		        	} else {
		        		PositionedHistoricTaskDefinition ptask = new PositionedHistoricTaskDefinition(htask, processDiagramLayout.getNode(htask.getTaskDefinitionKey()));
		  	          	alist.add(ptask);
		  	          	i++;
		        	}
	    		  } else {
		        		PositionedHistoricTaskDefinition ptask = new PositionedHistoricTaskDefinition(htask, processDiagramLayout.getNode(htask.getTaskDefinitionKey()));
		  	          	alist.add(ptask);
		  	          	i++;
	    		  }
	        }
	      }
	    }
	    return alist;
	  }


  
  
  public List<DiagramNode> getActiveActivityBoundsOfLatestProcessInstance() {
    ArrayList<DiagramNode> list = new ArrayList<DiagramNode>();
    ProcessInstance processInstance = getCurrentProcessInstance();
    if (processInstance != null) {
      DiagramLayout processDiagramLayout = repositoryService.getProcessDiagramLayout(processInstance.getProcessDefinitionId());
      if (processDiagramLayout != null) { // happens if no image is deployed
	      List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstance.getId());
	      for (String activeActivityId : activeActivityIds) {
	        list.add(processDiagramLayout.getNode(activeActivityId));
	      }
      }
    }
    return list;
  }
  
  public List<Comment> getCommentsOfLastHistoricTask() {
    ProcessInstance processInstance = getCurrentProcessInstance();
    List<HistoricTaskInstance> hlist = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).orderByHistoricTaskInstanceEndTime().desc().list();
	if (hlist.get(0).getEndTime() != null) {
	    return getTaskComments(hlist.get(0).getId());		
	} else {
		return null;
	}
  }
  
  public String getTaskComment (String taskId) {
	  String comment = "";
	  if (!taskService.getTaskComments(taskId).isEmpty()) comment = taskService.getTaskComments(taskId).get(0).getFullMessage();
      return comment;
  }
  
  public List<Comment> getTaskComments (String taskId) {
	  return taskService.getTaskComments(taskId);
  }

}
