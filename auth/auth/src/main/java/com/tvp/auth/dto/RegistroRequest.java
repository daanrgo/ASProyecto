package com.tvp.auth.dto;

import com.tvp.auth.entity.Usuario;
import lombok.Data;

@Data
public class RegistroRequest {
    private String email;
    private String password;
    private Usuario.Rol rol; // opcional, default CLIENTE
}