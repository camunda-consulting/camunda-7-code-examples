package org.camunda.bpm.camel;

import java.util.Arrays;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.QueueConnectionFactory;

import org.apache.camel.component.cdi.CdiCamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.camunda.demo.camel.OpenAccountRoute;


/**
 * This class takes care of bootstrapping the camel context in a CDI
 * environment. For documentation of the camel-cdi component check out the
 * project page at http://camel.apache.org/cdi.html and the Camel CDI BootStrap
 * project at https://github.com/cmoulliard/cdi-camel-example/
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 */
@Singleton
@Startup
public class BootStrap {

	private final static Logger log = Logger.getLogger(BootStrap.class.getCanonicalName());

	@Inject
	private CdiCamelContext cdiCamelContext;

	@Inject
	private OpenAccountRoute openAccountRoute;

	private CdiCamelContextProvider cdiCamelContextProvider;
	
	@Produces
	@Named("camel")
	public CamelBehaviour produceCamelBehaviour() {
	   return new CamelBehaviour(
	           Arrays.asList(new ContextProvider[] { getCdiCamelContextProvider() }));
	}
	
	@Produces
	public CdiCamelContextProvider getCdiCamelContextProvider() {
		if(cdiCamelContextProvider == null) {
			cdiCamelContextProvider = new CdiCamelContextProvider(cdiCamelContext); 
		}
		return cdiCamelContextProvider;
	}

	@Resource(mappedName = "java:/JmsXA")
	private QueueConnectionFactory queueConnectionFactory;

	@PostConstruct
	public void init() throws Exception {

		// inject the JMS connection factory into camel's JMS component
		JmsComponent jmsComponent = cdiCamelContext.getComponent("jms", JmsComponent.class);
		jmsComponent.setConnectionFactory(queueConnectionFactory);

		// add routes to camel context
		log.info("=======================");
		log.info("adding OpenAccountRoute to camel context");
		log.info("=======================");
		cdiCamelContext.addRoutes(openAccountRoute);
		log.info("=======================");
		log.info("Camel Components: " + cdiCamelContext.getComponentNames());
		log.info("=======================");
		log.info("=======================");
		log.info("starting camel context");
		log.info("=======================");
		// cdiCamelContext.setTracing(true);
		cdiCamelContext.start();
		log.info("=======================");
		log.info("successfully created camel context and started open account route!");
		log.info("=======================");
	}

	@PreDestroy
	public void shutDown() throws Exception {
		log.info("=======================");
		log.info("Shutting down camel context");
		log.info("=======================");
		cdiCamelContext.stop();
		log.info("=======================");
		log.info("Successfully shut down camel context");
		log.info("=======================");
	}

}
