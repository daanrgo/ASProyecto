package com.tvp.pagos.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tvp.pagos.config.RabbitMQPagos;
import com.tvp.pagos.dto.PagoRequest;
import com.tvp.pagos.entity.Pago;
import com.tvp.pagos.repository.PagoRepository;

@Service
public class PagosConsumer {

    @Autowired
    private PagoRepository pagoRepository;

    @RabbitListener(queues = RabbitMQPagos.QUEUE_PAGOS)
    public void recibirPago(PagoRequest dto) {
        System.out.println("💳 Procesando pago para compra: " + dto.getCompraId());

        Pago pago = Pago.builder()
                .compraId(dto.getCompraId())
                .monto(dto.getTotal())
                .estado("APROBADO")
                .build();

        pagoRepository.save(pago);
        System.out.println("✅ Pago registrado en BD.");
    }
}