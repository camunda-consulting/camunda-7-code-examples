package com.camunda.bpm.cob.mailNotification;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.task.IdentityLink;

public class MailNotificationListener implements TaskListener {
	
	private static final Logger log = Logger.getLogger(MailNotificationListener.class.getName());

	String smtpHost = "localhost";
	String user = "myUser";
	String password = "noPassword";

	@Override
	public void notify(DelegateTask delegateTask) {
		Set<IdentityLink> candidates = delegateTask.getCandidates();
		// goto Active directory and look for all the members
		
		for (IdentityLink identityLink : candidates) {
			// get the member of candidates
			String groupId = identityLink.getGroupId();
			List<User> usersToNotify = delegateTask.getProcessEngineServices().getIdentityService().createUserQuery().memberOfGroup(groupId).list();
			sendNotificationEmail(usersToNotify, delegateTask);
		}
	}

	public void sendNotificationEmail(List<User> usersToNotify, DelegateTask delegateTask) {
		Email email = new SimpleEmail();
		email.setCharset("utf-8");
		email.setHostName(smtpHost);
		email.setAuthentication(user, password);
		try {
			email.setFrom("noreply@camunda.com");
			email.setSubject("Task assigned: " + delegateTask.getName());
			email.setMsg("Please complete: " + 
					delegateTask.getName() + 
					" at  http://localhost:8080/camunda/app/tasklist/default/#/task/" + 
					delegateTask.getId() + 
					"\n\nmailto:" + user+"@localhost?subject=complete task now&body=please send message now\n\n"
							+ "possible input:\n"
							+ "---------------------\n"
							+ "**taskid=" + delegateTask.getId() + "\n"
							+ "##gotoTax=yes\n"
							+ "##gotoCompliance=no");
			for (User user : usersToNotify) {
				String completeName = user.getFirstName() + " " + user.getLastName();
				String recipient = user.getEmail();
				email.addTo(recipient, completeName, "utf-8");
				log.info("added " + recipient + ", " + completeName + " to the list of recients of task " + delegateTask.getId());
			}
			email.send();
		} catch (EmailException e) {
			log.warning(e.getMessage());
		}
	}

}