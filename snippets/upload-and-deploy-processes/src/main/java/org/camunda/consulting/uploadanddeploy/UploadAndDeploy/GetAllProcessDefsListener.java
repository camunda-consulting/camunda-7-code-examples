package org.camunda.consulting.uploadanddeploy.UploadAndDeploy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class GetAllProcessDefsListener implements TaskListener {

	@Override
	public void notify(DelegateTask execution) {
		
		//List<Deployment> deployments = execution.getProcessEngineServices().getRepositoryService().createDeploymentQuery().orderByDeploymentName().desc().list();
		
		List<ProcessDefinition> processDefinitions = execution.getProcessEngineServices().getRepositoryService().createProcessDefinitionQuery().orderByProcessDefinitionName().desc().list();
		
		Map<String, String> theProcessDefinitions = new HashMap<String, String>();
		
		for(ProcessDefinition thisProcDef : processDefinitions){
			
			String nameOfDeploy = thisProcDef.getName();
			if(nameOfDeploy == null){
				nameOfDeploy = thisProcDef.getKey();
			}
			theProcessDefinitions.put(thisProcDef.getKey(), nameOfDeploy);
			
		}
		ObjectValue processDefSerialized =
				Variables.objectValue(theProcessDefinitions).serializationDataFormat(SerializationDataFormats.JSON).create();

		execution.setVariable("theProDefs", processDefSerialized);
		

	}


}
