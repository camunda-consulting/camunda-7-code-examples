package org.camunda.bpm.example.acm.cmis;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;

public class CmisClient {

    private static final String CMIS_HOST = "127.0.0.1:8090";
    private static final String CMIS_USER = "xxx";
    private static final String CMIS_PASSWORD = "xxx";
    private static final String CMIS_FOLDER = "/xxx";
    
    private static boolean enabled = false;

    public static String storeFile(final byte[] file, final String caseInstanceId, final String fileName) {
      if (!enabled) {
        return null;
      }
        // Specify the connection settings

        // Public Alfresco CMIS Repo for demo purposes
        // parameter.put(SessionParameter.ATOMPUB_URL,
        // "http://cmis.alfresco.com/cmisatom");

        final Session session = createCmisSession();

        final Folder caseFolder = getCaseFolder(session, caseInstanceId);

        // properties
        // (minimal set: name and object type id)
        final Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
        properties.put(PropertyIds.NAME, fileName);

        // content
        // byte[] content = "Hello World!".getBytes();
        final ByteArrayInputStream stream = new ByteArrayInputStream(file);
        final ContentStream contentStream = new ContentStreamImpl(fileName, BigInteger.valueOf(file.length), "application/pdf", stream);

        // create a major version
        final Document document = caseFolder.createDocument(properties, contentStream, VersioningState.MAJOR);
        final String url = createDocumentUrl(document);
        return url;
    }

    private static String createDocumentUrl(final Document document) {
        return document.toString().replace("CMIS_DOCUMENT (cmis:document): workspace://",
                                           "http://" + CMIS_HOST + "/alfresco/navigate/showDocDetails/workspace/");
    }

    public static List<DocumentDto> getDocumentsForCase(final String caseInstanceId) {
      if (!enabled) {
        return new ArrayList<DocumentDto>();
      }
        try {
            final Session session = createCmisSession();
            final Folder caseFolder = getCaseFolder(session, caseInstanceId);

            final ArrayList<DocumentDto> documents = new ArrayList<DocumentDto>();

            final ItemIterable<CmisObject> children = caseFolder.getChildren();
            for (final CmisObject cmisObject : children) {
                if (cmisObject instanceof Document) {
                    final Document doc = (Document) cmisObject;
                    final String url = createDocumentUrl(doc);
                    documents.add(new DocumentDto(doc.getName(), url));
                }
            }
            return documents;
        } catch (final Exception ex) {
            // noooo - do not look at the next line ;-)
            ex.printStackTrace();
            return new ArrayList<DocumentDto>();
        }
    }

    private static Session createCmisSession() {
        final Map<String, String> parameter = new HashMap<String, String>();

        // Set the user credentials
        parameter.put(SessionParameter.USER, CMIS_USER);
        parameter.put(SessionParameter.PASSWORD, CMIS_PASSWORD);

        // Specify the connection settings

        // Public Alfresco CMIS Repo for demo purposes
        // parameter.put(SessionParameter.ATOMPUB_URL,
        // "http://cmis.alfresco.com/cmisatom");

        // Alternatively: My Local Alfresco Installation
        parameter.put(SessionParameter.ATOMPUB_URL, "http://" + CMIS_HOST + "/alfresco/service/cmis");

        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

        // Set the alfresco object factory
        parameter.put(SessionParameter.OBJECT_FACTORY_CLASS, "org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");

        // Create a session
        final SessionFactory factory = SessionFactoryImpl.newInstance();
        final Session session = factory.getRepositories(parameter).get(0).createSession();
        return session;
    }

    public static Folder getCaseFolder(final Session cmisSession, final String caseInstanceId) {
        final Folder parentFolder = (Folder) cmisSession.getObjectByPath(CMIS_FOLDER);
        Folder subFolder = null;
        try {
            subFolder = (Folder) cmisSession.getObjectByPath(parentFolder.getPath() + "/" + caseInstanceId);
            // folder exists - yeah :-)
        } catch (final CmisObjectNotFoundException onfe) {
            final Map props = new HashMap();
            props.put("cmis:objectTypeId", "cmis:folder");
            props.put("cmis:name", caseInstanceId);
            subFolder = parentFolder.createFolder(props);
            // final String subFolderId = subFolder.getId();
            // created folder on the fly
        }
        return subFolder;
    }

}
