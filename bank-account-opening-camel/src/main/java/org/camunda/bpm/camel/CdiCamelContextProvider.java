package org.camunda.bpm.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.cdi.CdiCamelContext;

/**
 * This class provides access to the cdi camel context for the activiti-camel
 * (fox-engine-camel) component. This is the central connection point between
 * camel and the process engine.
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 */
public class CdiCamelContextProvider implements ContextProvider {

	private CdiCamelContext cdiCamelContext;

	public CdiCamelContextProvider(CdiCamelContext cdiCamelContext) {
		this.cdiCamelContext = cdiCamelContext;
	}

	@Override
	public CamelContext getContext(String processName) {
		return cdiCamelContext;
	}

}
