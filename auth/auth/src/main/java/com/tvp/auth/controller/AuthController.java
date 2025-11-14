package com.tvp.auth.controller;

import com.tvp.auth.dto.LoginRequest;
import com.tvp.auth.dto.LoginResponse;
import com.tvp.auth.dto.RegistroRequest;
import com.tvp.auth.entity.Usuario;
import com.tvp.auth.repository.UsuarioRepository;
import com.tvp.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthService authService, UsuarioRepository usuarioRepository) {
        this.authService = authService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public LoginResponse registrar(@RequestBody RegistroRequest dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        Usuario usuario = Usuario.builder()
                .email(dto.getEmail())
                .password(dto.getPassword()) // plain text (lab)
                .rol(dto.getRol() != null ? dto.getRol() : Usuario.Rol.CLIENTE)
                .build();

        usuarioRepository.save(usuario);

        // Devuelve token igual que login
        return authService.login(LoginRequest.builder()
                .email(usuario.getEmail())
                .password(dto.getPassword())
                .build());
    }
}