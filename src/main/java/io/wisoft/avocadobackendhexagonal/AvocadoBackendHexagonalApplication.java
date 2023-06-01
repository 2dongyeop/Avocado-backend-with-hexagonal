package io.wisoft.avocadobackendhexagonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AvocadoBackendHexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvocadoBackendHexagonalApplication.class, args);
	}

}
