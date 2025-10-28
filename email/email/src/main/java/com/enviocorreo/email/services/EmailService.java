package com.enviocorreo.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.enviocorreo.email.entity.Email;
import com.enviocorreo.email.repository.EmailRepository;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    public void enviarEmail(Email email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(email.getFrom());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText());

            javaMailSender.send(mimeMessage);


            email.setSentAt(LocalDateTime.now());
            emailRepository.save(email);

            System.out.println("✅ Correo enviado y registro guardado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}