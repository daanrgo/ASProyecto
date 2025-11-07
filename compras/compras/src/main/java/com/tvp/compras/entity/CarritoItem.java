package com.tvp.compras.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrito_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    private Long paqueteId;

    private String nombrePaquete;

    private Integer cantidad;

    private Double precioUnitario;

    public Double getSubtotal() {
        return cantidad * precioUnitario;
    }
}