package com.camunda.demo.underwriting.jsf;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.User;

@SessionScoped
@Named
public class UserController implements Serializable {

  private static final long serialVersionUID = 1L;

  private String userId = "demo";
  private User user;
  private String password;

  @Inject
  private ProcessEngine processEngine;

  public boolean isLoggedIn() {
    return userId != null;
  }

  public String login() {
    user = processEngine.getIdentityService().createUserQuery().userId(userId).singleResult();
    if (user == null) {
      return null;
    }
    return "case-instances";
  }

  public String logout() {
    final Object session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    if (session instanceof HttpSession) {
      ((HttpSession) session).invalidate();
    }
    userId = null;
    return "index";
  }

  /**
   * @return the ivUserId
   */
  public String getUserId() {
    return userId;
  }

  /**
   * @param userId
   *          the ivUserId to set
   */
  public void setUserId(final String userId) {
    this.userId = userId;
  }

  /**
   * @return the ivUser
   */
  public User getUser() {
    return user;
  }

  /**
   * @param User
   *          the ivUser to set
   */
  public void setUser(final User user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
