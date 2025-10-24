package com.tvp.publicador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tvp.publicador.service.MensajeProducer;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeProducer mensajeProducer;

    @PostMapping("/enviar")
    public String enviarMensaje(@RequestBody String mensaje) {
        mensajeProducer.enviarMensaje(mensaje);
        return "Mensaje enviado: " + mensaje;
    }

    // Opción adicional para probar fácilmente desde el navegador:
    @GetMapping("/enviar/{mensaje}")
    public String enviarMensajePorGet(@PathVariable String mensaje) {
        mensajeProducer.enviarMensaje(mensaje);
        return "Mensaje enviado (GET): " + mensaje;
    }
}
