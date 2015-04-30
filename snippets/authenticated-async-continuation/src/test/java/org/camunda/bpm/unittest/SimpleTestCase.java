/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.unittest;

import java.util.Map;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

import org.junit.Rule;
import org.junit.Test;

/**
 * @author Daniel Meyer
 * @author Martin Schimak
 */
public class SimpleTestCase {
  
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
    LogFactory.useJdkLogging();
  }

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  @Test
  @Deployment(resources = {"testProcess.bpmn"})
  public void shouldExecuteProcess() {
    TestAuthenticationBean testAuthenticationBean = new TestAuthenticationBean();
    Mocks.register("testAuthenticationBean", testAuthenticationBean);
    
    ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) rule.getProcessEngine().getProcessEngineConfiguration();
    
    Map<String, JobHandler> jobHandlers = processEngineConfiguration.getJobHandlers();
    AuthenticatedAsyncContinuationJobHandler authenticatedAsyncContinuationJobHandler = new AuthenticatedAsyncContinuationJobHandler();
    jobHandlers.put(authenticatedAsyncContinuationJobHandler.getType(), authenticatedAsyncContinuationJobHandler);
    
    identityService().setAuthenticatedUserId("demo");
    // Given we create a new process instance
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("testProcess", withVariables("startUserId", "demo"));
    identityService().clearAuthentication();
    
    // Then it should be active
    assertThat(processInstance).isActive();
    // And it should be the only instance
    assertThat(processInstanceQuery().count()).isEqualTo(1);
    
    execute(job());
    
    assertThat(processInstance).variables().containsEntry("asyncUserId", "demo");
    
    execute(job());

    // Then the process instance should be ended
    assertThat(processInstance).isEnded();
  }

  @Test
  @Deployment(resources = {"testProcess.bpmn"})
  public void shouldExecuteProcessWithoutAuthentication() {
    TestAuthenticationBean testAuthenticationBean = new TestAuthenticationBean();
    Mocks.register("testAuthenticationBean", testAuthenticationBean);
    
    ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) rule.getProcessEngine().getProcessEngineConfiguration();
    Map<String, JobHandler> jobHandlers = processEngineConfiguration.getJobHandlers();
    AuthenticatedAsyncContinuationJobHandler authenticatedAsyncContinuationJobHandler = new AuthenticatedAsyncContinuationJobHandler();
    jobHandlers.put(authenticatedAsyncContinuationJobHandler.getType(), authenticatedAsyncContinuationJobHandler);
    
    // Given we create a new process instance
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("testProcess");
    
    // Then it should be active
    assertThat(processInstance).isActive();
    // And it should be the only instance
    assertThat(processInstanceQuery().count()).isEqualTo(1);
    
    execute(job());
    
    assertThat(processInstance).variables().containsEntry("asyncUserId", null);
    
    execute(job());

    // Then the process instance should be ended
    assertThat(processInstance).isEnded();
  }

}
