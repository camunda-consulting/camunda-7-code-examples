package org.camunda.bpm.example.twitter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Rejection is just done via a sysout since the fox platform does not support the Mail Task of Activiti!
 * See https://app.camunda.com/confluence/display/foxUserGuide/Activiti+5+Support for details.
 * 
 * Use your own Mail mechanisms for this or use your application server features. 
 */
public class RejectionNotificationDelegate implements JavaDelegate {
  
  public void execute(DelegateExecution execution) throws Exception {
    Tweet tweet = (Tweet) execution.getVariable("tweet");
    
    System.out.println("Hi!\n\n"
           + "Unfortunately your tweet has been rejected.\n\n"
           + "Original content: " + tweet.getContent() + "\n\n"
           + "Rejection comment: " + tweet.getRejectionComment() + "\n\n"
           + "Sorry, please try with better content the next time :-)");
  }
  
}
