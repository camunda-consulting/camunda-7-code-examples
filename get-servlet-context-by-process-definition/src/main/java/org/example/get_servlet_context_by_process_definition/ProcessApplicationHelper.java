package org.example.get_servlet_context_by_process_definition;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.ProcessApplicationService;
import org.camunda.bpm.ProcessEngineService;
import org.camunda.bpm.application.ProcessApplicationDeploymentInfo;
import org.camunda.bpm.application.ProcessApplicationInfo;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/**
 * Helper class for retrieving the servlet context path of a process application.
 *
 * @author Falko Menge (camunda)
 */
public class ProcessApplicationHelper {

	/**
	 * Retrieve the servlet context path of a process definition from a known process engine.
	 */
	public static String getServletContextPath(
			ProcessEngine processEngine, String processDefinitionId) {

		return getServletContextPath(getProcessApplicationInfo(processEngine, processDefinitionId));
	}

	/**
	 * Retrieve the servlet context path of a process definition from any process engine.
	 */
	public static String getServletContextPath(String processDefinitionId) {
		ProcessApplicationInfo processApplicationInfo = getProcessApplicationInfo(processDefinitionId);
		if (processApplicationInfo == null) {
			return null;
		}
		return processApplicationInfo.getProperties().get(
				ProcessApplicationInfo.PROP_SERVLET_CONTEXT_PATH);
	}

	/**
	 * Retrieve the servlet context path of a given process application.
	 */
	public static String getServletContextPath(ProcessApplicationInfo processApplicationInfo) {
		if (processApplicationInfo == null) {
			return null;
		}
		return processApplicationInfo.getProperties().get(ProcessApplicationInfo.PROP_SERVLET_CONTEXT_PATH);
	}

	public static ProcessApplicationInfo getProcessApplicationInfo(
			ProcessEngine processEngine, String processDefinitionId) {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().getProcessDefinition(processDefinitionId);

		// get the name of the process application that made the deployment
		String processApplicationName = processEngine.getManagementService()
				.getProcessApplicationForDeployment(processDefinition.getDeploymentId());

		if (processApplicationName == null) {
			// not a process application deployment
			return null;
		} else {
			ProcessApplicationService processApplicationService = BpmPlatform.getProcessApplicationService();
			return processApplicationService.getProcessApplicationInfo(processApplicationName);
		}
	}

	public static ProcessApplicationInfo getProcessApplicationInfo(String processDefinitionId) {
		ProcessEngineService processEngineService = BpmPlatform.getProcessEngineService();
		ProcessApplicationService processAppService = BpmPlatform.getProcessApplicationService();
		// iterate over all process applications
		for (String appName : processAppService.getProcessApplicationNames()) {
			ProcessApplicationInfo appInfo = processAppService
					.getProcessApplicationInfo(appName);
			// iterate over all deployments of a process application
			for (ProcessApplicationDeploymentInfo deploymentInfo : appInfo
					.getDeploymentInfo()) {
				long count = processEngineService
						.getProcessEngine(deploymentInfo.getProcessEngineName())
						.getRepositoryService().createProcessDefinitionQuery()
						.deploymentId(deploymentInfo.getDeploymentId())
						.processDefinitionId(processDefinitionId).count();
				if (count > 0) {
					return appInfo;
				}
			}
		}
		return null;
	}

}
