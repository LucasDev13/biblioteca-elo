package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers;

import br.com.elotech.biblioteca_elo.application.usecases.BookRecommendationUseCase;
import br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recommendations")
public record BookRecommendationController(
    BookRecommendationUseCase bookRecommendationUseCase
) {

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookResponse>> listRecommendBooks(@PathVariable UUID userId){
        List<BookResponse> listBooks = bookRecommendationUseCase.recommendBooks(userId);
        if(listBooks.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listBooks);
    }
}
