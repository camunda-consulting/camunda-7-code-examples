package org.camunda.bpm.demo.orderconfirmation.bean;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.demo.orderconfirmation.model.DiscountRuleEntry;

@Named
@ConversationScoped
public class RuleEntryEdit implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private RuleEntryDAO droolsBean;

  @Inject
  private Conversation conversation;

  private DiscountRuleEntry ruleEntry;
  private Long entryId;
  private boolean isNew = true;

  @Produces
  @Named
  @ConversationScoped
  public DiscountRuleEntry getRuleEntry() {
    if (ruleEntry == null) {
      load();
    }
    return ruleEntry;
  }

  private void load() {
    if (entryId == null) {
      ruleEntry = new DiscountRuleEntry();
    } else {
      ruleEntry = droolsBean.findDiscountRuleEntry(entryId);
    }
  }

  public void save() {
    droolsBean.save(ruleEntry);
  }

  public void cancel() {
    // this ends the current unit of work
    if (!conversation.isTransient()) {
      conversation.end();
    }
  }

  public void update() {
    droolsBean.update(ruleEntry);
    // this ends the current unit of work
    if (!conversation.isTransient()) {
      conversation.end();
    }
  }

  public void setEntryId(Long entryId) {
    this.entryId = entryId;
    isNew = false;
    // this begins a unit of work
    if (conversation.isTransient()) {
      conversation.begin();
    }
  }

  public Long getEntryId() {
    return entryId;
  }

  public boolean isNewEntry() {
    return isNew;
  }
}
