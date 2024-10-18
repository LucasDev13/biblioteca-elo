package br.com.elotech.biblioteca_elo;

import org.springframework.boot.SpringApplication;

public class TestBibliotecaEloApplication {

	public static void main(String[] args) {
		SpringApplication.from(BibliotecaEloApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
