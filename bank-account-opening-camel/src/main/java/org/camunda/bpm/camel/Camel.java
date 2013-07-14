package org.camunda.bpm.camel;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;

/**
 * This class just acts as a proxy that routes calls from the process engine to
 * camel.
 * 
 * The @Named annotation makes this bean available in the CDI context under the
 * name "camel". The camunda CDI integration allows you to
 * call CDI beans in your process definitions. So when ever a service
 * task contains the delegate expression "#{camel}", this bean will be invoked
 * and route the call to Camel so that a route can be executed.
 */
@Named
public class Camel implements ActivityBehavior {

	@Inject
	private CamelBehaviour camelBehaviour;

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		this.camelBehaviour.execute(execution);
	}

}
