package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.request;

import br.com.elotech.biblioteca_elo.infrastructure.persistence.entitiesPersistence.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record BookRequest(
        @NotEmpty(message = "Não pode estar vazio ou null!")
        String title,

        @NotEmpty(message = "Não pode estar vazio ou null!")
        String author,

        @NotEmpty(message = "Não pode estar vazio ou null!")
        String isbn,

        @NotEmpty(message = "Não pode estar vazio ou null!")
        Category category,

        @PastOrPresent(message = "A data precisa estar no passado ou no presente")
        LocalDate publicationDate) {
}
