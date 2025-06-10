package com.maxiamikel.BirthdayNotifierAPI.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendBirthdayNotification(String receiver, String subject, String message) {
        try {
            SimpleMailMessage simpleMessage = new SimpleMailMessage();
            simpleMessage.setFrom(sender);
            simpleMessage.setTo(receiver);
            simpleMessage.setSubject(subject);
            simpleMessage.setText(message);
            javaMailSender.send(simpleMessage);
            log.info("Sending email to: " + receiver);

        } catch (Exception e) {
            log.info("Failed sending the message to: " + receiver);
            throw new RuntimeException("Failed to send email:", e);
        }
    }

}
