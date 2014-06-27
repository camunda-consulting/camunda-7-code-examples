package org.camunda.bpm.cockpit.ige.plugin.test;

import static org.junit.Assert.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*; 

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.camunda.bpm.cockpit.db.QueryParameters;
import org.camunda.bpm.cockpit.ige.plugin.db.IGEBusinessPluginDto;
import org.camunda.bpm.cockpit.plugin.test.AbstractCockpitPluginTest;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Test;

public class TestIGEPluginQuery extends AbstractCockpitPluginTest {
  
  private static Logger log = Logger.getLogger(TestIGEPluginQuery.class.getName());
  
  

  @Test
  @Deployment(resources="org/camunda/bpm/cockpit/ige/plugin/test/process1.bpmn")
  public void testPluginQuery() {
    try {
      for (int i = 1; i < 3; i++) {
        Map<String, Object> procVars = initProcessVariables(i);
        log.info("Procvars: " + procVars);
        ProcessInstance pi = runtimeService().startProcessInstanceByKey("Process_1", procVars);        
        assertNotNull(pi);
      }
//      Map<String, Object> vars = runtimeService().getVariables(pi.getProcessInstanceId());
//      assertEquals("COO.2237.9903.1.1009370", vars.get("ANTRAG_ID"));
      
      QueryParameters<IGEBusinessPluginDto> parameters = new QueryParameters<IGEBusinessPluginDto>();
      IGEBusinessPluginDto igeBusinessDataQueryDto = new IGEBusinessPluginDto();
      igeBusinessDataQueryDto.setTitleNumber("1193300");
      
      parameters.setParameter(igeBusinessDataQueryDto);
      List<IGEBusinessPluginDto> businessData = getQueryService().executeQuery(
          "cockpit.ige.plugin.igeBusinessCockpitQuery", 
          parameters);

      assertNotNull("Query nicht ausgeführt", businessData);
      assertTrue("Keine Suchergebnisse gefunden", businessData.size() > 0);
      log.info("Abfrageergebnis: ");
      for (IGEBusinessPluginDto businessLine : businessData) {
        log.info("Zeile: " + businessLine);
      }
      assertEquals("Liste zu kurz/lang", 1, businessData.size());
      
      igeBusinessDataQueryDto.setTitleNumber("1193302");
      businessData = getQueryService().executeQuery(
          "cockpit.ige.plugin.igeBusinessCockpitQuery", 
          parameters);

      assertNotNull("Query nicht ausgeführt", businessData);
      assertTrue("Keine Suchergebnisse gefunden", businessData.size() > 0);
      log.info("Abfrageergebnis: ");
      for (IGEBusinessPluginDto businessLine : businessData) {
        log.info("Zeile: " + businessLine);
      }
      
      igeBusinessDataQueryDto.setTitleNumber(null);
      igeBusinessDataQueryDto.setRequestNumber("15");
      businessData = getQueryService().executeQuery(
          "cockpit.ige.plugin.igeBusinessCockpitQuery", 
          parameters);

      assertNotNull("Query nicht ausgeführt", businessData);
      assertTrue("Keine Suchergebnisse gefunden", businessData.size() > 0);
      log.info("Abfrageergebnis: ");
      for (IGEBusinessPluginDto businessLine : businessData) {
        log.info("Zeile: " + businessLine);
      }
      
    } catch (IOException e) {
      Assert.fail(e.getMessage());    
    }
  }
  
  public Map<String, Object> initProcessVariables(int lineNumber) throws IOException {
    Map<String, Object> procVars = new HashMap<String, Object>();
    
    InputStream testdatenExcelStream = ClassLoader.getSystemClassLoader().getResourceAsStream("xmlTestData/testvariablen.xls");
    
    HSSFWorkbook excelFile = new HSSFWorkbook(testdatenExcelStream);
    HSSFSheet excelSheet = excelFile.getSheet("Testdaten");
    Row headerRow = excelSheet.getRow(0);
    Iterator<Cell> headerIterator = headerRow.cellIterator();
    List<String> headers = new ArrayList<String>();
    while (headerIterator.hasNext()) {
      Cell headerCell = (Cell) headerIterator.next();
      headers.add(headerCell.getStringCellValue());
    }
    Row row = excelSheet.getRow(lineNumber);
    Iterator<Cell> cellIterator = row.cellIterator();
    
    int i = 0;
    while (cellIterator.hasNext()) {
      String header = headers.get(i++);
      Cell cell = (Cell) cellIterator.next();
      switch (cell.getCellType()) {
      case Cell.CELL_TYPE_STRING:
        log.finer("Zelle: String " + header + " " + cell.getStringCellValue()); 
        procVars.put(header, cell.getStringCellValue());
        break;
      case Cell.CELL_TYPE_NUMERIC:
        log.finer("Zelle: Zahl " + header + " " + cell.getNumericCellValue());
        procVars.put(header, cell.getNumericCellValue());
        break;
      case Cell.CELL_TYPE_BLANK:
        log.finer("Zelle " + header + "ist leer");
        procVars.put(header, null);
        break;
      default:
        break;
      }
      
    }
    return procVars;
  }
}
