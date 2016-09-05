package org.camunda.bpm.demo.group_matrix;

import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.task.IdentityLink;

public class GroupMatrixTaskListener implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
//    "camunda:matrix:${branch}:approver"
    String branch = new BranchVariable().getVariable(delegateTask.getExecution());
    
    Set<IdentityLink> candidates = delegateTask.getCandidates();
    for (IdentityLink candidate : candidates) {
      delegateTask.deleteCandidateGroup(candidate.getGroupId());
      delegateTask.addCandidateGroup("camunda:matrix:" + branch + ":" + candidate.getGroupId());
    }
  }

}
