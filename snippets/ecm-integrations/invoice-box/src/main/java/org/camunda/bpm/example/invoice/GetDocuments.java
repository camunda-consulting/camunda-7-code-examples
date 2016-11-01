package org.camunda.bpm.example.invoice;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.example.box.BoxConnector;

public class GetDocuments implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
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
		execution.setVariable("uploadInvoice", false);
	}

}
