package com.enviocorreo.email.services;

import com.enviocorreo.email.entity.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    private final EmailService emailService;

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "cola_mensajes")
    public void recibir(Email email) {
        System.out.println("📧 Mensaje recibido desde RabbitMQ: " + email);
        emailService.enviarEmail(email);
    }
}