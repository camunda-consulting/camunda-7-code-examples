package org.camunda.bpm.example.acm;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.camunda.bpm.engine.acm.CaseDefinition;
import org.camunda.bpm.engine.acm.CaseHandlingEventListener;
import org.camunda.bpm.engine.acm.CaseInstanceInfo;
import org.camunda.bpm.engine.acm.event.CaseEvent;
import org.camunda.bpm.engine.acm.impl.CaseInstanceEntity;
import org.camunda.bpm.engine.acm.impl.CaseServiceImpl;
import org.camunda.bpm.engine.impl.TaskServiceImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutorImpl;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

@Startup
@Singleton
public class DroolsCaseHandlingRuleManagerImpl implements CaseHandlingEventListener {

    protected static String RULE_BASE_EVENT = "rules-event";

    private final Map<String, KnowledgeBase> knowledgeBases = new HashMap<String, KnowledgeBase>();

    public DroolsCaseHandlingRuleManagerImpl() {
        knowledgeBases.put(RULE_BASE_EVENT, buildKnowledgeBase("creditApplication.drl"));
    }

    @PostConstruct
    protected void setUp() {
        System.out.println("############## set rule handler");
        CaseDefinition.eventListener = new DroolsCaseHandlingRuleManagerImpl();
    }

    protected KnowledgeBase buildKnowledgeBase(final String ruleFile) {
        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        try {
            kbuilder.add(ResourceFactory.newClassPathResource(ruleFile, getClass()), ResourceType.DRL);
        } catch (final Exception ex) {
            throw new RuntimeException("Could not parse rule base from 'rules.drl': " + ex.getMessage(), ex);
        }

        if (kbuilder.getErrors().size() > 0) {
            final StringBuffer buf = new StringBuffer();
            for (final KnowledgeBuilderError error : kbuilder.getErrors()) {
                buf.append(error).append("; ");
            }
            throw new RuntimeException("Rulebase contain errors: " + buf.toString());
        }
        return kbuilder.newKnowledgeBase();
    }

    protected StatefulKnowledgeSession createWorkingMemory(final String ruleBaseName) {
        return knowledgeBases.get(ruleBaseName).newStatefulKnowledgeSession();
    }

    @Override
    public void eventOccured(final CaseInstanceEntity caseInstance, final CaseInstanceInfo status, final CaseDefinition caseDefinition, final CaseEvent event) {
        final StatefulKnowledgeSession wm = createWorkingMemory(RULE_BASE_EVENT);
        prepareWorkingMemory(wm, caseInstance);

        // insert all facts
        wm.insert(caseInstance);
        wm.insert(caseDefinition);
        wm.insert(event);
        wm.insert(status);

        // do it
        wm.fireAllRules();

        // clean up
        wm.dispose();
    }


    private void prepareWorkingMemory(final StatefulKnowledgeSession wm, final CaseInstanceEntity caseInstance) {
        final CaseServiceImpl caseService = new CaseServiceImpl();
        caseService.setCommandExecutor(new CommandExecutorImpl());
        final TaskServiceImpl taskService = new TaskServiceImpl();
        taskService.setCommandExecutor(new CommandExecutorImpl());

        wm.setGlobal("caseService", caseService);
        wm.setGlobal("taskService", taskService);
        // wm.setGlobal("variables", new ProcessVariableAccessor(caseInstance));
    }

}
