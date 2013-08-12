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

package org.camunda.bpm.camel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultExchange;
import org.camunda.bpm.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;

/**
 * copied from camunda fox ee 6.2.3 / activiti-camel module. It is not yet decided 
 * if we have a comparable module in camunda BPM or do integration differently.
 * 
 * Let us know your oppinion in the <a href="http://www.camunda.org/community/forum.html">forum</a>
 * 
 * @author ruecker
 */
public class CamelBehaviour extends BpmnActivityBehavior implements ActivityBehavior {

  private Collection<ContextProvider> contextProviders;

  public CamelBehaviour(Collection<ContextProvider> camelContext) {
    this.contextProviders = camelContext;
  }

  public void execute(ActivityExecution execution) throws Exception {
//    getContext(execution).getEndpoint("").
    
    ActivitiEndpoint ae = createEndpoint(execution);
    Exchange ex = createExchange(execution, ae);
    ae.process(ex);
    execution.setVariables(ExchangeUtils.prepareVariables(ex, ae));
    performDefaultOutgoingBehavior(execution);

  }


  private ActivitiEndpoint createEndpoint(ActivityExecution execution) {
    String uri = "camunda://" + getProcessName(execution) + ":" + execution.getActivity().getId();
    return getEndpoint(getContext(execution), uri);
  }

  private ActivitiEndpoint getEndpoint(CamelContext ctx, String key) {
    for (Endpoint e : ctx.getEndpoints()) {
      if (e.getEndpointKey().equals(key) && (e instanceof ActivitiEndpoint)) {
        return (ActivitiEndpoint) e;
      }
    }
    throw new RuntimeException("Activiti endpoint not defined for " + key);    
  }

  private CamelContext getContext(ActivityExecution execution) {
    String processName = getProcessName(execution);
    String names = "";
    for (ContextProvider provider : contextProviders) {
      CamelContext ctx = provider.getContext(processName);
      if (ctx != null) {
        return ctx;
      }
    }
    throw new RuntimeException("Could not find camel context for " + processName + " names are " + names);
  }


  private Exchange createExchange(ActivityExecution activityExecution, ActivitiEndpoint endpoint) {
    Exchange ex = new DefaultExchange(getContext(activityExecution));
    Map<String, Object> variables = activityExecution.getVariables();
    if (endpoint.isCopyVariablesToProperties()) {
      for (Map.Entry<String, Object> var : variables.entrySet()) {
        ex.setProperty(var.getKey(), var.getValue());
      }
    }
    if (endpoint.isCopyVariablesToBody()) {
      ex.getIn().setBody(new HashMap<String,Object>(variables));
    }
    return ex;
  }

  private String getProcessName(ActivityExecution execution) {
    String id = execution.getActivity().getProcessDefinition().getId();
    return id.substring(0, id.indexOf(":"));

  }

}
