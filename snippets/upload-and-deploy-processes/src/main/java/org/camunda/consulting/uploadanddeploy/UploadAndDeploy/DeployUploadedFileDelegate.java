package org.camunda.consulting.uploadanddeploy.UploadAndDeploy;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class DeployUploadedFileDelegate implements JavaDelegate {
	
	
	private final Logger LOGGY = Logger.getLogger(LoggerDelegate.class.getName());
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
//		String bpmnFileName = "bpmnFile.bpmn";
//		//File tempBpmnFile = File.createTempFile(bpmnFileName, ".bpmn"); 
//		
//		String fileSparator = System.getProperty("file.separator");
//		String tempDir = System.getProperty("java.io.tmpdir"); 
//		File tempBpmnFile = new File(tempDir+"BPMNUPloadTemp"+fileSparator+bpmnFileName);
//		 File parent = tempBpmnFile.getParentFile();
//		    if(!parent.exists())
//		    	parent.mkdirs();
//		
		//File bpmnFile = (File)execution.getVariable("BPMNFile");
		
		boolean deployBPMN = (Boolean) execution.getVariable("deployBPMN");
		boolean deployDMN = (Boolean) execution.getVariable("deployDMN");
		
		if(deployBPMN){
			byte[] bpmnFile  = (byte[])execution.getVariable("BPMNFile");
			deploy(bpmnFile, execution, "bpmnFile.bpmn");
		}
		
		if(deployDMN){
			byte[] dmnFile  = (byte[])execution.getVariable("DMNFile");
			deploy(dmnFile, execution, "dmnFile.dmn");
		}

		
	}
	
	private void deploy(byte[] byteArray, DelegateExecution execution, String resourceName){
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
		
		
		String deploymentID = execution.getProcessEngineServices().getRepositoryService().createDeployment().addInputStream(resourceName, byteArrayInputStream).deploy().getId();
		LOGGY.info("File " + resourceName + " was deployed: " + deploymentID);

	}
	
	private void writeFile(ByteArrayInputStream byteArrayInputStream, File outputFile) throws IOException{
		
		InputStream in = byteArrayInputStream;
		OutputStream out = new FileOutputStream(outputFile);
		
		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
		    out.write(buf, 0, len);
		}
		in.close();
		out.close();


		
	}

}
