package com.camunda.demo.versicherungsneuantrag.adapter;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.demo.versicherungsneuantrag.ProcessVariables;
import com.camunda.demo.versicherungsneuantrag.model.Neuantrag;

public class EmailAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String mailtext = (String) execution.getVariable("mailBody");
    String subject = (String) execution.getVariable("mailSubject");
    Neuantrag antrag = (Neuantrag) execution.getVariable(ProcessVariables.VAR_NAME_neuantrag);

    sendEmail(antrag, mailtext, subject);
  }

  public void sendEmail(Neuantrag antrag, String mailtext, String subject) throws EmailException {
    Email email = new SimpleEmail();
    email.setHostName("mail.camunda.com");
    email.setAuthentication("demo@mx.camunda.com", "28484234386345");
    email.setFrom("demo@camunda.com");
    email.setCharset("utf-8");
    email.setSubject(subject);
    email.setMsg(mailtext);
    email.addTo(antrag.getAntragssteller().getEmail());
    email.send();
  }
}
