package org.camunda.bpm.demo.invoice.ui.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.camunda.bpm.demo.invoice.ProcessConstants;
import org.camunda.bpm.engine.RuntimeService;


@WebServlet(value = "/startByUpload", loadOnStartup = 1)
public class StartByUpload extends HttpServlet {

  private static Logger log = Logger.getLogger(StartByUpload.class.getName());

  private static final long serialVersionUID = 1L;

  @Inject
  private RuntimeService runtimeService;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    Map<String, Object> processVariables = new HashMap<String, Object>();
    RequestMetaData metaData = extractProcessVariablesFromRequest(request, processVariables);
    
    runtimeService.startProcessInstanceByKey(metaData.processDefinitionKey, processVariables);

    log.log(Level.INFO, "redirect to " + metaData.callbackUrl);
    response.sendRedirect(metaData.callbackUrl);
  }

  private static class RequestMetaData {
    String processDefinitionKey = null;
    String callbackUrl = null;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private RequestMetaData extractProcessVariablesFromRequest(HttpServletRequest request, Map<String, Object> processVariables) {
    RequestMetaData metaData = new RequestMetaData();
    try {
      // Create a factory for disk-based file items
      FileItemFactory factory = new DiskFileItemFactory();

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);

      // Parse the request
      List items = upload.parseRequest(request);

      // Process the uploaded items
      Iterator<FileItem> iter = items.iterator();

      while (iter.hasNext()) {
        FileItem item = iter.next();

        if (item.isFormField()) {
          if (item.getFieldName().equals("processDefinitionKey")) {
            metaData.processDefinitionKey = item.getString();
          } else if (item.getFieldName().equals("callbackUrl")) {
            metaData.callbackUrl = item.getString();
          } else {
            processVariables.put(item.getFieldName(), item.getString());
          }

        } else {
          processVariables.put(ProcessConstants.VARIABLE_INVOICE, item.get());
        }
      }

    } catch (Exception ex) {
      log.log(Level.SEVERE, "Exception while extracting content from HTTP parameters", ex);
    }
    return metaData;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/plain");

    String redirectUrl = request.getRequestURL().toString().replace("startByUpload", "taskList.jsf");
    log.log(Level.INFO, "redirect to " + redirectUrl);
    response.sendRedirect(redirectUrl);
  }
}
