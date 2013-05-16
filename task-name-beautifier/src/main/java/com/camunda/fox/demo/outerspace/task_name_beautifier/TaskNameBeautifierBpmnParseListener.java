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

package com.camunda.fox.demo.outerspace.task_name_beautifier;

import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.bpmn.helper.ClassDelegate;
import org.activiti.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.activiti.engine.impl.bpmn.parser.BpmnParseListener;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ScopeImpl;
import org.activiti.engine.impl.util.xml.Element;
import org.activiti.engine.task.Task;


/**
 * {@link BpmnParseListener} that enhances all user tasks with a 
 * {@link TaskListener} that removes hyphens from {@link Task} names.
 *
 * @author Falko Menge (camunda)
 */
public class TaskNameBeautifierBpmnParseListener extends AbstractBpmnParseListener {
  
  @Override
  public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {
    ActivityBehavior behavior = activity.getActivityBehavior();
    if (behavior instanceof UserTaskActivityBehavior) {
      ((UserTaskActivityBehavior) behavior).getTaskDefinition().addTaskListener(TaskListener.EVENTNAME_CREATE,  new ClassDelegate(TaskNameBeautifier.class, null));
    }
  }

}
