package com.enviocorreo.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.enviocorreo.email.entity.Email;
import com.enviocorreo.email.services.EmailService;

@SpringBootApplication
public class EmailApplication implements CommandLineRunner {

	@Autowired
	EmailService emailService; 

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        correo();
    }

    public void correo() {
        System.out.println("Correo de Prueba");
		emailService.enviarEmail(Email.builder()
		.from("ddataramirez@gmail.com")
		.to("ever.blancom@javeriana.edu.co")
		.subject("Notificacion de Compra (Ejercicio Prueba)")
		.text("Nombre del Paquete: Aventura en Machu Picchu\n Precio Total $1900.80\n Fecha de Compra: 2024-07-15\n ¡Gracias por su compra!")
		.build()
		);

    }
}

