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
		byte[] byteArray = (byte[])execution.getVariable("BPMNFile");
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
		
		//writeFile(byteArrayInputStream, tempBpmnFile);
		
	    //IOUtils.copy(byteArrayInputStream, new FileOutputStream(tempBpmnFile));
		
		//LOGGY.info("File was called: " + tempBpmnFile.getAbsolutePath());
		
		String deploymentID = execution.getProcessEngineServices().getRepositoryService().createDeployment().addInputStream("foo.bpmn", byteArrayInputStream).deploy().getId();
		LOGGY.info("File was deployed: " + deploymentID);
		
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
