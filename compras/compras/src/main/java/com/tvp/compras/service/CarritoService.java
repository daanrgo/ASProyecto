package com.tvp.compras.service;

import com.tvp.compras.entity.Carrito;
import com.tvp.compras.entity.CarritoItem;
import com.tvp.compras.repository.CarritoRepository;
import com.tvp.compras.repository.CarritoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoItemRepository itemRepository;

    public Carrito crearCarrito(Long clienteId) {
        Carrito carrito = new Carrito();
        carrito.setClienteId(clienteId);
        carrito.setTotal(0.0);
        carrito.setPagado(false);
        return carritoRepository.save(carrito);
    }

    public Carrito agregarItem(Long carritoId, Long paqueteId, String nombrePaquete, Integer cantidad, Double precioUnitario) {
        Carrito carrito = carritoRepository.findById(carritoId).orElseThrow();
        if (carrito.getPagado()) throw new RuntimeException("Carrito ya pagado");

        CarritoItem item = new CarritoItem();
        item.setCarrito(carrito);
        item.setPaqueteId(paqueteId);
        item.setNombrePaquete(nombrePaquete);
        item.setCantidad(cantidad);
        item.setPrecioUnitario(precioUnitario);
        itemRepository.save(item);

        carrito.getItems().add(item);
        carrito.setTotal(carrito.getItems().stream().mapToDouble(CarritoItem::getSubtotal).sum());
        return carritoRepository.save(carrito);
    }

    // ✅ Métodos que faltaban
    public Carrito obtenerPorId(Long id) {
        return carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    public Carrito guardar(Carrito carrito) {
        return carritoRepository.save(carrito);
    }
}