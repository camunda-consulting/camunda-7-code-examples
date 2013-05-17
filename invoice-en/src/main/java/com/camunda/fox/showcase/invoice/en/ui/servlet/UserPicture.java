package com.camunda.fox.showcase.invoice.en.ui.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.IdentityService;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.io.SVNRepository;

@WebServlet(value = "/userPicture", loadOnStartup = 1)
public class UserPicture extends HttpServlet {

  private static final long serialVersionUID = 1L;
  
  @Inject
  private IdentityService identityService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // response.setContentType("text/plain");
    response.setContentType("image/png");

    String userId = request.getParameter("user");

    // response.getWriter().write(userId);

    InputStream inputStream = identityService.getUserPicture(userId).getInputStream();

    byte[] imageBytes = IOUtils.toByteArray(inputStream);

    response.getOutputStream().write(imageBytes);

  }

  @SuppressWarnings("rawtypes")
  public static void listEntries(SVNRepository repository, String path) throws SVNException {
    Collection entries = repository.getDir(path, -1, null, (Collection) null);
    Iterator iterator = entries.iterator();
    while (iterator.hasNext()) {
      SVNDirEntry entry = (SVNDirEntry) iterator.next();
      System.out.println("/" + (path.equals("") ? "" : path + "/") + entry.getName() + " ( author: '" + entry.getAuthor() + "'; revision: "
              + entry.getRevision() + "; date: " + entry.getDate() + ")");
      if (entry.getKind() == SVNNodeKind.DIR) {
        listEntries(repository, (path.equals("")) ? entry.getName() : path + "/" + entry.getName());
      }
    }
  }
}
