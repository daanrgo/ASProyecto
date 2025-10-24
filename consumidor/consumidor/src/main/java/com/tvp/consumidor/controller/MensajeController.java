package com.tvp.consumidor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MensajeController {

    @GetMapping("/")
    public String estado() {
        return "🟢 Consumidor activo y escuchando mensajes...";
    }
}
