package org.camunda.bpm.consulting.process_test_coverage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.camunda.bpm.engine.history.HistoricActivityInstance;

public class BpmnJsReport {

  protected static final String PLACEHOLDER_ANNOTATIONS = "          //YOUR ANNOTATIONS GO HERE";
  protected static final String PLACEHOLDER_BPMN_XML = "YOUR BPMN XML CONTENT";

  public static void highlightActivities(String bpmnXml, List<HistoricActivityInstance> activities, String reportName, String targetDir) throws IOException {
    String javaScript = generateJavaScriptAnnotations(activities);
    String html = generateHtml(javaScript, bpmnXml);
    writeToFile(targetDir, reportName, html);
  }

  public static void highlightActivities(String bpmnXml, Set<String> coveredAcivityIds, String reportName, String targetDir) throws IOException {
    String javaScript = generateJavaScriptAnnotations(coveredAcivityIds);
    String html = generateHtml(javaScript, bpmnXml);
    writeToFile(targetDir, reportName, html);
  }

  protected static String generateHtml(String javaScript, String bpmnXml) throws IOException {
		String html = IOUtils.toString(ProcessTestCoverage.class.getClassLoader().getResourceAsStream("bpmn.js-report-template.html"));
		return injectIntoHtmlTemplate(javaScript, bpmnXml, html);
	}

  protected static String injectIntoHtmlTemplate(String javaScript, String bpmnXml, String html) {
		html = html.replace(PLACEHOLDER_BPMN_XML, StringEscapeUtils.escapeJavaScript(bpmnXml));
		html = html.replaceAll(PLACEHOLDER_ANNOTATIONS, javaScript + PLACEHOLDER_ANNOTATIONS);
		return html;
	}

  protected static String generateJavaScriptAnnotations(Set<String> acivityIds) {
    StringBuilder javaScript = new StringBuilder();
    for (String activityId : acivityIds) {
      javaScript.append("          canvas.addMarker('" + activityId + "', 'highlight');\n");
    }
    return javaScript.toString();
  }

  protected static String generateJavaScriptAnnotations(List<HistoricActivityInstance> activities) {
		StringBuilder javaScript = new StringBuilder();
		for (HistoricActivityInstance activity : activities) {
			javaScript.append("          canvas.addMarker('" + activity.getActivityId() + "', 'highlight');\n");
		}
		return javaScript.toString();
	}

  protected static void writeToFile(String targetDir, String fileName,	String html) throws IOException {
		prepareTargetDir(targetDir);
		FileUtils.writeStringToFile(new File(targetDir + "/" + fileName), html);
	}

  protected static void prepareTargetDir(String targetDir) throws IOException {
    File targetDirectory = new File(targetDir);
    FileUtils.forceMkdir(targetDirectory);
    if (!new File(targetDir + "/" + "bower_components").exists()) {
      extractBpmnJs(targetDirectory);
    }
  }

  protected static void extractBpmnJs(File targetDirectory) {
    InputStream bpmnJsSeed = BpmnJsReport.class.getClassLoader().getResourceAsStream("bpmn-js-seed-master.zip");
    ZipInputStream zin = new ZipInputStream(bpmnJsSeed);
    try {
      ZipEntry entry = null;
      while ((entry = zin.getNextEntry()) != null) {
        String entryName = entry.getName().replace("bpmn-js-seed-master" + "/", "");
        if (entryName.startsWith("bower_components")) {
          File entryDestination = new File(targetDirectory,  entryName);
          if (entry.isDirectory())
              entryDestination.mkdirs();
          else {
              entryDestination.getParentFile().mkdirs();
              OutputStream out = new FileOutputStream(entryDestination);
              IOUtils.copy(zin, out);
              out.close();
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      IOUtils.closeQuietly(zin);
    }
  }

}
