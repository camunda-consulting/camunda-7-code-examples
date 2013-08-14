package org.camunda.demo.liferay.tasklist;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class ConversationTest implements Serializable {
 
  private static final long serialVersionUID = 1L;
//  
  @Inject
  private Conversation conversation;
//  @Inject 
//  private Instance<Conversation> conversationInstance;
  
  
  public void start() {
    conversation.begin();
  }

  public void end() {
    conversation.end();
  }

}

