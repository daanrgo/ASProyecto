package com.tvp.compras.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_PAGOS = "cola.pagos";

    @Bean
    public Queue queuePagos() {
        return new Queue(QUEUE_PAGOS, false);
    }
}
