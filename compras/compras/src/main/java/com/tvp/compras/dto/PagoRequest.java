package com.tvp.compras.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequest {
    private Long compraId;
    private String producto;
    private int cantidad;
    private double total;
    private String tarjetaNumero;
    private String tarjetaTitular;
}
