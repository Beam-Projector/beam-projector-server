package com.projet.beamprojector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@SpringBootApplication
public class BeamProjectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeamProjectorApplication.class, args);
	}

}
