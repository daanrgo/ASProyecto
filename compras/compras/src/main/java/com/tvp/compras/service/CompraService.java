package com.tvp.compras.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tvp.compras.config.RabbitMQConfig;
import com.tvp.compras.entity.Compra;
import com.tvp.compras.repository.CompraRepository;

@Service
public class CompraService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CompraRepository compraRepository;

    public Compra registrarCompra(Compra compra) {
        compra.setPagado(false);
        Compra guardada = compraRepository.save(compra);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_PAGOS, guardada);
        return guardada;
    }
}
