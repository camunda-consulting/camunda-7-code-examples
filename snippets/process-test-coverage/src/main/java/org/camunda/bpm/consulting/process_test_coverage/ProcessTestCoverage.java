package org.camunda.bpm.consulting.process_test_coverage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class ProcessTestCoverage {

  @Deprecated public static String bpmnDir = "../classes"; // no longer needed as BPMN files are loaded from class path
	public static String targetDir = "target/process-test-coverage";
	
	private static Map<String, Set<String>> processCoverage = new HashMap<String, Set<String>>();

	/**
	 * calculate coverage for this, but also add to the overall coverage of the process
	 */
  public static void calculate(ProcessInstance processInstance, ProcessEngine processEngine) {
    calculate(processInstance.getId(), processEngine, getCaller());
  }

  /**
   * calculate coverage for this, but also add to the overall coverage of the process
   */
  public static void calculate(String processInstanceId, ProcessEngine processEngine) {
    calculate(processInstanceId, processEngine, getCaller());    
  }

	public static void calculate(String processInstanceId, ProcessEngine processEngine, String caller) {
		try {
	    HistoricProcessInstance processInstance = getProcessInstance(processInstanceId, processEngine);
			String bpmnXml = getBpmnXml(processInstance, processEngine);
			List<HistoricActivityInstance> activities = getAuditTrail(processInstanceId, processEngine);

			// write report for caller 
      String reportName = caller + ".html";
      BpmnJsReport.highlightActivities(bpmnXml, activities, reportName, targetDir);
//			BpmnJsReport.writeToFile(targetDir, reportName, html);
			
			// write report for overall process
			reportName = getProcessDefinitionKey(processEngine, processInstance) + ".html";
			Set<String> coveredAcivityIds = callculateProcessCoverage(getProcessDefinitionKey(processEngine, processInstance), activities);
//			File reportFile = new File(targetDir + "/" + reportName);
//			if (reportFile.exists()) {
//				html = BpmnJsReport.highlightActivities(bpmnXml, activities, reportFile);
//			}
			
			BpmnJsReport.highlightActivities(bpmnXml, coveredAcivityIds, reportName, targetDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

  private static Set<String> callculateProcessCoverage(String processDefinitionKey, List<HistoricActivityInstance> activities) {
    Set<String> coveredActivites;
    if (!processCoverage.containsKey(processDefinitionKey)) {
      coveredActivites = new HashSet<String>();
      processCoverage.put(processDefinitionKey, coveredActivites);
    } else {
      coveredActivites = processCoverage.get(processDefinitionKey);
    }
    
    for (HistoricActivityInstance activity : activities) {
      coveredActivites.add(activity.getActivityId());
    }
    
    return coveredActivites;
  }

	/**
   * calculate overall test coverage for all processes deployed in the engine
	 */
  public static void calculate(ProcessEngine processEngine) {
		try {
			List<ProcessDefinition> processDefinitions = processEngine.getRepositoryService().createProcessDefinitionQuery().list();
			for (ProcessDefinition processDefinition : processDefinitions) {
//				String bpmnXml = FileUtils.readFileToString(new File(bpmnDir + "/" + processDefinition.getResourceName()));//getBpmnXml(processDefinition.getId(), processEngine);
			  String bpmnXml = getBpmnXml(processDefinition);
				List<HistoricActivityInstance> activities = processEngine.getHistoryService().createHistoricActivityInstanceQuery().processDefinitionId(processDefinition.getId()).list();
	      Set<String> coveredAcivityIds = callculateProcessCoverage(processDefinition.getKey(), activities);
//				String html = BpmnJsReport.highlightActivities(bpmnXml, activities);
				String reportName = processDefinition.getKey() + "_sum.html";
//				File reportFile = new File(targetDir + "/" + reportName);
//				if (reportFile.exists()) {
//					html = BpmnJsReport.highlightActivities(bpmnXml, activities, reportFile);
//				}
//				BpmnJsReport.writeToFile(targetDir, reportName, html);
	      BpmnJsReport.highlightActivities(bpmnXml, coveredAcivityIds, reportName, targetDir);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

  private static String getProcessDefinitionKey(ProcessEngine processEngine, HistoricProcessInstance processInstance) {
    return processEngine.getRepositoryService().getProcessDefinition(processInstance.getProcessDefinitionId()).getKey();
  }
  
  private static String getBpmnXml(HistoricProcessInstance processInstance, ProcessEngine processEngine) throws IOException {
    String processDefinitionId = processInstance.getProcessDefinitionId();
//  return getBpmnXml(processDefinitionId, processEngine);
    ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
//    return IOUtils.toString(processEngine.getRepositoryService().getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName()));
//    return FileUtils.readFileToString(new File(bpmnDir + "/" + processDefinition.getResourceName()));
    return getBpmnXml(processDefinition);
  }

  private static String getBpmnXml(ProcessDefinition processDefinition) throws IOException {
    InputStream inputStream = ProcessTestCoverage.class.getClassLoader().getResourceAsStream(processDefinition.getResourceName());
    return IOUtils.toString(inputStream);
  }

  private static String getBpmnXml(String processDefinitionId, ProcessEngine processEngine) throws IOException {
    return IOUtils.toString(processEngine.getRepositoryService().getProcessDiagram(processDefinitionId));
  }
  
  private static String getBpmnFileName(String processInstanceId,	ProcessEngine processEngine) {
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

	private static String getCaller() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTraceElements[3];
    String callerMethod = caller.getClassName() + "." + caller.getMethodName();
		return callerMethod;
	}

}