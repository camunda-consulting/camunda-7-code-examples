package com.camunda.demo.tenant.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.application.ProcessApplicationReference;
import org.camunda.bpm.container.RuntimeContainerDelegate;
import org.camunda.bpm.container.impl.jmx.JmxRuntimeContainerDelegate;
import org.camunda.bpm.container.impl.jmx.JmxRuntimeContainerDelegate.ServiceTypes;
import org.camunda.bpm.container.impl.jmx.kernel.MBeanServiceContainer;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.application.ProcessApplicationManager;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.FoxFailedJobParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.jobexecutor.FoxFailedJobCommandFactory;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.camunda.bpm.engine.impl.persistence.StrongUuidGenerator;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.repository.ProcessDefinition;

public class TenantManager {
  /**
   * This is configured in your Container (e.g. bpm-platform.xml in Tomcat)
   */
  private static final String jobAcquisitionName = "default";

  private static List<String> tenants = new ArrayList<String>();

  static {
    tenants.add("tenant1");
    tenants.add("tenant2");
    tenants.add("tenant3");
  }

  public List<String> getTenants() {
    return tenants;
  }

  public void startProcessEngines() {
    for (String tenant : tenants) {
      // startProcessEngineUsingStep(tenant);
      ProcessEngine processEngine = startProcessEngine(tenant);
      deployDefaultProcessesToEngine(processEngine);
    }

    System.out.println("\n\n\nStarted process engines for tenants - now running:\n" + BpmPlatform.getProcessEngineService().getProcessEngineNames() + "\n\n");
  }

  public void stopProcessEngines() {
    for (String tenant : tenants) {
      stopProcessEngine(tenant);
    }
  }

  private void stopProcessEngine(String tenant) {
    getServiceContainer().stopService(ServiceTypes.PROCESS_ENGINE, tenant);
  }

  public void addTenant(String name) {
    tenants.add(name);
    ProcessEngine processEngine = startProcessEngine(name);
    deployDefaultProcessesToEngine(processEngine);

    System.out.println("\n\n\nStarted new process engine for tenants - now running:\n" + BpmPlatform.getProcessEngineService().getProcessEngineNames() + "\n\n");
  }

  public static MBeanServiceContainer getServiceContainer() {
    return ((JmxRuntimeContainerDelegate) RuntimeContainerDelegate.INSTANCE.get()).getServiceContainer();
  }

  // private void startProcessEngineUsingStep(String tenant) {
  // ProcessEngineXmlImpl processEngineXmlImpl = new ProcessEngineXmlImpl();
  // processEngineXmlImpl.setJobAcquisitionName("default");
  // processEngineXmlImpl.setName(tenant);
  // processEngineXmlImpl.setDefault(false);
  // processEngineXmlImpl.setConfigurationClass(StandaloneProcessEngineConfiguration.class.getName());
  // processEngineXmlImpl.setDatasource("jdbc/ProcessEngine-" + tenant);
  // /**
  // * Todo - add:
  // *
  // * <properties> <property name="history">full</property> <property
  // * name="databaseSchemaUpdate">true</property> <property
  // * name="authorizationEnabled">true</property> <property
  // * name="jobExecutorDeploymentAware">true</property> </properties>
  // *
  // *
  // * <plugins> <!-- plugin enabling Process Application event listener support
  // * --> <plugin> <class>org.camunda.bpm.application.impl.event.
  // * ProcessApplicationEventListenerPlugin</class> </plugin> </plugins>
  // */
  // processEngineXmlImpl.setProperties(new HashMap<String, String>());
  // processEngineXmlImpl.setPlugins(new ArrayList<ProcessEnginePluginXml>());
  //
  // ((JmxRuntimeContainerDelegate)
  // RuntimeContainerDelegate.INSTANCE.get()).getServiceContainer() //
  // .createDeploymentOperation("deploy tenant process engine into the container")
  // //
  // .addStep(new StartProcessEngineStep(processEngineXmlImpl)) //
  // .execute();
  // }

