package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookResponse(
        String title,
        String author,
        String isbn,
        LocalDate publicationDate,
        LocalDateTime registrationDate
) {
}
