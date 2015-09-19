package com.camunda.demo.underwriting.adapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionOutput;
import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.impl.DmnEngineConfigurationImpl;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;

import com.camunda.demo.underwriting.domain.Application;

public class AssignUnderwriterAdapter implements JavaDelegate {

  public static final String DECISION_TABLE_FILE_NAME = "/UnderwriterRules.xls";
  
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    // read input data
    Application application = (Application) execution.getVariable("application");

    // call service
    List<String> requiredSkills = takeDecision(application);    
    List<String> employeesWithRequirement = findEmployeesWithRequirement(requiredSkills);

    // write output data
    execution.setVariable("capableUnderwriters", //
        employeesWithRequirement.toString().substring(1, employeesWithRequirement.toString().length() - 1));
  }

  private List<String> takeDecision(Application application) {
    InputStream dmnResourceAsStream = AssignUnderwriterAdapter.class.getResourceAsStream("/required-skills.dmn");
    
    DmnEngine dmnEngine = new DmnEngineConfigurationImpl().buildEngine();
    DmnDecision decision = dmnEngine.parseDecision(dmnResourceAsStream);
    DmnDecisionResult decisionResult = dmnEngine.evaluate(
        decision, 
        Variables.createVariables().putValue("application", application));
    
    ArrayList<String> requiredSkills = new ArrayList<String>();
    for (Iterator iterator = decisionResult.iterator(); iterator.hasNext();) {
      DmnDecisionOutput result = (DmnDecisionOutput) iterator.next();
      requiredSkills.add((String)result.getValue());
    }
    return requiredSkills;
    
  }

  private List<String> findEmployeesWithRequirement(List<String> requiredSkills) {
    try {
      List<String> employeesWithRequirements = new ArrayList<String>();

      // Get the first sheet
      Sheet sheet = getWorkbook().getSheet("Employees");

      for (int row = 1; row < sheet.getRows(); row++) {
        String name = sheet.getCell(0, row).getContents();
        String capabilities = sheet.getCell(1, row).getContents();
        boolean suitable = true;

        for (String requirement : requiredSkills) {
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

  private static Workbook workbook;
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

}
