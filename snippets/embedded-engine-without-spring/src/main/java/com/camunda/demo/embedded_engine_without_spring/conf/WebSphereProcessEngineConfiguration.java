package com.camunda.demo.embedded_engine_without_spring.conf;

import org.camunda.bpm.engine.cdi.CdiJtaProcessEngineConfiguration;
import com.ibm.tx.jta.TransactionManagerFactory;

public class WebSphereProcessEngineConfiguration extends CdiJtaProcessEngineConfiguration {

  @Override
  protected void initTransactionManager() {
    // This is way IBM says to work with the Transaction manager
//    WebSphereUowTransactionManager webSphereUowTransactionManager = new WebSphereUowTransactionManager();
//    webSphereUowTransactionManager.afterPropertiesSet();
//    transactionManager = webSphereUowTransactionManager;
    
    // the camunda BPM Platform on websphere uses the spring helper to access the transaction manager
    // But we want to avoid spring
    
    // This is webSphere internal and not officially supported
    // see https://stackoverflow.com/a/37187719
    transactionManager = TransactionManagerFactory.getTransactionManager();
  }

}
