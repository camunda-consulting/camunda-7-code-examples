package com.camunda.demo.embedded_engine_without_spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camunda.demo.embedded_engine_without_spring.businesslogic.GuestService;
import com.camunda.demo.embedded_engine_without_spring.businesslogic.persistence.GuestEntry;

@Named
public class CreateGuestbookEntryDelegate implements JavaDelegate {
  
  @Inject 
  GuestService guestService;
  
  private static final Logger logger = LoggerFactory.getLogger(CreateGuestbookEntryDelegate.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String content = (String) execution.getVariable("content");
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    GuestEntry entry;
    if (content != null && content.length() > 0) {
      entry = new GuestEntry(content + " from given variable");
    } else {
      entry = new GuestEntry("created from delegate At " + dateFormatter.format(new Date()));
    }
    guestService.save(entry);
    logger.info("Guestbook entry created");
  }

}
