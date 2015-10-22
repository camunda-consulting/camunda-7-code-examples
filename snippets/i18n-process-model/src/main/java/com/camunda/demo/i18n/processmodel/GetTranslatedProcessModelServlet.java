package com.camunda.demo.i18n.processmodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.ProcessDefinition;

@WebServlet(value = "/model/translated", loadOnStartup = 1)
public class GetTranslatedProcessModelServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String languageCode = req.getParameter("language");
    if (languageCode==null) {
      languageCode = "en";
    }
    String processDefinitionKey = req.getParameter("processDefinitionKey");
    if (processDefinitionKey==null) {
      processDefinitionKey = "i18n-process-model";
    }
    
    Properties languageProperties = new Properties();
    String languageFile =  "/" + languageCode + ".properties";
    languageProperties.load( this.getClass().getResourceAsStream(languageFile) );

    ProcessModelTranslator translator = new ProcessModelTranslator(languageProperties);
    
    ProcessDefinition processDefinition = getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).latestVersion().singleResult();
    InputStream processModelInputStream = getRepositoryService().getProcessModel(processDefinition.getId());
    String processModelXml = translator.getTranslatedProcessModel(processModelInputStream);
    
    resp.getOutputStream().write( processModelXml.getBytes("UTF-8") );
  }
  
  protected RepositoryService getRepositoryService() {
    return BpmPlatform.getDefaultProcessEngine().getRepositoryService();    
  }

}
