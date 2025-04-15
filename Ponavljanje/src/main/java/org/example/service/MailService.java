package org.example.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendMail(String to, String subject, String message){
        try {
            SimpleMailMessage message1 = new SimpleMailMessage();
            message1.setFrom("milankitanovski00@gmail.com");
            message1.setTo(to);
            message1.setSubject(subject);
            message1.setText(message);
            javaMailSender.send(message1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
