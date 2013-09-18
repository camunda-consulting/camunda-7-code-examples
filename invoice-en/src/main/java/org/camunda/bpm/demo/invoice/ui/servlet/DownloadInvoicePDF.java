package org.camunda.bpm.demo.invoice.ui.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.camunda.bpm.demo.invoice.ProcessConstants;
import org.camunda.bpm.engine.TaskService;


@WebServlet(value = "/downloadInvoice", loadOnStartup = 1)
public class DownloadInvoicePDF extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  @Inject
  private TaskService taskService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/pdf");

    String taskId = request.getParameter("taskId");
    byte pdf[] = (byte[]) taskService.getVariable(taskId, ProcessConstants.VARIABLE_INVOICE);

    response.getOutputStream().write(pdf);

  }
}
