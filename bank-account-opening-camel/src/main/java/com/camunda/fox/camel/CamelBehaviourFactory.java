package com.camunda.fox.camel;

import java.util.Arrays;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.camunda.bpm.camel.CamelBehaviour;
import org.camunda.bpm.camel.ContextProvider;

/**
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 *
 */
public class CamelBehaviourFactory {

	@Inject
	CdiCamelContextProvider cdiCamelContextProvider;
	
	@Produces
	public CamelBehaviour camelBehaviour() {
		CamelBehaviour camelBehaviour = new CamelBehaviour(
				Arrays.asList(new ContextProvider[] { cdiCamelContextProvider }));
		return camelBehaviour;
	}

}
