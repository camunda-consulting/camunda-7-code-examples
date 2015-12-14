package org.camunda.consulting.uploadanddeploy.UploadAndDeploy;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.DecisionDefinition;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class UndeployProcessDelegate implements JavaDelegate {
	
	
	private final Logger LOGGY = Logger.getLogger(LoggerDelegate.class.getName());
	
	@Override
	public void execute(DelegateExecution ex) throws Exception {

		String nameOfProcessToBeUndeployed = (String) ex.getVariable("processName");
		String nameOfDmnToBeUndeployed = (String) ex.getVariable("dmnName");

		if(!nameOfDmnToBeUndeployed.equals("--None--")){
			//LOGGY.info("Process Definition Seleced for undeployment");
			undeployDMN(ex, nameOfDmnToBeUndeployed);

		}
		if(!nameOfProcessToBeUndeployed.equals("--None--")){
			//LOGGY.info("DMN Definition Seleced for undeployment");
			undeployBPMN(ex, nameOfProcessToBeUndeployed);
		}		
	}
	
	private void undeployBPMN(DelegateExecution ex, String nameOfDeployment)
	{
		LOGGY.info("HI EVERYONE! TIME TO UNDEPLOY STUFF! " + nameOfDeployment);
		
		//ex.getProcessEngineServices().getRepositoryService().deleteDeployment(nameOfProcessToBeUndeployed, true);
		List<ProcessDefinition> processDefinitions = ex.getProcessEngineServices().getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(nameOfDeployment).list();
		//Deployment deployment = ex.getProcessEngineServices().getRepositoryService().createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
		
		for(ProcessDefinition pd: processDefinitions ){
			LOGGY.info("Undeploy: "+pd.toString());	
			ex.getProcessEngineServices().getRepositoryService().deleteDeployment(pd.getDeploymentId(), true);

		}

	}
	private void undeployDMN(DelegateExecution ex, String nameOfDeployment)
	{
		LOGGY.info("HI EVERYONE! TIME TO UNDEPLOY STUFF! " + nameOfDeployment);
		
		//ex.getProcessEngineServices().getRepositoryService().deleteDeployment(nameOfProcessToBeUndeployed, true);
		List<DecisionDefinition> dmnDefinitions = ex.getProcessEngineServices().getRepositoryService().createDecisionDefinitionQuery().decisionDefinitionKey(nameOfDeployment).list();
		//Deployment deployment = ex.getProcessEngineServices().getRepositoryService().createDeploymentQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
		
		for(DecisionDefinition pd: dmnDefinitions ){
			LOGGY.info("Undeploy: "+pd.toString());	
			ex.getProcessEngineServices().getRepositoryService().deleteDeployment(pd.getDeploymentId(), true);

		}

	}

}
