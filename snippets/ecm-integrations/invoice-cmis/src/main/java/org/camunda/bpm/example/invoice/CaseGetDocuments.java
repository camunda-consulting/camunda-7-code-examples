package org.camunda.bpm.example.invoice;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.example.cmis.CMISConnector;

public class CaseGetDocuments implements CaseExecutionListener {

	@Override
	public void notify(DelegateCaseExecution execution) throws Exception {
		String folderName = (String) execution.getVariable("folderName");
		if (folderName != null) {
			CMISConnector cmis = new CMISConnector();
			String linkObject = cmis.getFileLinks(folderName);
			execution.setVariable("files", linkObject);
		} else {
			execution.setVariable("files", "nofiles");
		}
	}

}
