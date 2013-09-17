package org.camunda.bpm.demo.invoice.ui;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;

@Named
@SessionScoped
public class TaskListController implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private IdentityService identityService;

  public List<User> getUsersList() {
    return identityService.createUserQuery().list();
  }
 
}
