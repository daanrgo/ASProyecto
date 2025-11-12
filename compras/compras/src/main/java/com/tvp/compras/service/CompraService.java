package com.tvp.compras.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tvp.compras.config.RabbitMQConfig;
import com.tvp.compras.dto.PagoRequest;
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

        // Creamos el DTO con los datos del pago
        PagoRequest pagoDto = PagoRequest.builder()
                .compraId(guardada.getId())
                .producto(guardada.getProducto())
                .cantidad(guardada.getCantidad())
                .total(guardada.getPrecioUnitario() * guardada.getCantidad())
                .tarjetaNumero("4555555555555555") // fake
                .tarjetaTitular("Juan Pérez")       // fake
                .build();

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_PAGOS, pagoDto);
        return guardada;
    }
}