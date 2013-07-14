package org.camunda.bpm.camel;

import java.util.Arrays;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;


/**
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
