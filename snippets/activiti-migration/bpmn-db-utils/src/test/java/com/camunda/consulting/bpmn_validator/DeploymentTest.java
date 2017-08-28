package com.camunda.consulting.bpmn_validator;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Fail;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.camunda.consulting.bpmn_db_exporter.DbExporter;

@RunWith(Parameterized.class)
public class DeploymentTest {

	private static final String BPMN_MODEL_DIR = DbExporter.TARGET_DIR;
	private String bpmnFile;
	private static ProcessEngine engine;

	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> getBpmnFiles() {
		List<String> testCases = new ArrayList<>();
		File parentDir = new File(BPMN_MODEL_DIR);
		FilenameFilter filenameFilter = (dir, name) -> name.matches(".*\\.bpmn");
		// FilenameFilter filenameFilter = (dir, name) -> name.matches( "\\d.*\\.bpmn"
		// );
		for (File file : parentDir.listFiles(filenameFilter)) {
			testCases.add(file.getName());
		}
		Collections.sort(testCases);

		List<Object[]> parameters = new ArrayList<>();
		for (String String : testCases) {
			parameters.add(new Object[] { String });
		}
		
		engine = new StandaloneInMemProcessEngineConfiguration().buildProcessEngine();
		
		return parameters;
	}

	public DeploymentTest(String bpmnFile) {
		this.bpmnFile = bpmnFile;
	}

	@Test
	public void test() {

		Path file = Paths.get(BPMN_MODEL_DIR + File.separator + bpmnFile);
		InputStream fileInputStream = null;

		try {
			fileInputStream = Files.newInputStream(file);
			engine.getRepositoryService().createDeployment().addInputStream(bpmnFile, fileInputStream).name(bpmnFile).deploy();

		} catch (IOException e) {
			e.printStackTrace();
			Fail.fail(e.getMessage());
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
