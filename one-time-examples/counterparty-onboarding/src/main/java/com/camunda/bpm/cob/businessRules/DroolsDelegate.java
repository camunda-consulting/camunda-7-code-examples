package com.camunda.bpm.cob.businessRules;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class DroolsDelegate implements JavaDelegate {
	
	private static final Logger log = Logger.getLogger(DroolsDelegate.class.getName());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Object countryName = execution.getVariable("country");
		String decisionTablePath = null; 
		String factClassName = null;
		String factResultVariableName = null;
		
		CamundaProperties camundaProperties = execution.getBpmnModelElementInstance().getExtensionElements().getElementsQuery()
		        .filterByType(CamundaProperties.class).singleResult();

		Collection<CamundaProperty> properties = camundaProperties.getCamundaProperties();
		for (CamundaProperty property : properties) {
			String propertyName = property.getCamundaName();
			String propertyValue = property.getCamundaValue();
			if (propertyName.equals("decisionTable")) {
				decisionTablePath = propertyValue;
			} else if (propertyName.equals("factClass")) {
				factClassName = propertyValue;
			} else if (propertyName.equals("factResultVariableName")) {
				factResultVariableName = propertyValue;
			}
		} 
		
		Class<?> clazz = Class.forName(factClassName);
		Object fact = clazz.newInstance();
		Field setName = clazz.getDeclaredField("name");
		setName.setAccessible(true);
		setName.set(fact, countryName);
		
		StatefulKnowledgeSession workingMemory = createWorkingMemory(decisionTablePath);
		workingMemory.insert(fact);
		int numberOfRules = workingMemory.fireAllRules();
		
		log.info("Number of Rules fired from the decisionTable " + decisionTablePath + ": " + numberOfRules);
		
		execution.setVariable(factResultVariableName, fact);

//		Field hint = clazz.getDeclaredField("hint");
//		hint.setAccessible(true);
//		String hintFromRules = (String) hint.get(country);
//		execution.setVariable("highRiskCountryHint", hintFromRules);
//
//		Field highRisk = clazz.getDeclaredField("highRisk");
//		highRisk.setAccessible(true); 
//		boolean isHighRiskCountry = highRisk.getBoolean(country);
//		if (isHighRiskCountry) {
//			execution.setVariable("isHighRiskCountry", "yes");
//		} else {
//			execution.setVariable("isHighRiskCountry", "no");
//		}
	}
	
	private StatefulKnowledgeSession createWorkingMemory(String decisionTablePath) {
		DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		dtableconfiguration.setInputType( DecisionTableInputType.XLS);
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add( 
				ResourceFactory.newClassPathResource(decisionTablePath, getClass()),
				ResourceType.DTABLE,
			    dtableconfiguration );
		return kbuilder.newKnowledgeBase().newStatefulKnowledgeSession();
	}

}
