package com.camunda.fox.mule;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.camunda.bpm.engine.RuntimeService;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextFactory;

@Singleton
@Startup
public class MuleBootStrap {

	private static final Logger log = Logger.getLogger(MuleBootStrap.class
			.getCanonicalName());

	private MuleContext muleContext;

	@Produces
	public MuleContext getMuleContext() {
		return muleContext;
	}

	@Inject
	RuntimeService runtimeService;
	
	@PostConstruct
	public void init() throws MuleException {

		log.info("=======================");
		log.info("initializing mule");
		log.info("=======================");
		DefaultMuleContextFactory muleContextFactory = new DefaultMuleContextFactory();
		SpringXmlConfigurationBuilder configBuilder = new SpringXmlConfigurationBuilder("mule-config.xml");
		muleContext = muleContextFactory.createMuleContext(configBuilder);
		log.info("=======================");
		log.info("starting mule context");
		log.info("=======================");
		muleContext.start();
		log.info("=======================");
		log.info("successfully initialized mule and started mule context");
		log.info("=======================");

	}

	@PreDestroy
	public void shutDown() throws MuleException {
		log.info("=======================");
		log.info("Shutting down mule context");
		log.info("=======================");
		muleContext.stop();
		muleContext.dispose();
		log.info("=======================");
		log.info("Successfully shut down mule context");
		log.info("=======================");
	}

}
