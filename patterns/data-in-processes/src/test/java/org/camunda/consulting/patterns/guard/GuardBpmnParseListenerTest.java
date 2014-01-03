package org.camunda.consulting.patterns.guard;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.collect.Maps;

public class GuardBpmnParseListenerTest {

  private static final String BPMN_FILE = "test-process.bpmn";

  private static final String PROCESS_KEY = "test-process";

  @Rule
  public final ProcessEngineRule processEngine = new ProcessEngineRule("camunda-guards.cfg.xml");

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  private final Map<String, Object> variables = Maps.newHashMap();

  @Test
  @Deployment(resources = BPMN_FILE)
  public void shouldRunWithAutomaticGuards() {
    Mocks.register("serviceTask", new JavaDelegate() {

      public void execute(final DelegateExecution execution) throws Exception {
        execution.setVariable("world", Boolean.TRUE);
      }
    });

    variables.put("foo", Boolean.TRUE);

    processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_KEY, variables);

    variables.put("bar", Boolean.FALSE);
    variables.put("hello", Boolean.FALSE);
    completeTask(variables);
    assertNoMoreRunningInstances();
  }

  private void assertNoMoreRunningInstances() {
    assertEquals(0, processEngine.getRuntimeService().createProcessInstanceQuery().count());
  }

  @Test
  @Deployment(resources = BPMN_FILE)
  public void shouldFailWhenFooIsNotSetOnStart() {
    thrown.expect(ProcessEngineException.class);
    thrown.expectMessage("'foo'");
    processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_KEY);
  }

  @Test
  @Deployment(resources = BPMN_FILE)
  public void shouldFailWhenBarIsNotSetInUserTask() {

    thrown.expect(ProcessEngineException.class);
    thrown.expectMessage("'bar'");

    variables.put("foo", Boolean.TRUE);

    processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_KEY, variables);
    completeTask();
  }

  /**
   * Completes the current task.
   * 
   * @see #getTask()
   */
  private void completeTask() {
    completeTask(new HashMap<String, Object>());
  }

  /**
   * Completes the current task with variables.
   * 
   * @param variables
   *          stored in process payload
   */
  private void completeTask(final Map<String, Object> variables) {
    final Task singleResult = processEngine.getTaskService().createTaskQuery().singleResult();
    processEngine.getTaskService().complete(singleResult.getId(), variables);
  }

}
