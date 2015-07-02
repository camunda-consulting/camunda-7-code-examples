package org.camunda.bpm.example.twitter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.test.ProcessEngineAssert;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( fullyQualifiedNames = {"twitter4j.*", "org.camunda.bpm.example.twitter.*"} )
public class MockitoTestCase {

	@Rule
	ProcessEngineRule rule = new ProcessEngineRule();
	
	// enable more detailed logging
	static {
		LogUtil.readJavaUtilLoggingConfigFromClasspath();
	}

	@Test
	@Deployment( resources="TwitterDemoProcess.bpmn")
	public void testUserTaskRejected() throws Exception{
		
		Mocks.register("tweetAdapter", new TweetContentDelegate()); // not actually a mock, but works without CDI
		
		AccessToken mockToken = Mockito.mock(AccessToken.class);
		PowerMockito.whenNew(AccessToken.class).withAnyArguments().thenReturn(mockToken);
		
		TwitterFactory mockFactory = Mockito.mock(TwitterFactory.class);
		PowerMockito.whenNew(TwitterFactory.class).withNoArguments().thenReturn(mockFactory);
		
		Twitter mockTwitter = Mockito.mock(Twitter.class);
		Mockito.when(mockFactory.getInstance()).thenReturn(mockTwitter);

		Mockito.doNothing().when(mockTwitter).setOAuthAccessToken( Mockito.any(AccessToken.class));
		Mockito.doNothing().when(mockTwitter).setOAuthConsumer(Mockito.anyString(), Mockito.anyString());
		
		String content = "We will never see this content on Twitter";
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("content", content);
		
		ProcessInstance instance = rule.getProcessEngine().getRuntimeService().startProcessInstanceByKey("TwitterDemoProcess", variables);
		
		TaskService taskService = rule.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getProcessInstanceId()).list();		
		assertEquals( "There should be a task", 1, tasks.size());
		
		Map<String, Object> approval = new HashMap<String, Object>();
		approval.put("approved", false);
		
		taskService.complete(tasks.get(0).getId(), approval);

		Mockito.verify(mockTwitter, Mockito.never()).updateStatus(content);
		
		ProcessEngineAssert.assertProcessEnded(rule.getProcessEngine(), instance.getProcessInstanceId());
	}

//	@Test
//	@Deployment( resources="TwitterDemoProcess.bpmn")
//	public void testUserTaskApproved() throws Exception{
//		
//		Mocks.register("tweetAdapter", new TweetContentDelegate()); // not actually a mock, but works without CDI
//		
//		AccessToken mockToken = Mockito.mock(AccessToken.class);
//		PowerMockito.whenNew(AccessToken.class).withAnyArguments().thenReturn(mockToken );
//		
//		TwitterFactory mockFactory = Mockito.mock(TwitterFactory.class);
//		PowerMockito.whenNew(TwitterFactory.class).withNoArguments().thenReturn(mockFactory);
//		
//		Twitter mockTwitter = Mockito.mock(Twitter.class);
//		Mockito.when(mockFactory.getInstance()).thenReturn(mockTwitter);
//
//		Mockito.doNothing().when(mockTwitter).setOAuthAccessToken( Mockito.any(AccessToken.class));
//		Mockito.doNothing().when(mockTwitter).setOAuthConsumer(Mockito.anyString(), Mockito.anyString());
//
//		String content = "Hallo Welt!";
//		Map<String, Object> input = new HashMap<String, Object>();
//		input.put("content", content);
//		
//		ProcessInstance instance = rule.getProcessEngine().getRuntimeService().startProcessInstanceByKey("TwitterDemoProcess", input);
//		
//		TaskService taskService = rule.getTaskService();
//		List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getProcessInstanceId()).list();		
//		
//		assertEquals( "There should be a task", 1, tasks.size());
//	
//		Map<String, Object> approval = new HashMap<String, Object>();
//		approval.put("approved", true);
//		
//		taskService.complete(tasks.get(0).getId(), approval);
//		
//		Mockito.verify(mockTwitter).updateStatus(content);
//		
//		ProcessEngineAssert.assertProcessEnded(rule.getProcessEngine(), instance.getProcessInstanceId());
//		
//	}
	
}
