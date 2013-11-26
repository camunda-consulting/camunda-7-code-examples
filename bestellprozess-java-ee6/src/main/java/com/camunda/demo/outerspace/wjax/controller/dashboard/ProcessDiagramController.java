package com.camunda.demo.outerspace.wjax.controller.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.DiagramLayout;
import org.camunda.bpm.engine.repository.DiagramNode;

@Named
@SessionScoped
public class ProcessDiagramController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private RepositoryService repositoryService;

  @Inject
  private HistoryService historyService;

  private String processDefinitionId;

  public String getProcessDefinitionId() {
    if (processDefinitionId == null) {
      processDefinitionId = repositoryService.createProcessDefinitionQuery() //
              .processDefinitionKey("wjax2012-bestellprozess") //
              .latestVersion() //
              .singleResult().getId();
    }
    return processDefinitionId;
  }

  public List<FlowNodeDTO> getFlowNodes() {
    ArrayList<FlowNodeDTO> result = new ArrayList<FlowNodeDTO>();
    DiagramLayout processDiagramLayout = this.repositoryService.getProcessDiagramLayout(getProcessDefinitionId());

    for (DiagramNode node : processDiagramLayout.getNodes()) {
      if (!filterNode(node)) {
        long allCount = historyService.createHistoricActivityInstanceQuery().processDefinitionId(getProcessDefinitionId()).activityId(node.getId()).count();
        long unfinishedCount = historyService.createHistoricActivityInstanceQuery().processDefinitionId(getProcessDefinitionId()).activityId(node.getId()).unfinished().count();
        result.add(new FlowNodeDTO(node, allCount, unfinishedCount));
      }
    }
    return result;
  }

  private boolean filterNode(DiagramNode node) {
    if (!node.isNode()) {
      return true;
    }
    // hard coded ugly - but quick way to get rid of the Pool in the list of
    // nodes
    if (node.getId().toLowerCase().contains("participant") || node.getId().toLowerCase().contains("bpmndiagram")
            || node.getId().toLowerCase().startsWith("sid-")) {
      return true;
    }
    // and the boundary events
    if (node.getId().toLowerCase().contains("boundaryevent")) {
      return true;
    }

    return false;
  }

}
