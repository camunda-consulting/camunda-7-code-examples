package org.camunda.consulting.uploadanddeploy.UploadAndDeploy;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class UndeployProcessDelegate implements JavaDelegate {
	
	
	private final Logger LOGGY = Logger.getLogger(LoggerDelegate.class.getName());
	
	@Override
	public void execute(DelegateExecution ex) throws Exception {

		String nameOfProcessToBeUndeployed = (String) ex.getVariable("processName");
		
		LOGGY.info("HI EVERYONE! TIME TO UNDEPLOY STUFF! " + nameOfProcessToBeUndeployed);
		
		//ex.getProcessEngineServices().getRepositoryService().deleteDeployment(nameOfProcessToBeUndeployed, true);
		List<ProcessDefinition> processDefinitions = ex.getProcessEngineServices().getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(nameOfProcessToBeUndeployed).list();
		//Deployment deployment = ex.getProcessEngineServices().getRepositoryService().createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
		
		for(ProcessDefinition pd: processDefinitions ){
			LOGGY.info("Undeploy: "+pd.toString());	
			ex.getProcessEngineServices().getRepositoryService().deleteDeployment(pd.getDeploymentId(), true);

		}
		
		
			
		
		
		
		
	}

}
