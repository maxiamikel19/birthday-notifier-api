package com.maxiamikel.BirthdayNotifierAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Birthday Notifier API", version = "1.0", description = "Birthday notifier API documentation."))
public class BirthdayNotifierApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirthdayNotifierApiApplication.class, args);
	}

}
