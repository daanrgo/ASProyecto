package com.tvp.pagos.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQPagos {

    public static final String QUEUE_PAGOS = "cola.pagos";
    public static final String QUEUE_NOTIFICACIONES = "cola.notificaciones";

    @Bean
    public Queue queuePagos() {
        return new Queue(QUEUE_PAGOS, false);
    }

    @Bean
    public Queue queueNotificaciones() {
        return new Queue(QUEUE_NOTIFICACIONES, false);
    }
}