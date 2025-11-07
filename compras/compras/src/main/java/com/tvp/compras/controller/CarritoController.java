package com.tvp.compras.controller;

import com.tvp.compras.entity.Carrito;
import com.tvp.compras.service.CarritoService;
import com.tvp.compras.service.CompraService;
import com.tvp.compras.entity.CarritoItem;
import com.tvp.compras.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CompraService compraService;

    @PostMapping
    public Carrito crear(@RequestParam Long clienteId) {
        return carritoService.crearCarrito(clienteId);
    }

    @PostMapping("/{id}/agregar")
    public Carrito agregar(@PathVariable Long id,
                           @RequestParam Long paqueteId,
                           @RequestParam String nombrePaquete,
                           @RequestParam Integer cantidad,
                           @RequestParam Double precioUnitario) {
        return carritoService.agregarItem(id, paqueteId, nombrePaquete, cantidad, precioUnitario);
    }

    @PostMapping("/{id}/pagar")
    public String pagar(@PathVariable Long id) {
        Carrito carrito = carritoService.obtenerPorId(id);
        if (carrito.getPagado()) throw new RuntimeException("Carrito ya pagado");

        
        Compra compra = new Compra();
        compra.setProducto("Paquete de turismo"); 
        compra.setCantidad(carrito.getItems().stream().mapToInt(CarritoItem::getCantidad).sum());
        compra.setPrecioUnitario(carrito.getTotal()); 
        compra.setPagado(false);

        compraService.registrarCompra(compra);

        carrito.setPagado(true);
        carritoService.guardar(carrito);

        return "Compra creada y evento de pago enviado";
    }
}