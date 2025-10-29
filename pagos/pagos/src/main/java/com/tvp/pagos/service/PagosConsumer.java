package com.tvp.pagos.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tvp.pagos.config.RabbitMQConfig;
import com.tvp.pagos.entity.Pago;
import com.tvp.pagos.repository.PagoRepository;
import com.tvp.pagos.entity.Compra; 

@Service
public class PagoConsumer {

    @Autowired
    private PagoRepository pagoRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PAGOS)
    public void recibirCompra(Compra compra) {
        System.out.println("💳 Procesando pago para compra: " + compra.getId());
        Pago pago = new Pago(null, compra.getId(), compra.getPrecioUnitario() * compra.getCantidad(), "APROBADO");
        pagoRepository.save(pago);
        System.out.println("✅ Pago registrado en BD.");
    }
}
