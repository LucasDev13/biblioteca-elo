package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers;

import br.com.elotech.biblioteca_elo.infrastructure.middleware.interfaces.GoogleBooksUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/googlebooks")
@Tag(name = "Google Books", description = "API for search books the google")
@Validated
public class GoogleBooksController{

    private final GoogleBooksUseCase googleBooksUseCase;

    @Autowired
    public GoogleBooksController(GoogleBooksUseCase googleBooksUseCase) {
        this.googleBooksUseCase = googleBooksUseCase;
    }

    @GetMapping
    public ResponseEntity<String> getBooksWithGoogleBooks(){
        List<String> titulo = googleBooksUseCase.searchBookTitle("Tutulo");
        return ResponseEntity.ok().body("Retorno do google books não implementado" + titulo);
    }
}
