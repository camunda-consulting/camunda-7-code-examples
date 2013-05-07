package com.camunda.fox.showcase.invoice.en.servlet;

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
import org.camunda.bpm.engine.RuntimeService;

import com.camunda.fox.showcase.invoice.en.ProcessConstants;

@WebServlet(value = "/startByUpload", loadOnStartup = 1)
public class StartByUpload extends HttpServlet {
  
  private static Logger log = Logger.getLogger(StartByUpload.class.getName());

  private static final long serialVersionUID = 1L;

  @Inject
  private RuntimeService runtimeService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/plain");

    String redirectUrl = request.getRequestURL().toString().replace("startByUpload", "taskList.jsf");
    log.log(Level.INFO, "redirect to " + redirectUrl);
    response.sendRedirect(redirectUrl);    
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Map<String, Object> processVariables = new HashMap<String, Object>();
    String processDefinitionKey = null;
    String callbackUrl = null;

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
          String name = item.getFieldName();

          if (item.getFieldName().equals("processDefinitionKey")) {
        	  processDefinitionKey = item.getString();
          }
          else if (item.getFieldName().equals("callbackUrl")) {
                  callbackUrl = item.getString();
          }
          else {
            processVariables.put(item.getFieldName(), item.getString());
          }

        } else {
          processVariables.put(ProcessConstants.VARIABLE_INVOICE, item.get());
        }
      }

       runtimeService.startProcessInstanceByKey(processDefinitionKey, processVariables);
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Exception while extracting content from HTTP parameters", ex);
    }

    String redirectUrl = callbackUrl;
    log.log(Level.INFO, "redirect to " + redirectUrl);
    response.sendRedirect(redirectUrl);
  }

}
