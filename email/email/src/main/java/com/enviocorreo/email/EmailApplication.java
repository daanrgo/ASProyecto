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
		.to("ramirezg.david@javeriana.edu.co")
		.subject("Correo de prueba")
		.text("Compra realizada con exito")
		.build()
		);

    }
}
