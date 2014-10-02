package org.camunda.bpm.demo.skip_service_task_expression;

import org.camunda.bpm.engine.delegate.DelegateExecution;

/**
 * use:
 *   ${skip.isSetIn(execution) || logger.execute(execution)}
 * instead of:
 *   ${(execution.hasVariable('skipNextExpression') || logger.execute(execution)) && execution.removeVariable('skip')}
 * 
 * @author Falko Menge
 *
 */
public class Skip {

	public boolean isSetIn(DelegateExecution execution) {
		if (execution.hasVariableLocal("skipNextExpression")) {
			execution.removeVariableLocal("skipNextExpression");
			return true;
		} else {
			return false;
		}
	}
	
}
