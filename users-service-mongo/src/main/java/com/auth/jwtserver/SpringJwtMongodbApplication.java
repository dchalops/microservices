package com.auth.jwtserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableMongoRepositories
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
	info = @Info(title = "Proyecto Microservicios Mongo UISRAEL", version="1.0"), 
	servers = { @Server(description = "Local Env")
})
@SecurityScheme(
	name = "bearerAuthToken",
	description = "JWT Authentication Token",
	scheme = "bearer",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	in = SecuritySchemeIn.HEADER
)
public class SpringJwtMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtMongodbApplication.class, args);
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
