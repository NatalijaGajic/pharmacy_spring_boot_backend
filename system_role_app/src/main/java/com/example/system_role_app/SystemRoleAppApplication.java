package com.example.system_role_app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SystemRoleAppApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper(); 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SystemRoleAppApplication.class, args);
	}

}
