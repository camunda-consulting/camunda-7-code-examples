package com.camunda.demo.outerspace.wjax.controller.dashboard;

import java.io.Serializable;

import org.camunda.bpm.engine.repository.DiagramNode;

public class FlowNodeDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private long countUnfinished;
  private long countAll;

  private DiagramNode bounds;

  public FlowNodeDTO(DiagramNode bounds, long countAll, long countUn1finished) {
    this.bounds = bounds;
    this.countAll = countAll;
    countUnfinished = countUn1finished;
    
  }

  public DiagramNode getBounds() {
    return bounds;
  }

  
  public long getCountUnfinished() {
    return countUnfinished;
  }

  
  public long getCountAll() {
    return countAll;
  }


}
