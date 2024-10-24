package com.example.demo1;

import com.example.demo1.Model.Usuario;
import com.example.demo1.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Crear el usuario Secre
		Usuario secre = new Usuario();
		secre.setUsername("Secre");
		secre.setPassword("321");

		// Crear el usuario Doctor
		Usuario doctor = new Usuario();
		doctor.setUsername("doctor");
		doctor.setPassword("321");

		// Guardar ambos usuarios en la base de datos
		usuarioRepository.save(secre);
		usuarioRepository.save(doctor);

		System.out.println("Usuarios 'Secre' y 'doctor' creados.");
	}
}
