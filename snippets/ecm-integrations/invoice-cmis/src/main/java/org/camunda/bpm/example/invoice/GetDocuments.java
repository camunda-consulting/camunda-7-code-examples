package org.camunda.bpm.example.invoice;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.example.cmis.CMISConnector;

public class GetDocuments implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
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
