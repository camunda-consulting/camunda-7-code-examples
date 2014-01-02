package com.camunda.fox.process.stub;

import org.camunda.bpm.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;

/**
 * This class is used to stub the activiti-camel component in test cases. 
 * 
 * Why is this needed?
 * -------------------
 * The activiti-camel component hooks into the process execution by extending the 
 * BpmnActivityBehavior class. At the end of the "execute" method, activiti-camel usually
 * calls "performDefaultOutgoingBehavior(execution)", which continues the execution of the 
 * process instance. However, if we replaced activiti-camel's CamelBehaviour with a mock,
 * this method call would not be executed and the process instance would be stuck at the 
 * first occurrence of a service task that contains a delegate-expression with the value 
 * "#{camel}". 
 * 
 * @author Nils Preusker - nilspreusker
 */
public class CamelBehaviourStub extends BpmnActivityBehavior implements ActivityBehavior {

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		performDefaultOutgoingBehavior(execution);
	}

}
