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

import java.io.PrintWriter;
import java.io.StringWriter;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.bpmn.behavior.TaskActivityBehavior;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.jobexecutor.AsyncContinuationJobHandler;
import org.camunda.bpm.engine.impl.persistence.entity.MessageEntity;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;


/**
 * @author Falko Menge
 */
public class AsyncOnErrorActivityBehavior extends TaskActivityBehavior implements ActivityBehavior {

  protected JavaDelegate delegate;

  public AsyncOnErrorActivityBehavior(JavaDelegate delegate) {
    this.delegate = delegate;
  }

  public void execute(ActivityExecution execution) throws Exception {
    if (!Boolean.TRUE.equals(execution.getVariable(getStateVariableName(execution)))) {
      // First try
      try {
        executeBusinessLogic(execution);
        leave(execution);
      } catch (BpmnError e) {
        throw e;
      } catch (Exception e) {
        execution.setVariableLocal(getStateVariableName(execution), true);
        createAsynchronousContinuationJob(execution, e);
      }
    } else {
      // Retries
      executeBusinessLogic(execution);
      execution.setVariableLocal(getStateVariableName(execution), null);
      leave(execution);
    }
  }
  
  protected void executeBusinessLogic(ActivityExecution execution) throws Exception {
    delegate.execute(execution);
  }

  protected String getStateVariableName(ActivityExecution execution) {
    return "AsyncOnError_FIRST_EXCUTION_OF_" + execution.getActivity().getId() + "_FAILED";
  }

  public static void createAsynchronousContinuationJob(DelegateExecution execution, Exception exception) {
    MessageEntity message = new MessageEntity();
    message.setProcessInstanceId(execution.getProcessInstanceId());
    message.setProcessDefinitionId(execution.getProcessDefinitionId());
    message.setExecutionId(execution.getId());
    message.setExclusive(true);
    message.setJobHandlerType(AsyncContinuationJobHandler.TYPE);
    message.setExceptionMessage(exception.getMessage());
    message.setExceptionStacktrace(getExceptionStacktrace(exception));
    
    Context
      .getCommandContext()
      .getJobManager()
      .send(message);
  }

  public static String getExceptionStacktrace(Exception exception) {
    StringWriter stringWriter = new StringWriter();
    exception.printStackTrace(new PrintWriter(stringWriter));
    return stringWriter.toString();
  }

}
