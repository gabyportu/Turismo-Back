package com.example.ProyectoFinal;

import com.example.ProyectoFinal.config.JwtProperties;
import com.example.ProyectoFinal.config.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class, MinioProperties.class})
public class ProyectoFinalApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProyectoFinalApplication.class, args);
	}

}
