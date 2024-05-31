package com.example.toko_kacamata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.example.toko_kacamata" })
@EntityScan({ "com.example.toko_kacamata.persistence", "com.example.persistence" })
@EnableJpaRepositories({ "com.example.toko_kacamata.persistence", "com.example.persistence" })
public class TokoKacamataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokoKacamataApplication.class, args);
	}

}
