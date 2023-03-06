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

import java.util.*;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.history.HistoryLevel;

public class AuditWithWhitelistCustomHistoryLevelProcessEnginePlugin extends AuditWithWhitelistPluginConfiguration implements ProcessEnginePlugin {

	private static List<String> variableWhitelistList;

	public static List<String> getVariableWhitelistList() {
		return variableWhitelistList;
	}

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		List<HistoryLevel> customHistoryLevels = processEngineConfiguration.getCustomHistoryLevels();
		if (customHistoryLevels == null) {
			customHistoryLevels = new ArrayList<HistoryLevel>();
			processEngineConfiguration.setCustomHistoryLevels(customHistoryLevels);
		}
		customHistoryLevels.add(AuditHistoryLevelWithWhitelist.getInstance());

		variableWhitelistList = new ArrayList<>(Arrays.asList(variableWhitelist.split("\\s*,\\s*")));
	}

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine) {
	}

}
