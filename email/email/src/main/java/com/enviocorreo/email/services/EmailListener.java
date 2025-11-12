package com.enviocorreo.email.services;

import com.enviocorreo.email.dto.NotificacionRequest;
import com.enviocorreo.email.entity.Email;
import com.enviocorreo.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "cola.notificaciones")
    public void recibirNotificacion(NotificacionRequest dto) {
        System.out.println("📬 Recibiendo notificación para: " + dto.getToEmail());

        Email email = Email.builder()
                .to(dto.getToEmail())
                .subject(dto.getSubject())
                .text(dto.getBody())
                .build();

        emailService.enviarEmail(email);
        System.out.println("✅ Correo enviado a: " + dto.getToEmail());
    }
}