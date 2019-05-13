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

package com.camunda.bpm.demo.async_on_error;

import java.util.List;

import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.ManagementServiceImpl;
import org.camunda.bpm.engine.impl.ProcessInstantiationBuilderImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;


/**
 * @author Falko Menge
 */
@Named
public class AsyncOnError {

  public AsyncOnErrorActivityBehavior getAsyncOnErrorActivityBehavior(JavaDelegate delegate) {
    return new AsyncOnErrorActivityBehavior(delegate);
  }

  public static ProcessInstanceWithVariables startProcessInstance(ProcessEngine processEngine, ProcessInstantiationBuilder processInstantiationBuilder) {
    // ensure we are not participating in any open TX, which would be marked for rollback only by exceptions
    CommandExecutor commandExecutor = getCommandExecutorTxRequiresNew(processEngine);

    // start process and suspend jobs created at start
    Command<ProcessInstanceWithVariables> cmd = new StartProcessInstanceAndSuspendJobsCmd((ProcessInstantiationBuilderImpl) processInstantiationBuilder);
    ProcessInstanceWithVariables processInstance = commandExecutor.execute(cmd);

    // initialize ManagementService to enforce new TX
    ManagementServiceImpl managementService = getManagementService(commandExecutor);
    // query jobs
    List<Job> jobs = managementService.createJobQuery()
      .processInstanceId(processInstance.getId())
      .suspended()
      .list();
    for (Job job : jobs) {
      try {
        // try to run jobs synchronously
        managementService.executeJob(job.getId());
      } catch (Exception e) {
        // if it fails hand over to Job Executor to retry asynchronously
        managementService.activateJobById(job.getId());
      }
    }
    return processInstance;
  }

  private static ManagementServiceImpl getManagementService(CommandExecutor commandExecutor) {
    ManagementServiceImpl managementService = new ManagementServiceImpl();
    managementService.setCommandExecutor(commandExecutor);
    return managementService;
  }

  private static CommandExecutor getCommandExecutorTxRequiresNew(ProcessEngine processEngine) {
    return ((ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration()).getCommandExecutorTxRequiresNew();
  }

}
