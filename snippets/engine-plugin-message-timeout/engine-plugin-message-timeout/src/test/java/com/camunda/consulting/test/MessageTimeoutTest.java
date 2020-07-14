package com.camunda.consulting.test;
import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.EventSubscription;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camunda.consulting.exception.TimeoutDurationMissingException;
import com.camunda.consulting.exception.TimeoutListenerMissingException;
import com.camunda.consulting.exception.TimeoutListenerTypeMissingException;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.util.List;

public class MessageTimeoutTest {

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Mock
    ExecutionListener executionListener;
 
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        init(processEngineRule.getProcessEngine());
    }

    @Test
    @Deployment(resources = "MessageTimeoutWithDelegateExpression.bpmn")
    public void testWithMessageTaskAndDelegateExpression() throws Exception {
        Mocks.register("someBean", executionListener);

        ProcessInstance messageProcess = runtimeService().startProcessInstanceByKey("MessageTimeoutWithDelegateExpression");

        managementService().createJobQuery().list().stream().map(job -> ((JobEntity) job)).forEach(jobEntity -> {
            logger.info("JobEntity " + jobEntity.toString());
        });

        runtimeService().createEventSubscriptionQuery().list().forEach(eventSubscription -> logger.info(eventSubscription.toString()));

        execute(jobQuery().singleResult());

        runtimeService().createMessageCorrelation("SomeMessage").correlate();

        assertThat(messageProcess).isEnded();
        Assertions.assertThat(jobQuery().list()).isEmpty();

        verify(executionListener).notify(any());
    }
    
    @Test
    @Deployment(resources = "MessageTimeoutWithExpression.bpmn")
    public void testWithMessageTaskAndExpression() throws Exception {
    	Mocks.register("expressionBean", executionListener);
    	
    	ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("MessageTimeoutWithExpression", withVariables("Testvariable", "test"));
    	assertThat(processInstance).isStarted();
    	
    	List<Job> jobs = managementService().createJobQuery().list();
    	Assertions.assertThat(!jobs.isEmpty());
    	Assertions.assertThat(jobs.size()==1);
    	
    	List<EventSubscription> events = runtimeService().createEventSubscriptionQuery().list();
    	Assertions.assertThat(!events.isEmpty());
    	Assertions.assertThat(events.size()==1);
    	
    	execute(jobs.get(0));
    	
    	assertThat(processInstance).isWaitingAt("SomeMessageTask");
    	runtimeService().createMessageCorrelation("SomeMessage").correlate();
    	
    	assertThat(processInstance).isEnded();
        Assertions.assertThat(jobQuery().list()).isEmpty();
        
        verify(executionListener).notify(any());
    }
    
    @Test
    @Deployment(resources = "MessageTimeoutWithJavaClass.bpmn")
    public void testWithMessageTaskAndJavaClass() throws Exception {
    	logger.info("Test");
    	ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("MessageTimeoutWithJavaClass");
    	assertThat(processInstance).isStarted();
    	
    	List<Job> jobs = managementService().createJobQuery().list();
    	Assertions.assertThat(!jobs.isEmpty());
    	Assertions.assertThat(jobs.size()==1);
    	
    	List<EventSubscription> events = runtimeService().createEventSubscriptionQuery().list();
    	Assertions.assertThat(!events.isEmpty());
    	Assertions.assertThat(events.size()==1);
    	
    	execute(jobs.get(0));
    	
    	assertThat(processInstance).isWaitingAt("SomeMessageTask");
    	runtimeService().createMessageCorrelation("SomeMessage").correlate();
    	
    	assertThat(processInstance).isEnded();
        Assertions.assertThat(jobQuery().list()).isEmpty();
   }

	@Test
	@Deployment(resources = "IntermediateMessageTimeoutWithJavaClass.bpmn")
	public void testWithIntermediateMessageTaskAndJavaClass() throws Exception {
		logger.info("Test");
		ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("IntermediateMessageTimeoutWithJavaClass");
		assertThat(processInstance).isStarted();

		List<Job> jobs = managementService().createJobQuery().list();
		Assertions.assertThat(!jobs.isEmpty());
		Assertions.assertThat(jobs.size()==1);

		List<EventSubscription> events = runtimeService().createEventSubscriptionQuery().list();
		Assertions.assertThat(!events.isEmpty());
		Assertions.assertThat(events.size()==1);

		execute(jobs.get(0));

		assertThat(processInstance).isWaitingAt("SomeMessageEvent");
		runtimeService().createMessageCorrelation("SomeMessage").correlate();

		assertThat(processInstance).isEnded();
		Assertions.assertThat(jobQuery().list()).isEmpty();
	}

    @Test
    public void testMessageTimeoutMissingPropertiesTimeoutDuration() throws Exception {
    	DeploymentBuilder deploymentBuilder = repositoryService()
    		.createDeployment()
    		.addClasspathResource("MessageTimeoutMissingPropertiesTimeoutDuration.bpmn");
    	
    	Throwable thrownException = null;
    	
    	try {
    		deploymentBuilder.deploy();   		
    	} catch (ProcessEngineException e) {
    		thrownException = e.getCause();
    	}
    	
    	Assertions.assertThat(thrownException).isInstanceOf(TimeoutDurationMissingException.class);
    }

    @Test
    public void testMessageTimeoutMissingPropertiesTimeoutListener() throws Exception {
    	DeploymentBuilder deploymentBuilder = repositoryService()
        		.createDeployment()
        		.addClasspathResource("MessageTimeoutMissingPropertiesTimeoutListener.bpmn");
        	
        	Throwable thrownException = null;
        	
        	try {
        		deploymentBuilder.deploy();   		
        	} catch (ProcessEngineException e) {
        		thrownException = e.getCause();
        	}
        	
        	Assertions.assertThat(thrownException).isInstanceOf(TimeoutListenerMissingException.class);
    }
    
    @Test
    public void testMessageTimeoutMissingPropertiesTimeoutListenerType() throws Exception {
    	DeploymentBuilder deploymentBuilder = repositoryService()
        		.createDeployment()
        		.addClasspathResource("MessageTimeoutMissingPropertiesTimeoutListenerType.bpmn");
        	
        	Throwable thrownException = null;
        	
        	try {
        		deploymentBuilder.deploy();   		
        	} catch (ProcessEngineException e) {
        		thrownException = e.getCause();
        	}
        	
        	Assertions.assertThat(thrownException).isInstanceOf(TimeoutListenerTypeMissingException.class);
    }
}
