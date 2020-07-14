package com.camunda.consulting.listeners;

import com.camunda.consulting.dto.EmailDto;
import com.camunda.consulting.services.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailListener implements ExecutionListener {

    private final EmailService emailService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        EmailDto emailDto = new EmailDto();
        emailDto.setContent((String) execution.getVariable("content"));
        emailDto.setTo((String) execution.getVariable("to"));
        emailDto.setSubject((String) execution.getVariable("subject"));
        logger.info("Sending email {} ", emailDto);

        emailService.send(emailDto);
    }
}
