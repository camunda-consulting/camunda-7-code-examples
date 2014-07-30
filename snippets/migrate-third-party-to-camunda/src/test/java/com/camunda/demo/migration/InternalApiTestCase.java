package com.camunda.demo.migration;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.RuntimeServiceImpl;
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.deploy.DeploymentCache;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.runtime.ProcessInstanceStartContext;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.camunda.bpm.engine.test.mock.MockExpressionManager;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InternalApiTestCase extends ProcessEngineTestCase {

  private static ProcessEngine currentProcessEngine;

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
  }

  @Override
  protected void initializeProcessEngine() {
    if (currentProcessEngine != null) {
      processEngine = currentProcessEngine;
    } else {
      StandaloneInMemProcessEngineConfiguration configuration = new StandaloneInMemProcessEngineConfiguration();
      configuration.setExpressionManager(new MockExpressionManager());
      configuration.getProcessEnginePlugins().add(new MigrationProcessEnginePlugin());
      processEngine = configuration.buildProcessEngine();

      // init camunda-bpm-assert
      init(processEngine);

      // and remember the engine (would normally done by TestHelper)
      currentProcessEngine = processEngine;
    }
  }

  @Deployment(resources = { "example/process-b.bpmn" })
  public void testStartSubProcessInDifferentState() {
    ((RuntimeServiceImpl) processEngine.getRuntimeService()).getCommandExecutor().execute(new Command<Void>() {

      @Override
      public Void execute(CommandContext ctx) {
        try {
          ProcessDefinitionEntity processDefinition = ctx.getProcessDefinitionManager().findLatestProcessDefinitionByKey("migration-example-process-b");

          DeploymentCache deploymentCache = Context.getProcessEngineConfiguration().getDeploymentCache();            
          processDefinition = deploymentCache.findDeployedProcessDefinitionById(processDefinition.getId());
      
          ActivityImpl startActivity = processDefinition.findActivity("MIGRATION_SCENARIO_03");

          ExecutionEntity subProcessInstance = (ExecutionEntity) processDefinition.createProcessInstance("businessKey", startActivity);

          // add start context as the used element is not a StartEvent
          if (subProcessInstance.getExecutions().size() == 1) {
            Field processInstanceStartContextField = ExecutionEntity.class.getDeclaredField("processInstanceStartContext");
            processInstanceStartContextField.setAccessible(true);
            processInstanceStartContextField.set(subProcessInstance.getExecutions().get(0), new ProcessInstanceStartContext(startActivity));

            subProcessInstance.setActive(false);
          }

          // and start normally
          subProcessInstance.start(new HashMap<String, Object>());

          return null;
        } catch (Exception ex) {
          throw new ProcessEngineException("broken", ex);
        }
      }
    });
    
    ProcessInstance pi = runtimeService.createProcessInstanceQuery().singleResult();
    assertThat(pi).isStarted().isNotEnded().isWaitingAtExactly("UserTaskHandleManually").task("UserTaskHandleManually");
    complete(task());
    
    assertThat(pi).isNotEnded().isWaitingAtExactly("UserTaskDoTheWork").task("UserTaskDoTheWork");
    complete(task());
    
    assertThat(pi).isEnded();
  }

}
