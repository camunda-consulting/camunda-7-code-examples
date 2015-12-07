package org.camunda.demo.dmnTest.RugbyRules;

import java.util.Vector;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class SetupTeamColours implements ExecutionListener {

	 private final Logger LOGGY = Logger.getLogger(LoggerDelegate.class.getName());
		
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		
		LOGGY.info("Started Setting up colours");
		
		Vector<String> teamColours = new Vector<String>();
		teamColours.add("Green");
		teamColours.add("Red");
		teamColours.add("White");
		teamColours.add("Black");
		teamColours.add("Yellow");
		teamColours.add("Green");
		teamColours.add("Dark Blue");
		teamColours.add("Light Blue");
		
		ObjectValue teamColoursSerialized =
				Variables.objectValue(teamColours).serializationDataFormat(SerializationDataFormats.JSON).create();

		
		arg0.setVariable("teamColours", teamColoursSerialized);
		
//		arg0.getProcessEngineServices().getHistoryService().createHistoricActivityInstanceQuery().p
		
		
	}

}
