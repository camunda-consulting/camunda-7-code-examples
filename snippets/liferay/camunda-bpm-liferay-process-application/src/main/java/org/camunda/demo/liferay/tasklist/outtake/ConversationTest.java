package org.camunda.demo.liferay.tasklist.outtake;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;



@Named
//@SessionScoped
@RequestScoped
public class ConversationTest implements Serializable {
 
  private static final long serialVersionUID = 1L;
//  
//  @Inject
//  private transient Conversation conversation;
  
  private int count = 1;
  
//  @Inject 
//  private Instance<Conversation> conversationInstance;
  
  @Inject
  private transient BusinessProcess bp;
  
  
  public void start() {
//    conversation.begin();
    bp.startTask("63908d71-03f7-11e3-a04c-80c720524153");
    count++;
  }

  public void end() {
//    conversation.end();
  }

  public String getCount() {
    return count + " - Task: " + bp.getTask() + " process " + bp.getProcessInstance();
  }

}

