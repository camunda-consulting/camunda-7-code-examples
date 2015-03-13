package com.camunda.demo.underwriting.adapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
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

import com.camunda.demo.underwriting.domain.Application;

public class AssignUnderwriterAdapter implements JavaDelegate {

  public static final String DECISION_TABLE_FILE_NAME = "/UnderwriterRules.xls";
  
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    // read input data
    Application application = (Application) execution.getVariable("application");

    // call service
    callRuleEngine(application);
    List<String> employeesWithRequirement = findEmployeesWithRequirement(application);

    // write output data
    execution.setVariable("capableUnderwriters", //
        employeesWithRequirement.toString().substring(1, employeesWithRequirement.toString().length() - 1));
  }

  private List<String> findEmployeesWithRequirement(Application application) {
    try {
      List<String> employeesWithRequirements = new ArrayList<String>();

      // Get the first sheet
      Sheet sheet = getWorkbook().getSheet("Employees");

      for (int row = 1; row < sheet.getRows(); row++) {
        String name = sheet.getCell(0, row).getContents();
        String capabilities = sheet.getCell(1, row).getContents();
        boolean suitable = true;

        for (String requirement : application.getClerkRequirements()) {
          if (capabilities == null || !capabilities.contains(requirement)) {
            suitable = false;
          }
        }
        if (suitable) {
          employeesWithRequirements.add(name);
        }
      }
      return employeesWithRequirements;
    } catch (Exception e) {
      throw new RuntimeException("could not parse Excel file", e);
    }
  }

  private Workbook getWorkbook() throws Exception {
    if (workbook!=null) {
      return workbook;
    }
    
    InputStream inputWorkbook = AssignUnderwriterAdapter.class.getResourceAsStream(DECISION_TABLE_FILE_NAME);
    WorkbookSettings ws = new WorkbookSettings();
    ws.setEncoding("Cp1252");
    workbook = Workbook.getWorkbook(inputWorkbook, ws);
    return workbook;
  }

  public void callRuleEngine(Object parameter) throws Exception {
    StatelessKnowledgeSession ksession = getKnowledgeBase().newStatelessKnowledgeSession();
    ksession.execute(Arrays.asList(new Object[] { parameter }));
  }

  public static KnowledgeBase getKnowledgeBase() throws Exception {
    if (kbase != null) {
      return kbase;
    }

    DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
    dtableconfiguration.setInputType(DecisionTableInputType.XLS);
    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

    kbuilder.add(ResourceFactory.newClassPathResource(DECISION_TABLE_FILE_NAME, AssignUnderwriterAdapter.class), ResourceType.DTABLE, dtableconfiguration);

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
        new SpreadsheetCompiler().compile(AssignUnderwriterAdapter.class.getResourceAsStream(table), InputType.XLS));
  }

  /**
   * some static stuff which should be actually created differently. Well hidden down at the bottom :-)
   * 
   * Just for demo purposes.
   */
  public static boolean sysoutDrl = true;
  public static KnowledgeBase kbase;
  public static Workbook workbook;

}
