package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record BookResponse(
        UUID id,
        String title,
        String author,
        String isbn,
        String category,
        LocalDate publicationDate,
        LocalDateTime registrationDate
) {
}
