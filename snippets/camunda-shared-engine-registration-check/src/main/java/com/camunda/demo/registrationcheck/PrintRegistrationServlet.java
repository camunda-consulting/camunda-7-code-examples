package com.camunda.demo.registrationcheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.application.ProcessApplicationManager;

@WebServlet("/registrations")
public class PrintRegistrationServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();

    writer.write("<html><body>");
    
    ProcessApplicationManager processApplicationManager = ((ProcessEngineImpl)BpmPlatform.getDefaultProcessEngine()).getProcessEngineConfiguration().getProcessApplicationManager();
    String registrationSummary = processApplicationManager.getRegistrationSummary();
    registrationSummary = registrationSummary.replaceAll(",", "<br>");
    
    writer.write(registrationSummary);
    
    writer.write("</body></html>");
  }

}
