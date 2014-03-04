package org.camunda.bpm.consulting.process_test_coverage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.history.HistoricActivityInstance;

public class CamundaBpmnJs {

	private static final String PLACEHOLDER_ANNOTATIONS = "        //YOUR ANNOTATIONS GO HERE";
	private static final String PLACEHOLDER_BPMN_FILE = "/PATH/TO/YOUR/BPMN/FILE";

	public static String highlightActivities(String bpmnFileName,
			List<HistoricActivityInstance> activities) throws IOException {
		String javaScript = generateJavaScriptAnnotations(activities);
		String html = generateHtml(javaScript, bpmnFileName);
		return html;
	}

	public static String highlightActivities(String bpmnFileName,
			List<HistoricActivityInstance> activities, File templateFile) throws IOException {
		String javaScript = generateJavaScriptAnnotations(activities);
		
		String html = FileUtils.readFileToString(templateFile);
		return injectIntoHtmlTemplate(javaScript, bpmnFileName, html);
	}


	public static String generateHtml(String javaScript,
			String bpmnFileName) throws IOException {
		String html = IOUtils.toString(ProcessTestCoverage.class.getClassLoader().getResourceAsStream("camunda-bpmn.js-template.html"));
		return injectIntoHtmlTemplate(javaScript, bpmnFileName, html);
	}

	private static String injectIntoHtmlTemplate(String javaScript,
			String bpmnFileName, String html) {
		html = html.replaceAll(PLACEHOLDER_BPMN_FILE, bpmnFileName);
		html = html.replaceAll(PLACEHOLDER_ANNOTATIONS, javaScript + PLACEHOLDER_ANNOTATIONS);
		return html;
	}

	public static String generateJavaScriptAnnotations(
			List<HistoricActivityInstance> activities) {
		StringBuilder javaScript = new StringBuilder();
		for (HistoricActivityInstance activity : activities) {
			javaScript.append("        bpmn.annotation('" + activity.getActivityId() + "').addClasses(['highlight']);\n");
		}
		return javaScript.toString();
	}

	public static void writeToFile(String targetDir, String fileName,	String html) throws IOException {
		FileUtils.forceMkdir(new File(targetDir));
		FileUtils.writeStringToFile(new File(targetDir + "/" + fileName), html);
	}

}