  private ProcessEngine startProcessEngine(String tenant) {
    ProcessEngineConfigurationImpl configuration = new StandaloneProcessEngineConfiguration();
    configuration.setIdGenerator(new StrongUuidGenerator());

    configureCustomRetryStrategy(configuration);

    configuration.setProcessEngineName(tenant);

    configuration.setJdbcDriver("org.h2.Driver");
    configuration.setJdbcUrl("jdbc:h2:./camunda-h2-dbs/process-engine-" + tenant + ";MVCC=TRUE;TRACE_LEVEL_FILE=0;DB_CLOSE_ON_EXIT=FALSE");
    configuration.setDatabaseSchemaUpdate(StandaloneProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    // configuration.setDataSourceJndiName("jdbc/ProcessEngine-"+tenant);
    configuration.setJobExecutorDeploymentAware(true);
    configuration.setHistory(StandaloneProcessEngineConfiguration.HISTORY_FULL);

    // instantiate plugins:
    // * <plugins> <!-- plugin enabling Process Application event listener
    // support
    // * --> <plugin> <class>org.camunda.bpm.application.impl.event.
    // * ProcessApplicationEventListenerPlugin</class> </plugin> </plugins>
    // */
    // configurePlugins(configuration, processEngineXml, classLoader);

    JobExecutor jobExecutor = getServiceContainer().getServiceValue(ServiceTypes.JOB_EXECUTOR, jobAcquisitionName);
    if (jobExecutor == null) {
      throw new ProcessEngineException("Cannot find referenced job executor with name '" + jobAcquisitionName + "'");
    }

    // set JobExecutor on process engine
    configuration.setJobExecutor(jobExecutor);

    ProcessEngine processEngine = configuration.buildProcessEngine();

    // start the process engine inside the container.
    RuntimeContainerDelegate.INSTANCE.get().registerProcessEngine(processEngine);

    return processEngine;
  }

  protected void configureCustomRetryStrategy(ProcessEngineConfigurationImpl configurationImpl) {
    List<BpmnParseListener> customPostBPMNParseListeners = configurationImpl.getCustomPostBPMNParseListeners();
    if (customPostBPMNParseListeners == null) {
      customPostBPMNParseListeners = new ArrayList<BpmnParseListener>();
      configurationImpl.setCustomPostBPMNParseListeners(customPostBPMNParseListeners);
    }
    customPostBPMNParseListeners.add(new FoxFailedJobParseListener());
    configurationImpl.setFailedJobCommandFactory(new FoxFailedJobCommandFactory());
  }

  public static void deployDefaultProcessesToEngine(ProcessEngine processEngine) {
    ProcessEngine defaultProcessEngine = BpmPlatform.getDefaultProcessEngine();
    ProcessEngineConfigurationImpl defaultProcessEngineConfiguration = ((ProcessEngineImpl) defaultProcessEngine).getProcessEngineConfiguration();
    ProcessApplicationManager defaultProcessApplicationManager = defaultProcessEngineConfiguration.getProcessApplicationManager();

    Map<String, DeploymentBuilder> deployments = new HashMap<String, DeploymentBuilder>();

    List<ProcessDefinition> processDefinitions = defaultProcessEngine.getRepositoryService().createProcessDefinitionQuery().list();
    for (ProcessDefinition processDefinition : processDefinitions) {
      String deploymentId = processDefinition.getDeploymentId();

      if (!deployments.containsKey(deploymentId)) {
        deployments.put(deploymentId, processEngine.getRepositoryService().createDeployment());
      }

      deployments.get(deploymentId).addInputStream(//
          processDefinition.getResourceName(), //
          defaultProcessEngine.getRepositoryService().getResourceAsStream(deploymentId, processDefinition.getResourceName()));
    }

    for (Entry<String, DeploymentBuilder> entry : deployments.entrySet()) {
      // do the deployment
      Deployment deployment = entry.getValue().enableDuplicateFiltering().deploy();

      // and registration
      String defaultExistingDeploymentId = entry.getKey();
      String newDeploymentId = deployment.getId();

      ProcessApplicationReference processApplication = defaultProcessApplicationManager.getProcessApplicationForDeployment(defaultExistingDeploymentId);
      if (processApplication != null) {
        processEngine.getManagementService().registerProcessApplication(newDeploymentId, processApplication);
      } else {
        // no Process Application is deployed - ignore
      }

      // TODO: Resume old versions!
    }

  }

}
