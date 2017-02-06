package com.camunda.example.drg.DMNChainingExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class FormatConsultantNamesListener implements TaskListener {

	@Override
	public void notify(DelegateTask exe) {
		
		List<String> suitableConsultants = (List<String>) exe.getVariable("suitableConsultants");
		
		Map<String, String> consultants = new HashMap<String, String>();
		
		for(String name : suitableConsultants){

			consultants.put(name, name);
			
		}
		
		
		
		consultants.put("--None--", "--None--");
		
		ObjectValue consultantsSerialized =
				Variables.objectValue(consultants).serializationDataFormat(SerializationDataFormats.JSON).create();

		exe.setVariable("suitableConsultantsSerialized", consultantsSerialized);


	}

}
