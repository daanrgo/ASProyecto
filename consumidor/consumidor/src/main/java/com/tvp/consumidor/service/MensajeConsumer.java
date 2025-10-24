package com.tvp.consumidor.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.tvp.consumidor.config.RabbitMQConfig;

@Service
public class MensajeConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void recibirMensaje(String mensaje) {
        System.out.println("📥 Mensaje recibido: " + mensaje);
    }
}
