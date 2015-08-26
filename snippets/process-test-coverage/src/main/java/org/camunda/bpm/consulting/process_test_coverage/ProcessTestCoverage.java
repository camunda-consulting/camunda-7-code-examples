package org.camunda.bpm.consulting.process_test_coverage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;

public class ProcessTestCoverage {

  public static String bpmnDir = "../classes";
	public static String targetDir = "target/process-test-coverage";
	
//	private static Map<String, Set<String>> processCoverage = new HashMap<String, Set<String>>();

	public static void calculate(String processInstanceId, ProcessEngine processEngine) {
		try {
	    HistoricProcessInstance processInstance = getProcessInstance(processInstanceId, processEngine);
			String bpmnXml = getBpmnXml(processInstance, processEngine);
			List<HistoricActivityInstance> activities = getAuditTrail(processInstanceId, processEngine);
			String html = BpmnJsReport.highlightActivities(bpmnXml, activities);

			// write report for caller 
			StackTraceElement caller = getCaller();
			String reportName = caller.getClassName() + "." + caller.getMethodName() + ".html";
			BpmnJsReport.writeToFile(targetDir, reportName, html);
			
			// write report for process
			reportName = getProcessDefinitionKey(processEngine, processInstance) + ".html";
//			callculateProcessCoverage(getProcessDefinitionKey(processEngine, processInstance), activities);
			File reportFile = new File(targetDir + "/" + reportName);
			if (reportFile.exists()) {
				html = BpmnJsReport.highlightActivities(bpmnXml, activities, reportFile);
			}
			BpmnJsReport.writeToFile(targetDir, reportName, html);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//  private static void callculateProcessCoverage(String processDefinitionKey, List<HistoricActivityInstance> activities) {
//    
//    if (!processCoverage.containsKey(processDefinitionKey)) {
//      processCoverage.put(processDefinitionKey, new HashSet<String>());
//    }
//    
//  }

  public static void calculate(ProcessEngine processEngine) {
		try {
			List<ProcessDefinition> processDefinitions = processEngine.getRepositoryService().createProcessDefinitionQuery().list();
			for (ProcessDefinition processDefinition : processDefinitions) {
//				String bpmnXml = FileUtils.readFileToString(new File(bpmnDir + "/" + processDefinition.getResourceName()));//getBpmnXml(processDefinition.getId(), processEngine);
			  String bpmnXml = IOUtils.toString(ProcessTestCoverage.class.getClassLoader().getResourceAsStream(processDefinition.getResourceName()));
				List<HistoricActivityInstance> activities = processEngine.getHistoryService().createHistoricActivityInstanceQuery().processDefinitionId(processDefinition.getId()).list();
				String html = BpmnJsReport.highlightActivities(bpmnXml, activities);
				String reportName = processDefinition.getKey() + "_sum.html";
				File reportFile = new File(targetDir + "/" + reportName);
				if (reportFile.exists()) {
					html = BpmnJsReport.highlightActivities(bpmnXml, activities, reportFile);
				}
				BpmnJsReport.writeToFile(targetDir, reportName, html);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

  protected static String getProcessDefinitionKey(ProcessEngine processEngine, HistoricProcessInstance processInstance) {
    return processEngine.getRepositoryService().getProcessDefinition(processInstance.getProcessDefinitionId()).getKey();
  }
  
  protected static String getBpmnXml(HistoricProcessInstance processInstance, ProcessEngine processEngine) throws IOException {
    String processDefinitionId = processInstance.getProcessDefinitionId();
//  return getBpmnXml(processDefinitionId, processEngine);
    ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
//    return IOUtils.toString(processEngine.getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName()));
//    return FileUtils.readFileToString(new File(bpmnDir + "/" + processDefinition.getResourceName()));
    return IOUtils.toString(ProcessTestCoverage.class.getClassLoader().getResourceAsStream(processDefinition.getResourceName()));
  }

  protected static String getBpmnXml(String processDefinitionId, ProcessEngine processEngine) throws IOException {
    return IOUtils.toString(processEngine.getRepositoryService().getProcessDiagram(processDefinitionId));
  }
  
	protected static String getBpmnFileName(String processInstanceId,	ProcessEngine processEngine) {
		HistoricProcessInstance processInstance = getProcessInstance(processInstanceId, processEngine);
		String processDefinitionId = processInstance.getProcessDefinitionId();
    ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    return processDefinition.getResourceName();
	}

  private static HistoricProcessInstance getProcessInstance(String processInstanceId, ProcessEngine processEngine) {
    return processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
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