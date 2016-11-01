package org.camunda.bpm.example.box;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.UUID;

import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxSharedLink;
import com.box.sdk.BoxAPIException;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFileVersion;
import com.box.sdk.BoxItem.Info;

public class BoxConnector {
	static final String USER_ID = ConfigHelper.properties().getProperty("boxUserId");
	static final String BOX_FOLDER = ConfigHelper.properties().getProperty("boxFolderId");
	
	public JSONObject upload(InputStream fileContent, String fileName, String boxfoldername) throws Exception {
		Boolean folderExists = false;
		BoxFolder boxfolder = null;
		String uniqueID = boxfoldername;
		if (uniqueID == null || uniqueID.isEmpty()) {
			uniqueID = UUID.randomUUID().toString();
		} 
		
		BoxDeveloperEditionAPIConnection api = BoxHelper.userClient(USER_ID);
		BoxFolder parentFolder = new BoxFolder(api, BOX_FOLDER);
		for (BoxItem.Info itemInfo : parentFolder) {
			if (itemInfo.getName().equals(uniqueID)) {
				folderExists = true;
				boxfolder = (BoxFolder) itemInfo.getResource();
				break;
			}
		}
		
		if (folderExists == false) {
			BoxFolder.Info childFolderInfo = parentFolder.createFolder(uniqueID);
			boxfolder = childFolderInfo.getResource();
		}
		
		Boolean fileExists = false;
		String fileExistsId = "";
		Iterable<Info> childs = boxfolder.getChildren();
		for (Info i: childs) {
			if (("/"+i.getName()).equals(fileName)) {
				fileExists = true;
				fileExistsId = i.getID();
				break;
			}
		}
		
		JSONObject returnObject = new JSONObject();
		returnObject.put("uniqueID", uniqueID);
		if (fileExists) {
			BoxFile file = new BoxFile(api, fileExistsId);
			try {
				file.uploadVersion(fileContent);
				returnObject.put("success", true);
			} catch (Exception e) {
				returnObject.put("success", false);
			}
		} else {
			try {
				boxfolder.uploadFile(fileContent, fileName);
				returnObject.put("success", true);
			} catch(Exception e) {
				returnObject.put("success", false);
			}
		}
		
		return returnObject;
	}
	
	public String getEmbedLink(String boxfoldername, String file) {
		BoxDeveloperEditionAPIConnection api = BoxHelper.userClient(USER_ID);
		BoxFolder parentFolder = new BoxFolder(api, BOX_FOLDER);
		BoxFolder boxfolder = null;
		for (BoxItem.Info itemInfo : parentFolder) {
			if (itemInfo.getName().equals(boxfoldername)) {
				boxfolder = (BoxFolder) itemInfo.getResource();
				break;
			}
		}
		Iterable<Info> childs = boxfolder.getChildren();
		String fileExistsId = "";
		for (Info i: childs) {
			if (i.getName().equals(file)) {
				fileExistsId = i.getID();
				break;
			}
		}
		BoxFile boxfile = new BoxFile(api, fileExistsId);
		try {
			return boxfile.getPreviewLink().toString();
		} catch (BoxAPIException e) {
			return "no";
		}
		
	}
	
	public String getFolderSharedLink(String boxfoldername) {
		BoxDeveloperEditionAPIConnection api = BoxHelper.userClient(USER_ID);
		BoxFolder parentFolder = new BoxFolder(api, BOX_FOLDER);
		BoxFolder boxfolder = null;
		
		for (BoxItem.Info itemInfo : parentFolder.getChildren()) {
			if (itemInfo.getName().equals(boxfoldername)) {
				boxfolder = (BoxFolder) itemInfo.getResource();
				break;
			}
		}
		BoxSharedLink link = boxfolder.createSharedLink(BoxSharedLink.Access.COMPANY, null, null);
		return link.getURL();
	}
	
	public String getFileLinks(String boxfoldername) {
		BoxDeveloperEditionAPIConnection api = BoxHelper.userClient(USER_ID);
		BoxFolder parentFolder = new BoxFolder(api, BOX_FOLDER);
		BoxFolder boxfolder = null;
		
		for (BoxItem.Info itemInfo : parentFolder.getChildren()) {
			if (itemInfo.getName().equals(boxfoldername)) {
				boxfolder = (BoxFolder) itemInfo.getResource();
				break;
			}
		}
		
		Iterable<Info> childs = boxfolder.getChildren();
		JSONArray files = new JSONArray();
		
		for (Info i: childs) {
			BoxFile file = new BoxFile(api, i.getID());
			URL downloadUrl = file.getDownloadURL();
			JSONObject fileObject = new JSONObject();
			BoxFile.Info fileInfo = file.getInfo("content_modified_at,version_number");
			fileObject.put("filename", i.getName());
			fileObject.put("downloadlink", downloadUrl.toString());
			fileObject.put("date", fileInfo.getContentModifiedAt().getTime());
			
			Collection<BoxFileVersion> versions = file.getVersions();
			if (versions.size()>0) {
				JSONArray versionArray = new JSONArray();
				for (BoxFileVersion version: versions) {
					JSONObject versionObject = new JSONObject();
					System.out.println("VersionID "+version.getID());
					versionObject.put("date", version.getModifiedAt().getTime());
					versionArray.put(versionObject);
				}
				fileObject.put("versions", versionArray);
			}
			if (fileInfo.getVersionNumber() != null) {
				fileObject.put("version", fileInfo.getVersionNumber());
			} else {
				fileObject.put("version", "1");
			}
			
			files.put(fileObject);
		}
		return files.toString();
	}
	
}
