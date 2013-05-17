package com.camunda.fox.showcase.invoice.en.ui.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;


@WebServlet(value = "/processDiagram", loadOnStartup = 1)
public class ProcessDiagramServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Inject
  private RepositoryService repositoryService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
    String processDefinitionId = request.getParameter("processDefinitionId");    
    if (processDefinitionId==null) {
    	String processDefinitionKey = request.getParameter("processDefinitionKey");
    	
    	ProcessDefinition pdef = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).latestVersion().singleResult();
    	
    	processDefinitionId = pdef.getId();
    }
    
    InputStream processDiagram = repositoryService.getProcessDiagram(processDefinitionId);

    response.setContentType("image/png");
    response.getOutputStream().write(IOUtils.toByteArray(processDiagram));
  }
}