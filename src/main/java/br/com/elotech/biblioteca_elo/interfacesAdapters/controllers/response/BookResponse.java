package br.com.elotech.biblioteca_elo.interfacesAdapters.controllers.response;

import java.time.LocalDate;

public record BookResponse(
        String title,
        String author,
        String isbn,
        LocalDate publicationDate
) {
}
