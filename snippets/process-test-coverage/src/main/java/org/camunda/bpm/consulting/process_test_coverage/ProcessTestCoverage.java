package org.camunda.bpm.consulting.process_test_coverage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;

public class ProcessTestCoverage {

	public static String bpmnDir = "../classes/";
	private static String targetDir = "target/process-test-coverage";

	public static void calculate(String processInstanceId, ProcessEngine processEngine) {
		try {
			String bpmnFileName = getBpmnFileName(processInstanceId, processEngine);
			List<HistoricActivityInstance> activities = getAuditTrail(processInstanceId, processEngine);
			String html = CamundaBpmnJs.highlightActivities(bpmnDir + bpmnFileName, activities);

			// write report for caller 
			StackTraceElement caller = getCaller();
			String reportName = caller.getClassName() + "." + caller.getMethodName() + ".html";
			CamundaBpmnJs.writeToFile(targetDir, reportName, html);
			
			// write report for process
			reportName = bpmnFileName + ".html";
			File reportFile = new File(targetDir + "/" + reportName);
			if (reportFile.exists()) {
				html = CamundaBpmnJs.highlightActivities(bpmnDir + bpmnFileName, activities, reportFile);
			}
			CamundaBpmnJs.writeToFile(targetDir, reportName, html);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void calculate(ProcessEngine processEngine) {
		try {
			List<ProcessDefinition> processDefinitions = processEngine.getRepositoryService().createProcessDefinitionQuery().list();
			for (ProcessDefinition processDefinition : processDefinitions) {
				String bpmnFileName = processDefinition.getResourceName(); 
				List<HistoricActivityInstance> activities = processEngine.getHistoryService().createHistoricActivityInstanceQuery().processDefinitionId(processDefinition.getId()).list();
				String html = CamundaBpmnJs.highlightActivities(bpmnDir + bpmnFileName, activities);
				String reportName = bpmnFileName + "_.html";
				File reportFile = new File(targetDir + "/" + reportName);
				if (reportFile.exists()) {
					html = CamundaBpmnJs.highlightActivities(bpmnDir + bpmnFileName, activities, reportFile);
				}
				CamundaBpmnJs.writeToFile(targetDir, reportName, html);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static String getBpmnFileName(String processInstanceId,
			ProcessEngine processEngine) {
		HistoricProcessInstance processInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String processDefinitionId = processInstance.getProcessDefinitionId();
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		return processDefinition.getResourceName();
	}

	private static List<HistoricActivityInstance> getAuditTrail(
			String processInstanceId, ProcessEngine processEngine) {
		List<HistoricActivityInstance> activities = processEngine.getHistoryService().createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
		return activities;
	}

	private static StackTraceElement getCaller() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTraceElements[3];
		return caller;
	}

}