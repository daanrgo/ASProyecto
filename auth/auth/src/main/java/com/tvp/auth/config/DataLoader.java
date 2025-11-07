package com.tvp.auth.config;

import com.tvp.auth.entity.Usuario;
import com.tvp.auth.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(UsuarioRepository repo) {
        return args -> {
            if (repo.findByEmail("user@mail.com").isEmpty()) {
                repo.save(Usuario.builder()
                        .email("user@mail.com")
                        .password("123456")
                        .rol("USER")
                        .build());
            }
        };
    }
}