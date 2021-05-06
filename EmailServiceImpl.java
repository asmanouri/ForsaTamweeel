package com.ii.app.services;

import com.ii.app.services.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    public final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    @Override
    public void sendAccountMail(String receiver, String identifier) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiver);
        message.setSubject("New Account Created !");
        message.setText("Welcome to ForsaTamweel " + identifier + ", We hope that you pass a nice time with us");
        System.out.println(message.toString());
        try {
            emailSender.send(message);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    @Async
    @Override
    public void sendTransactionMail(String receiver, String identifier) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiver);
        message.setSubject("Transaction Completed Successfully !");
        message.setText("Transaction successfully compmleted " + identifier + ", We hope that you pass a nice time with us");
        System.out.println(message.toString());
        try {
            emailSender.send(message);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
