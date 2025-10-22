package com.enviocorreo.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.enviocorreo.email.entity.Email;
import jakarta.mail.internet.MimeMessage;

@Service

public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;
    
    public void enviarEmail (Email email)

    {

    try {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessage.setFrom(email.getFrom());

        mimeMessageHelper.setTo(email.getTo());

        mimeMessageHelper.setSubject(email.getSubject());

        mimeMessageHelper.setText(email.getText());

        javaMailSender.send(mimeMessage);

    }catch (Exception e){
        e.printStackTrace();
    }
} }
    