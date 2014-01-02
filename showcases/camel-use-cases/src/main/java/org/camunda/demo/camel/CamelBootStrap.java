package org.camunda.demo.camel;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.cdi.CdiCamelContext;
import org.camunda.bpm.camel.component.CamundaBpmComponent;
import org.camunda.bpm.engine.ProcessEngine;


/**
 * This class takes care of bootstrapping the camel context in a CDI
 * environment. For documentation of the camel-cdi component check out the
 * project page at http://camel.apache.org/cdi.html and the Camel CDI BootStrap
 * project at https://github.com/cmoulliard/cdi-camel-example/
 * 
 */
@Singleton
@Startup
public class CamelBootStrap {

	private final static Logger log = Logger.getLogger(CamelBootStrap.class.getCanonicalName());

	@Inject
	private CdiCamelContext cdiCamelContext;
	
  @Inject
  private ProcessEngine processEngine;	

	@Inject
	private MyCamelRouteBuilder routeBuilder;

	@PostConstruct
	public void init() throws Exception {
    log.info("=======================");
    log.info("Initializing Camel");
	  
	  CamundaBpmComponent component = new CamundaBpmComponent(processEngine);
    component.setCamelContext(cdiCamelContext);
    cdiCamelContext.addComponent("camunda-bpm", component); 
		cdiCamelContext.addRoutes(routeBuilder);
		cdiCamelContext.start();
		
		log.info("Successfully started Camel with components: " + cdiCamelContext.getComponentNames());
		log.info("=======================");
	}

	@PreDestroy
	public void shutDown() throws Exception {
		log.info("=======================");
		log.info("Shutting down Camel");
		cdiCamelContext.stop();
		log.info("=======================");
	}

}
