package br.com.elotech.biblioteca_elo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de Livros da elotech", version = "1.0", description = "Documentação da API de Livros."))
public class BibliotecaEloApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaEloApplication.class, args);
	}

}
