package org.camunda.bpm.demo.orderconfirmation.config;

import org.camunda.bpm.demo.orderconfirmation.bean.RuleEntryDAO;
import org.camunda.bpm.demo.orderconfirmation.model.DiscountRuleEntry;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

/*
 * Helper class to create an initial set of discount rules
 */
@Singleton
@Startup
public class DiscountRuleEntryConfig {

  private final static Logger log = Logger.getLogger(DiscountRuleEntryConfig.class.getCanonicalName());

  @Inject
  private RuleEntryDAO rulesDAO;

  /*
   * If no discount rules are present in the database, we create some initial ones
   */
  @PostConstruct
  public void initializeDiscountRules() {
    List<DiscountRuleEntry> rules = rulesDAO.findAllDiscountRuleEntries();
    if ((rules == null) || (rules.size() == 0)) {
      log.info("Creating initial sample discount rules: ...");
      rulesDAO.save(new DiscountRuleEntry("Small orders", 100, 500, 5));
      rulesDAO.save(new DiscountRuleEntry("Mediun orders", 501, 1000, 7));
      rulesDAO.save(new DiscountRuleEntry("Big orders", 1001, 5000, 10));
    }
  }
}
