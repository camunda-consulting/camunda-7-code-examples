package com.camunda.consulting.services;

import com.camunda.consulting.dto.EmailDto;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void send(EmailDto email){
        /*try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setTo(email.getTo());
            helper.setText(email.getContent(), true);
            helper.setSubject(email.getSubject());
            helper.setFrom("lucasc.alm.silva@gmail.com");
            emailSender.send(message);
            return true;
        }
        catch(MessagingException e){
            return false;
        }*/
    }
}
