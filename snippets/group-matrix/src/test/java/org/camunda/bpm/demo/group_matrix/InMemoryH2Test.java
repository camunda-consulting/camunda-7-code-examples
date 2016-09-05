package org.camunda.bpm.demo.group_matrix;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  private static final String VARIABLE_BRANCH = "branch";

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "group-matrix";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() {
    Map<String, Object> variables = new HashMap<String, Object>();

    new BranchVariable("Koblenz").addTo(variables);
    
	  ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
	  
	  // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
	  assertThat(processInstance).isActive()
	    .task().hasCandidateGroup("camunda:matrix:Berlin:approver");
	  
	  // To generate the coverage report for a single tests add this line as the last line of your test method:
	  //ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  }
  
  public void draftFilterGenerationFromLdap() {
    Map<String, Set<String>> branchesPerRole = new HashMap<String, Set<String>>();
    List<Group> matrixGroups = processEngine().getIdentityService().createGroupQuery().list();
    for (Group matrixGroup : matrixGroups) {
      // camunda:matrix:Berlin:approver
      String role = getRole(matrixGroup);
      String branch = getBranch(matrixGroup);
      if (!branchesPerRole.containsKey(role)) {
        branchesPerRole.put(role, new HashSet<String>());
      }
      branchesPerRole.get(role).add(branch);
    }
    
    for (Entry<String, Set<String>> entry : branchesPerRole.entrySet()) {
      // TODO generate Filter
//      {"id":"8977f81c-6eb7-11e6-a87d-56847afe9799","name":"approver","resourceType":"Task","query":{"candidateGroups":["camunda:matrix:Koblenz:approver","camunda:matrix:Berlin:approver"]},"properties":{"priority":0,"color":"#555555","refresh":false,"showUndefinedVariable":false}}
//
//      Zusätzlich Authorizations für die gleichen Gruppen.

    }
  }

  private String getBranch(Group matrixGroup) {
    // TODO Auto-generated method stub
    return null;
  }

  private String getRole(Group matrixGroup) {
    // TODO Auto-generated method stub
    return null;
  }

  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }  

}
