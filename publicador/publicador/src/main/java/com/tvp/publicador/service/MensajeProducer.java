package com.tvp.publicador.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvp.publicador.config.RabbitMQConfig;
import com.tvp.publicador.entity.Email;

@Service
public class MensajeProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarMensaje(String mensaje) {
        System.out.println("📤 Enviando mensaje: " + mensaje);
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE,
            RabbitMQConfig.ROUTING_KEY,
            mensaje
        );
    }


    public void enviarEmail(Email email) {
        System.out.println("📤 Enviando email a RabbitMQ: " + email.getTo());
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE,
            RabbitMQConfig.ROUTING_KEY,
            email
        );
    }
}