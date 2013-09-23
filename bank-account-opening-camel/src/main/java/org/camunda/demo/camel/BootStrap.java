package org.camunda.demo.camel;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.cdi.CdiCamelContext;
import org.apache.camel.component.seda.SedaComponent;
import org.camunda.bpm.camel.component.CamundaBpmComponent;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.demo.camel.route.OpenAccountRoute;


/**
 * This class takes care of bootstrapping the camel context in a CDI
 * environment. For documentation of the camel-cdi component check out the
 * project page at http://camel.apache.org/cdi.html and the Camel CDI BootStrap
 * project at https://github.com/cmoulliard/cdi-camel-example/
 * 
 */
@Singleton
@Startup
public class BootStrap {

	private final static Logger log = Logger.getLogger(BootStrap.class.getCanonicalName());

	@Inject
	private CdiCamelContext cdiCamelContext;
	
  @Inject
  private ProcessEngine processEngine;	

	@Inject
	private OpenAccountRoute openAccountRoute;

	// For simplicity JMS was removed from the example - but you can easily re-add it
//	@Resource(mappedName = "java:/JmsXA")
//	private QueueConnectionFactory queueConnectionFactory;

	@PostConstruct
	public void init() throws Exception {
    log.info("=======================");
    log.info("Adding camunda BPM Component to Camel");
    log.info("=======================");
	  
	  CamundaBpmComponent component = new CamundaBpmComponent(processEngine);
    component.setCamelContext(cdiCamelContext);
    cdiCamelContext.addComponent("camunda-bpm", component);

    // For simplicity JMS was removed from the example - but you can easily re-add it:
		// inject the JMS connection factory into camel's JMS component
//		JmsComponent jmsComponent = cdiCamelContext.getComponent("jms", JmsComponent.class);
//		jmsComponent.setConnectionFactory(queueConnectionFactory);

    // Instead we exchanged the JMS component by replacing it with Camel seda component for in memory queues
    cdiCamelContext.addComponent("jms", cdiCamelContext.getComponent("seda", SedaComponent.class));

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
