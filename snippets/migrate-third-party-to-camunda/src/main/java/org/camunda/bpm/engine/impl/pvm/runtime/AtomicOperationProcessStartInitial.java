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
package org.camunda.bpm.engine.impl.pvm.runtime;

import java.util.List;

import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.pvm.runtime.AtomicOperationActivityInstanceStart;
import org.camunda.bpm.engine.impl.pvm.runtime.InterpretableExecution;
import org.camunda.bpm.engine.impl.pvm.runtime.ProcessInstanceStartContext;

/**
 * Needed to patch the original version because of bug - and cannot overwrite any AtomicOperation in the interface (public static final)
 */
public class AtomicOperationProcessStartInitial extends AtomicOperationActivityInstanceStart {

  @Override
  protected ScopeImpl getScope(InterpretableExecution execution) {
    return (ScopeImpl) execution.getActivity();
  }

  @Override
  protected String getEventName() {
    return org.camunda.bpm.engine.impl.pvm.PvmEvent.EVENTNAME_START;
  }

  @Override
  protected void eventNotificationsCompleted(InterpretableExecution execution) {
    
    super.eventNotificationsCompleted(execution);
    
    ActivityImpl activity = (ActivityImpl) execution.getActivity();
    ProcessDefinitionImpl processDefinition = execution.getProcessDefinition();
    ProcessInstanceStartContext processInstanceStartContext = execution.getProcessInstanceStartContext();
    if (activity==processInstanceStartContext.getInitial()) {
      
      processInstanceStartContext.initialStarted(execution);
      
      execution.disposeProcessInstanceStartContext();
      execution.performOperation(ACTIVITY_EXECUTE);

    } else {
      List<ActivityImpl> initialActivityStack = processDefinition.getInitialActivityStack(processInstanceStartContext.getInitial());
//      int index = initialActivityStack.indexOf(activity);
      // use the activity "at the end" of the hierarchy:
      activity = initialActivityStack.get(initialActivityStack.size()-1);

      // and search for the correct execution to set the Activity to  
      InterpretableExecution executionToUse = execution; 
      while (executionToUse.getActivity().isScope()) { //if (activity.isScope()) {        
        executionToUse.setActive(false); // Deactivate since we jump to a node further down the hierarchy
        executionToUse = (InterpretableExecution) executionToUse.getExecutions().get(0);
      }
      executionToUse.setActivity(activity);
      executionToUse.performOperation(PROCESS_START_INITIAL);
    }
  }

  @Override
  public String getCanonicalName() {
    return "process-start-initial";
  }

}
