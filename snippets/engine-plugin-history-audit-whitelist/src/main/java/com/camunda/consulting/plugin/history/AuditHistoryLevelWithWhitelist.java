/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.camunda.consulting.plugin.history;

import java.util.List;

import org.camunda.bpm.engine.impl.history.HistoryLevelAudit;
import org.camunda.bpm.engine.impl.history.event.HistoricVariableUpdateEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEventType;
import org.camunda.bpm.engine.impl.history.event.HistoryEventTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AuditHistoryLevelWithWhitelist extends HistoryLevelAudit {

	private static final Logger log = LoggerFactory.getLogger(AuditHistoryLevelWithWhitelist.class);

	/**
	 * history level name
	 */
	public static final String NAME = "audit-with-whitelist";

	public static final AuditHistoryLevelWithWhitelist INSTANCE = new AuditHistoryLevelWithWhitelist();

	public static AuditHistoryLevelWithWhitelist getInstance() {
		return INSTANCE;
	}

	@Override
	public int getId() {
		return 21;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean isHistoryEventProduced(HistoryEventType historyEventType, Object entity) {
		log.warn("HistoryEvent: {} with entity {}", historyEventType, entity);

		// If the entity is null the engine tests if the history level in general
		// handles such history events.
		if (entity == null) {
			return super.isHistoryEventProduced(historyEventType, entity);
		}

		// We are only interested in variable-instance-updates
		if (historyEventType.getEventName().equals(HistoryEventTypes.VARIABLE_INSTANCE_UPDATE_DETAIL.getEventName())) {
			return isWhitelistedVariableInstance(historyEventType, entity);
		}

		// delegate it to the super class
		return super.isHistoryEventProduced(historyEventType, entity);

	}

	protected boolean isWhitelistedVariableInstance(HistoryEventType historyEventType, Object entity) {
		List<String> variableWhitelist = AuditWithWhitelistCustomHistoryLevelProcessEnginePlugin.getVariableWhitelistList();

		log.info("Found entity with update-detail: {}", entity);
		log.info("variableWhitelist: {}", variableWhitelist);

		if ( null == variableWhitelist || variableWhitelist.isEmpty()) {
			log.info("No whitelisted variables found!");

			return false;

		}

		if (entity instanceof HistoricVariableUpdateEventEntity) {
			HistoricVariableUpdateEventEntity variableInstance = ((HistoricVariableUpdateEventEntity) entity);

			String variableName = variableInstance.getVariableName();

			log.info("Found variableName: {}", variableName);
			log.info("Boolean result: {}", variableWhitelist.contains(variableName));
			return variableWhitelist.contains(variableName);
		}

		return true;

	}

}
