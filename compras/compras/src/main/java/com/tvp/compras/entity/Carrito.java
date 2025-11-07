package com.tvp.compras.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carritos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<CarritoItem> items = new ArrayList<>();

    @Builder.Default
    private Double total = 0.0;

    @Builder.Default
    private Boolean pagado = false;

    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}