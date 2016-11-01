package org.camunda.bpm.example.invoice;

import static org.junit.Assert.*;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;
import org.camunda.bpm.example.cmis.CMISConnector;
import org.junit.Test;

public class TestCase {
	
	/*
	 * Just checks if CMIS is up and running...
	 */
	@Test
	public void testCMIS() {
		 CMISConnector bc = new CMISConnector();
		 Session session = bc.getSession();
		 Folder folder = session.getRootFolder();
		 ItemIterable<CmisObject> children = folder.getChildren();
		 for (CmisObject child : children) {
			 System.out.println(child.getName());
		 }

		 ItemIterable<CmisObject> childrenq = folder.getChildren();
		 for (CmisObject child : childrenq) {
			 System.out.println(child.getName());
		 }
	}
}
