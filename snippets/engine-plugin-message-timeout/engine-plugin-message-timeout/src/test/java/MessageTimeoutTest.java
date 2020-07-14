import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
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

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@Deployment(resources = {"MessageTimeoutMissingProperties.bpmn", "MessageTimeoutWithDelegateExpression.bpmn", "MessageTimeoutWithExpression.bpmn", "MessageTimeoutWithJavaClass.bpmn"})
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
}
