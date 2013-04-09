package org.camunda.bpm.demo.orderconfirmation.bean;

import java.io.InputStream;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.template.ObjectDataCompiler;

import org.camunda.bpm.demo.orderconfirmation.model.DiscountRuleEntry;

@Named
@ApplicationScoped
public class DroolsRulebaseBean {

  private String droolsRulebaseAsDrl;

  private KnowledgeBase knowledgeBase;

  @Inject
  private RuleEntryDAO rulesDAO;

  public void updateRulebase() {
    generateRules();
    createKnowledgebase();
  }

  private void generateRules() {
    List<DiscountRuleEntry> ruleEntries = rulesDAO.findAllDiscountRuleEntries();

    InputStream is = this.getClass().getResourceAsStream("/test.drt");
    droolsRulebaseAsDrl = new ObjectDataCompiler().compile(ruleEntries, is);
  }

  public void createKnowledgebase() {
    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

    try {
      kbuilder.add(ResourceFactory.newByteArrayResource(droolsRulebaseAsDrl.getBytes("UTF-8")), ResourceType.DRL);
    } catch (Exception ex) {
      throw new IllegalStateException("Could not parse rule base \n" + droolsRulebaseAsDrl, ex);
    }

    if (kbuilder.getErrors().size() > 0) {
      StringBuffer buf = new StringBuffer();
      for (KnowledgeBuilderError error : kbuilder.getErrors()) {
        buf.append(error).append("; ");
      }
      throw new IllegalStateException("Rulebase is invalid: " + buf.toString() + "\n" + droolsRulebaseAsDrl);
    }
    knowledgeBase = kbuilder.newKnowledgeBase();
  }

  public StatefulKnowledgeSession createNewWorkingMemory() {
    return getKnowledgeBase().newStatefulKnowledgeSession();
  }

  private KnowledgeBase getKnowledgeBase() {
    if (knowledgeBase == null) {
      updateRulebase();
    }
    return knowledgeBase;
  }

  public String getDroolsRulebaseAsDrl() {
    return droolsRulebaseAsDrl;
  }

  public String getDroolsRulebaseDrlHtml() {
    if (droolsRulebaseAsDrl == null) {
      return "";
    } else {
      return droolsRulebaseAsDrl.replaceAll("  ", "&nbsp;").replaceAll("\n", "<br/>");
    }
  }
}
