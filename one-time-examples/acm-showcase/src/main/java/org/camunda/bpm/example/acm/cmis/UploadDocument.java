package org.camunda.bpm.example.acm.cmis;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.cdi.annotation.TaskId;
import org.camunda.bpm.example.acm.controller.CaseController;

@WebServlet(value = "/uploadDocument", loadOnStartup = 1)
public class UploadDocument extends HttpServlet {

    private static Logger log = Logger.getLogger(UploadDocument.class.getName());

    private static final long serialVersionUID = 1L;

    @Inject
    @Named
    private CaseController caseController;

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        final String callback = request.getHeader("referer");
        RequestMetaData metaData = storeDocumentInCmis(request);
        caseController.resetDocuments();
        log.info("Callback: " + callback);
        if (metaData.taskId!=null) {
          response.sendRedirect(callback + "?taskId=" + metaData.taskId);
        }
        else {
          response.sendRedirect(callback);
        }
    }

    private static class RequestMetaData {
        String caseInstanceId = null;
        String callbackUrl = null;
        String fileName = null;
        String taskId = null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private RequestMetaData storeDocumentInCmis(final HttpServletRequest request) {
        final RequestMetaData metaData = new RequestMetaData();
        try {
            // Create a factory for disk-based file items
            final FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            final ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            final List items = upload.parseRequest(request);

            // Process the uploaded items
            final Iterator<FileItem> iter = items.iterator();

            byte[] fileContent = null;

            while (iter.hasNext()) {
                final FileItem item = iter.next();

                if (item.isFormField()) {
                    if (item.getFieldName().equals("caseInstanceId")) {
                        metaData.caseInstanceId = item.getString();
                    }
                    if (item.getFieldName().equals("taskId")) {
                      metaData.taskId = item.getString();
                    }                    
                    if (item.getFieldName().equals("callbackUrl")) {
                      metaData.callbackUrl = item.getString();
                    }                    
                } else {
                    metaData.fileName = item.getName();
                    fileContent = item.get();
                }
            }
            final String url = CmisClient.storeFile(fileContent, metaData.caseInstanceId, metaData.fileName);

        } catch (final Exception ex) {
            log.log(Level.SEVERE, "Exception while extracting content from HTTP parameters", ex);
        }
        return metaData;
    }

}
