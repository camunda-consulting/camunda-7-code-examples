package com.camunda.bpm.cob;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.sun.mail.pop3.POP3Store;

public class SimpleEmailServerTest {
	public static void main(String[] args) {
		GreenMail mailserver = new GreenMail(ServerSetup.ALL);
		mailserver.setUser("myUser@localhost", "myUser", "noPassword");

		String mailPop3Host = "localhost";
		String mailStoreType = "pop3";
		String mailUser = "myUser";
		String mailPassword = "noPassword";

		receiveEmail(mailPop3Host, mailStoreType, mailUser, mailPassword);
	}

	public static void receiveEmail(String pop3Host, String storeType, String user, String password) {

		try {
			Properties properties = new Properties();
			properties.put("mail.pop3.host", pop3Host);
			Session emailSession = Session.getDefaultInstance(properties);

			POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
			emailStore.connect(user, password);

			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("==============================");
				System.out.println("Email #" + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Text: " + message.getContent().toString());
			}

			emailFolder.close(false);
			emailStore.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
