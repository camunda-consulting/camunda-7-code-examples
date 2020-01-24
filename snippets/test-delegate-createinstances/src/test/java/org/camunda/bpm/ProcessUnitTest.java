package org.camunda.bpm;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.demo.CamundaApplication;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CamundaApplication.class, webEnvironment = WebEnvironment.NONE)
public class ProcessUnitTest {

  private Logger logger = Logger.getLogger(getClass().getName());

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Rule
  public final ProcessEngineRule processEngine = new StandaloneInMemoryTestConfiguration().rule();

  @Test
  @Deployment(resources = {"process.bpmn","subprocess.bpmn"})
  public void testHappyPath() {
    String businesKey = UUID.randomUUID().toString();
    ProcessInstance processInstance = processEngine().getRuntimeService()
            .createProcessInstanceByKey("ParentProcess")
            .businessKey(businesKey)
            .setVariable("listSize",1000)
            .setVariable("subprocess", "ChildProcess")
            .execute();

    assertThat(processInstance).isWaitingAt("CreateInstancesTask");
    execute(job());
    assertThat(processInstance).isWaitingAt("ConditionEvent");

    List<Job> startJobs = managementService().createJobQuery().activityId("StartEvent_1").list();
    startJobs.forEach(job -> {
      managementService().executeJob(job.getId());
    });

    List<Job> loggerJobs = managementService().createJobQuery().activityId("CallLoggerTask").list();
    loggerJobs.forEach(job -> {
      managementService().executeJob(job.getId());
    });

    List<Job> updateInstanceJobs = managementService().createJobQuery().activityId("UpdateParentInstanceCountTask").list();
    updateInstanceJobs.forEach(job -> {
      managementService().executeJob(job.getId());
    });

    List<HistoricProcessInstance> processInstances = historyService().createHistoricProcessInstanceQuery()
            .variableValueEquals("parentBusinessKey", businesKey).list();

    assertThat(processInstances.size()).isEqualTo(1000);
  }

  @Test
  @Deployment(resources = {"process-multi-instance.bpmn"})
  public void testMultInstance(){
    ProcessInstance processInstance = processEngine().getRuntimeService()
            .createProcessInstanceByKey("ProcessMultInstance")
            .setVariable("listSize",1000)
            .execute();
    runtimeService().createEventSubscriptionQuery().list();
    assertThat(processInstance).isWaitingAt("StartEvent_17h9qmv");
    execute(job());
    assertThat(processInstance).isWaitingAt("CallLogger#multiInstanceBody");
    execute(job());
    List<Job> callLoggerJobs = managementService().createJobQuery().activityId("CallLogger").list();
    callLoggerJobs.forEach(job -> {
      managementService().executeJob(job.getId());
    });
    assertThat(processInstance).isEnded();

  }

  public List<String> generateRandomData(Integer size){
    List<String> listData = new ArrayList<String>();
    for(int i=0;i<size;i++){
      listData.add(UUID.randomUUID().toString());
    }
    return listData;
  }

}

