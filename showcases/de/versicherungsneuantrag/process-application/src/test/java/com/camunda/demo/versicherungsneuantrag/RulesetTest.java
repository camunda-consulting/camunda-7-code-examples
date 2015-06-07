package com.camunda.demo.versicherungsneuantrag;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.camunda.demo.versicherungsneuantrag.adapter.CheckApplicationWithRulesetAdapater;
import com.camunda.demo.versicherungsneuantrag.model.Neuantrag;

public class RulesetTest {

  @Test
  public void testMatureDriverVwGolf() throws Exception {
    List<String> risks = new ArrayList<String>();
    List<String> statusRedRisks = new ArrayList<String>();
    Neuantrag neuantrag = DemoData.createNeuantrag(40, false, "VW", "Golf IV");
    new CheckApplicationWithRulesetAdapater().callRuleEngine(neuantrag, risks, statusRedRisks);
    assertEquals(0, risks.size());
    assertEquals(0, statusRedRisks.size());
  };

  @Test
  public void testTooYoungTooFastDriver() throws Exception {
    List<String> risks = new ArrayList<String>();
    List<String> statusRedRisks = new ArrayList<String>();
    Neuantrag neuantrag = DemoData.createNeuantrag(20, false, "Porsche", "911");
    new CheckApplicationWithRulesetAdapater().callRuleEngine(neuantrag, risks, statusRedRisks);
    assertEquals(2, risks.size());
    assertEquals(1, statusRedRisks.size());
  };

  @Test
  public void testMatureDriverX3() throws Exception {
    List<String> risks = new ArrayList<String>();
    List<String> statusRedRisks = new ArrayList<String>();
    Neuantrag neuantrag = DemoData.createNeuantrag(40, false, "BMW", "X3");
    new CheckApplicationWithRulesetAdapater().callRuleEngine(neuantrag, risks, statusRedRisks);
    assertEquals(1, risks.size());
    assertEquals(0, statusRedRisks.size());
  };

}
