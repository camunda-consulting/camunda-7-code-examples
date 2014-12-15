package com.camunda.bpm.cob.region;

import java.util.Set;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.IdentityLinkEntity;
import org.camunda.bpm.engine.task.IdentityLink;

public class AdjustRegionCandidateTaskListener implements TaskListener {
	
	private static final Logger log = Logger.getLogger(AdjustRegionCandidateTaskListener.class.getName());

	@Override
	public void notify(DelegateTask delegateTask) {
		log.info("Adjust the candidate group");
		ExecutionEntity processInstance = ((ExecutionEntity) delegateTask.getExecution()).getProcessInstance();
		HistoricProcessInstance historicProcessInstance = delegateTask.getProcessEngineServices().getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
		String startUserId = null;
		if (historicProcessInstance != null) {
			startUserId = historicProcessInstance.getStartUserId();
		} else {
			Authentication currentAuthentication = delegateTask.getProcessEngineServices().getIdentityService().getCurrentAuthentication();
			if (currentAuthentication != null) {
				startUserId = currentAuthentication.getUserId();
			}
		}
		log.info("process instance started by user " + startUserId);
		if (startUserId != null) {
			User userIdentity = delegateTask.getProcessEngineServices().getIdentityService().createUserQuery().userId(startUserId).singleResult();
			log.info("user from identity service: " + userIdentity);
			if (userIdentity != null) {
				// just a shortcut to get the region from the active directory
				String region = userIdentity.getEmail();
				Set<IdentityLink> candidates = delegateTask.getCandidates();
				for (IdentityLink identityLink : candidates) {
					String groupId = identityLink.getGroupId();
					log.info("change group " + groupId + " for task " + delegateTask.getName());
					IdentityLinkEntity identityLinkEntity = (IdentityLinkEntity) identityLink;
					identityLinkEntity.setGroupId(groupId + "_" + region);
				}
			}
		} else {
			log.info("unable to change candidate group because startUserId is null");
		}
	}
}
