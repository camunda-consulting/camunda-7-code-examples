package com.camunda.consulting;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.camunda.bpm.engine.rest.impl.CamundaRestResources;
import org.camunda.bpm.engine.rest.impl.ProcessDefinitionRestServiceImpl;
@ApplicationPath("/")
public class MyApplication extends Application {    
  @Override 
  public Set<Class<?>> getClasses() { 
    Set<Class<?>> classes = new HashSet<Class<?>>();              
    // add your own classes       
    
    // add camunda engine rest classes that you need    
    /*
     * If you want all camunda engine-rest classes, just add
     * classes.addAll(CamundaRestResources.getResourceClasses());
     * OR
     * classes.add(ProcessDefinitionRestServiceImpl.class);
     * Then you can remove the CustomProcessEngineRestServiceImpl
     */
    //
    classes.add(CustomProcessEngineRestServiceImpl.class);
     // mandatory manual
    //classes.add(org.camunda.bpm.engine.rest.mapper.JacksonConfigurator.class);
    //classes.add(org.camunda.bpm.engine.rest.exception.RestExceptionHandler.class);
    // classes.add(org.camunda.bpm.engine.rest.exception.ProcessEngineExceptionHandler.class);
    // mandatory all
    classes.addAll(CamundaRestResources.getConfigurationClasses());
    return classes;
  }
}

