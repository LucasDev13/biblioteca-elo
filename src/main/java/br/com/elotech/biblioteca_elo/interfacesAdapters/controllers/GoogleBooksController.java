package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers;

import br.com.elotech.biblioteca_elo.application.usecases.GoogleBooksUseCase;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record GoogleBooksController(
        GoogleBooksUseCase googleBooksUseCase
) {

}
