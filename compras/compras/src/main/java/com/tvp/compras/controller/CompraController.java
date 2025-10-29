package com.tvp.compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tvp.compras.model.Compra;
import com.tvp.compras.service.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping("/agregar")
    public Compra agregarCompra(@RequestBody Compra compra) {
        return compraService.registrarCompra(compra);
    }
}
