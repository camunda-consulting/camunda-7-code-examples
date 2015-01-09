import static org.junit.Assert.*;

import java.io.File;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;



public class TestProcessWithFileConnector {
  
  private static final String SLASH = System.getProperty("file.separator");
  private static final String USER_HOME = System.getProperty("user.home");
  
  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  @Test
  @Deployment(resources="file-connector.bpmn")
  public void testFileConnector() {
    
    String filePath = USER_HOME + SLASH + "camunda-file-connector-test.txt";
    File file = new File(filePath);
    
    if (file.exists()) {
      file.delete();
    }
    
    assertFalse(file.exists());    
    rule.getProcessEngine().getRuntimeService().startProcessInstanceByKey("file-connector", //
        Variables.createVariables().putValue("filePath", filePath));    
    assertTrue(file.exists());
  }

}
