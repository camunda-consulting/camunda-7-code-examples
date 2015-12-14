package org.camunda.consulting.uploadanddeploy.UploadAndDeploy;

import java.io.File;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DetermineFileTypeDelegate implements JavaDelegate {

	private final Logger LOGGY = Logger.getLogger(LoggerDelegate.class.getName());
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		
		
		boolean isBpmnFile = false;
		boolean isDmnFile = false;
		boolean isUnsupportedFile = false;
		
		byte[] bpmnFile = (byte[]) execution.getVariable("BPMNFile");
		byte[] dmnFile = (byte[]) execution.getVariable("DMNFile");
		
		
	
		if(bpmnFile != null && bpmnFile.length>0)
		{
			isBpmnFile = true;
			LOGGY.info("BPMN 2.0 file found to have been uploaded");
			
		}if(dmnFile != null && dmnFile.length>0){
			
			isDmnFile = true;
			LOGGY.info("DMN 1.1 file found to have been uploaded");
			
		}if(!isBpmnFile && !isDmnFile) {
			isUnsupportedFile = true;
			LOGGY.info("Unsupported file type found to have been uploaded");
		}
		
		execution.setVariable("isDmnFile", isDmnFile);
		execution.setVariable("isBpmnFile", isBpmnFile);
		execution.setVariable("isUnsupportedFile", isUnsupportedFile);
		
		//add default values for deplyment
		execution.setVariable("deployBPMN", false);
		execution.setVariable("deployDMN", false);
	}
	
	private String getFileType(File theFile){
		
		String fileType = "";

		int i = theFile.getAbsolutePath().lastIndexOf('.');
		int p = Math.max(theFile.getAbsolutePath().lastIndexOf('/'), theFile.getAbsolutePath().lastIndexOf('\\'));

		if (i > p) {
			fileType = theFile.getAbsolutePath().substring(i+1);
		}
		
		return fileType;
		
		
	}

}
