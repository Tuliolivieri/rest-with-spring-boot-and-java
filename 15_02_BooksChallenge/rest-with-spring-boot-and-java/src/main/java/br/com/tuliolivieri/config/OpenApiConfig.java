package br.com.tuliolivieri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(
					new Info()
						.title("RESTful API with Java 18 and Spring Boot 3")
						.version("v1")
						.description("Some description about API...")
						.termsOfService("https://tuliolivieri.github.io")
						.license(
							new License()
								.name("Apache 2.0")
								.url("https://tuliolivieri.github.io")
						)
				);
	}
}
