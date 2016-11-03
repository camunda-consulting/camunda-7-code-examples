package org.camunda.bpm.example.cmis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.client.util.FileUtils;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.camunda.bpm.example.cmis.ConfigHelper;

public class CMISConnector {
	private static Session session;
	private static String CMIS_ROOT_FOLDER_NAME = ConfigHelper.properties().getProperty("CmisRootFolder");
	private static String CMIS_USERNAME = ConfigHelper.properties().getProperty("CmisUser");
	private static String CMIS_PASSWORD = ConfigHelper.properties().getProperty("CmisPassword");
	private static String CMIS_BROWSER_URL = ConfigHelper.properties().getProperty("CmisBrowserUrl");
	private static String CMIS_REPO_ID = ConfigHelper.properties().getProperty("CmisRepoId");
	
	
	public Session getSession() {
		// default factory implementation
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String, String> parameters = new HashMap<String, String>();

		// user credentials
		parameters.put(SessionParameter.USER, CMIS_USERNAME);
		parameters.put(SessionParameter.PASSWORD, CMIS_PASSWORD);

		// connection settings
		parameters.put(SessionParameter.BROWSER_URL, CMIS_BROWSER_URL);
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
		parameters.put(SessionParameter.REPOSITORY_ID, CMIS_REPO_ID);

		// create session
		session = factory.createSession(parameters);
		return session;
	}
	
	public JSONObject upload(InputStream fileContent, String fileName, String folderName) throws Exception {
		session = getSession();
		JSONObject jsonObject = new JSONObject();
		String uniqueID = folderName;
		if (uniqueID == null || uniqueID.isEmpty()) {
			uniqueID = UUID.randomUUID().toString();
		} 
		
		Folder rootFolder;
		try {
			rootFolder = FileUtils.getFolder("/"+CMIS_ROOT_FOLDER_NAME, session);
		} catch(Exception e) {
			createFolder(session.getRootFolder(), CMIS_ROOT_FOLDER_NAME);
			rootFolder = FileUtils.getFolder("/"+CMIS_ROOT_FOLDER_NAME, session);
		}

		Folder newFolder;
		try {
			newFolder = FileUtils.getFolder("/"+CMIS_ROOT_FOLDER_NAME + "/" + uniqueID, session);
		} catch(Exception e) {
			createFolder(rootFolder, uniqueID);
			newFolder = FileUtils.getFolder("/"+CMIS_ROOT_FOLDER_NAME + "/" + uniqueID, session);
		}

		createDocument(newFolder, fileContent, fileName);
		
		jsonObject.put("success", true);
		jsonObject.put("uniqueID", uniqueID);
		
		return jsonObject;
	}
	
	public String getFileLinks(String folderName) {
		Folder folder = FileUtils.getFolder("/"+CMIS_ROOT_FOLDER_NAME+"/"+folderName, session);
		
		ItemIterable<CmisObject> childs = folder.getChildren();
		JSONArray files = new JSONArray();
		
		for (CmisObject child: childs) {
			Document d = (Document) child;

			JSONObject fileObject = new JSONObject();
			fileObject.put("filename", d.getName());
			fileObject.put("id", d.getId());
			fileObject.put("date", d.getLastModificationDate());
			
			List<Document> documents = d.getAllVersions();
			if (documents.size() > 1) {
				JSONArray versionArray = new JSONArray();
				for (Document versionDocument : documents) {
					JSONObject versionObject = new JSONObject();
					versionObject.put("date", versionDocument.getLastModificationDate());
					versionObject.put("version", versionDocument.getVersionLabel());
					versionArray.put(versionObject);
				}
				fileObject.put("versions", versionArray);
			}
			fileObject.put("version", d.getVersionLabel());
			files.put(fileObject);
		}
		
		return files.toString();
	}
	
	private static void createDocument(Folder target, InputStream stream, String fileName) throws IOException {
		Map<String, String> props = new HashMap<String, String>();
		props.put(PropertyIds.OBJECT_TYPE_ID, "VersionableType");
		props.put(PropertyIds.NAME, fileName);
		
		
        byte[] buff = new byte[8000];
        int bytesRead = 0;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        while((bytesRead = stream.read(buff)) != -1) {
           bao.write(buff, 0, bytesRead);
        }
        byte[] data = bao.toByteArray();

        ByteArrayInputStream bin = new ByteArrayInputStream(data);
		ContentStream contentStream = session.getObjectFactory().createContentStream(fileName, data.length, "charset=UTF-8", bin);
		
		try {
			target.createDocument(props, contentStream, VersioningState.MAJOR);
		} catch (Exception e) {
			Document d = (Document) FileUtils.getObject(target.getPath() + "/" + fileName, session);
			ObjectId pwcId = d.checkOut();
			Document pwc = (Document) session.getObject(pwcId.getId());
			bin.reset();
			pwc.setContentStream(session.getObjectFactory().createContentStream(fileName, data.length, "charset=UTF-8", bin), true);
			bin.reset();
			pwc.checkIn(true, props, session.getObjectFactory().createContentStream(fileName, data.length, "charset=UTF-8", bin), "New Upload");
		}
	}
	
	private static void createFolder(Folder target, String folderName) {
		 Map<String, String> props = new HashMap<String,String>();
		 props.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
		 props.put(PropertyIds.NAME, folderName);
		 target.createFolder(props);
	}

	public byte[] downloadFile(String fileId) throws IOException {
		session = getSession();
		Document d = (Document) FileUtils.getObject(fileId, session);
		byte[] buff = new byte[8000];
        int bytesRead = 0;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        InputStream is = d.getContentStream().getStream();
        while((bytesRead = is.read(buff)) != -1) {
           bao.write(buff, 0, bytesRead);
        }
        is.close();
        byte[] data = bao.toByteArray();
        return data;
	}
}
