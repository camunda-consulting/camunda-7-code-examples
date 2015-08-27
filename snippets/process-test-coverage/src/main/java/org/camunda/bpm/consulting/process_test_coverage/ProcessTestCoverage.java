package org.camunda.bpm.consulting.process_test_coverage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class ProcessTestCoverage {

  @Deprecated public static String bpmnDir = "../classes"; // no longer needed as BPMN files are loaded from class path
	public static String targetDir = "target/process-test-coverage";
	
//	private static Map<String, Set<String>> processCoverage = new HashMap<String, Set<String>>();

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
			String html = BpmnJsReport.highlightActivities(bpmnXml, activities);

			// write report for caller 
      String reportName = caller + ".html";
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