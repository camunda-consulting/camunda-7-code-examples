package org.camunda.bpm.example.invoice;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.example.box.BoxConnector;

public class CaseGetDocuments implements CaseExecutionListener {

	@Override
	public void notify(DelegateCaseExecution execution) throws Exception {
		String boxfolder = (String) execution.getVariable("boxfolder");
		
		if (boxfolder != null) {
			BoxConnector bc = new BoxConnector();
			String linkObject = bc.getFileLinks(boxfolder);
			String folderURL = bc.getFolderSharedLink(boxfolder);
			execution.setVariable("files", linkObject);
			execution.setVariable("folderUrl", folderURL);
		} else {
			execution.setVariable("files", "nofiles");
		}
		
	}

}
