package org.camunda.bpm.example.twitter;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class DroolsDelegate implements JavaDelegate {

  private Expression drlFile;
  private Expression facts;

  private HashMap<String, Object> factMap = new HashMap<String, Object>();

  public void execute(DelegateExecution execution) throws Exception {
    String drlFileName = (String) drlFile.getValue(execution);

    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    kbuilder.add(ResourceFactory.newClassPathResource(drlFileName, getClass()), ResourceType.DRL);
    if (kbuilder.hasErrors()) {
      throw new RuntimeException("Error in drools: " + kbuilder.getErrors().toString());
    }
    KnowledgeBase knowledgeBase = kbuilder.newKnowledgeBase();
    StatefulKnowledgeSession workingMemory = knowledgeBase.newStatefulKnowledgeSession();

    if (facts != null) {
      // Very easy implementation to fetch the parameters :-) Must be improved
      // for real live
      StringTokenizer st = new StringTokenizer((String) facts.getValue(execution), ",");
      while (st.hasMoreTokens()) {
        String variableName = st.nextToken().trim();

        // TODO: Add retrieval of variable and insert it into the working memory
        Object variable = execution.getVariable(variableName);
        workingMemory.insert(variable);

        // remember the variable to update it later in the process context
        factMap.put(variableName, variable);
      }
    }

    workingMemory.fireAllRules();

    // update variables
    for (Entry<String, Object> factEntry : factMap.entrySet()) {
      // update variable in the process variables
      execution.setVariable(factEntry.getKey(), factEntry.getValue());
    }
  }
}