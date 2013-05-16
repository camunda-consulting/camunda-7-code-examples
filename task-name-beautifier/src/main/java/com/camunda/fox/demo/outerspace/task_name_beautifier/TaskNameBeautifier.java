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

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;


/**
 * {@link TaskListener} that removes hyphens from {@link Task} names.
 *
 * @author Falko Menge (camunda)
 */
public class TaskNameBeautifier implements TaskListener {

  @Override
  public void notify(DelegateTask task) {
    String name = task.getName();
    String beautifiedName = beautifyTaskName(name);
    task.setName(beautifiedName);
  }

  public String beautifyTaskName(String name) {
    String beautifiedName = name.replaceAll("(\\w)-\\s*([a-z])", "$1$2");
    return beautifiedName;
  }

}
