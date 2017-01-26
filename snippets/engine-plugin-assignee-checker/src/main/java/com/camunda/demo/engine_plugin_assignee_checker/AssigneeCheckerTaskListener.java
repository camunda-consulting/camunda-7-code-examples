/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.camunda.demo.engine_plugin_assignee_checker;

import java.util.logging.Logger;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.UserQuery;
import org.camunda.bpm.engine.task.IdentityLink;


/**
 * {@link TaskListener} that checks assignees of User Tasks.
 *
 * @author Falko Menge (Camunda)
 */
public class AssigneeCheckerTaskListener implements TaskListener {

  private final Logger LOGGER = Logger.getLogger(AssigneeCheckerTaskListener.class.getName());

  @Override
  public void notify(DelegateTask task) {
    LOGGER.info("Event '" + task.getEventName() + "' received by Task Listener for Task:"
        + " activityId=" + task.getTaskDefinitionKey()
        + ", name='" + task.getName() + "'"
        + ", taskId=" + task.getId()
        + ", assignee='" + task.getAssignee() + "'"
        + ", candidateGroups='" + task.getCandidates() + "'");
    String assignee = task.getAssignee();
    if (assignee != null) {
      IdentityService identityService = task.getProcessEngineServices().getIdentityService();
      UserQuery userQuery = identityService.createUserQuery().userId(assignee);
      if (userQuery.count() == 0) {
        throw new AssigneeUnknownException(assignee);
      }
      for (IdentityLink candidate : task.getCandidates()) {
        if (assignee.equals(candidate.getUserId())) {
          return;
        } else if (candidate.getGroupId() != null
          && userQuery.memberOfGroup(candidate.getGroupId()).count() > 0) {
          return;
        }
      }
      throw new AssigneeNotInCandidatesException(task);
    }
  }

}
