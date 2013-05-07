package com.camunda.fox.showcase.invoice.en;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;


/**
 * TODO: Allow two invoices per creditor on the same day
 */
@Named("svnService")
public class StorePDFDelegate implements JavaDelegate {

  private String svnUrl = "https://camunda-jakob/svn/archive/invoices/";
  private String svnUser = "demo";
  private String svnPwd = "demo";


  
  public void execute(DelegateExecution execution) throws Exception {
    // retrieve process variables
	String repo =  (String) execution.getVariable("repo");
	  
    byte[] file = (byte[]) execution.getVariable(ProcessConstants.VARIABLE_INVOICE);
    String creditor = (String) execution.getVariable("creditor");
    Date invoiceDate = (Date) execution.getVariable("invoice_date");

    // format correctly
    String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(invoiceDate);
    String fileName = creditor.replace(" ", "_") + "_" + formattedDate + ".pdf";
    String commitComment = "Freigabeprotokoll...";

    // and do SVN handling
    if (repo.equals("svn")) {
    	// store in SVN
    	storeInSubversion(file, fileName, commitComment);
    } else if (repo.equals("alfresco")) {
    	// store in Alfresco ECM 4
    	storeInAlfresco(file, fileName);
    } else {
    	// Store locally
    	storeInFile(file, fileName);    	
    }
  }

  private void storeInFile (byte[] file, String fileName) throws IOException {
	  String home = System.getProperty("user.home");
	  File path = new File(home + "/fox-invoice/");
	  if (!path.exists()){
	      path.mkdir();
	  }	  
	  
	  FileOutputStream fos = new FileOutputStream(home + "/fox-invoice/" + fileName);
	  fos.write(file);
	  fos.close();
  }
  
  private void storeInAlfresco (byte[] file, String fileName) {
	  
	  	Map<String, String> parameter = new HashMap<String, String>();

		// Set the user credentials
		parameter.put(SessionParameter.USER, "admin");
		parameter.put(SessionParameter.PASSWORD, "admin");

		// Specify the connection settings
		
		// Public Alfresco CMIS Repo for demo purposes
		parameter.put(SessionParameter.ATOMPUB_URL, "http://cmis.alfresco.com/cmisatom");
		
		// Alternatively: My Local Alfresco Installation
		//parameter.put(SessionParameter.ATOMPUB_URL, "http://127.0.0.1:8090/alfresco/service/cmis");
		
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		// Set the alfresco object factory
		parameter.put(SessionParameter.OBJECT_FACTORY_CLASS, "org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");

		// Create a session
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Session session = factory.getRepositories(parameter).get(0).createSession();
		
        Folder parent = (Folder) session.getObjectByPath("/camunda fox/fox-invoice");
        
		// properties 
		// (minimal set: name and object type id)
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		properties.put(PropertyIds.NAME, fileName);

		// content
		//byte[] content = "Hello World!".getBytes();
		ByteArrayInputStream stream = new ByteArrayInputStream(file);
		ContentStream contentStream = new ContentStreamImpl(fileName, BigInteger.valueOf(file.length), "application/pdf", stream);

		// create a major version
		Document newDoc = parent.createDocument(properties, contentStream, VersioningState.MAJOR);		
		
  }
  
  private void storeInSubversion(byte[] file, String fileName, String commitComment) throws SVNException {
    try {
      ISVNAuthenticationManager authManager = new BasicAuthenticationManager(svnUser, svnPwd);
      DAVRepositoryFactory.setup();
      SVNURL url = SVNURL.parseURIDecoded(svnUrl);
      SVNRepository repository = DAVRepositoryFactory.create(url, null);
      repository.setAuthenticationManager(authManager);

      // Get exact value of the latest (HEAD) revision.
      long latestRevision = repository.getLatestRevision();
      System.out.println("Repository latest revision (before committing): " + latestRevision);

      ISVNEditor editor = repository.getCommitEditor(commitComment, null /* locks */, true /* keepLocks */, null /* mediator */);

      editor.openRoot(-1);
      editor.addFile(fileName, null, -1);

      editor.applyTextDelta(fileName, null);

      SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
      String checksum = deltaGenerator.sendDelta(fileName, new ByteArrayInputStream(file), editor, true);

      editor.closeFile(fileName, checksum);
      editor.closeDir();
      editor.closeEdit();
    } catch (Exception ex) {
      throw new IllegalArgumentException("PDF could not be stored to SVN: " + ex.getMessage(), ex);
    }

  }

}
