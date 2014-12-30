package com.camunda.bpm.cob.completeByMail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.sun.mail.pop3.POP3Store;

public class FetchEmailsDelegate implements JavaDelegate {
	
	private static final Logger log = Logger.getLogger(FetchEmailsDelegate.class.getName());

	String pop3Host = "localhost";
	String storeType = "pop3";
	String user = "myUser";
	String password = "noPassword";

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("Pick up the emails from the server");
		List<CompleteTaskMessage> completeTaskMessages = new ArrayList<CompleteTaskMessage>();
		
		POP3Store emailStore = null;
		Folder emailFolder = null;
		try {
			// pick up the mails from the inbox like this
			Properties properties = new Properties();
			properties.put("mail.pop3.host", pop3Host);
			Session emailSession = Session.getDefaultInstance(properties);

			emailStore = (POP3Store) emailSession.getStore(storeType);
			emailStore.connect(user, password);

			emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_WRITE);

			Message[] messages = emailFolder.getMessages();
			log.info("picked up " + messages.length + " messages from the email account"); 
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				if (message.getSubject().contains("complete task")) {
					CompleteTaskMessage completeTaskMessage = new CompleteTaskMessage();
					completeTaskMessage.setSubject(message.getSubject());
					completeTaskMessage.setBody(message.getContent().toString());
					completeTaskMessages.add(completeTaskMessage);
				}
			}
		} catch (NoSuchProviderException e) {
			log.warning("NSPException: " + e.getMessage());
			e.printStackTrace();
		} catch (MessagingException e) {
			log.warning("MessagingException: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (emailFolder != null) {
				emailFolder.close(false);
			}
			if (emailStore != null) {
				emailStore.close();
			}
		}
		execution.setVariable("numberOfMessages", completeTaskMessages.size());
		execution.setVariable("messages", completeTaskMessages);
		log.info("messages picked up for further inspection");
	}

}
