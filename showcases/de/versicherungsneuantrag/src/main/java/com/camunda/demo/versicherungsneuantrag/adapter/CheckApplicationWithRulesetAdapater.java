package com.camunda.demo.versicherungsneuantrag.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import com.camunda.demo.versicherungsneuantrag.ProcessVariables;
import com.camunda.demo.versicherungsneuantrag.model.Neuantrag;

public class CheckApplicationWithRulesetAdapater implements JavaDelegate {

  public static final String DECISION_TABLE_FILE_NAME = "/Antragspruefung.xls";

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    // read input data
    Neuantrag application = (Neuantrag) execution.getVariable(ProcessVariables.VAR_NAME_neuantrag);

    // call DRM rule set, currently only "Risikoprüfung" is implemented
    List<String> risks = new ArrayList<String>();
    List<String> statusRedRisks = new ArrayList<String>();
    callRuleEngine(application, risks, statusRedRisks);

    // write output data
    execution.setVariable(ProcessVariables.VAR_NAME_risks,  Variables.objectValue(risks).serializationDataFormat(SerializationDataFormats.JSON));
    execution.setVariable(ProcessVariables.VAR_NAME_statusRedRisks, Variables.objectValue(statusRedRisks).serializationDataFormat(SerializationDataFormats.JSON));
    
    execution.setVariable("test", "hallo");
  }

  public void callRuleEngine(Object parameter, List<String> risks, List<String> statusRedRisks) throws Exception {
    StatelessKnowledgeSession ksession = getKnowledgeBase().newStatelessKnowledgeSession();

    ksession.setGlobal("risks", risks);
    ksession.setGlobal("statusRedRisks", statusRedRisks);
    ksession.execute(Arrays.asList(new Object[] { parameter }));
  }

  public static KnowledgeBase getKnowledgeBase() throws Exception {
    if (kbase != null) {
      return kbase;
    }

    DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
    dtableconfiguration.setInputType(DecisionTableInputType.XLS);
    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

    kbuilder.add(
         ResourceFactory.newClassPathResource(DECISION_TABLE_FILE_NAME, CheckApplicationWithRulesetAdapater.class), 
         ResourceType.DTABLE,
         dtableconfiguration);
    
//    kbuilder.add(
//        ResourceFactory.newClassPathResource("/rules.drl", CheckApplicationWithRulesetAdapater.class), 
//        ResourceType.DRL);    

    if (sysoutDrl) {
      sysoutDrl(DECISION_TABLE_FILE_NAME);
    }

    if (kbuilder.hasErrors()) {
      StringBuffer message = new StringBuffer();
      KnowledgeBuilderErrors errors = kbuilder.getErrors();
      for (KnowledgeBuilderError error : errors) {
        message.append("\n").append(error.getMessage());
      }
      throw new Exception("Errors parsing decision table: " + message.toString());
    }

    KnowledgeBase newKBase = KnowledgeBaseFactory.newKnowledgeBase();
    newKBase.addKnowledgePackages(kbuilder.getKnowledgePackages());
    kbase = newKBase;
    return kbase;
  }

  private static void sysoutDrl(String table) {
    System.out.println( //
        new SpreadsheetCompiler().compile(CheckApplicationWithRulesetAdapater.class.getResourceAsStream(table), InputType.XLS));
  }

  /**
   * some static stuff which should be actually created differently. Well hidden
   * down at the bottom :-)
   * 
   * Just for demo purposes.
   */
  public static boolean sysoutDrl = false;
  public static KnowledgeBase kbase;  

}
