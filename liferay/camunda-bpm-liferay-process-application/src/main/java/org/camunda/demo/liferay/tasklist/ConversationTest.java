package org.camunda.demo.liferay.tasklist;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ConversationScoped
public class ConversationTest implements Serializable {
 
  private static final long serialVersionUID = 1L;
//  
//  @Inject
//  private Conversation conversation;
//  @Inject 
//  private Instance<Conversation> conversationInstance;
  
  
  public void start() {
//    conversationInstance.get().begin();
  }

  public void end() {
//    conversationInstance.get().end();
  }

}

